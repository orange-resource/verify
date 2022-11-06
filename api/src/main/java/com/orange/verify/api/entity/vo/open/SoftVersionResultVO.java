package com.orange.verify.api.entity.vo.open;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SoftVersionResultVO implements Serializable {

    private String notice;

    private Integer forcedStatus;

    private String number;

    private String updateUrl;

}
