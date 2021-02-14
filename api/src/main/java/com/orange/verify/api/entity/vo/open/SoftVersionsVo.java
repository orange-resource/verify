package com.orange.verify.api.entity.vo.open;

import lombok.Data;

import java.io.Serializable;

@Data
public class SoftVersionsVo implements Serializable {

    private String notice;

    private Integer novatioNecessaria;

    private String number;

    private String updateUrl;

}
