package com.rongdong.utils;

import com.rongdong.model.priDataSource.ChannelInfo;
import com.rongdong.model.priDataSource.ChannelRole;
import com.rongdong.service.ChannelRoleService;
import com.rongdong.service.ChannelService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * JWT工具类
 * 负责 JWT生成和验签
 *
 * @author hsh
 * @create 2018-03-31 22:53
 **/
public class TokenAuthenticationUtil {



    private static final long EXPIRATIONTIME = 432_000_000;     // 5天
    private static final String SECRET = "H@shZ@mq";            // JWT密码
    private static final String TOKEN_PREFIX = "Bearer";        // Token前缀
    public static final String HEADER_STRING = "authorization";// 存放Token的Header Key
    private static final String HEADER_AND_PWD = "_&&";// 存放Token的Header Key

    // JWT生成方法
    public static void addAuthentication(HttpServletResponse response, String username, String password, String role) {
        // 生成JWT
        String JWT = Jwts.builder()
                // 保存权限（角色）
                .claim("authorities", role)
                // 用户名 密码写入标题
                .setSubject(username + HEADER_AND_PWD + DigestUtils.md5Hex(password))
                // 有效期设置
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                // 签名设置
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        // 将 JWT 写入 body
        try {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.addHeader(HEADER_STRING, "Bearer " + JWT);
            CookieUtils.addCookie(HEADER_STRING, JWT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // JWT验证方法
    public void getAuthentication(String token) throws Exception {
        // 从Header中拿到token
        if (token != null) {
            // 解析 Token
            Claims claims = Jwts.parser()
                    // 验签
                    .setSigningKey(SECRET)
                    // 去掉 Bearer
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();

            // 验证用户名用户名
            String user = claims.getSubject();
            if (StringUtils.isBlank(user))
                throw new Exception("签名失效");
            String[] split = user.split(HEADER_AND_PWD);
            if (split.length<2||StringUtils.isBlank(split[0]) || StringUtils.isBlank(split[1]))
                throw new Exception("签名失效");
//            ChannelInfo channelInfo = channelService.getChannelByName(split[0]);
//            if (!StringUtils.equals(split[1], channelInfo.getChannelPwd()))
//                throw new Exception("签名失效,密码错误");
//            Date expiration = claims.getExpiration();
//            if (DateUtil.compareDate(expiration, new Date()) < 0)
//                throw new Exception("签名已过期，请重新登录");
//            String audience = claims.getAudience();
//            ChannelRole channelRole = new ChannelRole();
//            channelRole.setRole(audience);
//            channelRole.setChannelId(channelInfo.getId());
//            if (CollectionUtils.isEmpty(channelRoleService.getChannelRoleList(channelRole)))
//                throw new Exception("权限无效，请联系管理员");
        }

    }

    public static void main(String[] args) {
      String s="eyJhbGciOiJIUzUxMiJ9.eyJhdWQiOiJhZG1pbiIsInN1YiI6Inl5c18mJmUxMGFkYzM5NDliYTU5YWJiZTU2ZTA1N2YyMGY4ODNlIiwiZXhwIjoxNTIyNzQzNDE0fQ.NJ1bLU6jl9vWsMzlWzF8Igt84tM6637eczf9b3dkWmZSrhCOO69gcNgRJHcRENzqygq8sY4XWer-rU-EsqojFg";
     s.replace(TOKEN_PREFIX, "");

        System.out.println(123);
    }
//    // JWT验证方法
//    public static Authentication getAuthentication(HttpServletRequest request) {
//        // 从Header中拿到token
//        String token = request.getHeader(HEADER_STRING);
//
//        if (token != null) {
//            // 解析 Token
//            Claims claims = Jwts.parser()
//                    // 验签
//                    .setSigningKey(SECRET)
//                    // 去掉 Bearer
//                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
//                    .getBody();
//
//            // 拿用户名
//            String user = claims.getSubject();
//
//        }
//        return null;
//    }

}
