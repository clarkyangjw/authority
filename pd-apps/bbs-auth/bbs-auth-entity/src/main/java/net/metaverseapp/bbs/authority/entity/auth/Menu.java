package net.metaverseapp.bbs.authority.entity.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itheima.pinda.base.entity.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * Entity
 * Menu
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bbs_menu")
@ApiModel(value = "Menu", description = "Menu")
public class Menu extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Menu name
     */
    @ApiModelProperty(value = "Menu name")
    @NotEmpty(message = "Menu name can not be null")
    @Length(max = 100, message = "The length of Menu name can not exceed 100")
    @TableField(value = "name", condition = LIKE)
    private String name;

    /**
     * Is the menu published
     * It means this menu is showed without assigning authority.
     */
    @ApiModelProperty(value = "Is public")
    @TableField("is_public")
    private Boolean isPublic;

    /**
     * route path for front end
     */
    @ApiModelProperty(value = "route path for front end")
    @Length(max = 100, message = "The length of path can not exceed 100")
    @TableField(value = "path", condition = LIKE)
    private String path;

    /**
     * the path of the component
     */
    @ApiModelProperty(value = "the path of the component")
    @Length(max = 100, message = "The length of path can not exceed 100")
    @TableField(value = "component", condition = LIKE)
    private String component;

    /**
     * Sort order
     */
    @ApiModelProperty(value = "Sort order")
    @TableField("sort_order")
    private Integer sortOrder;

    /**
     * Menu icon
     */
    @ApiModelProperty(value = "Menu icon")
    @Length(max = 100, message = "The length of path can not exceed 100")
    @TableField(value = "icon", condition = LIKE)
    private String icon;

    /**
     * Parent id
     */
    @ApiModelProperty(value = "Parent id")
    @TableField("parent_id")
    private Long parentId;

    /**
     * isActive
     */
    @ApiModelProperty(value = "isActive 1-enabled 0-disabled")
    @TableField("is_active")
    private Boolean isActive;

    @Builder
    public Menu(Long id, Long createUser, LocalDateTime createTime, Long updateUser, LocalDateTime updateTime,
                String name, Boolean isPublic, String path, String component,
                 Integer sortValue, String icon, Long parentId, Boolean isActive) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.name = name;
        this.isPublic = isPublic;
        this.path = path;
        this.component = component;
        this.sortOrder = sortValue;
        this.icon = icon;
        this.parentId = parentId;
        this.isActive = isActive;
    }


}
