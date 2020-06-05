package com.yonyou.iuap.purpayment.controller;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yonyou.iuap.base.web.BaseController;
import com.yonyou.iuap.mvc.constants.RequestStatusEnum;
import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.iuap.mvc.constants.RequestStatusEnum;
import com.yonyou.iuap.mvc.annotation.FrontModelExchange;
import com.yonyou.iuap.baseservice.persistence.support.CustomSelectListable;
import com.yonyou.iuap.baseservice.persistence.support.SimpleCustomSelectList;
import com.yonyou.iuap.baseservice.statistics.service.StatCommonService;
import com.yonyou.iuap.baseservice.intg.ext.EnumValueUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;

    import com.yonyou.iuap.common.utils.ExcelExportImportor;
    import org.springframework.ui.ModelMap;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.util.MultiValueMap;
    import org.springframework.web.multipart.MultipartFile;
    import org.springframework.web.multipart.MultipartHttpServletRequest;
    import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
            import com.yonyou.iuap.purpayment.service.Purpayment_detailService;
            import com.yonyou.iuap.purpayment.entity.Purpayment_detail;
        /**
        * 说明：采购付款明细 基础Controller——提供数据增、删、改、查等rest接口
        * 
        * @date 2019-8-28 16:46:53
        */
        @Api(value="采购付款明细接口",description = "采购付款明细基础Controller——提供数据增(CREATE)、删(DELETE、改(UPDATE)、查(READ)等rest接口")
        @Controller("com.yonyou.iuap.purpayment.controller.Purpayment_detailController")
        @RequestMapping(value="/purpayment/purpayment_detail")
        public class Purpayment_detailController extends BaseController{

        private Logger logger = LoggerFactory.getLogger(Purpayment_detailController.class);

        private Purpayment_detailService purpayment_detailService;

        @Autowired
        private StatCommonService statCommonService;

        @Autowired
        public void setPurpayment_detailService(Purpayment_detailService purpayment_detailService) {
            this.purpayment_detailService = purpayment_detailService;
        }

        /**
        * 分页动态查询
        * @param pageRequest 分页及排序参数
        * @param searchMap  查询条件
        * @return 分页结果集
        */
        @ApiOperation(value = "分页动态查询",httpMethod = "POST" ,tags = "READ", response = Purpayment_detail.class)
        @ApiResponse(response = Page.class, code = 200, message = "接口返回对象参数")
        @RequestMapping(value = "/list", method = RequestMethod.POST)
        @ResponseBody
        public Object list(PageRequest pageRequest, @RequestBody Map<String, Object> searchMap) {
            SearchParams searchParams = new SearchParams();
            searchParams.setSearchMap(searchMap);

            if (pageRequest.getPageSize() == 1) {
            Integer allCount = Integer.MAX_VALUE-1;
            pageRequest = new PageRequest(pageRequest.getPageNumber(), allCount, pageRequest.getSort());
            }

            Page<Map> page =  this.statCommonService.selectFieldsByPage(pageRequest, searchParams, "com.yonyou.iuap.purpayment.entity.Purpayment_detail");
            EnumValueUtils.i18nEnumMapKeyToValue(page.getContent(),Purpayment_detail.class);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data", page);
            return this.buildMapSuccess(map);
        }


        /**
        * 单条添加
        * @param entity 业务实体
        * @return 标准JsonResponse结构
        */
        @ApiOperation( value = "单条添加",tags = "CREATE", response = Purpayment_detail.class)
        @RequestMapping(value = "/insertSelective"  ,method = {RequestMethod.POST,RequestMethod.PATCH})
        @ResponseBody
        public Object insertSelective(@RequestBody Purpayment_detail entity) {
            try {
                entity = this.purpayment_detailService.insertSelective(entity);
                Purpayment_detail purpayment_detail = this.purpayment_detailService.findById(entity.getId());
                return this.buildSuccess(purpayment_detail);
            } catch (Exception exp) {
                return this.buildError("msg", exp.getMessage(), RequestStatusEnum.FAIL_FIELD);
            }
        }

        /**
        * 单条更新
        * @param entity 业务实体
        * @return 标准JsonResponse结构
        */
        @ApiOperation( value = "单条更新" ,tags = "UPDATE", response = Purpayment_detail.class)
        @RequestMapping(value = "/updateSelective" ,method = {RequestMethod.POST,RequestMethod.PATCH})
        @ResponseBody
        public Object updateSelective(@RequestBody Purpayment_detail entity) {
            try {
                entity = this.purpayment_detailService.updateSelective(entity);
                Purpayment_detail purpayment_detail = this.purpayment_detailService.findById(entity.getId());
                return this.buildSuccess(purpayment_detail);
            } catch (Exception exp) {
                return this.buildError("msg", exp.getMessage(), RequestStatusEnum.FAIL_FIELD);
            }
        }

        /**
        * 删除数据
        * @param listData
        * @param request
        * @param response
        * @return
        * @throws Exception
        */
        @ApiOperation(value = "删除数据",httpMethod = "POST",tags = "DELETE", response = Purpayment_detail.class)
        @RequestMapping(value = "/deleteBatch")
        @ResponseBody
        public Object deleteBatch(@RequestBody List<Purpayment_detail> listData, HttpServletRequest request, HttpServletResponse response) throws Exception {
            this.purpayment_detailService.deleteBatch(listData);
            return super.buildSuccess();
        }

        }
