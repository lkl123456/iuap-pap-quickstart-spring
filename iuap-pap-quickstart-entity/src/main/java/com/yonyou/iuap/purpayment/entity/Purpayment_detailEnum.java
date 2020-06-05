package com.yonyou.iuap.purpayment.entity;

import com.yonyou.iuap.baseservice.intg.ext.I18nEnum;
import com.yonyou.iuap.pap.base.i18n.MessageSourceUtil;

import java.util.HashMap;
import java.util.Map;


  
/**
 * 采购付款明细枚举定义,约定为po的fieldName_key为匹配原则
 * @date 2019-8-28 16:46:52
 */
public enum Purpayment_detailEnum implements I18nEnum {
                                ;
    private String code;
    private String value;
    private String i18nKey;

    Purpayment_detailEnum(String code, String value, String i18nKey) {
        this.code = code;
        this.value = value;
        this.i18nKey = i18nKey;
    }

   @Override
    public Map getMappings() {
        Map result = new HashMap();
        for(Purpayment_detailEnum item: Purpayment_detailEnum.values()){
            result.put( item.name(), MessageSourceUtil.getMessage(item.i18nKey, item.value));
        }

        return result;
    }
}
