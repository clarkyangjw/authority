package net.metaverseapp.bbs.authority.controller.common;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.pinda.base.BaseController;
import com.itheima.pinda.base.R;
import com.itheima.pinda.database.mybatis.conditions.Wraps;
import com.itheima.pinda.database.mybatis.conditions.query.LbqWrapper;
import com.itheima.pinda.log.annotation.SysLog;
import com.itheima.pinda.log.util.AddressUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.metaverseapp.bbs.authority.biz.service.auth.UserService;
import net.metaverseapp.bbs.authority.biz.service.common.LoginLogService;
import net.metaverseapp.bbs.authority.entity.common.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 前端控制器
 * 登录日志
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/loginLog")
@Api(value = "LoginLog", tags = "LoginLog")
public class LoginLogController extends BaseController {
    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private UserService userService;
    /**
     * paging query login log
     */
    @ApiOperation(value = "page", notes = "paging query login log")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    public R<IPage<LoginLog>> page(LoginLog data) {
        IPage<LoginLog> page = this.getPage();
        // 构建值不为null的查询条件
        LbqWrapper<LoginLog> query = Wraps.<LoginLog>lbQ()
                .eq(LoginLog::getUserUsername, data.getUserUsername())
                .likeRight(LoginLog::getUserUsername, data.getUserUsername())
                .likeRight(LoginLog::getRequestIp, data.getRequestIp())
                .like(LoginLog::getLocation, data.getLocation())
                .leFooter(LoginLog::getCreateTime, this.getEndCreateTime())
                .geHeader(LoginLog::getCreateTime, this.getStartCreateTime())
                .orderByDesc(LoginLog::getId);
        this.loginLogService.page(page, query);
        return this.success(page);
    }

    /**
     * query login log
     */
    @ApiOperation(value = "get", notes = "query login log")
    @GetMapping("/{id}")
    public R<LoginLog> get(@PathVariable Long id) {
        return this.success(this.loginLogService.getById(id));
    }

    /**
     * add login log
     */
    @ApiOperation(value = "save", notes = "add login log")
    @GetMapping("/anno/login/{account}")
    public R<LoginLog> save(@NotBlank(message = "用户名不能为为空") @PathVariable String userUsername, @RequestParam(required = false, defaultValue = "登陆成功") String description) {
        String ua = StrUtil.sub(this.request.getHeader("user-agent"), 0, 500);
        String ip = ServletUtil.getClientIP(this.request);
        String location = AddressUtil.getRegion(ip);
        // update last login time
        this.userService.updateLoginTime(userUsername);
        LoginLog loginLog = this.loginLogService.save(userUsername, ua, ip, location, description);
        return this.success(loginLog);
    }

    /**
     * delete login log
     */
    @ApiOperation(value = "delete", notes = "delete login log physically")
    @DeleteMapping
    @SysLog("delete login log")
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        this.loginLogService.removeByIds(ids);
        return this.success(true);
    }
}
