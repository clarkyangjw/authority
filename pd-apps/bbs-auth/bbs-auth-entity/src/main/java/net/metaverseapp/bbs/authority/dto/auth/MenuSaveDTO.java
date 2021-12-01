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
 * 菜单
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
@ApiModel(value = "MenuSaveDTO", description = "MenuSaveDTO")
public class MenuSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Menu name
     */
    @ApiModelProperty(value = "Menu name")
    @NotEmpty(message = "Menu name can not be null")
    @Length(max = 100, message = "The length of Menu name can not exceed 100")
    private String name;

    /**
     * Is the menu published
     * It means this menu is showed without assigning authority.
     */
    @ApiModelProperty(value = "Is public")
    private Boolean isPublic;

    /**
     * route path for front end
     */
    @ApiModelProperty(value = "route path for front end")
    @Length(max = 100, message = "The length of path can not exceed 100")
    private String path;

    /**
     * the path of the component
     */
    @ApiModelProperty(value = "the path of the component")
    @Length(max = 100, message = "The length of path can not exceed 100")
    private String component;

    /**
     * Sort order
     */
    @ApiModelProperty(value = "Sort order")
    private Integer sortOrder;

    /**
     * Menu icon
     */
    @ApiModelProperty(value = "Menu icon")
    @Length(max = 100, message = "The length of path can not exceed 100")
    private String icon;

    /**
     * Parent id
     */
    @ApiModelProperty(value = "Parent id")
    private Long parentId;

    /**
     * isActive
     */
    @ApiModelProperty(value = "isActive 1-enabled 0-disabled")
    private Boolean isActive;

}
