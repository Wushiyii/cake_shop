package com.jesse.onecake.controller.base;


import com.jesse.onecake.biz.base.BaseBiz;
import com.jesse.onecake.common.response.BaseResponse;
import com.jesse.onecake.common.utils.BaseContextHandler;
import com.jesse.onecake.common.utils.StringEscapeEditor;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BaseController<Biz extends BaseBiz,Entity> {
    @Autowired
    protected Biz biz;
    @Autowired
    protected HttpServletRequest request;

    public BaseController() {
    }
    @InitBinder
    public void InitBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class,new StringEscapeEditor());
        binder.registerCustomEditor(String[].class,new StringEscapeEditor());
    }

    @ApiOperation("新增一个实体")
    @ApiImplicitParam(
            name = "entity",
            value = "要添加的实体对象",
            required = true,
            dataType = "Entity"
    )
    @RequestMapping(
            value = {""},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public BaseResponse<Entity> add(@RequestBody Entity entity) {
        this.biz.insertSelective(entity);
        return new BaseResponse().withData(entity);
    }

    @ApiOperation("获取一个实体")
    @ApiImplicitParam(
            name = "id",
            value = "要获取的对象id",
            required = true,
            dataType = "long"
    )
    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public BaseResponse<Entity> get(@PathVariable Object id) {
        BaseResponse<Entity> entityObjectRestResponse = new BaseResponse();
        Object o = this.biz.selectById(id);
        entityObjectRestResponse.withData(o);
        return entityObjectRestResponse;
    }

    @ApiOperation("修改一个实体")
    @ApiImplicitParam(
            name = "entity",
            value = "要修改的实体对象",
            required = true,
            dataType = "Entity"
    )
    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.PUT}
    )
    @ResponseBody
    public BaseResponse<Entity> update(@RequestBody Entity entity) {
        this.biz.updateSelectiveById(entity);
        return new BaseResponse().withData(entity);
    }

    @ApiOperation("删除一个实体")
    @ApiImplicitParam(
            name = "id",
            value = "要删除的实体对象",
            required = true,
            dataType = "Object"
    )
    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.DELETE}
    )
    @ResponseBody
    public BaseResponse<Entity> remove(@PathVariable Object id) {
        this.biz.deleteById(id);
        return new BaseResponse();
    }

    @ApiOperation("获取所有数据")
    @RequestMapping(
            value = {"/all"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public List<Entity> all() {
        return this.biz.selectAll();
    }

    @ApiOperation("分页获取数据")
    @ApiImplicitParam(
            name = "params",
            value = "查询参数",
            required = true,
            dataType = "Map<String, Object>"
    )
    @RequestMapping(
            value = {"/page"},
            method = {RequestMethod.GET}
    )

    public String getCurrentUserName() {
        return BaseContextHandler.getUsername();
    }

    public String getCurrentUserId() {
        return BaseContextHandler.getUserID();
    }
}
