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
 * Resource
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bbs_resource")
@ApiModel(value = "Resource", description = "Resource")
public class Resource extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * resource code
     */
    @ApiModelProperty(value = "Resource code")
    @Length(max = 50, message = "The length of username can not exceed 50")
    @TableField(value = "code", condition = LIKE)
    private String code;

    /**
     * resource name
     */
    @ApiModelProperty(value = "Resource name")
    @NotEmpty(message = "Resource name can not be null")
    @Length(max = 100, message = "The length of resource name can not exceed 100")
    @TableField(value = "name", condition = LIKE)
    private String name;

    /**
     * Menu Id
     * #bbs_menu
     */
    @ApiModelProperty(value = "Menu Id")
    @TableField("menu_id")
    private Long menuId;

    @ApiModelProperty(value = "Request method")
    @Length(max = 10, message = "The length of method can not exceed 10")
    @TableField(value = "method")
    private String method;

    @ApiModelProperty(value = "Request url")
    @Length(max = 100, message = "The length of method can not exceed 100")
    @TableField(value = "url")
    private String url;

    /**
     * isActive
     */
    @ApiModelProperty(value = "isActive 1-enabled 0-disabled")
    @TableField("is_active")
    private Boolean isActive;

    @Builder
    public Resource(Long id, Long createUser, LocalDateTime createTime, Long updateUser, LocalDateTime updateTime,
                    String code, String name, Long menuId, String method, String url, Boolean isActive) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.code = code;
        this.name = name;
        this.menuId = menuId;
        this.method = method;
        this.url = url;
        this.isActive = isActive;
    }

}
