package net.metaverseapp.bbs.authority.biz.dao.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.metaverseapp.bbs.authority.entity.auth.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * Role
 * </p>
 *
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 查询用户拥有的角色
     *
     * @param userId
     * @return
     */
    List<Role> findRoleByUserId(@Param("userId") Long userId);


}
