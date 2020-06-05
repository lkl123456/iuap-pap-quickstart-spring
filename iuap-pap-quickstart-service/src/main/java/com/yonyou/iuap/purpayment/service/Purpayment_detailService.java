package com.yonyou.iuap.purpayment.service;
import com.yonyou.iuap.baseservice.intg.service.GenericIntegrateService;
import com.yonyou.iuap.baseservice.intg.support.ServiceFeature;
       
import com.yonyou.iuap.purpayment.dao.Purpayment_detailMapper;
import com.yonyou.iuap.purpayment.entity.Purpayment_detail;
          
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.yonyou.uap.busilog.annotation.LogConfig;
import java.util.List;
import static com.yonyou.iuap.baseservice.intg.support.ServiceFeature.*;

    
@Service("com.yonyou.iuap.purpayment.service.Purpayment_detailService")
/**
 * Purpayment_detail CRUD 核心服务,提供逻辑删除/乐观锁
 */
public class Purpayment_detailService extends  GenericIntegrateService<Purpayment_detail>{

    private Purpayment_detailMapper purpayment_detailMapper;
    @Autowired
    public void setPurpayment_detailMapper(Purpayment_detailMapper purpayment_detailMapper) {

        this.purpayment_detailMapper = purpayment_detailMapper;
        super.setGenericMapper(purpayment_detailMapper);
    }

    @Override
    @LogConfig(busiCode = "purpayment_detail_deleteBatch", busiName = "采购付款明细", operation = "采购付款明细删除", templateId = "purpayment_detail_deleteBatch")
    public int deleteBatch(List< Purpayment_detail> list) {
    return super.deleteBatch(list);
    }

    @Override
    @LogConfig(busiCode = "purpayment_detail_insertSelective", busiName = "采购付款明细", operation = "采购付款明细添加", templateId = "purpayment_detail_insertSelective")
    public  Purpayment_detail insertSelective( Purpayment_detail entity) {
    return super.insertSelective(entity);
    }

    @Override
    @LogConfig(busiCode = "purpayment_detail_updateSelective", busiName = "采购付款明细", operation = "采购付款明细修改", templateId = "purpayment_detail_updateSelective")
    public  Purpayment_detail updateSelective( Purpayment_detail entity) {
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