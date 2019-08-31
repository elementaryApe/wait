package com.rongdong.controller.base;

import com.rongdong.constant.CommonConstants;
import com.rongdong.exception.SignatureException;
import com.rongdong.model.priDataSource.ChannelInfo;
import com.rongdong.model.priDataSource.ChannelRole;
import com.rongdong.service.ChannelRoleService;
import com.rongdong.service.ChannelService;
import com.rongdong.utils.CookieUtils;
import com.rongdong.utils.DateUtil;
import com.rongdong.vo.ChannelInfoVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @author hsh
 * @create 2018-04-01 19:13
 **/
@RestController
public class BaseController {

    @Autowired
    private ChannelService channelService;

    @Autowired
    private ChannelRoleService channelRoleService;

//    private static final long EXPIRATIONTIME = 432_000_000;     // 5天
    private static final long EXPIRATIONTIME = 1800000;     // 30分钟

    private static final String SECRET = "H@shZ@mq";            // JWT密码
    private static final String TOKEN_PREFIX = "Bearer";        // Token前缀
    public static final String HEADER_STRING = "authorization";// 存放Token的Header Key
    private static final String HEADER_AND_PWD = "_&&";// 存放Token的Header Key

    // JWT生成方法
    public static String addAuthentication(HttpServletResponse response, String username, String password, String role) {
        // 生成JWT
        String JWT = Jwts.builder()
                // 保存权限（角色）
                .claim("aud", role)
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
            return JWT;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // JWT验证方法
    public ChannelInfoVo getAuthentication(String token, Boolean isAdmin) throws Exception {
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
                throw new SignatureException("签名失效");
            String[] split = user.split(HEADER_AND_PWD);
            if (split.length < 2 || StringUtils.isBlank(split[0]) || StringUtils.isBlank(split[1]))
                throw new SignatureException("签名失效");
            ChannelInfo channelInfo = channelService.getChannelByName(split[0]);
            if (!StringUtils.equals(split[1], channelInfo.getChannelPwd()))
                throw new SignatureException("签名失效,密码错误");
            Date expiration = claims.getExpiration();
            if (DateUtil.compareDate(expiration, new Date()) < 0)
                throw new SignatureException("签名已过期，请重新登录");
            //验证权限是否有效
            String audience = claims.getAudience();
            if(StringUtils.isBlank(audience))
                throw new SignatureException("签名失效,密码错误");
            ChannelRole channelRole = new ChannelRole();
            channelRole.setRole(audience);
            channelRole.setChannelId(channelInfo.getId());
            List<ChannelRole> channelRoleList = channelRoleService.getChannelRoleList(channelRole);
            if (CollectionUtils.isEmpty(channelRoleList))
                throw new SignatureException("权限无效，请联系管理员");
            ChannelInfoVo channelInfoVo = new ChannelInfoVo();
            BeanUtils.copyProperties(channelInfo, channelInfoVo);
            channelInfoVo.setRole(channelRoleList.get(0));
            //admin 权限校验
            if (!StringUtils.equals(audience, CommonConstants.ROLE_CHANNEL_ADMIN) && isAdmin)
                throw new SignatureException("您暂时无权访问");
            return channelInfoVo;
        }
        return null;
    }
}
