package com.orange.verify.adminweb.service;

import com.orange.verify.adminweb.common.util.BaiduIpUtil;
import com.orange.verify.adminweb.mapper.BaiduMapTokenMapper;
import com.orange.verify.api.entity.po.BaiduMapTokenPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaiduMapApiServiceL {

    @Autowired
    private BaiduMapTokenMapper baiduMapTokenMapper;

    public String getIpInfo(String ip) throws Exception {

        if ("127.0.0.1".equals(ip)) {
            return "本地测试";
        }

        try {

            BaiduMapTokenPO baiduMapTokenPO = baiduMapTokenMapper.selectSingle();

            String ipInfo = BaiduIpUtil.start(baiduMapTokenPO.getAppkey())
                    .getAddressByIp(ip);

            return ipInfo;

        } catch (Exception e) {
            throw new Exception();
        }
    }

}
