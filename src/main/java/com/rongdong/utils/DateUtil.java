package com.rongdong.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by hansh on 2017/8/24.
 */
public class DateUtil {
    /**
     * 查询天数
     */
    private int queryDays;
    private int num;

    public DateUtil(int days) {
        this.queryDays = days;
    }

    public DateUtil() {
    }

    /**
     * 根据日期生成指定的天数(已废弃)
     *
     * @return
     */
    @Deprecated
    public ArrayList<String> getDays() {
        Calendar cal = Calendar.getInstance();//使用日历类
        int year = cal.get(Calendar.YEAR);//得到年
        int month = cal.get(Calendar.MONTH) + 1;//得到月，因为从0开始的，所以要加1
        int day = cal.get(Calendar.DAY_OF_MONTH);//得到天
        int hour = cal.get(Calendar.HOUR);//得到小时
        int minute = cal.get(Calendar.MINUTE);//得到分钟
        int second = cal.get(Calendar.SECOND);//得到秒
        System.out.println("结果：" + year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second);

        ArrayList<String> list = new ArrayList<>();

        int sum = queryDays - day;
        //正常查 当期日期到 当月1日的数据  如果当月是 9月5日

        ///输出 4 - 1号的数据
        for (int abc = day - 1; abc > 0; abc--) {
            if (month < 10) {

                if (abc < 10) {
                    String sql = year + "0" + month + "0" + abc + "";
                    list.add(sql);

                    num = num + 1;
                } else {
                    String sql = year + "0" + month + abc + "";
                    list.add(sql);
                    num = num + 1;
                }
            } else {
                if (abc < 10) {
                    String sql = year + month + "0" + abc + "";
                    list.add(sql);
                    num = num + 1;
                } else {
                    String sql = year + month + abc + "";
                    list.add(sql);
                    num = num + 1;
                }
            }
        }

        if (month == 11) {
            int tianshu = sum - 31;//减去当月时间
            if (tianshu > 0) {

                for (int abc = 31; abc > 0; abc--) {
                    if (abc < 10) {
                        String sql = year + (month - 1) + "0" + abc + "";

                        list.add(sql);
                        num = num + 1;
                    } else {
                        String sql = year + (month - 1) + abc + "";
                        list.add(sql);
                        num = num + 1;
                    }
                }

                for (int abc = 30; abc >= 30 - tianshu; abc--) {
                    if (abc < 10) {
                        String sql = year + "0" + (month - 2) + "0" + abc + "";
                        list.add(sql);
                        num = num + 1;
                    } else {
                        String sql = year + "0" + (month - 2) + abc + "";
                        list.add(sql);
                        num = num + 1;
                    }
                }
            }

            //// 45减去当前日期剩余的时间小于 1个月

            else {
                for (int abc = 31; abc > 31 - sum; abc--) {
                    if (abc < 10) {
                        String sql = year + (month - 1) + "0" + abc + "";
                        list.add(sql);
                        num = num + 1;
                    } else {
                        String sql = year + (month - 1) + abc + "";
                        list.add(sql);
                        num = num + 1;
                    }

                }
            }
        }

        //如果是小月                                如果选项的查询日期比较小      45 减去 查询日期 剩余时间大于1个月 的天数
        if (month == 2 || month == 4 || month == 6 || month == 9) {


            //如果当月9月份  上个月8月有31天   45 - 5 =  40  7月份 从 31号开始输出数据

            if (month == 9) {//上个月
                int aiaiai = sum - 31;//减去当月的时间
                if (aiaiai > 0) {
                    //输出8月数据
                    for (int abc = 31; abc > 0; abc--) {
                        if (abc < 10) {
                            String sql = year + "0" + (month - 1) + "0" + abc + "";
                            list.add(sql);
                            num = num + 1;
                        } else {
                            String sql = year + "0" + (month - 1) + abc + "";
                            list.add(sql);
                            num = num + 1;
                        }

                    }
                    //输出7月份数据
                    for (int abc = 31; abc >= 31 - aiaiai; abc--) {
                        if (abc < 10) {
                            String sql = year + "0" + (month - 2) + "0" + abc + "";
                            list.add(sql);
                            num = num + 1;
                        } else {
                            String sql = year + "0" + (month - 2) + abc + "";
                            list.add(sql);
                            num = num + 1;
                        }
                    }
                }
                //// 45减去当前日期剩余的时间小于 1个月
                else {
                    for (int abc = 31; abc >= 31 - sum; abc--) {
                        if (abc < 10) {
                            String sql = year + "0" + (month - 1) + "0" + abc + "";
                            list.add(sql);
                            num = num + 1;
                        } else {
                            String sql = year + "0" + (month - 1) + abc + "";
                            list.add(sql);
                            num = num + 1;
                        }
                    }
                }
            } else if (month != 4 && month != 2 && month != 9) {//如果不是8月
                int tianshu = sum - 31;//减去当月时间
                if (tianshu > 0) {

                    for (int abc = 31; abc > 0; abc--) {
                        if (abc < 10) {
                            String sql = year + (month - 1) + "0" + abc + "";
                            list.add(sql);
                            num = num + 1;
                        } else {
                            String sql = year + (month - 1) + abc + "";
                            list.add(sql);
                            num = num + 1;
                        }
                    }

                    for (int abc = 30; abc >= 30 - tianshu; abc--) {
                        if (abc < 10) {
                            String sql = year + "0" + (month - 2) + "0" + abc + "";
                            list.add(sql);
                            num = num + 1;
                        } else {
                            String sql = year + "0" + (month - 2) + abc + "";
                            list.add(sql);
                            num = num + 1;
                        }
                    }
                }

                //// 45减去当前日期剩余的时间小于 1个月

                else {
                    for (int abc = 31; abc > 31 - sum; abc--) {
                        if (abc < 10) {
                            String sql = year + (month - 1) + "0" + abc + "";
                            list.add(sql);
                            num = num + 1;
                        } else {
                            String sql = year + (month - 1) + abc + "";
                            list.add(sql);
                            num = num + 1;
                        }

                    }


                }
            } else if (month == 4) { // 如果4月
                int tianshu = sum - 31;// 剩余天数
                if (tianshu > 0) {
                    //输出3月数据
                    for (int abc = 31; abc > 0; abc--) {
                        if (abc < 10) {
                            String sql = year + (month - 1) + "0" + abc + "";
                            list.add(sql);
                            num = num + 1;
                        } else {
                            String sql = year + (month - 1) + abc + "";
                            list.add(sql);
                            num = num + 1;
                        }

                    }
                    //输入2月数据
                    if ((year % 4 == 0 && year % 1 != 0) || (year % 400 == 0))  //输出2月份数据   // 闰年
                    {
                        for (int abc = 29; abc >= 29 - tianshu; abc--) {
                            if (abc < 10) {
                                String sql = year + (month - 2) + "0" + abc + "";
                                list.add(sql);
                                num = num + 1;
                            } else {
                                String sql = year + (month - 2) + abc + "";
                                list.add(sql);
                                num = num + 1;
                            }
                        }
                    } else {

                        //输出2月份数据   // 平年
                        for (int abc = 28; abc > 28 - tianshu; abc--) {
                            if (abc < 10) {
                                String sql = year + (month - 2) + "0" + abc + "";
                                list.add(sql);
                                num = num + 1;
                            } else {
                                String sql = year + (month - 2) + abc + "";
                                list.add(sql);
                                num = num + 1;
                            }
                        }
                    }


                }
                //// 45减去当前日期剩余的时间小于 1个月
                else {
                    for (int abc = 31; abc > 31 - sum; abc--) {
                        if (abc < 10) {
                            String sql = year + (month - 1) + "0" + abc + "";
                            list.add(sql);
                            num = num + 1;
                        } else {
                            String sql = year + (month - 1) + abc + "";
                            list.add(sql);
                            num = num + 1;
                        }

                    }


                }
            } else if (month == 2) {
                //输出2月份数据   // 闰年
                if ((year % 4 == 0 && year % 1 != 0) || (year % 400 == 0)) {
                    int tianshu = sum - 31;// 剩余天数
                    if (tianshu > 0) {


                        for (int abc = 31; abc > 0; abc--) {
                            if (abc < 10) {
                                String sql = year + "0" + (month - 1) + "0" + abc + "";
                                list.add(sql);
                                num = num + 1;
                            } else {
                                String sql = year + "0" + (month - 1) + abc + "";
                                list.add(sql);
                                num = num + 1;
                            }
                        }
                        for (int abc = 31; abc >= 31 - tianshu; abc--) {
                            if (abc < 10) {

                                String sql = (year - 1) + (month + 10) + "0" + abc + "";
                                list.add(sql);
                                num = num + 1;
                            } else {

                                String sql = (year - 1) + (month + 10) + abc + "";
                                list.add(sql);
                                num = num + 1;
                            }
                        }
                    } else {
                        for (int abc = 31; abc >= 31 - sum; abc--) {
                            if (abc < 10) {
                                String sql = year + "0" + (month - 1) + "0" + abc + "";
                                list.add(sql);
                                num = num + 1;
                            } else {
                                String sql = year + "0" + (month - 1) + abc + "";
                                list.add(sql);
                                num = num + 1;
                            }

                        }
                    }
                }
                //输出2月份数据   // 平年年
                else {
                    int tianshu = sum - 31;// 剩余天数
                    //输出2月份数据   // 平年
                    if (tianshu > 0) {
                        for (int abc = 31; abc > 0; abc--) {
                            if (abc < 10) {
                                String sql = year + (month - 1) + "0" + abc + "";
                                list.add(sql);
                                num = num + 1;
                            } else {
                                String sql = year + (month - 1) + abc + "";
                                list.add(sql);
                                num = num + 1;
                            }
                        }
                        for (int abc = 31; abc >= 31 - tianshu; abc--) {
                            if (abc < 10) {
                                String sql = year + (month - 2) + "0" + abc + "";
                                list.add(sql);
                                num = num + 1;
                            } else {
                                String sql = year + (month - 2) + abc + "";
                                list.add(sql);
                                num = num + 1;
                            }
                        }
                    } else {
                        for (int abc = 31; abc > 31 - sum; abc--) {
                            if (abc < 10) {
                                String sql = year + (month - 1) + "0" + abc + "";
                                list.add(sql);
                                num = num + 1;
                            } else {
                                String sql = year + (month - 1) + abc + "";
                                list.add(sql);
                                num = num + 1;
                            }

                        }
                    }
                }
            }
        }
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            //如果当月9月份  上个月8月有31天   45 - 5 =  40  7月份 从 31号开始输出数据

            if (month == 12) {

                int tianshu = sum - 31;//减去当月的时间
                if (tianshu > 0) {
                    //输出7月数据

                    for (int abc = 30; abc > 0; abc--) {
                        if (abc < 10) {
                            String sql = year + (month - 1) + "0" + abc + "";
                            list.add(sql);
                            num = num + 1;
                        } else {
                            String sql = year + (month - 1) + abc + "";
                            list.add(sql);
                            num = num + 1;
                        }
                    }
                    //输出6月份数据
                    for (int abc = 31; abc > 30 - tianshu; abc--) {
                        if (abc < 10) {
                            String sql = year + (month - 1) + "0" + abc + "";
                            list.add(sql);
                            num = num + 1;
                        } else {
                            String sql = year + (month - 1) + abc + "";
                            list.add(sql);
                            num = num + 1;
                        }
                    }
                } else {
                    for (int abc = 30; abc > 0; abc--) {
                        if (abc < 10) {
                            String sql = year + (month - 1) + "0" + abc + "";
                            list.add(sql);
                            num = num + 1;
                        } else {
                            String sql = year + (month - 1) + abc + "";
                            list.add(sql);
                            num = num + 1;
                        }
                    }
                }
            }
            if (month == 10) {
                int tianshu = sum - 31;//减去当月的时间
                if (tianshu > 0) {
                    //输出7月数据

                    for (int abc = 31; abc > 0; abc--) {
                        if (abc < 10) {
                            String sql = year + "0" + (month - 1) + "0" + abc + "";
                            list.add(sql);
                            num = num + 1;
                        } else {
                            String sql = year + "0" + (month - 1) + abc + "";
                            list.add(sql);
                            num = num + 1;
                        }

                    }
                    //输出6月份数据
                    for (int abc = 31; abc >= 31 - tianshu; abc--) {
                        if (abc < 10) {
                            String sql = year + "0" + (month - 2) + "0" + abc + "";
                            list.add(sql);
                            num = num + 1;
                        } else {
                            String sql = year + "0" + (month - 2) + abc + "";
                            list.add(sql);
                            num = num + 1;
                        }
                    }
                } else {
                    for (int abc = 30; abc > 0; abc--) {
                        if (abc < 10) {
                            String sql = year + "0" + (month - 1) + "0" + abc + "";
                            list.add(sql);
                            num = num + 1;
                        } else {
                            String sql = year + "0" + (month - 1) + abc + "";
                            list.add(sql);
                            num = num + 1;
                        }
                    }
                }

            }
            if (month == 1) {
                int tianshu = sum - 31;//减去当月的时间
                if (tianshu > 0) {
                    //输出7月数据


                    for (int abc = 31; abc > 0; abc--) {
                        if (abc < 10) {
                            String sql = (year - 1) + (month + 11) + "0" + abc + "";
                            list.add(sql);
                            num = num + 1;
                        } else {
                            String sql = (year - 1) + (month + 11) + abc + "";
                            list.add(sql);
                            num = num + 1;
                        }

                    }
                    //输出6月份数据
                    for (int abc = 30; abc > 30 - tianshu; abc--) {
                        if (abc < 10) {
                            String sql = (year - 1) + (month + 10) + "0" + abc + "";
                            list.add(sql);
                            num = num + 1;
                        } else {
                            String sql = (year - 1) + (month + 10) + abc + "";
                            list.add(sql);
                            num = num + 1;
                        }
                    }
                }
            }
            if (month == 8) {//上个月

                int tianshu = sum - 31;//减去当月的时间
                if (tianshu > 0) {
                    //输出7月数据


                    for (int abc = 31; abc > 0; abc--) {
                        if (abc < 10) {
                            String sql = year + "0" + (month - 1) + "0" + abc + "";
                            list.add(sql);
                            num = num + 1;
                        } else {
                            String sql = year + "0" + (month - 1) + abc + "";
                            list.add(sql);
                            num = num + 1;
                        }

                    }
                    //输出6月份数据
                    for (int abc = 30; abc >= 30 - tianshu; abc--) {
                        if (abc < 10) {
                            String sql = year + "0" + (month - 2) + "0" + abc + "";
                            list.add(sql);
                            num = num + 1;
                        } else {
                            String sql = year + "0" + (month - 2) + abc + "";
                            list.add(sql);
                            num = num + 1;
                        }
                    }
                } else {
                    //// 45减去当前日期剩余的时间小于 1个月
                    for (int abc = 31; abc >= 31 - sum; abc--) {
                        if (abc < 10) {
                            String sql = year + "0" + (month - 1) + "0" + abc + "";
                            list.add(sql);
                            num = num + 1;
                        } else {
                            String sql = year + "0" + (month - 1) + abc + "";
                            list.add(sql);
                            num = num + 1;
                        }
                    }


                }

            } else if (month != 3 && month != 8 && month != 1 && month != 2 && month != 4 && month != 12 && month != 11 && month != 10) {//如果不是8月
                int tianshu = sum - 30;//减去当月时间
                if (tianshu > 0) {
                    //输出7月数据
                    for (int abc = 30; abc > 0; abc--) {
                        if (abc < 10) {

                            String sql = year + "0" + (month - 1) + "0" + abc + "";
                            list.add(sql);
                            num = num + 1;
                        } else {
                            String sql = year + "0" + (month - 1) + abc + "";
                            list.add(sql);
                            num = num + 1;
                        }
                    }
                    //输出6月份数据
                    for (int abc = 31; abc >= 31 - tianshu; abc--) {
                        if (abc < 10) {
                            String sql = year + "0" + (month - 2) + "0" + abc + "";
                            list.add(sql);
                            num = num + 1;
                        } else {
                            String sql = year + "0" + (month - 2) + abc + "";
                            list.add(sql);
                            num = num + 1;
                        }
                    }
                } else {
                    //// 45减去当前日期剩余的时间小于 1个月
                    for (int abc = 30; abc > 30 - sum; abc--) {
                        if (abc < 10) {
                            String sql = year + (month - 1) + "0" + abc + "";
                            list.add(sql);
                            num = num + 1;
                        } else {
                            String sql = year + (month - 1) + abc + "";
                            list.add(sql);
                            num = num + 1;
                        }
                    }
                }
            } else if (month == 3) { // 如果4月
                if ((year % 4 == 0 && year % 1 != 0) || (year % 400 == 0))  //输出2月份数据   // 闰年
                {
                    int tianshu = sum - 29;// 剩余天数
                    //如果剩余天数大于上一个月的天数
                    if (tianshu > 0) {
                        //输入2月数据

                        for (int abc = 29; abc > 0; abc--) {
                            if (abc < 10) {
                                String sql = year + "0" + (month - 1) + "0" + abc + "";
                                list.add(sql);
                                num = num + 1;
                            } else {
                                String sql = year + "0" + (month - 1) + abc + "";
                                list.add(sql);
                                num = num + 1;
                            }
                        }
                        for (int abc = 31; abc >= 31 - tianshu; abc--) {
                            if (abc < 10) {
                                String sql = year + "0" + (month - 2) + "0" + abc + "";
                                list.add(sql);
                                num = num + 1;
                            } else {
                                String sql = year + "0" + (month - 2) + abc + "";
                                list.add(sql);
                                num = num + 1;
                            }
                        }

                    }
                    // 剩余时间小于下个月的时间
                    else {
                        for (int abc = 29; abc > 29 - tianshu; abc--) {
                            if (abc < 10) {
                                String sql = year + "0" + (month - 1) + "0" + abc + "";
                                list.add(sql);
                                num = num + 1;
                            } else {
                                String sql = year + "0" + (month - 1) + abc + "";
                                list.add(sql);
                                num = num + 1;
                            }
                        }
                    }
                } else {
                    //输出2月份数据   // 平年
                    int tianshu = sum - 28;// 剩余天数
                    //如果剩余天数大于上一个月的天数
                    if (tianshu > 0) {
                        for (int abc = 28; abc > 0; abc--) {
                            if (abc < 10) {
                                String sql = year + "0" + (month - 1) + "0" + abc + "";
                                list.add(sql);
                                num = num + 1;
                            } else {
                                String sql = year + "0" + (month - 1) + abc + "";
                                list.add(sql);
                                num = num + 1;
                            }
                        }
                        for (int abc = 31; abc >= 31 - tianshu; abc--) {
                            if (abc < 10) {
                                String sql = year + "0" + (month - 2) + "0" + abc + "";
                                list.add(sql);
                                num = num + 1;
                            } else {
                                String sql = year + "0" + (month - 2) + abc + "";
                                list.add(sql);
                                num = num + 1;
                            }
                        }
                    }
                    /// 剩余天数小于 上一个月的天数
                    else {
                        for (int abc = 28; abc > 28 - sum; abc--) {
                            if (abc < 10) {
                                String sql = year + "0" + (month - 1) + "0" + abc + "";
                                list.add(sql);
                                num = num + 1;
                            } else {
                                String sql = year + "0" + (month - 1) + abc + "";
                                list.add(sql);
                                num = num + 1;
                            }
                        }
                    }

                }
            }
        }
        return list;
    }

    /**
     * 获得指定日期的前几天 日期
     *
     * @param specifiedDay 特定日期
     * @param oneDay       前几天
     * @return yyyy-MM-dd
     */
    public static String getSpecifiedDayBefore(String specifiedDay, int oneDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, (day - oneDay));
        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }


    /**
     * 获取当前年
     *
     * @return year
     */
    public static String getCurrentYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return sdf.format(date);
    }

    /**
     * 获取某日期 年
     *
     * @return year
     */
    public static String getYear(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat nsdf = new SimpleDateFormat("yyyy");
        return nsdf.format(sdf.parse(date));
    }


    /**
     * 获取当前月
     *
     * @return month
     */
    public static String getCurrentMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        Date date = new Date();
        return sdf.format(date);
    }

    /**
     * 获取某日期 月
     *
     * @return month
     */
    public static String getMonth(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat nsdf = new SimpleDateFormat("MM");
        Date parse = sdf.parse(date);
        return nsdf.format(parse);
    }

    /**
     * 获取当前日
     *
     * @return month
     */
    public static String getCurrentDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        Date date = new Date();
        return sdf.format(date);
    }

    /**
     * 获取某日期 日
     *
     * @param date
     * @return month
     */
    public static String getDay(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat nsdf = new SimpleDateFormat("dd");
        return nsdf.format(sdf.parse(date));
    }


    /**
     * 获取某个区间内所有时间
     *
     * @param startDay 开始日期(before)
     * @param endDay   结束日期(after)
     * @return List<String>所有日期集合
     */
    public static List<String> getDayList(String startDay, String endDay) {
        List<String> dayList = new ArrayList<>();
        Calendar start = Calendar.getInstance();
        try {
            start.set(Integer.parseInt(getYear(startDay)), Integer.parseInt(getMonth(startDay)) - 1, Integer.parseInt(getDay(startDay)));
            Long startTIme = start.getTimeInMillis();
            Calendar end = Calendar.getInstance();
            end.set(Integer.parseInt(getYear(endDay)), Integer.parseInt(getMonth(endDay)) - 1, Integer.parseInt(getDay(endDay)));
            Long endTime = end.getTimeInMillis();
            Long oneDay = 1000 * 60 * 60 * 24l;
            Long time = startTIme;
            while (time <= endTime) {
                Date d = new Date(time);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                dayList.add(df.format(d));
                time += oneDay;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dayList;
    }

    /**
     * 获取指定月份的天数
     *
     * @param year  年
     * @param month 月
     * @return 天数
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 获取某月所有天数
     *
     * @param month 指定月任意天数
     * @return List<String>
     */
    public static List<String> getMontyDay(Date month) {
        List<String> dayList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(month);//month 为指定月份任意日期
        int year = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH);
        int dayNumOfMonth = getDaysByYearMonth(year, m + 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);// 从一号开始
        for (int i = 0; i < dayNumOfMonth; i++, cal.add(Calendar.DATE, 1)) {
            Date d = cal.getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dayList.add(simpleDateFormat.format(d));
        }
        return dayList;
    }

    /**
     * 生成指定日期Date
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return Date
     */
    public static Date setDate(String year, String month, String day) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return (dateFormat.parse(year + "-" + month + "-" + day));
        } catch (ParseException e) {
            throw new IllegalArgumentException("日期格式有误");
        }
    }


    /**
     * 当前时间距离某个日期相差多少分钟
     *
     * @param date 某个日期
     * @return int 分钟数
     */
    public static long getMinuteByDate(Date date) {
        Calendar dateOne = Calendar.getInstance();
        Calendar dateTwo = Calendar.getInstance();
        dateOne.setTime(new Date());    //设置为当前系统时间
        dateTwo.setTime(date);            //设置为2015年1月15日
        long timeOne = dateOne.getTimeInMillis();
        long timeTwo = dateTwo.getTimeInMillis();
        long minute = (timeOne - timeTwo) / (1000 * 60);//转化minute
        return minute;
    }

    public static String formateMinute(Date date) {
        long minutes = getMinuteByDate(date);
//        System.out.println(minutes);
        if (minutes <= 1) {
            return "1分钟前";
        } else if (minutes <= 3) {
            return "5分钟前";
        } else if (minutes <= 10) {
            return "10分钟前";
        } else if (minutes <= 30) {
            return "半小时前";
        } else if (minutes <= 60) {
            return "1小时前";
        } else if (minutes <= 120) {
            return "2小时前";
        } else if (minutes <= 360) {
            return "半天前";
        } else if (minutes <= 600) {
            return "一天前";
        } else {
            return new SimpleDateFormat("YYYY/MM/dd").format(date);
        }
    }

    public static int compareDate(String date1, String date2) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date dt1 = df.parse(date1);
        Date dt2 = df.parse(date2);
        return dt1.getTime() > dt2.getTime() ? 1 : (dt1.getTime() < dt2.getTime() ? -1 : 0);
    }

    public static int compareDate(Date date1, Date date2) throws ParseException {
        return date1.getTime() > date2.getTime() ? 1 : (date1.getTime() < date2.getTime() ? -1 : 0);
    }

    public static int compareDate(String date1, String date2, String pattern) throws ParseException {
        DateFormat df = new SimpleDateFormat(pattern);
        Date dt1 = df.parse(date1);
        Date dt2 = df.parse(date2);
        return dt1.getTime() > dt2.getTime() ? 1 : (dt1.getTime() < dt2.getTime() ? -1 : 0);
    }

    public static String format(Date date) {
        return format(date, getDatePattern());
    }

    public static String getDatePattern() {
        return "yyyy-MM-dd";
    }

    public static String format(Date date, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static void main(String[] args) throws ParseException, IOException {
        String str = "哈哈哈123";
        byte[] bytes = str.getBytes("UTF-8");
        String encode = new BASE64Encoder().encode(bytes);
        String decode = null;
        String decode12 = "5bey57uP5Y2B54K55LqG44CC44CC44CC44CC44CC";
        decode = new String(new BASE64Decoder().decodeBuffer(decode12), "UTF-8");
//        System.out.println(encode);
//        DateUtils.compareDate()
        System.out.println(decode);


    }
}
