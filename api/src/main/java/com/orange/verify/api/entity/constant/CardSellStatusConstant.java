package com.orange.verify.api.entity.constant;

import lombok.Getter;

@Getter
public enum CardSellStatusConstant {

    NO(1, "未卖出"),
    YES(2, "已卖出"),
    ;

    private Integer type;
    private String explain;

    CardSellStatusConstant(Integer type, String explain) {
        this.type = type;
        this.explain = explain;
    }

}
