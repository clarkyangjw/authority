package net.metaverseapp.bbs.authority.controller.auth;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.pinda.auth.server.utils.JwtTokenServerUtils;
import com.itheima.pinda.auth.utils.JwtUserInfo;
import com.itheima.pinda.base.BaseController;
import com.itheima.pinda.base.R;
import com.itheima.pinda.base.entity.SuperEntity;
import com.itheima.pinda.database.mybatis.conditions.Wraps;
import com.itheima.pinda.database.mybatis.conditions.query.LbqWrapper;
import com.itheima.pinda.dozer.DozerUtils;
import com.itheima.pinda.log.annotation.SysLog;
import com.itheima.pinda.utils.TreeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.metaverseapp.bbs.authority.biz.service.auth.MenuService;
import net.metaverseapp.bbs.authority.dto.auth.MenuSaveDTO;
import net.metaverseapp.bbs.authority.dto.auth.MenuTreeDTO;
import net.metaverseapp.bbs.authority.dto.auth.MenuUpdateDTO;
import net.metaverseapp.bbs.authority.entity.auth.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 前端控制器
 * Menu
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/menu")
@Api(value = "Menu", tags = "Menu")
public class MenuController extends BaseController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private DozerUtils dozer;
    /**
     * paging query menu
     */
    @ApiOperation(value = "page", notes = "paging query menu")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("paging query menu")
    public R<IPage<Menu>> page(Menu data) {
        IPage<Menu> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<Menu> query = Wraps.lbQ(data).orderByDesc(Menu::getUpdateTime);
        menuService.page(page, query);
        return success(page);
    }

    /**
     * query menu
     */
    @ApiOperation(value = "get", notes = "query menu")
    @GetMapping("/{id}")
    @SysLog("query menu")
    public R<Menu> get(@PathVariable Long id) {
        return success(menuService.getById(id));
    }

    /**
     * add menu
     */
    @ApiOperation(value = "save", notes = "add menu")
    @PostMapping
    @SysLog("add menu")
    public R<Menu> save(@RequestBody @Validated MenuSaveDTO data) {
        Menu menu = dozer.map(data, Menu.class);
        menuService.save(menu);
        return success(menu);
    }

    /**
     * update menu
     */
    @ApiOperation(value = "update", notes = "update menu")
    @PutMapping
    @SysLog("update menu")
    public R<Menu> update(@RequestBody @Validated(SuperEntity.Update.class) MenuUpdateDTO data) {
        Menu menu = dozer.map(data, Menu.class);
        menuService.updateById(menu);
        return success(menu);
    }

    /**
     * delete menu
     */
    @ApiOperation(value = "delete", notes = "delete menu")
    @DeleteMapping
    @SysLog("delete menu")
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        menuService.removeByIds(ids);
        return success(true);
    }

    /**
     * Queries all menus available to the user
     * @param group  菜单分组
     * @param userId 指定用户id
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "group", value = "菜单组", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "long", paramType = "query"),
    })
    @ApiOperation(value = "myMenus", notes = "Queries all menus available to the user")
    @GetMapping
    @Deprecated
    public R<List<MenuTreeDTO>> myMenus(@RequestParam(value = "group", required = false) String group,
                                        @RequestParam(value = "userId", required = false) Long userId) {
        if (userId == null || userId <= 0) {
            userId = getUserId();
        }
        List<Menu> list = menuService.findVisibleMenu(group, userId);
        List<MenuTreeDTO> treeList = dozer.mapList(list, MenuTreeDTO.class);

        List<MenuTreeDTO> tree = TreeUtil.build(treeList);
        return success(tree);
    }

    @Autowired
    private JwtTokenServerUtils jwtTokenServerUtils;

    /**
     * 查询系统中所有的的菜单树结构， 不用缓存，因为该接口很少会使用，就算使用，也会管理员维护菜单时使用
     */
    @ApiOperation(value = "allTree", notes = "Query all menus in the tree module")
    @GetMapping("/tree")
    @SysLog("Query all menus in the tree module")
    public R<List<MenuTreeDTO>> allTree() {
        List<Menu> list = menuService.list(Wraps.<Menu>lbQ().orderByAsc(Menu::getSortOrder));
        List<MenuTreeDTO> treeList = dozer.mapList(list, MenuTreeDTO.class);
        return success(TreeUtil.build(treeList));
    }
}
