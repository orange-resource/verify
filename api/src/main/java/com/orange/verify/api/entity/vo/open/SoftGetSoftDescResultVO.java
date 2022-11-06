package com.orange.verify.api.entity.vo.open;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SoftGetSoftDescResultVO implements Serializable {

    private Integer changeStrategy;

    private Integer dosingStrategy;

    private String name;

    private String notice;

    private String registeCloseMsg;

    private Integer registerStatus;

    private String serviceCloseMsg;

    private Integer serviceStatus;

}
