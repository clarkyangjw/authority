package net.metaverseapp.bbs.authority.biz.service.auth;

import com.baomidou.mybatisplus.extension.service.IService;
import net.metaverseapp.bbs.authority.dto.auth.RoleAuthoritySaveDTO;
import net.metaverseapp.bbs.authority.dto.auth.UserRoleSaveDTO;
import net.metaverseapp.bbs.authority.entity.auth.RoleAuthority;

public interface RoleAuthorityService extends IService<RoleAuthority> {
    /**
     * 给用户分配角色
     */
    boolean saveUserRole(UserRoleSaveDTO userRole);

    /**
     * 给角色重新分配 权限（资源/菜单）
     */
    boolean saveRoleAuthority(RoleAuthoritySaveDTO roleAuthoritySaveDTO);
}
