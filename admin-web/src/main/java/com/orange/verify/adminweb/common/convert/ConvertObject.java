package com.orange.verify.adminweb.common.convert;

import com.orange.verify.api.entity.po.SoftAccountPO;
import com.orange.verify.api.entity.vo.open.AccountRegisterParamVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConvertObject {

    @Mapping(target = "createIp",source = "ip")
    SoftAccountPO fromVo(AccountRegisterParamVO accountRegisterParamVO);

}
