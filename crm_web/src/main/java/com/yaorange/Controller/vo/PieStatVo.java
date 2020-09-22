package com.yaorange.Controller.vo;


import java.math.BigInteger;

/**
 *
 **/
public class PieStatVo {
    private BigInteger value;
    private String name;

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PieStatVo{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }
}
