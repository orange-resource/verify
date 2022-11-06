package com.orange.verify.api.entity.constant;

import lombok.Getter;

@Getter
public enum SoftServiceStatusConstant {

    CHARGE(1, "收费"),
    FREE(2, "免费开放"),
    CLOSE(3, "关闭开放使用"),
    ;

    private Integer type;
    private String explain;

    SoftServiceStatusConstant(Integer type, String explain) {
        this.type = type;
        this.explain = explain;
    }

}
