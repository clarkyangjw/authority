package net.metaverseapp.bbs.authority.biz.service.auth;

import com.baomidou.mybatisplus.extension.service.IService;
import net.metaverseapp.bbs.authority.dto.auth.RoleSaveDTO;
import net.metaverseapp.bbs.authority.dto.auth.RoleUpdateDTO;
import net.metaverseapp.bbs.authority.entity.auth.Role;

import java.util.List;

/**
 * 业务接口
 * 角色
 */
public interface RoleService extends IService<Role> {
    /**
     * 根据ID删除
     */
    boolean removeById(List<Long> ids);

    /**
     * 查询用户拥有的角色
     */
    List<Role> findRoleByUserId(Long userId);

    /**
     * 保存角色
     */
    void saveRole(RoleSaveDTO data, Long userId);

    /**
     * 修改
     */
    void updateRole(RoleUpdateDTO role, Long userId);

}
