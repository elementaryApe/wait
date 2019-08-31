package com.rongdong.constant;

/**
 * 数据源枚举
 * Created by hsh on 2018/3/21.
 */
public enum DataSourceTypeEnum {
    one("first",1),two("second",2),three("three",3),four("four",4),five("five",5);

    DataSourceTypeEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    private String name;
    private int index;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
