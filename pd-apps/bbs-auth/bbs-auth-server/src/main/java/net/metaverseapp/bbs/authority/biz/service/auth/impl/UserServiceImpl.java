package net.metaverseapp.bbs.authority.biz.service.auth.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.pinda.database.mybatis.conditions.Wraps;
import com.itheima.pinda.database.mybatis.conditions.query.LbqWrapper;
import com.itheima.pinda.utils.BizAssert;
import lombok.extern.slf4j.Slf4j;
import net.metaverseapp.bbs.authority.biz.dao.auth.UserMapper;
import net.metaverseapp.bbs.authority.biz.service.auth.UserRoleService;
import net.metaverseapp.bbs.authority.biz.service.auth.UserService;
import net.metaverseapp.bbs.authority.dto.auth.UserUpdatePasswordDTO;
import net.metaverseapp.bbs.authority.entity.auth.User;
import net.metaverseapp.bbs.authority.entity.auth.UserRole;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 业务实现类
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public List<User> findUserByRoleId(Long roleId, String keyword) {
        return baseMapper.findUserByRoleId(roleId, keyword);
    }

    @Override
    public User getByUsername(String username) {
        return super.getOne(Wraps.<User>lbQ().eq(User::getUsername, username));
    }

    @Override
    public void updateLoginTime(String username) {
        baseMapper.updateLastLoginTime(username, LocalDateTime.now());
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        super.save(user);
        return user;
    }

//    @Override
//    public void updatePasswordErrorNumById(Long id) {
//        baseMapper.incrPasswordErrorNumById(id);
//    }


    @Override
    public boolean reset(List<Long> ids) {
        LocalDateTime passwordExpireTime = null;
        String defPassword = "cea87ef1cb2e47570020bf7c014e1074";//pinda123
        super.update(Wraps.<User>lbU()
                .set(User::getPassword, defPassword)
                .in(User::getId, ids)
        );
        return true;
    }

    @Override
    public User updateUser(User user) {
        if (StrUtil.isNotEmpty(user.getPassword())) {
            user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        }
        super.updateById(user);
        return user;
    }

    @Override
    public boolean remove(List<Long> ids) {
        userRoleService.remove(Wraps.<UserRole>lbQ()
                .in(UserRole::getUserId, ids)
        );
        return super.removeByIds(ids);
    }

    @Override
    public IPage<User> findPage(IPage<User> page, LbqWrapper<User> wrapper) {
        return baseMapper.findPage(page, wrapper);
    }

    @Override
    public Boolean updatePassword(UserUpdatePasswordDTO data) {
//        BizAssert.equals(data.getConfirmPassword(), data.getPassword(), "密码与确认密码不一致");

        User user = getById(data.getId());
        BizAssert.notNull(user, "User does not exist");
        String oldPassword = DigestUtils.md5Hex(data.getOldPassword());
        BizAssert.equals(user.getPassword(), oldPassword, "Old password incorrect");

        User build = User.builder().password(data.getPassword()).id(data.getId()).build();
        this.updateUser(build);
        return true;
    }

//    @Override
//    public int resetPassErrorNum(Long id) {
//        return baseMapper.resetPassErrorNum(id);
//    }

}
