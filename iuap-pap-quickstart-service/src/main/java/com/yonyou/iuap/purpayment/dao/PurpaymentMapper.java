package com.yonyou.iuap.purpayment.dao;
import com.yonyou.iuap.purpayment.entity.Purpayment;
import com.yonyou.iuap.baseservice.persistence.mybatis.mapper.GenericExMapper;
import com.yonyou.iuap.baseservice.persistence.mybatis.mapper.GenericMapper;
import com.yonyou.iuap.mybatis.anotation.MyBatisRepository;
import org.springframework.stereotype.Component;
import java.util.List;

        import org.apache.ibatis.annotations.Param;
        import com.yonyou.iuap.mybatis.type.PageResult;
        import com.yonyou.iuap.mvc.type.SearchParams;
        import org.springframework.data.domain.PageRequest;

@MyBatisRepository
@Component("com.yonyou.iuap.purpayment.dao.PurpaymentMapper")
public interface PurpaymentMapper extends GenericExMapper<Purpayment> {

    PageResult<Purpayment> selectAllBySonCode(@Param("page") PageRequest paramPageRequest, @Param("condition") SearchParams paramSearchParams);


        List selectListByExcelData(List list);
}

