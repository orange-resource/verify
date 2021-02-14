package com.orange.verify.api.entity.constant;

import lombok.Getter;

@Getter
public enum CardTypeUnitTypeConstant {

    MINUTE(1, "分"),
    HOUR(2, "时"),
    DAY(3, "天"),
    WEEK(4, "周"),
    MONTH(5, "月"),
    YEAR(6, "年"),
    ;

    private Integer type;
    private String explain;

    CardTypeUnitTypeConstant(Integer type, String explain) {
        this.type = type;
        this.explain = explain;
    }

}
