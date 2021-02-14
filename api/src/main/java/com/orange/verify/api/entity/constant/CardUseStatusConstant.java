package com.orange.verify.api.entity.constant;

import lombok.Getter;

@Getter
public enum CardUseStatusConstant {

    NO(1, "未使用"),
    YES(2, "已使用"),
    ;

    private Integer type;
    private String explain;

    CardUseStatusConstant(Integer type, String explain) {
        this.type = type;
        this.explain = explain;
    }

}
