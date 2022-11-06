package com.orange.verify.api.entity.constant;

import lombok.Getter;

@Getter
public enum SoftVersionForcedStatusConstant {

    YES(2, "强制更新"),
    NO(1, "不强制更新"),
    ;

    private Integer type;
    private String explain;

    SoftVersionForcedStatusConstant(Integer type, String explain) {
        this.type = type;
        this.explain = explain;
    }

}
