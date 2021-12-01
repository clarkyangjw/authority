package net.metaverseapp.bbs.authority.controller.auth;

import com.itheima.pinda.base.BaseController;
import com.itheima.pinda.base.R;
import com.itheima.pinda.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.metaverseapp.bbs.authority.biz.service.auth.ValidateCodeService;
import net.metaverseapp.bbs.authority.biz.service.auth.impl.AuthManager;
import net.metaverseapp.bbs.authority.dto.auth.LoginDTO;
import net.metaverseapp.bbs.authority.dto.auth.LoginParamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录（认证）控制器
 */
@RestController
@RequestMapping("/anno")
@Api(tags = "LoginController",value = "LoginController")
public class LoginController extends BaseController {
    @Autowired
    private ValidateCodeService validateCodeService;
    @Autowired
    private AuthManager authManager;

    //Generate captcha
    @GetMapping(value = "/captcha",produces = "image/png")
    @ApiOperation(notes = "Captcha",value = "captcha")
    @SysLog("Generate captcha")
    public void captcha(@RequestParam(value = "key") String key, HttpServletResponse response) throws IOException {
        validateCodeService.create(key,response);
    }

    //Login authentication
    @PostMapping("/login")
    @ApiOperation(notes = "login",value = "login")
    @SysLog("Login")
    public R<LoginDTO> login(@Validated @RequestBody LoginParamDTO loginParamDTO){
        //Verify that the verification code is correct
        boolean check = validateCodeService.check(loginParamDTO.getKey(), loginParamDTO.getCode());
        if(check){
            //If the verification code succeeds, the login authentication logic is executed
            R<LoginDTO> r = authManager.login(loginParamDTO.getUsername(),loginParamDTO.getPassword());
            return r;
        }
        //If the verification code fails, the system returns directly
        return this.success(null);
    }

    //Verification code
    @PostMapping("/check")
    @ApiOperation(notes = "checking captcha code",value = "validateCode")
    public boolean check(@RequestBody LoginParamDTO loginParamDTO){
        //Verify that the verification code is correct
        return validateCodeService.check(loginParamDTO.getKey(),loginParamDTO.getCode());
    }

}
