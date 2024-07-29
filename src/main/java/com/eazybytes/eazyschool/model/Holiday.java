package com.eazybytes.eazyschool.model;

import lombok.Data;

@Data
public class Holiday extends BaseEntity {

    private String day;
    private String reason;
    private Type type;

    public Holiday(String s, String s1, Type type) {
        day = s;
        reason = s1;
        this.type = type;
    }


    public enum Type {
        FESTIVAL, FEDERAL
    }
}
