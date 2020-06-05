package com.yonyou.iuap.purpayment.entity;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yonyou.iuap.baseservice.bpm.entity.AbsBpmModel;
import com.yonyou.iuap.baseservice.intg.ext.I18nEnumCode;
import com.yonyou.iuap.baseservice.print.entity.Printable;      
import com.yonyou.iuap.baseservice.entity.TenantId;
import com.yonyou.iuap.baseservice.entity.annotation.Reference;
import com.yonyou.iuap.baseservice.entity.annotation.Associative;
import com.yonyou.iuap.baseservice.support.condition.Condition;
import com.yonyou.iuap.baseservice.support.condition.Match;
import com.yonyou.iuap.baseservice.support.generator.GeneratedValue;
import com.yonyou.iuap.baseservice.support.generator.Strategy;
import com.yonyou.iuap.baseservice.entity.annotation.CodingEntity;
import com.yonyou.iuap.baseservice.entity.annotation.CodingField;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.math.BigDecimal;

  
/**
 * 采购付款明细
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "train_purpayment_detail")
@Entity(name = "com.yonyou.iuap.purpayment.entity.Purpayment_detail")
public class Purpayment_detail extends AbsBpmModel  implements Serializable,TenantId,Printable
{

    @Id
    @GeneratedValue
    @Condition
    protected String id;//ID
    @Override
    public String getId() {
        return id;
    }
    @Override
    public void setId(Serializable id){
        this.id= id.toString();
        super.id = id;
    }
    public void setId(String id) {
        this.id = id;
    }
        
        @Override
        public String getMainBoCode() {
                return "train_purpayment_detail";
        }


    @Condition(match=Match.EQ)
    @Transient
    private String materialcode;        //采购货物

    public void setMaterialcode(String materialcode){
    this.materialcode = materialcode;
    }
    public String getMaterialcode(){
    return this.materialcode;
    }

    @Condition
    @Column(name="material")
    @Reference(code="material",srcProperties={ "materialcode"}, desProperties={ "materialcode"})
    private String material;        //采购货物

    public void setMaterial(String material){
    this.material = material;
    }
    public String getMaterial(){
    return this.material;
    }

    @Condition
    @Column(name="materialname")
    private String materialname;        //采购货物名称

    public void setMaterialname(String materialname){
    this.materialname = materialname;
    }
    public String getMaterialname(){
    return this.materialname;
    }

    @Condition
    @Column(name="num")
    private Integer num;        //采购数量

    public void setNum(Integer num){
    this.num = num;
    }
    public Integer getNum(){
    return this.num;
    }

    @Condition
    @Column(name="price")
    private BigDecimal price;        //采购价格

    public void setPrice(BigDecimal price){
    this.price = price;
    }
    public BigDecimal getPrice(){
    return this.price;
    }

    @Condition
    @Column(name="money")
    private BigDecimal money;        //采购金额

    public void setMoney(BigDecimal money){
    this.money = money;
    }
    public BigDecimal getMoney(){
    return this.money;
    }

    @Condition
    @Column(name="paymentid")
    private String paymentid;        //主表主键

    public void setPaymentid(String paymentid){
    this.paymentid = paymentid;
    }
    public String getPaymentid(){
    return this.paymentid;
    }

    @Condition
    @Column(name="memo")
    private String memo;        //说明

    public void setMemo(String memo){
    this.memo = memo;
    }
    public String getMemo(){
    return this.memo;
    }

        @Override
        public String getBpmBillCode() {
        return  DateUtil.format(new Date(), "yyyyMMddHHmmss"+new Random().nextInt(10))   ;
        }


    @Column(name="TENANT_ID")
    @Condition
    private String tenantId;
    public String getTenantId() {
        return this.tenantId;
    }
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

}


