package net.metaverseapp.bbs.authority.dto.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * RoleSaveDTO
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "RoleSaveDTO", description = "RoleSaveDTO")
public class RoleSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Role name
     */
    @ApiModelProperty(value = "Role name")
    @NotEmpty(message = "Role name can not be null")
    @Length(max = 100, message = "The length of Menu name can not exceed 100")
    private String name;

    /**
     * isActive
     */
    @ApiModelProperty(value = "isActive 1-enabled 0-disabled")
    private Boolean isActive;

}
