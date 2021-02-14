package com.orange.verify.api.entity.vo.open;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SoftVersionsVO implements Serializable {

    private String notice;

    private Integer novatioNecessaria;

    private String number;

    private String updateUrl;

}
