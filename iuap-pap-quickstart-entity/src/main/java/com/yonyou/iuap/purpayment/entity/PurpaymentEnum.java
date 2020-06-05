package com.yonyou.iuap.purpayment.entity;

import com.yonyou.iuap.baseservice.intg.ext.I18nEnum;
import com.yonyou.iuap.pap.base.i18n.MessageSourceUtil;

import java.util.HashMap;
import java.util.Map;


/**
 * 采购付款单枚举定义,约定为po的fieldName_key为匹配原则
 * @date 2019-8-28 16:46:52
 */
public enum PurpaymentEnum implements I18nEnum {
                
    PAYTYPE_0("0", "现金","purpayment.enum.paytype0")
,
    PAYTYPE_1("1", "支票","purpayment.enum.paytype1")
,
    PAYTYPE_2("2", "汇票","purpayment.enum.paytype2")
,
    PAYTYPE_3("3", "转账","purpayment.enum.paytype3")
,
    PAYTYPE_4("4", "其他","purpayment.enum.paytype4")


    ;
   private String code;
   private String value;
   private String i18nKey;

   PurpaymentEnum(String code, String value, String i18nKey) {
       this.code = code;
       this.value = value;
       this.i18nKey = i18nKey;
   }

   @Override
   public Map getMappings() {
       Map result = new HashMap();
       for(PurpaymentEnum item: PurpaymentEnum.values()){
            result.put( item.name(), MessageSourceUtil.getMessage(item.i18nKey, item.value));
       }

       return result;
   }

}
