package com.yonyou.iuap.allowances.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.yonyou.cloud.middleware.rpc.RemoteCall;
import com.yonyou.iuap.allowances.entity.Allowances;
import com.yonyou.iuap.pap.busilog.AppInfoConstant;


@RemoteCall("iuap-pap-quickstart-service@c87e2267-1001-4c70-bb2a-ab41f3b81aa3")
public interface ExternalAllowancesService{

	/**
     * 插入单条记录
     * @param entity
     * @return
     */
    Allowances insertSelective(Allowances entity);

    /**
     * 更新单条记录
     * @param entity
     * @return
     */
     Allowances updateSelective(Allowances entity);

    /**
     * 批量保存
     *
     * @param listData
     */
    void saveMultiple(List<Allowances> listData);

    /**
     * 批量删除
     *
     * @param list
     * @return
     */
    int deleteBatch(List<Allowances> list);

    /**
     * 批量更新
     * @param listData
     */
     void updateMultiple(List<Allowances> listData);

}