package net.metaverseapp.bbs.forum.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.pinda.base.BaseController;
import com.itheima.pinda.base.R;
import com.itheima.pinda.database.mybatis.conditions.Wraps;
import com.itheima.pinda.database.mybatis.conditions.query.LbqWrapper;
import com.itheima.pinda.dozer.DozerUtils;
import com.itheima.pinda.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.metaverseapp.bbs.forum.biz.service.BbsStateTypeInfoService;
import net.metaverseapp.bbs.forum.biz.service.BbsSubjectInfoService;
import net.metaverseapp.bbs.forum.dto.BbsStateTypeDTO;
import net.metaverseapp.bbs.forum.dto.BbsSubjectDTO;
import net.metaverseapp.bbs.forum.dto.BbsSubjectUpdateDTO;
import net.metaverseapp.bbs.forum.entity.BbsStateType;
import net.metaverseapp.bbs.forum.entity.BbsSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/BbsSubjectInfo")
@Api(value = "BbsSubjectInfo", tags = "板块分类信息")
public class BbsSubjectController extends BaseController {

    @Autowired
    private BbsSubjectInfoService BbsSubjectInfoService;
    @Autowired
    private DozerUtils dozer;


    @ApiOperation(value = "check a Subject by id", notes = "check a Subject by id")
    @GetMapping("/{id}")
    @SysLog("check a StateType by id")
    public R<BbsSubject> get(@PathVariable Long id) {
        return success(BbsSubjectInfoService.getById(id));
    }

    @ApiOperation(value = "view subject by the id", notes = "view  state type by the topic id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("view subject by page")
    public R<IPage<BbsSubject>> view(BbsSubject data) {
        IPage<BbsSubject> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<BbsSubject> query = Wraps.lbQ(data).orderByDesc(BbsSubject::getUpdateTime);
        BbsSubjectInfoService.page(page, query);
        return success(page);
    }

    @ApiOperation(value = "create a subject", notes = "cannot be empty")
    @PostMapping
    @SysLog("new a subject")
    public R<BbsSubject> save(@RequestBody @Validated BbsSubjectDTO data) {
        BbsSubject reply = dozer.map(data, BbsSubject.class);
        BbsSubjectInfoService.save(reply);
        return success(reply);
    }

    @ApiOperation(value = "update a subject", notes = "cannot be empty")
    @PutMapping
    @SysLog("update a subject")
    public R<BbsSubject> update(@RequestBody @Validated BbsSubjectUpdateDTO data) {
        BbsSubject reply = dozer.map(data, BbsSubject.class);
        BbsSubjectInfoService.updateById(reply);
        return success(reply);
    }

    @ApiOperation(value = "delete a subject", notes = "delete a subject according to id")
    @DeleteMapping
    @SysLog("delete a subject")
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        BbsSubjectInfoService.removeByIds(ids);
        return success(true);
    }

}
