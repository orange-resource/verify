package com.orange.verify.api.entity.constant;

import lombok.Getter;

@Getter
public enum SoftChangeTypeConstant {

    NO(2, "不支持换绑定"),
    YES(1, "支持换绑定"),
    ;

    private Integer type;
    private String explain;

    SoftChangeTypeConstant(Integer type, String explain) {
        this.type = type;
        this.explain = explain;
    }

}
