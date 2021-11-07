package net.metaverseapp.bbs.authority.biz.service.auth;

import com.baomidou.mybatisplus.extension.service.IService;
import net.metaverseapp.bbs.authority.dto.auth.ResourceQueryDTO;
import net.metaverseapp.bbs.authority.entity.auth.Resource;

import java.util.List;

public interface ResourceService extends IService<Resource> {
    /**
     * 查询 拥有的资源
     */
    List<Resource> findVisibleResource(ResourceQueryDTO resource);

    /**
     * 根据菜单id删除资源
     */
    void removeByMenuId(List<Long> menuIds);

    /**
     * 根据资源id 查询菜单id
     */
    List<Long> findMenuIdByResourceId(List<Long> resourceIdList);
}
