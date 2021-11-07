package net.metaverseapp.bbs.authority.dto.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import net.metaverseapp.bbs.authority.enumeration.auth.Gender;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

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
@ApiModel(value = "UserSaveDTO", description = "UserSaveDTO")
public class UserSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty(value = "id")
//    @NotEmpty(message = "Id can not be null")
//    private Long id;
    /**
     * username
     */
    @ApiModelProperty(value = "username")
    @NotEmpty(message = "Username can not be null")
    @Length(max = 30, message = "The length of username can not exceed 30")
    private String username;

    /**
     * password
     */
    @ApiModelProperty(value = "password")
    @NotEmpty(message = "Password can not be null")
    @Length(max = 64, message = "The length of password can not exceed 64")
    private String password;

    /**
     * email
     */
    @ApiModelProperty(value = "email")
    @Length(max = 50, message = "The length of email can not exceed 50")
    @TableField(value = "email", condition = LIKE)
    private String email;

}
