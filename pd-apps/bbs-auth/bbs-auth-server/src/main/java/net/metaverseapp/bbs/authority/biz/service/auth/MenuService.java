package net.metaverseapp.bbs.authority.biz.service.auth;

import com.baomidou.mybatisplus.extension.service.IService;
import net.metaverseapp.bbs.authority.entity.auth.Menu;

import java.util.List;

/**
 * Service
 * MenuService
 */
public interface MenuService extends IService<Menu> {
    /**
     * removeByIds
     */
    boolean removeByIds(List<Long> ids);

    /**
     * findVisibleMenu
     *
     * @param group
     * @param userId
     * @return
     */
    List<Menu> findVisibleMenu(String group, Long userId);
}
