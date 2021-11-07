package net.metaverseapp.bbs.authority.biz.service.auth.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.itheima.pinda.auth.server.utils.JwtTokenServerUtils;
import com.itheima.pinda.auth.utils.JwtUserInfo;
import com.itheima.pinda.auth.utils.Token;
import com.itheima.pinda.base.R;
import com.itheima.pinda.dozer.DozerUtils;
import com.itheima.pinda.exception.code.ExceptionCode;
import lombok.extern.slf4j.Slf4j;
import net.metaverseapp.bbs.authority.biz.service.auth.ResourceService;
import net.metaverseapp.bbs.authority.biz.service.auth.UserService;
import net.metaverseapp.bbs.authority.dto.auth.LoginDTO;
import net.metaverseapp.bbs.authority.dto.auth.ResourceQueryDTO;
import net.metaverseapp.bbs.authority.dto.auth.UserDTO;
import net.metaverseapp.bbs.authority.entity.auth.Resource;
import net.metaverseapp.bbs.authority.entity.auth.User;
import net.oschina.j2cache.CacheChannel;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 认证管理器
 */
@Service
@Slf4j
public class AuthManager {
    @Autowired
    private UserService userService;
    @Autowired
    private DozerUtils dozerUtils;
    @Autowired
    private JwtTokenServerUtils jwtTokenServerUtils;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private CacheChannel cacheChannel;

    //login authentication
    public R<LoginDTO> login(String username, String password) {
        //Verify that the username and password are correct
        R<User> userR = check(username, password);
        Boolean isError = userR.getIsError();
        if(isError){
            return R.fail("authentication failure");
        }

        //generate user token
        User user = userR.getData();
        Token token = generateUserToken(user);

        //Query the permission of the current user
        List<Resource> userResource = resourceService.findVisibleResource(ResourceQueryDTO.builder().userId(user.getId()).build());
        log.info("Current user has resources：" + userResource);

        List<String> permissionList =null;
        if(userResource != null && userResource.size() > 0){
            //User permissions(for front end)
            permissionList = userResource.stream().map(Resource::getCode).collect(Collectors.toList());

            //User resources(for front end)
            List<String> visibleResource = userResource.stream().map((resource -> {
                return resource.getMethod() + resource.getUrl();
            })).collect(Collectors.toList());
            //store resources in the cache
            cacheChannel.set("bbs_user_resource",user.getId().toString(),visibleResource);
        }

        //Encapsulate result
        LoginDTO loginDTO = LoginDTO.builder().
                user(dozerUtils.map(userR.getData(), UserDTO.class)).
                token(token).
                permissionsList(permissionList).
                build();
        return R.success(loginDTO);
    }

    //username and password verification
    public R<User> check(String username,String password){
        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));

        //The password submitted by the front-end is encrypted by MD5
        String md5Hex = DigestUtils.md5Hex(password);

        if(user == null || !user.getPassword().equals(md5Hex)){
            //Authentication failed
            return R.fail(ExceptionCode.JWT_USER_INVALID);
        }
        //Authentication success
        return R.success(user);
    }

    //Generate the corresponding JWT token for the currently logged in user
    public Token generateUserToken(User user){
        //Because the user is the public user of forum, the orgId, and stationId should be null
        JwtUserInfo jwtUserInfo = new JwtUserInfo(user.getId(),user.getUsername(),user.getNickname(),null,null);
        Token token = jwtTokenServerUtils.generateUserToken(jwtUserInfo, null);
        return token;
    }

}
