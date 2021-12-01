package net.metaverseapp.bbs.authority.entity.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itheima.pinda.base.entity.SuperEntity;
import net.metaverseapp.bbs.authority.enumeration.auth.AuthorizeType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 实体类
 * 角色的资源
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bbs_role_authority")
@ApiModel(value = "RoleAuthority", description = "RoleAuthority")
public class RoleAuthority extends SuperEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Role id
     * #bbs_role
     */
    @ApiModelProperty(value = "Role id")
    @NotNull(message = "Role ID can not be null")
    @TableField("role_id")
    private Long roleId;

    /**
     * Authority id includes resource id and menu id
     * #bbs_resource
     * #bbs_menu
     */
    @ApiModelProperty(value = "Authority id")
    @NotNull(message = "Authority ID can not be null")
    @TableField("authority_id")
    private Long authorityId;

    /**
     * Authority type
     * #AuthorizeType{MENU;RESOURCE;}
     */
    @ApiModelProperty(value = "Authority type")
    @NotNull(message = "Authority type can not be null")
    @TableField("authority_type")
    private AuthorizeType authorityType;

    @Builder
    public RoleAuthority(Long id, LocalDateTime createTime, Long createUser,
                         Long authorityId, AuthorizeType authorityType, Long roleId) {
        this.id = id;
        this.createTime = createTime;
        this.createUser = createUser;
        this.authorityId = authorityId;
        this.authorityType = authorityType;
        this.roleId = roleId;
    }


}
