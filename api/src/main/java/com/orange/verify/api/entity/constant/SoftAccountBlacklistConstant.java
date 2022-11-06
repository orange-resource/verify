package com.orange.verify.api.entity.constant;

import lombok.Getter;

@Getter
public enum SoftAccountBlacklistConstant {

    NO(1, "未加入黑名单"),
    YES(2, "已加入黑名单"),
    ;

    private Integer type;
    private String explain;

    SoftAccountBlacklistConstant(Integer type, String explain) {
        this.type = type;
        this.explain = explain;
    }

}
