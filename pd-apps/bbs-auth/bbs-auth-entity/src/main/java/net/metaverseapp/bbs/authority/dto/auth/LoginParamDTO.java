package net.metaverseapp.bbs.authority.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * 登录参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "LoginParamDTO", description = "Login Parameters")
public class LoginParamDTO {
    @ApiModelProperty(value = "captcha key")
    @NotEmpty(message = "captcha key can not be null")
    private String key;
    @ApiModelProperty(value = "captcha code")
    @NotEmpty(message = "captcha code can not be null")
    private String code;
    @ApiModelProperty(value = "username")
    @NotEmpty(message = "username can not be null")
    private String username;
    @ApiModelProperty(value = "password")
    @NotEmpty(message = "password can not be null")
    private String password;
}
