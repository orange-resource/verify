package com.orange.verify.api.entity.constant;

import lombok.Getter;

@Getter
public enum SoftDosingTypeConstant {

    ONE_MACHINE(1, "只支持单机"),
    DOES_NOT_MATTER(2, "无限制"),
    ;

    private Integer type;
    private String explain;

    SoftDosingTypeConstant(Integer type, String explain) {
        this.type = type;
        this.explain = explain;
    }

}
