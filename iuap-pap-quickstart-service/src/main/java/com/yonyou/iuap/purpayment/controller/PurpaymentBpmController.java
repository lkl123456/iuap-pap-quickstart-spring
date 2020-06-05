package com.yonyou.iuap.purpayment.controller;

import com.yonyou.iuap.baseservice.bpm.controller.GenericBpmController;
import com.yonyou.iuap.purpayment.entity.Purpayment;
import com.yonyou.iuap.purpayment.service.PurpaymentBpmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.yonyou.iuap.mvc.annotation.FrontModelExchange;
import com.yonyou.iuap.mvc.type.SearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 说明：采购付款单 流程控制Controller——提供流程提交、收回、审批回调等rest接口
 * 
 * @date 2019-8-28 16:46:52
 */
@Controller("com.yonyou.iuap.purpayment.controller.PurpaymentBpmController")
@RequestMapping(value="/purpayment/purpayment")
public class PurpaymentBpmController extends GenericBpmController<Purpayment>{
    
    private Logger logger = LoggerFactory.getLogger(PurpaymentController.class);


    private PurpaymentBpmService service;
    @Autowired
    public void setService(PurpaymentBpmService service) {
        this.service = service;
        super.setService(service);
    }

}
