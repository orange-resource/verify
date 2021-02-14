package com.orange.verify.adminweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orange.verify.api.entity.po.BaseTableName;
import com.orange.verify.api.entity.po.SoftAccountPO;
import com.orange.verify.api.entity.vo.admin.SoftAccountPageResultVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoftAccountMapper extends BaseMapper<SoftAccountPO>, BaseTableName {

    @Select("<script>" +
            "SELECT a.*,s.name as soft_name FROM" + t_soft_account_space + "a " +
            "LEFT JOIN" + t_soft_space + "s " +
            "ON a.soft_id = s.id " +
            "where 1=1 " +
            "<if test=\"softId != null and softId != ''\"> and a.soft_id = #{softId} </if>" +
            "<if test=\"createIp != null and createIp != ''\"> and a.create_ip = #{createIp} </if>" +
            "<if test=\"username != null and username != ''\"> and a.username like concat('%',#{username},'%') </if>" +
            "order by create_date desc" +
            "</script>")
    List<SoftAccountPageResultVO> page(@Param("softId") String softId,
                                       @Param("createIp") String createIp,
                                       @Param("username") String username,
                                       Page page);

}