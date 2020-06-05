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
 * 采购付款单
 * @date 2019年08月28日 下午04点46分52秒
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "train_purpayment")
@Associative(fkName = "paymentid")
@CodingEntity(codingField="")
@Entity(name = "com.yonyou.iuap.purpayment.entity.Purpayment")
public class Purpayment extends AbsBpmModel  implements Serializable,TenantId,Printable
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
                return "train_purpayment";
        }


    @Condition
    @Column(name="billno")
    private String billno;        //付款单号

    public void setBillno(String billno){
    this.billno = billno;
    }
    public String getBillno(){
    return this.billno;
    }
    

    @Condition(match=Match.EQ)
    @Column(name="org")
    @Reference(code="neworganizition_tree",srcProperties={ "name"}, desProperties={ "orgname"})
    private String org;        //组织

    public void setOrg(String org){
    this.org = org;
    }
    public String getOrg(){
    return this.org;
    }
    

    @Condition(match=Match.EQ)
    @Transient
    private String orgname;        //组织

    public void setOrgname(String orgname){
    this.orgname = orgname;
    }
    public String getOrgname(){
    return this.orgname;
    }
    

    @Condition(match=Match.EQ)
    @Column(name="dept")
    @Reference(code="newdept_tree",srcProperties={ "name"}, desProperties={ "deptname"})
    private String dept;        //申请付款部门

    public void setDept(String dept){
    this.dept = dept;
    }
    public String getDept(){
    return this.dept;
    }
    

    @Condition(match=Match.EQ)
    @Transient
    private String deptname;        //申请付款部门

    public void setDeptname(String deptname){
    this.deptname = deptname;
    }
    public String getDeptname(){
    return this.deptname;
    }
    

    @Condition(match=Match.EQ)
    @Transient
    private String suppliername;        //供应商

    public void setSuppliername(String suppliername){
    this.suppliername = suppliername;
    }
    public String getSuppliername(){
    return this.suppliername;
    }
    

    @Condition(match=Match.EQ)
    @Column(name="supplier")
    @Reference(code="supplier",srcProperties={ "name"}, desProperties={ "suppliername"})
    private String supplier;        //供应商

    public void setSupplier(String supplier){
    this.supplier = supplier;
    }
    public String getSupplier(){
    return this.supplier;
    }
    

    @Condition(match=Match.EQ)
    @Column(name="paytype")
    @I18nEnumCode(clazz = PurpaymentEnum.class,target ="paytypeEnumValue" )
    private String paytype;        //付款方式

    public void setPaytype(String paytype){
    this.paytype = paytype;
    }
    public String getPaytype(){
    return this.paytype;
    }
    
    @Transient
    private String paytypeEnumValue;   //付款方式

    public void setPaytypeEnumValue(String paytypeEnumValue){
        this.paytypeEnumValue = paytypeEnumValue;
    }
    public String getPaytypeEnumValue(){
        return this.paytypeEnumValue;
    }

    @Condition
    @Column(name="banktype")
    @Reference(code="banktype",srcProperties={ "typename"}, desProperties={ "banktypename"})
    private String banktype;        //开户行

    public void setBanktype(String banktype){
    this.banktype = banktype;
    }
    public String getBanktype(){
    return this.banktype;
    }
    

    @Condition(match=Match.EQ)
    @Transient
    private String banktypename;        //开户行

    public void setBanktypename(String banktypename){
    this.banktypename = banktypename;
    }
    public String getBanktypename(){
    return this.banktypename;
    }
    

    @Condition(match=Match.EQ)
    @Transient
    private String paybankacccode;        //付款银行账号

    public void setPaybankacccode(String paybankacccode){
    this.paybankacccode = paybankacccode;
    }
    public String getPaybankacccode(){
    return this.paybankacccode;
    }
    

    @Condition
    @Column(name="paybankacc")
    @Reference(code="bankacc",srcProperties={ "bankacc"}, desProperties={ "paybankacccode"})
    private String paybankacc;        //付款银行账号

    public void setPaybankacc(String paybankacc){
    this.paybankacc = paybankacc;
    }
    public String getPaybankacc(){
    return this.paybankacc;
    }
    

    @Condition
    @Column(name="paybankname")
    private String paybankname;        //户名

    public void setPaybankname(String paybankname){
    this.paybankname = paybankname;
    }
    public String getPaybankname(){
    return this.paybankname;
    }
    

    @Condition
    @Column(name="currtype")
    private String currtype;        //币种

    public void setCurrtype(String currtype){
    this.currtype = currtype;
    }
    public String getCurrtype(){
    return this.currtype;
    }
    

    @Condition(match=Match.EQ)
    @Transient
    private String currtypename;        //币种

    public void setCurrtypename(String currtypename){
    this.currtypename = currtypename;
    }
    public String getCurrtypename(){
    return this.currtypename;
    }
    

    @Condition(match=Match.LIKE)
    @Column(name="receiveacc")
    private String receiveacc;        //收款银行账号

    public void setReceiveacc(String receiveacc){
    this.receiveacc = receiveacc;
    }
    public String getReceiveacc(){
    return this.receiveacc;
    }
    

    @Condition(match=Match.GTEQ)
    @Column(name="total")
    private BigDecimal total;        //付款总金额

    public void setTotal(BigDecimal total){
    this.total = total;
    }
    public BigDecimal getTotal(){
    return this.total;
    }
    

    @Condition(match=Match.LTEQ)
    @Column(name="paydate")
    private String paydate;        //单据日期

    public void setPaydate(String paydate){
    this.paydate = paydate;
    }
    public String getPaydate(){
    return this.paydate;
    }
    

    @Condition(match=Match.LIKE)
    @Column(name="payreason")
    private String payreason;        //付款理由

    public void setPayreason(String payreason){
    this.payreason = payreason;
    }
    public String getPayreason(){
    return this.payreason;
    }
    

    @Condition
    @Column(name="sourceid")
    private String sourceid;        //来源单据ID

    public void setSourceid(String sourceid){
    this.sourceid = sourceid;
    }
    public String getSourceid(){
    return this.sourceid;
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


