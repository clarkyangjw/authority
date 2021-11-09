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
import net.metaverseapp.bbs.forum.biz.service.BbsClassInfoService;
import net.metaverseapp.bbs.forum.biz.service.BbsReplyInfoService;
import net.metaverseapp.bbs.forum.biz.service.BbsTopicInfoService;
import net.metaverseapp.bbs.forum.dto.BbsClassDTO;
import net.metaverseapp.bbs.forum.dto.BbsReplyDTO;
import net.metaverseapp.bbs.forum.dto.BbsReplyUpdateDTO;
import net.metaverseapp.bbs.forum.entity.BbsClass;
import net.metaverseapp.bbs.forum.entity.BbsReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Validated
@RestController
@RequestMapping("/reply")
@Api(value = "BbsReplyInfo", tags = "回复信息")
public class BbsReplyController extends BaseController {

    @Autowired
    private BbsReplyInfoService BbsReplyInfoService;
    @Autowired
    private BbsTopicInfoService BbsTopicInfoService;
    @Autowired
    private DozerUtils dozer;


    @ApiOperation(value = "check a Reply by id", notes = "check a Reply by id")
    @GetMapping("/{id}")
    @SysLog("check a Reply by id")
    public R<BbsReply> get(@PathVariable Long id) {
        return success(BbsReplyInfoService.getById(id));
    }

    @ApiOperation(value = "view Replys by the topic id", notes = "view Replys by the topic id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("view comments page by page")
    public R<IPage<BbsReply>> view(BbsReply data) {
        IPage<BbsReply> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<BbsReply> query = Wraps.lbQ(data).orderByDesc(BbsReply::getUpdateTime);
        BbsReplyInfoService.page(page, query);
        return success(page);
    }

    @ApiOperation(value = "create a new reply", notes = "cannot be empty")
    @PostMapping
    @SysLog("new a reply")
    public R<BbsReply> save(@RequestBody @Validated BbsReplyDTO data) {
        BbsReply reply = dozer.map(data, BbsReply.class);
        BbsReplyInfoService.save(reply);
        return success(reply);
    }

    @ApiOperation(value = "update a reply", notes = "cannot be empty")
    @PutMapping
    @SysLog("update a reply")
    public R<BbsReply> update(@RequestBody @Validated BbsReplyUpdateDTO data) {
        BbsReply reply = dozer.map(data, BbsReply.class);
        BbsReplyInfoService.updateById(reply);
        return success(reply);
    }

    @ApiOperation(value = "delete a class", notes = "delete a class according to id")
    @DeleteMapping
    @SysLog("delete a class")
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        BbsReplyInfoService.removeByIds(ids);
        return success(true);
    }
}
