package com.orange.verify.adminweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orange.verify.api.entity.po.BaseTableName;
import com.orange.verify.api.entity.po.DDTokenPO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface DDTokenMapper extends BaseMapper<DDTokenPO>, BaseTableName {

}