package net.metaverseapp.bbs.authority.dto.auth;

import com.itheima.pinda.auth.utils.Token;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 登录返回信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "LoginDTO", description = "Login info")
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = -3124612657759050173L;
    @ApiModelProperty(value = "User info")
    private UserDTO user;
    @ApiModelProperty(value = "Token")
    private Token token;
    @ApiModelProperty(value = "Permissions list")
    private List<String> permissionsList;
}
