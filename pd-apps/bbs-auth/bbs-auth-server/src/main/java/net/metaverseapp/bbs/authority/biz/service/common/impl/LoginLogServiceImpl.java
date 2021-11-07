package net.metaverseapp.bbs.authority.biz.service.common.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import net.metaverseapp.bbs.authority.biz.dao.common.LoginLogMapper;
import net.metaverseapp.bbs.authority.biz.service.auth.UserService;
import net.metaverseapp.bbs.authority.biz.service.common.LoginLogService;
import net.metaverseapp.bbs.authority.entity.auth.User;
import net.metaverseapp.bbs.authority.entity.common.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 业务实现类
 * 登录日志
 */
@Slf4j
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {
    @Autowired
    private UserService userService;

    private final static String[] BROWSER = new String[]{
            "Chrome", "Firefox", "Microsoft Edge", "Safari", "Opera"
    };
    private final static String[] OPERATING_SYSTEM = new String[]{
            "Android", "Linux", "Mac OS X", "Ubuntu", "Windows 10", "Windows 8", "Windows 7", "Windows XP", "Windows Vista"
    };

    private static String simplifyOperatingSystem(String operatingSystem) {
        for (String b : OPERATING_SYSTEM) {
            if (StrUtil.containsIgnoreCase(operatingSystem, b)) {
                return b;
            }
        }
        return operatingSystem;
    }

    private static String simplifyBrowser(String browser) {
        for (String b : BROWSER) {
            if (StrUtil.containsIgnoreCase(browser, b)) {
                return b;
            }
        }
        return browser;
    }

    @Override
    public LoginLog save(String userUsername, String ua, String ip, String location, String description) {
        User user = this.userService.getByUsername(userUsername);
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);
        Browser browser = userAgent.getBrowser();
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        LoginLog loginLog = LoginLog.builder()
                .userUsername(userUsername).location(location)
                .loginDatetime(LocalDate.now())
                .description(description)
                .requestIp(ip).ua(ua).userNickname(user.getNickname())
                .browser(simplifyBrowser(browser.getName())).browserVersion(userAgent.getBrowserVersion().getVersion())
                .operatingSystem(simplifyOperatingSystem(operatingSystem.getName()))
                .build();
        if (user != null) {
            loginLog.setUserUsername(user.getUsername()).setUserNickname(user.getNickname());
        }
        super.save(loginLog);
        return loginLog;
    }

    @Override
    public Long findTotalVisitCount() {
        return this.baseMapper.findTotalVisitCount();
    }

    @Override
    public Long findTodayVisitCount() {
        LocalDate now = LocalDate.now();
        return this.baseMapper.findTodayVisitCount(now);
    }

    @Override
    public Long findTodayIp() {
        LocalDate now = LocalDate.now();
        return this.baseMapper.findTodayIp(now);
    }

    @Override
    public List<Map<String, Object>> findLastTenDaysVisitCount(String userUsername) {
        LocalDate tenDays = LocalDate.now().plusDays(-9);
        return this.baseMapper.findLastTenDaysVisitCount(tenDays, userUsername);
    }

    @Override
    public List<Map<String, Object>> findByBrowser() {
        return this.baseMapper.findByBrowser();
    }

    @Override
    public List<Map<String, Object>> findByOperatingSystem() {
        return this.baseMapper.findByOperatingSystem();
    }
}
