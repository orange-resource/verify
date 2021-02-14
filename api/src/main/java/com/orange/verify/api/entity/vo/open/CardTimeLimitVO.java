package com.orange.verify.api.entity.vo.open;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CardTimeLimitVO implements Serializable {

    private Long startDate;

    private Long endDate;

}
