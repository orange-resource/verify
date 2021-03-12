package com.orange.verify.adminweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orange.verify.api.entity.po.BaiduMapTokenPO;
import com.orange.verify.api.entity.po.BaseTableName;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface BaiduMapTokenMapper extends BaseMapper<BaiduMapTokenPO>, BaseTableName {

    @Select("select * from" + t_baidu_map_token_space + "limit 1")
    BaiduMapTokenPO selectOne();

}
