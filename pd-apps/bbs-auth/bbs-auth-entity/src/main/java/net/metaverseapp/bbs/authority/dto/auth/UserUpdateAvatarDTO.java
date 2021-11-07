package net.metaverseapp.bbs.authority.dto.auth;

import com.itheima.pinda.base.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

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
@ApiModel(value = "UserUpdateDTO", description = "用户")
public class UserUpdateAvatarDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @NotNull(message = "Id can not be null", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * avatar
     */
    @ApiModelProperty(value = "avatar")
    @Length(max = 400, message = "The length of username can not exceed 400")
    private String avatar;

}
