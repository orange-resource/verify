package com.orange.verify.api.entity.vo.admin;

import com.orange.verify.api.entity.po.CardPO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardPageResultVO extends CardPO {

    private Integer cardTypeUnit;

    private String cardTypeValue;

    private String accountName;

    private String softName;

}
