package com.yonyou.iuap.purpayment.controller;
import com.yonyou.iuap.baseservice.controller.util.BaseController;
import com.yonyou.iuap.baseservice.intg.service.GenericIntegrateService;
import com.yonyou.iuap.mvc.constants.RequestStatusEnum;
import com.yonyou.iuap.baseservice.entity.annotation.Associative;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
   
import com.yonyou.iuap.purpayment.service.Purpayment_detailService;
import com.yonyou.iuap.purpayment.entity.Purpayment;
import com.yonyou.iuap.purpayment.service.PurpaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.yonyou.iuap.mvc.type.SearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
/**
 * 说明：采购付款单 打印Controller——提供数据打印回调rest接口
 * 
 * @date 2019-8-28 16:46:53
 */
@Controller("com.yonyou.iuap.purpayment.controller.PurpaymentPrintController")
@RequestMapping(value = "/purpayment/purpayment")
public class PurpaymentPrintController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(PurpaymentPrintController.class);

    private PurpaymentService service;
private Map<String, GenericIntegrateService> subServices = new HashMap();
    @Autowired
    public void setService(PurpaymentService service) {
        this.service = service;
    }

  
    private Purpayment_detailService purpayment_detailService;
    @Autowired
    public void setPurpayment_detailService(Purpayment_detailService purpayment_detailService) {
        this.purpayment_detailService = purpayment_detailService;
        this.subServices.put("train_purpayment_detail",purpayment_detailService);
    }


     @RequestMapping(
            value = {"/dataForPrint"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public Object getDataForPrint(HttpServletRequest request) {
        String params = request.getParameter("params");
        JSONObject jsonObj = JSON.parseObject(params);
        String id = (String)jsonObj.get("id");
        Purpayment vo = (Purpayment)this.service.findById(id);
        if (vo.getMainBoCode() == null) {
            return this.buildError("mainBoCode", "主表业务对象编码为打印关键参数不可为空", RequestStatusEnum.FAIL_FIELD);
        } else {
            JSONObject jsonVo = JSONObject.parseObject(JSONObject.toJSON(vo).toString());
            JSONObject mainData = new JSONObject();
            JSONArray mainDataJson = new JSONArray();
            Set<String> setKey = jsonVo.keySet();
    Iterator var11 = setKey.iterator();

    String subBoCode;
    while(var11.hasNext()) {
    String key = (String)var11.next();
    subBoCode = jsonVo.getString(key);
    mainData.put(key, subBoCode);
    }

    mainDataJson.add(mainData);
    JSONObject boAttr = new JSONObject();
    boAttr.put(vo.getMainBoCode(), mainDataJson);
    Iterator var18 = this.subServices.keySet().iterator();

    while(var18.hasNext()) {
    subBoCode = (String)var18.next();
    Associative associative = (Associative)vo.getClass().getAnnotation(Associative.class);
    if (associative == null || StringUtils.isEmpty(associative.fkName())) {
    return this.buildError("", "主子表打印需要在entity上增加@Associative并指定fkName", RequestStatusEnum.FAIL_FIELD);
    }

    List subList = ((GenericIntegrateService)this.subServices.get(subBoCode)).queryList(associative.fkName(), id);
    JSONArray childrenDataJson = new JSONArray();
    childrenDataJson.addAll(subList);
    boAttr.put(subBoCode, childrenDataJson);
    }

    this.logger.debug("打印回调数据:" + boAttr.toString());
    return boAttr.toString();
    }
    }
}
