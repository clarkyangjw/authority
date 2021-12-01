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
import net.metaverseapp.bbs.authority.biz.service.auth.ResourceService;
import net.metaverseapp.bbs.authority.dto.auth.ResourceQueryDTO;
import net.metaverseapp.bbs.authority.dto.auth.ResourceSaveDTO;
import net.metaverseapp.bbs.authority.dto.auth.ResourceUpdateDTO;
import net.metaverseapp.bbs.authority.entity.auth.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 前端控制器
 * Resource
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/resource")
@Api(value = "Resource", tags = "Resource")
public class ResourceController extends BaseController {
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private DozerUtils dozer;
    /**
     * Paging query resources
     */
    @ApiOperation(value = "page", notes = "Paging query resources")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("Paging query resources")
    public R<IPage<Resource>> page(Resource data) {
        IPage<Resource> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<Resource> query = Wraps.lbQ(data);
        resourceService.page(page, query);
        return success(page);
    }

    /**
     * Query resources
     */
    @ApiOperation(value = "get", notes = "Query resources")
    @GetMapping("/{id}")
    @SysLog("Query resources")
    public R<Resource> get(@PathVariable Long id) {
        return success(resourceService.getById(id));
    }

    /**
     * Add resources
     */
    @ApiOperation(value = "save", notes = "Add resources")
    @PostMapping
    @SysLog("Add resources")
    public R<Resource> save(@RequestBody @Validated ResourceSaveDTO data) {
        Resource resource = dozer.map(data, Resource.class);
        resourceService.save(resource);
        return success(resource);
    }

    /**
     * Update resources
     */
    @ApiOperation(value = "update", notes = "Update resources")
    @PutMapping
    @SysLog("Update resources")
    public R<Resource> update(@RequestBody @Validated(SuperEntity.Update.class) ResourceUpdateDTO data) {
        Resource resource = dozer.map(data, Resource.class);
        resourceService.updateById(resource);
        return success(resource);
    }

    /**
     * 删除资源
     * 链接类型的资源 只清空 menu_id
     * 按钮和数据列 则物理删除
     * Delete resource
     */
    @ApiOperation(value = "delete", notes = "Delete resource physically")
    @DeleteMapping
    @SysLog("Delete resource")
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        return success(resourceService.removeByIds(ids));
    }

    /**
     * Query all resources available to the user
     */
    @ApiOperation(value = "visible", notes = "Query all resources available to the user")
    @GetMapping
    @SysLog("Query all resources available to the user")
    public R<List<Resource>> visible(ResourceQueryDTO resource) {
        if (resource == null) {
            resource = new ResourceQueryDTO();
        }

        if (resource.getUserId() == null) {
            resource.setUserId(getUserId());
        }
        return success(resourceService.findVisibleResource(resource));
    }

    /**
     * Querying all Resources
     */
    @ApiOperation(value = "list", notes = "Querying all Resources")
    @GetMapping("/list")
    @SysLog("Querying all Resources")
    public R<List> list() {
        List<Resource> list = resourceService.list();
        List<String> resourceList = list.stream().map((Resource r) -> {
            return r.getMethod() + r.getUrl();
        }).collect(Collectors.toList());
        return success(resourceList);
    }
}
