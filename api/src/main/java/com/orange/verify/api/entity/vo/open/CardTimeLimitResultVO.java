package com.orange.verify.api.entity.vo.open;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class CardTimeLimitResultVO implements Serializable {

    private Date startDate;

    private Date endDate;

}
