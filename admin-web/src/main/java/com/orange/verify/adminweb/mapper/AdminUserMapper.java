package com.orange.verify.adminweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orange.verify.api.entity.po.AdminUserPO;
import com.orange.verify.api.entity.po.BaseTableName;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserMapper extends BaseMapper<AdminUserPO>, BaseTableName {

    @Select("select count(*) from" + t_admin_user_space + "where username = #{username} and password = #{password}")
    int selectCountByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}