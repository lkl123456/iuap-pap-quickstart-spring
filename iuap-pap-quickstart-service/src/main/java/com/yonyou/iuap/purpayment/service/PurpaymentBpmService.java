package com.yonyou.iuap.purpayment.service;

import com.yonyou.iuap.baseservice.bpm.service.GenericBpmService;
import com.yonyou.iuap.bpm.pojo.BPMFormJSON;
import com.yonyou.iuap.context.InvocationInfoProxy;
import com.yonyou.uap.wb.utils.JsonConverter;
import org.apache.commons.beanutils.PropertyUtils;
import com.yonyou.iuap.purpayment.dao.PurpaymentMapper;
import com.yonyou.iuap.purpayment.entity.Purpayment;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

@Service("com.yonyou.iuap.purpayment.service.PurpaymentBpmService")
public class PurpaymentBpmService extends GenericBpmService<Purpayment>{

    private PurpaymentMapper purpaymentMapper;

    @Autowired
    public void setPurpaymentMapper(PurpaymentMapper purpaymentMapper) {
        this.purpaymentMapper = purpaymentMapper;
        super.setGenericMapper(purpaymentMapper);
    }

    @Override
    public BPMFormJSON buildBPMFormJSON(String processDefineCode, Purpayment entity) {
        try {
            BPMFormJSON bpmform = new BPMFormJSON();
            bpmform.setProcessDefinitionKey(processDefineCode);
            String userName = InvocationInfoProxy.getUsername()== null ? "default user" : InvocationInfoProxy.getUsername();
            try {
                userName = URLDecoder.decode(userName,"utf-8");
                userName = URLDecoder.decode(userName,"utf-8");
            } catch (UnsupportedEncodingException e) {
                userName =InvocationInfoProxy.getUsername();
            }
            //title
            String title = userName + "提交的【采购付款单】单据编号是" + entity.getBpmBillCode() + ", 请审批";
            bpmform.setTitle(title);
            // 实例名
            bpmform.setFunCode("purpayment");
            bpmform.setFunCodeName("采购付款单");
            // 单据id
            bpmform.setFormId(entity.getId().toString());
            // 单据号
            bpmform.setBillNo(entity.getBpmBillCode());
            // 制单人
            bpmform.setBillMarker(InvocationInfoProxy.getUserid());
            // 提交日期
            bpmform.setSubmitTime(new Date());
            // 其他变量
            bpmform.setOtherVariables(buildEntityVars(entity));
            // 单据url
            bpmform.setFormUrl("/purpayment-fe/purpayment-purpayment#/purpayment?btnFlag=2&search_id="+ entity.getId());   // 单据url
// 流程实例名称
            bpmform.setProcessInstanceName(title);                                                                              // 流程实例名称
            // 流程审批后，执行的业务处理类(controller对应URI前缀)
            bpmform.setServiceClass("/purpayment-be/purpayment/purpayment");// 流程审批后，执行的业务处理类(controller对应URI前缀)
            //设置单据打开类型 uui/react
            bpmform.setFormType(BPMFormJSON.FORMTYPE_REACT);
            // 过滤其他表单变量,流程实例名称只存储关键信息
            BPMFormJSON tempFormJson = new BPMFormJSON();
            PropertyUtils.copyProperties(tempFormJson, bpmform);
            tempFormJson.setOtherVariables(null);
            bpmform.setProcessInstanceName(JsonConverter.toJson(tempFormJson));
            return bpmform;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}

