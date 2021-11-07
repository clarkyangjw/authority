package net.metaverseapp.bbs.authority.controller.auth;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.pinda.base.BaseController;
import com.itheima.pinda.base.R;
import com.itheima.pinda.base.entity.SuperEntity;
import com.itheima.pinda.database.mybatis.conditions.Wraps;
import com.itheima.pinda.database.mybatis.conditions.query.LbqWrapper;
import com.itheima.pinda.dozer.DozerUtils;
import com.itheima.pinda.log.annotation.SysLog;
import com.itheima.pinda.user.feign.UserQuery;
import com.itheima.pinda.user.model.SysOrg;
import com.itheima.pinda.user.model.SysRole;
import com.itheima.pinda.user.model.SysStation;
import com.itheima.pinda.user.model.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.metaverseapp.bbs.authority.biz.service.auth.RoleService;
import net.metaverseapp.bbs.authority.biz.service.auth.UserService;
import net.metaverseapp.bbs.authority.dto.auth.*;
import net.metaverseapp.bbs.authority.entity.auth.Role;
import net.metaverseapp.bbs.authority.entity.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 前端控制器
 * 用户
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/user")
@Api(value = "User", tags = "User")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
//    @Autowired
//    private OrgService orgService;
    @Autowired
    private RoleService roleService;
//    @Autowired
//    private StationService stationService;
    @Autowired
    private DozerUtils dozer;
    /**
     * paging query users
     */
    @ApiOperation(value = "page", notes = "paging query users")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "分页条数", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("paging query users")
    public R<IPage<User>> page(UserPageDTO userPage) {
        IPage<User> page = getPage();

        User user = dozer.map2(userPage, User.class);
        LbqWrapper<User> wrapper = Wraps.lbQ(user);
        wrapper.geHeader(User::getCreateTime, userPage.getStartCreateTime())
                .leFooter(User::getCreateTime, userPage.getEndCreateTime())
                .like(User::getNickname, userPage.getNickname())
                .like(User::getUsername, userPage.getUsername())
                .like(User::getEmail, userPage.getEmail())
                .eq(User::getGender, userPage.getGender())
                .eq(User::getIsActive, userPage.getIsActive())
                .like(User::getPortraitUrl, userPage.getPortraitUrl())
                .orderByDesc(User::getId);
//        userService.page(page, wrapper);

        userService.findPage(page, wrapper);
        return success(page);
    }

    /**
     * Query user
     */
    @ApiOperation(value = "get user", notes = "get user")
    @GetMapping("/{id}")
    @SysLog("Query a user")
    public R<User> get(@PathVariable Long id) {
        return success(userService.getById(id));
    }

    @ApiOperation(value = "get user list", notes = "get user list")
    @GetMapping("/find")
    @SysLog("Query user list")
    public R<List<Long>> findAllUserId() {
        return success(userService.list().stream().mapToLong(User::getId).boxed().collect(Collectors.toList()));
    }

    /**
     * Add user
     */
    @ApiOperation(value = "Add user", notes = "Add user")
    @PostMapping
    @SysLog("Add user")
    public R<User> save(@RequestBody @Validated UserSaveDTO data) {
        User user = dozer.map(data, User.class);
        userService.saveUser(user);
        return success(user);
    }

    /**
     * Update user
     */
    @ApiOperation(value = "Update user", notes = "Update user")
    @PutMapping
    @SysLog("Update user")
    public R<User> update(@RequestBody @Validated(SuperEntity.Update.class) UserUpdateDTO data) {
        User user = dozer.map(data, User.class);
        userService.updateUser(user);
        return success(user);
    }

    @ApiOperation(value = "avatar", notes = "avatar")
    @PutMapping("/avatar")
    @SysLog("Update avatar")
    public R<User> avatar(@RequestBody @Validated(SuperEntity.Update.class) UserUpdateAvatarDTO data) {
        User user = dozer.map(data, User.class);
        userService.updateUser(user);
        return success(user);
    }

    @ApiOperation(value = "updatePassword", notes = "update password")
    @PutMapping("/password")
    @SysLog("update password")
    public R<Boolean> updatePassword(@RequestBody UserUpdatePasswordDTO data) {
        return success(userService.updatePassword(data));
    }

    @ApiOperation(value = "resetTx", notes = "reset password")
    @GetMapping("/reset")
    @SysLog("reset password")
    public R<Boolean> resetTx(@RequestParam("ids[]") List<Long> ids) {
        userService.reset(ids);
        return success();
    }

    /**
     * delete user
     */
    @ApiOperation(value = "deleteUser", notes = "Delete user physically")
    @DeleteMapping
    @SysLog("Delete user")
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        userService.remove(ids);
        return success(true);
    }


    /**
     * Single query user
     */
    @ApiOperation(value = "singleQueryUser", notes = "Single query user")
    @PostMapping(value = "/anno/id/{id}")
    public R<SysUser> getById(@PathVariable Long id, @RequestBody UserQuery query) {
        User user = userService.getById(id);
        if (user == null) {
            return success(null);
        }
        SysUser sysUser = dozer.map(user, SysUser.class);

        if (query.getFull() || query.getRoles()) {
            List<Role> list = roleService.findRoleByUserId(id);
            sysUser.setRoles(dozer.mapList(list, SysRole.class));
        }

        return success(sysUser);
    }

    /**
     * Query the users associated with a role
     * @param roleId  角色id
     * @param keyword 账号account或名称name
     */
    @ApiOperation(value = "findUserByRoleId", notes = "Query the users associated with a role")
    @GetMapping(value = "/role/{roleId}")
    public R<UserRoleDTO> findUserByRoleId(@PathVariable("roleId") Long roleId, @RequestParam(value = "keyword", required = false) String keyword) {
        List<User> list = userService.findUserByRoleId(roleId, keyword);
        List<Long> idList = list.stream().mapToLong(User::getId).boxed().collect(Collectors.toList());
        return success(UserRoleDTO.builder().idList(idList).userList(list).build());
    }

}
