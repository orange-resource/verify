package com.orange.verify.api.entity.constant;

import lombok.Getter;

@Getter
public enum SoftRegisterStatusConstant {

    OPEN(1, "开放注册"),
    CLOSE(2, "关闭注册"),
    ;

    private Integer type;
    private String explain;

    SoftRegisterStatusConstant(Integer type, String explain) {
        this.type = type;
        this.explain = explain;
    }

}
