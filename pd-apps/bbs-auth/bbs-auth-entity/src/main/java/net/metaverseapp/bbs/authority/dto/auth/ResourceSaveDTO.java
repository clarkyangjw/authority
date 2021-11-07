package net.metaverseapp.bbs.authority.dto.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 资源
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
@ApiModel(value = "ResourceSaveDTO", description = "ResourceSaveDTO")
public class ResourceSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * resource code
     */
    @ApiModelProperty(value = "Resource code")
    @Length(max = 50, message = "The length of username can not exceed 50")
    private String code;

    /**
     * resource name
     */
    @ApiModelProperty(value = "Resource name")
    @NotEmpty(message = "Resource name can not be null")
    @Length(max = 100, message = "The length of resource name can not exceed 100")
    private String name;

    /**
     * Menu Id
     * #bbs_menu
     */
    @ApiModelProperty(value = "Menu Id")
    private Long menuId;
}
