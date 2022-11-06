package com.orange.verify.api.entity.vo.admin;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CardPageParamVO implements Serializable {

    private String softId;

    private Integer cardTypeUnit;

    private Integer closeStatus;

    private Integer useStatus;

    private Integer offset;

    private Integer limit;

}
