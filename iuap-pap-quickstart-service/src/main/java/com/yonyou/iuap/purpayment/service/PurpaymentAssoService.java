package com.yonyou.iuap.purpayment.service;
import com.yonyou.iuap.baseservice.intg.support.ServiceFeature;
import com.yonyou.iuap.baseservice.service.GenericAssoService;
import com.yonyou.iuap.baseservice.vo.GenericAssoVo;
import com.yonyou.iuap.purpayment.entity.Purpayment;
import com.yonyou.iuap.purpayment.dao.PurpaymentMapper;
import com.yonyou.iuap.purpayment.entity.Purpayment_detail;
import com.yonyou.iuap.purpayment.service.Purpayment_detailService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static com.yonyou.iuap.baseservice.intg.support.ServiceFeature.*;


/**
 * 主子表联合查询,修改服务
 * @date 2019年08月28日 下午04点46分52秒
 */
@Service("com.yonyou.iuap.purpayment.service.PurpaymentAssoService")
public class PurpaymentAssoService  extends GenericAssoService<Purpayment> {

    private PurpaymentMapper mapper;
    /**
     * 注入主表mapper
     */
    @Autowired
    public void setService(PurpaymentMapper mapper) {
        this.mapper = mapper;
        super.setGenericMapper(mapper);
    }

    /**
     * 注入子表Purpayment_detailService
     */
    @Autowired
    public void setPurpayment_detailService(Purpayment_detailService subService) {
        super.setSubService(Purpayment_detail.class,subService);
    }
    

    @Override
    protected ServiceFeature[] getFeats() {
        return new ServiceFeature[]{ I18N_ENUM,UNION_REFERENCE,LOGICAL_DEL,BPM,MULTI_TENANT };
    }



}
