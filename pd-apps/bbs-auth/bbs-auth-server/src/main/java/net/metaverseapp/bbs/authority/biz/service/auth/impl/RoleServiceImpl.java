package net.metaverseapp.bbs.authority.biz.service.auth.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.pinda.base.id.CodeGenerate;
import com.itheima.pinda.common.constant.CacheKey;
import com.itheima.pinda.database.mybatis.conditions.Wraps;
import com.itheima.pinda.dozer.DozerUtils;
import com.itheima.pinda.utils.StrHelper;
import lombok.extern.slf4j.Slf4j;
import net.metaverseapp.bbs.authority.biz.dao.auth.RoleMapper;
import net.metaverseapp.bbs.authority.biz.service.auth.RoleAuthorityService;
import net.metaverseapp.bbs.authority.biz.service.auth.RoleService;
import net.metaverseapp.bbs.authority.biz.service.auth.UserRoleService;
import net.metaverseapp.bbs.authority.biz.service.auth.UserService;
import net.metaverseapp.bbs.authority.dto.auth.RoleSaveDTO;
import net.metaverseapp.bbs.authority.dto.auth.RoleUpdateDTO;
import net.metaverseapp.bbs.authority.entity.auth.Role;
import net.metaverseapp.bbs.authority.entity.auth.RoleAuthority;
import net.metaverseapp.bbs.authority.entity.auth.User;
import net.metaverseapp.bbs.authority.entity.auth.UserRole;
import net.oschina.j2cache.CacheChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 业务实现类
 * 角色
 */
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private CacheChannel cache;
    @Autowired
    private DozerUtils dozer;
    @Autowired
    private RoleAuthorityService roleAuthorityService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserService userService;

    @Override
    public boolean removeById(List<Long> ids) {
        if (ids.isEmpty()) {
            return true;
        }
        ids.forEach(roleId -> {
            List<User> userList = userService.findUserByRoleId(roleId, null);
            if(userList != null && userList.size() > 0){
                userList.forEach(user -> {
                    cache.evict(CacheKey.USER_RESOURCE, user.getId().toString());
                });
            }
        });

        //delete pd_auth_role data
        super.removeByIds(ids);
        //delete pd_auth_role_authority data
        roleAuthorityService.remove(Wraps.<RoleAuthority>lbQ().in(RoleAuthority::getRoleId, ids));
        //delete pd_auth_user_role data
        userRoleService.remove(Wraps.<UserRole>lbQ().in(UserRole::getRoleId,ids));

        return true;
    }

    @Override
    public List<Role> findRoleByUserId(Long userId) {
        return baseMapper.findRoleByUserId(userId);
    }

    /**
     * 1，保存角色
     * 2，保存 与组织的关系
     */
    @Override
    public void saveRole(RoleSaveDTO data, Long userId) {
        Role role = dozer.map(data, Role.class);
        super.save(role);

    }

    @Override
    public void updateRole(RoleUpdateDTO data, Long userId) {
        Role role = dozer.map(data, Role.class);
        super.updateById(role);
    }

}
