package net.metaverseapp.bbs.authority.controller.auth;

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
import net.metaverseapp.bbs.authority.biz.service.auth.RoleAuthorityService;
import net.metaverseapp.bbs.authority.biz.service.auth.RoleService;
import net.metaverseapp.bbs.authority.biz.service.auth.UserRoleService;
import net.metaverseapp.bbs.authority.dto.auth.*;
import net.metaverseapp.bbs.authority.entity.auth.Role;
import net.metaverseapp.bbs.authority.entity.auth.RoleAuthority;
import net.metaverseapp.bbs.authority.entity.auth.UserRole;
import net.metaverseapp.bbs.authority.enumeration.auth.AuthorizeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * controller
 * Role
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/role")
@Api(value = "Role", tags = "Role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleAuthorityService roleAuthorityService;
//    @Autowired
//    private RoleOrgService roleOrgService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private DozerUtils dozer;
    /**
     * Paging query role
     */
    @ApiOperation(value = "page", notes = "Paging query role")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("Paging query role")
    public R<IPage<Role>> page(RolePageDTO param) {
        IPage<Role> page = getPage();
        Role role = dozer.map(param, Role.class);
        // 构建值不为null的查询条件
        LbqWrapper<Role> query = Wraps.lbQ(role)
                .geHeader(Role::getCreateTime, param.getStartCreateTime())
                .leFooter(Role::getCreateTime, param.getEndCreateTime())
                .orderByDesc(Role::getId);
        roleService.page(page, query);
        return success(page);
    }

    /**
     * get role
     */
    @ApiOperation(value = "get", notes = "get role")
    @GetMapping("/{id}")
    @SysLog("get role")
    public R<RoleQueryDTO> get(@PathVariable Long id) {
        Role role = roleService.getById(id);

        RoleQueryDTO roleQueryDTO = dozer.map(role, RoleQueryDTO.class);
        return success(roleQueryDTO);
    }

    /**
     * add role
     */
    @ApiOperation(value = "save", notes = "add role")
    @PostMapping
    @SysLog("add role")
    public R<RoleSaveDTO> save(@RequestBody @Validated RoleSaveDTO data) {
        roleService.saveRole(data, getUserId());
        return success(data);
    }

    /**
     * update role
     */
    @ApiOperation(value = "update", notes = "update role")
    @PutMapping
    @SysLog("update role")
    public R<RoleUpdateDTO> update(@RequestBody @Validated(SuperEntity.Update.class) RoleUpdateDTO data) {
        roleService.updateRole(data, getUserId());
        return success(data);
    }

    /**
     * delete role
     */
    @ApiOperation(value = "delete", notes = "delete role physically")
    @DeleteMapping
    @SysLog("delete role")
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        roleService.removeById(ids);
        return success(true);
    }

    /**
     * save UserRole
     */
    @ApiOperation(value = "saveUserRole", notes = "save UserRole")
    @PostMapping("/user")
    @SysLog("save UserRole")
    public R<Boolean> saveUserRole(@RequestBody UserRoleSaveDTO userRole) {
        return success(roleAuthorityService.saveUserRole(userRole));
    }

    /**
     * find UserId By RoleId
     */
    @ApiOperation(value = "findUserIdByRoleId", notes = "find UserId By RoleId")
    @GetMapping("/user/{roleId}")
    @SysLog("find UserId By RoleId")
    public R<List<Long>> findUserIdByRoleId(@PathVariable Long roleId) {
        List<UserRole> list = userRoleService.list(Wraps.<UserRole>lbQ().eq(UserRole::getRoleId, roleId));
        return success(list.stream().mapToLong(UserRole::getUserId).boxed().collect(Collectors.toList()));
    }

    /**
     * find AuthorityId By RoleId
     */
    @ApiOperation(value = "findAuthorityIdByRoleId", notes = "find AuthorityId By RoleId")
    @GetMapping("/authority/{roleId}")
    @SysLog("find AuthorityId By RoleId")
    public R<RoleAuthoritySaveDTO> findAuthorityIdByRoleId(@PathVariable Long roleId) {
        List<RoleAuthority> list = roleAuthorityService.list(Wraps.<RoleAuthority>lbQ().eq(RoleAuthority::getRoleId, roleId));
        List<Long> menuIdList = list.stream().filter(item -> AuthorizeType.MENU.eq(item.getAuthorityType())).mapToLong(RoleAuthority::getAuthorityId).boxed().collect(Collectors.toList());
        List<Long> resourceIdList = list.stream().filter(item -> AuthorizeType.RESOURCE.eq(item.getAuthorityType())).mapToLong(RoleAuthority::getAuthorityId).boxed().collect(Collectors.toList());
        RoleAuthoritySaveDTO roleAuthority = RoleAuthoritySaveDTO.builder()
                .menuIdList(menuIdList).resourceIdList(resourceIdList)
                .build();
        return success(roleAuthority);
    }


    /**
     * assigning permission to role
     */
    @ApiOperation(value = "saveRoleAuthority", notes = "assigning permission to role")
    @PostMapping("/authority")
    @SysLog("assigning permission to role")
    public R<Boolean> saveRoleAuthority(@RequestBody RoleAuthoritySaveDTO roleAuthoritySaveDTO) {
        return success(roleAuthorityService.saveRoleAuthority(roleAuthoritySaveDTO));
    }


}
