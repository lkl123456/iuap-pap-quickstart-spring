package com.yonyou.iuap.purpayment.dao;
import com.yonyou.iuap.purpayment.entity.Purpayment_detail;
import com.yonyou.iuap.baseservice.persistence.mybatis.mapper.GenericExMapper;
import com.yonyou.iuap.baseservice.persistence.mybatis.mapper.GenericMapper;
import com.yonyou.iuap.mybatis.anotation.MyBatisRepository;
import org.springframework.stereotype.Component;


@MyBatisRepository
   @Component("com.yonyou.iuap.purpayment.dao.Purpayment_detailMapper")
public interface Purpayment_detailMapper extends GenericExMapper<Purpayment_detail> {

}

