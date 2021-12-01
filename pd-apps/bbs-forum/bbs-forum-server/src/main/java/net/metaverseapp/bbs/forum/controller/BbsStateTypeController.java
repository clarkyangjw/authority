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
import net.metaverseapp.bbs.forum.biz.service.BbsReplyInfoService;
import net.metaverseapp.bbs.forum.biz.service.BbsStateTypeInfoService;
import net.metaverseapp.bbs.forum.dto.BbsReplyDTO;
import net.metaverseapp.bbs.forum.dto.BbsStateTypeDTO;
import net.metaverseapp.bbs.forum.dto.BbsStateTypeUpdateDTO;
import net.metaverseapp.bbs.forum.entity.BbsReply;
import net.metaverseapp.bbs.forum.entity.BbsStateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/stateType")
@Api(value = "BbsStateTypeInfo", tags = "帖子状态信息")
public class BbsStateTypeController extends BaseController {


    @Autowired
    private BbsStateTypeInfoService BbsStateTypeInfoService;
    @Autowired
    private DozerUtils dozer;

    @ApiOperation(value = "check a StateType by id", notes = "check a StateType by id")
    @GetMapping("/{id}")
    @SysLog("check a StateType by id")
    public R<BbsStateType> get(@PathVariable Long id) {
        return success(BbsStateTypeInfoService.getById(id));
    }


    @ApiOperation(value = "view state type by the id", notes = "view  state type by the id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("view state type by page")
    public R<IPage<BbsStateType>> view(BbsStateType data) {
        IPage<BbsStateType> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<BbsStateType> query = Wraps.lbQ(data).orderByDesc(BbsStateType::getUpdateTime);
        BbsStateTypeInfoService.page(page, query);
        return success(page);
    }

    @ApiOperation(value = "create a new state type", notes = "cannot be empty")
    @PostMapping
    @SysLog("new a state type")
    public R<BbsStateType> save(@RequestBody @Validated BbsStateTypeDTO data) {
        BbsStateType reply = dozer.map(data, BbsStateType.class);
        BbsStateTypeInfoService.save(reply);
        return success(reply);
    }

    @ApiOperation(value = "update a state type", notes = "cannot be empty")
    @PutMapping
    @SysLog("update a state type")
    public R<BbsStateType> update(@RequestBody @Validated BbsStateTypeUpdateDTO data) {
        BbsStateType reply = dozer.map(data, BbsStateType.class);
        BbsStateTypeInfoService.updateById(reply);
        return success(reply);
    }

    @ApiOperation(value = "delete a state type", notes = "delete a state type according to id")
    @DeleteMapping
    @SysLog("delete a state type")
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        BbsStateTypeInfoService.removeByIds(ids);
        return success(true);
    }
}
