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
import net.metaverseapp.bbs.forum.biz.service.BbsSubjectInfoService;
import net.metaverseapp.bbs.forum.biz.service.BbsTopicInfoService;
import net.metaverseapp.bbs.forum.dto.BbsSubjectDTO;
import net.metaverseapp.bbs.forum.dto.BbsTopicDTO;
import net.metaverseapp.bbs.forum.dto.BbsTopicUpdateDTO;
import net.metaverseapp.bbs.forum.entity.BbsSubject;
import net.metaverseapp.bbs.forum.entity.BbsTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/BbsTopicInfo")
@Api(value = "BbsTopicInfo", tags = "帖子信息")
public class BbsTopicController extends BaseController {

    @Autowired
    private BbsTopicInfoService BbsTopicInfoService;
    @Autowired
    private DozerUtils dozer;


    @ApiOperation(value = "check a topic by id", notes = "check a topic by id")
    @GetMapping("/{id}")
    @SysLog("check a StateType by id")
    public R<BbsTopic> get(@PathVariable Long id) {
        return success(BbsTopicInfoService.getById(id));
    }

    @ApiOperation(value = "view topic by the id", notes = "view topic by the id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("view topic by page")
    public R<IPage<BbsTopic>> view(BbsTopic data) {
        IPage<BbsTopic> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<BbsTopic> query = Wraps.lbQ(data).orderByDesc(BbsTopic::getUpdateTime);
        BbsTopicInfoService.page(page, query);
        return success(page);
    }

    @ApiOperation(value = "create a topic", notes = "cannot be empty")
    @PostMapping
    @SysLog("new a topic")
    public R<BbsTopic> save(@RequestBody @Validated BbsTopicDTO data) {
        BbsTopic reply = dozer.map(data, BbsTopic.class);
        BbsTopicInfoService.save(reply);
        return success(reply);
    }

    @ApiOperation(value = "update a topic", notes = "cannot be empty")
    @PutMapping
    @SysLog("update a topic")
    public R<BbsTopic> update(@RequestBody @Validated BbsTopicUpdateDTO data) {
        BbsTopic reply = dozer.map(data, BbsTopic.class);
        BbsTopicInfoService.updateById(reply);
        return success(reply);
    }

    @ApiOperation(value = "delete a subject", notes = "delete a subject according to id")
    @DeleteMapping
    @SysLog("delete a subject")
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        BbsTopicInfoService.removeByIds(ids);
        return success(true);
    }

}
