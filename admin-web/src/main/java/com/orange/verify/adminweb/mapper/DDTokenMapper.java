package com.orange.verify.adminweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orange.verify.api.entity.po.BaseTableName;
import com.orange.verify.api.entity.po.DDTokenPO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DDTokenMapper extends BaseMapper<DDTokenPO>, BaseTableName {

    @Select("select * from" + t_dingding_token_space + "limit 1")
    DDTokenPO selectOne();

}