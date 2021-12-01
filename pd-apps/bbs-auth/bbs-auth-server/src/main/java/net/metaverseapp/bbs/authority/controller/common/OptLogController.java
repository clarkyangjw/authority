package net.metaverseapp.bbs.authority.controller.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.pinda.base.BaseController;
import com.itheima.pinda.base.R;
import com.itheima.pinda.database.mybatis.conditions.Wraps;
import com.itheima.pinda.database.mybatis.conditions.query.LbqWrapper;
import com.itheima.pinda.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.metaverseapp.bbs.authority.biz.service.common.OptLogService;
import net.metaverseapp.bbs.authority.entity.common.OptLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前端控制器
 * OptLogController
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/optLog")
@Api(value = "OptLog", tags = "OptLogController")
public class OptLogController extends BaseController {
    @Autowired
    private OptLogService optLogService;
    /**
     * Paging query system operation logs
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @ApiOperation(value = "page", notes = "Paging query system operation logs")
    @GetMapping("/page")
    public R<IPage<OptLog>> page(OptLog data) {
        IPage<OptLog> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<OptLog> query = Wraps.lbQ(data)
                .leFooter(OptLog::getCreateTime, getEndCreateTime())
                .geHeader(OptLog::getCreateTime, getStartCreateTime())
                .orderByDesc(OptLog::getId);
        optLogService.page(page, query);
        return success(page);
    }

    /**
     * Query system operation logs
     */
    @ApiOperation(value = "get", notes = "Query system operation logs")
    @GetMapping("/{id}")
    public R<OptLog> get(@PathVariable Long id) {
        return success(optLogService.getById(id));
    }

    /**
     * Delete system operation logs
     */
    @ApiOperation(value = "delete", notes = "Delete system operation logs physically")
    @DeleteMapping
    @SysLog("Delete system operation logs")
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        optLogService.removeByIds(ids);
        return success(true);
    }
}
