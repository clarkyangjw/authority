package net.metaverseapp.bbs.forum.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.pinda.base.BaseController;
import com.itheima.pinda.base.R;

import com.itheima.pinda.base.entity.SuperEntity;
import com.itheima.pinda.database.mybatis.conditions.Wraps;
import com.itheima.pinda.database.mybatis.conditions.query.LbqWrapper;
import com.itheima.pinda.dozer.DozerUtils;
import com.itheima.pinda.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.metaverseapp.bbs.forum.biz.service.BbsClassInfoService;
import net.metaverseapp.bbs.forum.dto.BbsClassDTO;
import net.metaverseapp.bbs.forum.dto.BbsClassUpdateDTO;
import net.metaverseapp.bbs.forum.dto.BbsTopicUpdateDTO;
import net.metaverseapp.bbs.forum.entity.BbsClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Validated
@RestController
@RequestMapping("/class")
@Api(value = "BbsClassInfo", tags = "主板信息")
public class BbsClassController extends BaseController {

    @Autowired
    private BbsClassInfoService classService;
    @Autowired
    private DozerUtils dozer;



    @ApiOperation(value = "check a class by id", notes = "check a class by id")
    @GetMapping("/{id}")
    @SysLog("check a class by id")
    public R<BbsClass> get(@PathVariable Long id) {
        return success(classService.getById(id));
    }


    @ApiOperation(value = "view classes by page", notes = "view classes by page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询菜单")
    public R<IPage<BbsClass>> view(BbsClass data) {
        IPage<BbsClass> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<BbsClass> query = Wraps.lbQ(data).orderByDesc(BbsClass::getUpdateTime);
        classService.page(page, query);
        return success(page);
    }

    @ApiOperation(value = "create a new class", notes = "cannot be empty")
    @PostMapping
    @SysLog("new a class")
    public R<BbsClass> save(@RequestBody @Validated BbsClassDTO data) {
        BbsClass menu = dozer.map(data, BbsClass.class);
        classService.save(menu);
        return success(menu);
    }

    @ApiOperation(value = "update a class", notes = "cannot be empty")
    @PutMapping
    @SysLog("update a class")
    public R<BbsClass> update(@RequestBody @Validated BbsClassUpdateDTO data) {
        BbsClass bbsClass = dozer.map(data, BbsClass.class);
        classService.updateById(bbsClass);
        return success(bbsClass);
    }

    @ApiOperation(value = "delete a class", notes = "delete a class according to id")
    @DeleteMapping
    @SysLog("delete a class")
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        classService.removeByIds(ids);
        return success(true);
    }






}
