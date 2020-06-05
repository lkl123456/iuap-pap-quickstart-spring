package com.yonyou.iuap.refservice;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.yonyou.iuap.pap.base.common.exception.BusinessException;
import com.yonyou.iuap.pap.base.ref.entity.RefParamConfig;
import com.yonyou.iuap.pap.base.ref.service.base.impl.RefBaseCommonService;
import com.yonyou.iuap.pap.base.ref.service.base.itf.IRefBaseCommon;
import com.yonyou.iuap.pap.base.ref.service.base.itf.IRefBaseGrid;

/**
 * 转换字段
 */
@Service
public class BankaccRefServiceImpl implements IRefBaseGrid, IRefBaseCommon {

	@Autowired 
	private RefBaseCommonService refcommonService;

	@Override
	public Page<Map<String, Object>> pageBlobRefTreeGrid(PageRequest pageRequest, RefParamConfig refParamConfigTable,
			String content, List<String> fidFields, String fid, Set<String> accessableIds, String clientParam)
			throws BusinessException {
		Page<Map<String, Object>> ret = refcommonService.pageBlobRefTreeGrid(pageRequest, refParamConfigTable, content,
				fidFields, fid, accessableIds, clientParam);

		List<Map<String, Object>> list = ret.getContent();
		if (list != null && list.size() > 0) {
			Iterator iter = list.iterator();
			while (iter.hasNext()) {
				Map map = (Map) iter.next();
				if (map.get("acctype") != null) {
					Integer acctype = (Integer) map.get("acctype");
					map.put("acctype", convertType(acctype));
				}
			}
		}

		return ret;
	}

	private String convertType(Integer acctype) {
		String result = "";
		if (acctype.intValue() == 0) {
			result = "个人";
		} else {
			result = "集团";
		}
		return result;
	}

	@Override
	public Page<Map<String, Object>> filterRefJSON(PageRequest pageRequest, RefParamConfig refParamConfigTable,
			String content, Set<String> accessableIds, String clientParam) throws BusinessException {
		return refcommonService.filterRefJSON(pageRequest, refParamConfigTable, content, accessableIds, clientParam);
	}

	@Override
	public List<Map<String, Object>> matchPKRefJSON(RefParamConfig refParamConfigTable, String[] idAry,
			Set<String> accessableIds) throws BusinessException {
		return refcommonService.matchPKRefJSON(refParamConfigTable, idAry, accessableIds);
	}

	@Override
	public List<Map<String, Object>> getByIds(String tablename, String idfield, String codefield, String namefield,
			List<String> extColumns, List<String> ids) throws BusinessException {
		return refcommonService.getByIds(tablename, idfield, codefield, namefield, extColumns, ids);
	}

	@Override
	public List<Map<String, Object>> likeSearch(RefParamConfig refParamConfigTable, String tablename, String idfield,
			String codefield, String namefield, List<String> extColumns, List<String> ids, String keyword)
			throws BusinessException {
		return refcommonService.likeSearch(refParamConfigTable, tablename, idfield, codefield, namefield, extColumns, ids, keyword);
	}

	@Override
	public List<Map<String, Object>> findRefListByIds(RefParamConfig refParamConfigTable, List<String> ids) throws BusinessException {
		return refcommonService.findRefListByIds(refParamConfigTable, ids);
	}

}
