package com.yonyou.iuap.allowances.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yonyou.iuap.CSRFToken;
import com.yonyou.iuap.allowances.entity.Allowances;
import com.yonyou.iuap.allowances.service.ExternalAllowancesService;
import com.yonyou.iuap.base.web.BaseController;
import com.yonyou.iuap.baseservice.statistics.service.StatCommonService;
import com.yonyou.iuap.baseservice.support.exception.CodingException;
import com.yonyou.iuap.mvc.constants.RequestStatusEnum;
import com.yonyou.iuap.pap.base.i18n.MessageSourceUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 说明：员工津贴记录 基础Controller——提供数据增、删、改、查、导入导出等rest接口
 * 
 * @date 2018-10-9 16:44:58
 */
@Controller
@RequestMapping(value = "/inline_allowances")
@Api(tags="测试client服务远程调用单表模态框数据")
public class InlineAllowancesController extends BaseController {
	
	//多语常量
	private static final String KEY1 = "ja.all.con1.0001";
	private static final String MSG1 = "查询数据异常！";
	private static final String KEY2 = "ja.all.con1.0002";
	private static final String MSG2 = "新增数据异常！";
	private static final String KEY11 = "ja.all.con1.0011";
	private static final String MSG11 = "按编码规则生成编码出错，原因：获取后编码失败";
	private static final String KEY3 = "ja.all.con1.0003";
	private static final String MSG3 = "修改数据异常！";
	private static final String KEY4 = "ja.all.con1.0004";
	private static final String MSG4 = "删除数据异常！";
	private static final String KEY5 = "ja.all.con1.0005";
	private static final String MSG5 = "Excel模板下载失败！";
	private static final String KEY6 = "ja.all.con1.0006";
	private static final String MSG6 = "Excel模板下載成功！";
	private static final String KEY7 = "ja.all.con1.0007";
	private static final String MSG7 = "导入文件格式异常！";
	private static final String KEY8 = "ja.all.con1.0008";
	private static final String MSG8 = "导入数据异常！";
	private static final String KEY9 = "ja.all.con1.0009";
	private static final String MSG9 = "Excel导入失败！";
	private static final String KEY10 = "ja.all.con1.0010";
	private static final String MSG10 = "Excel导入成功！";
	private static final String NAME = "name";
	private static final String KEY = "ja.all.con.00001";
	private static final String MESSAGE = "名称不能为空！";
	private static final String MODELCODE = "Allowances";
	
	private Logger logger = LoggerFactory.getLogger(InlineAllowancesController.class);
	
	//注入远程service
	private ExternalAllowancesService allowancesService;
	
	@Autowired
	private StatCommonService statCommonService;
	
	/**
	 * client端调用远程服务
	 * @param listData
	 * @return
	 */
	@CSRFToken
	@RequestMapping(value = "/saveMultiple", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "保存单据",httpMethod="POST")
	public Object saveMultiple(@RequestBody List<Allowances> listData) {
		try {
			/**
			 * 处理自身业务后，需要调用远程服务
			 */
			this.allowancesService.saveMultiple(listData);
			return this.buildSuccess();
		} catch (CodingException e) {
			logger.error(e.getMessage(), e);
			return this.buildError("msg", e.getMessage(), RequestStatusEnum.FAIL_FIELD);
		} catch (Exception e) {
			logger.error(MessageSourceUtil.getMessage(KEY1, MSG1), e);
			return this.buildError("msg", MessageSourceUtil.getMessage(KEY1, MSG1), RequestStatusEnum.FAIL_FIELD);
		}
	}
	
}
