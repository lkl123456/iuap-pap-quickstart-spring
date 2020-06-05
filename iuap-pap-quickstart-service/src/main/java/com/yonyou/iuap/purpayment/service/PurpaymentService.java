package com.yonyou.iuap.purpayment.service;
import com.yonyou.iuap.baseservice.intg.service.GenericIntegrateService;
import com.yonyou.iuap.baseservice.intg.support.ServiceFeature;
import com.yonyou.iuap.purpayment.dao.PurpaymentMapper;
import com.yonyou.iuap.purpayment.entity.Purpayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.yonyou.uap.busilog.annotation.LogConfig;
import java.util.List;
import static com.yonyou.iuap.baseservice.intg.support.ServiceFeature.*;

@Service("com.yonyou.iuap.purpayment.service.PurpaymentService")
/**
 * Purpayment CRUD 核心服务,提供逻辑删除/乐观锁
 */
public class PurpaymentService extends GenericIntegrateService<Purpayment>{


    private PurpaymentMapper purpaymentMapper;

        @Autowired
        public void setPurpaymentMapper(PurpaymentMapper purpaymentMapper) {
                this.purpaymentMapper = purpaymentMapper;
                super.setGenericMapper(purpaymentMapper);
        }


    @Override
    @LogConfig(busiCode = "purpayment_deleteBatch", busiName = "采购付款单", operation = "采购付款单删除", templateId = "purpayment_deleteBatch")
    public int deleteBatch(List< Purpayment> list) {
    return super.deleteBatch(list);
    }

    @Override
    @LogConfig(busiCode = "purpayment_insertSelective", busiName = "采购付款单", operation = "采购付款单添加", templateId = "purpayment_insertSelective")
    public  Purpayment insertSelective( Purpayment entity) {
    return super.insertSelective(entity);
    }

    @Override
    @LogConfig(busiCode = "purpayment_updateSelective", busiName = "采购付款单", operation = "采购付款单修改", templateId = "purpayment_updateSelective")
    public  Purpayment updateSelective( Purpayment entity) {
    return super.updateSelective(entity);
    }




    /**
     * @CAU 可插拔设计
     * @return 向父类 GenericIntegrateService 提供可插拔的特性声明
     */
    @Override
    protected ServiceFeature[] getFeats() {
        return new ServiceFeature[]{ I18N_ENUM,UNION_REFERENCE,LOGICAL_DEL,BPM,MULTI_TENANT };
    }
}