package com.yonyou.iuap.purpayment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yonyou.iuap.baseservice.service.util.RestUtils;
import com.yonyou.iuap.cache.CacheManager;
import com.yonyou.iuap.utils.PropertyUtil;

@Service
public class PurpaymentRefQueryService {

	@Autowired
	private CacheManager cache;
	
	private static String CURRTYPE_KEY="CURRTYPE_MDM_KEY";
	
	public List<Map> afterListQuery(List<Map> list) {
		if(list==null||list.size()==0)
			return list;
		
		Object mdmCache = cache.get(CURRTYPE_KEY);
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		if(mdmCache!=null) {
			map = (HashMap<String, String>) mdmCache;
		}else {
			map = queryCurrtypemdm(map);
			cache.set(CURRTYPE_KEY, map);
		}
		
		for (Map paymap : list) {
			String currtype = paymap.get("currtype")==null?"":paymap.get("currtype").toString();
			paymap.put("currtypename", map.get(currtype));
		}
		return list;
	}

	private HashMap<String, String> queryCurrtypemdm(HashMap<String, String> map) {	
		String modelUrl = PropertyUtil.getPropertyByKey("iuaprmodel.url")+"/modeling/data/query?tableName=currtype";
		JSONObject currtypeInfo = RestUtils.getInstance().doGet(modelUrl, null, JSONObject.class);
		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>) currtypeInfo.get("data");
		
		for (Map curmap : list) {
			if(map.get(curmap.get("code"))==null) {
				map.put(curmap.get("mdm_code").toString(),curmap.get("name").toString());
			}
		}
		return map;
	}

}
