package net.metaverseapp.bbs.authority.dto.auth;

import com.itheima.pinda.base.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
/**
 * <p>
 * 实体类
 * 用户
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "UserUpdatePasswordDTO", description = "UserUpdatePasswordDTO")
public class UserUpdatePasswordDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @NotNull(message = "id can not be null", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 密码
     */
    @ApiModelProperty(value = "oldPassword")
    @NotEmpty(message = "OldPassword can not be null")
    @Length(max = 64, message = "The length of oldPassword can not exceed 64")
    private String oldPassword;
    /**
     * 密码
     */
    @ApiModelProperty(value = "password")
    @NotEmpty(message = "password can not be null")
    @Length(max = 64, message = "The length of password can not exceed 64")
    private String password;

}
