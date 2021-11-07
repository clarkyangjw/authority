package net.metaverseapp.bbs.authority.controller.auth;

import com.itheima.pinda.base.BaseController;
import com.itheima.pinda.base.R;
import com.itheima.pinda.database.mybatis.conditions.Wraps;
import com.itheima.pinda.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.metaverseapp.bbs.authority.biz.service.auth.RoleAuthorityService;
import net.metaverseapp.bbs.authority.entity.auth.RoleAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 前端控制器
 * RoleAuthorityController
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/roleAuthority")
@Api(value = "RoleAuthority", tags = "RoleAuthority")
public class RoleAuthorityController extends BaseController {
    @Autowired
    private RoleAuthorityService roleAuthorityService;
    /**
     * Query the menus and resources associated with a specified role
     */
    @ApiOperation(value = "page", notes = "Query the menus and resources associated with a specified role")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/{roleId}")
    @SysLog("Query the menus and resources associated with a specified role")
    public R<List<RoleAuthority>> page(@PathVariable Long roleId) {
        return success(roleAuthorityService.list(Wraps.<RoleAuthority>lbQ().eq(RoleAuthority::getRoleId, roleId)));
    }
}
