package com.orange.verify.api.entity.constant;

import lombok.Getter;

@Getter
public enum CardCloseStatusConstant {

    NO(1, "未封停"),
    YES(2, "已封停"),
    ;

    private Integer type;
    private String explain;

    CardCloseStatusConstant(Integer type, String explain) {
        this.type = type;
        this.explain = explain;
    }

}
