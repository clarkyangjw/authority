package com.metaverseapp.bbs.authority.entity.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itheima.pinda.base.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * Entity
 * Role assigning
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bbs_user_role")
@ApiModel(value = "UserRole", description = "UserRole")
public class UserRole extends SuperEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * User ID
     * #bbs_user
     */
    @ApiModelProperty(value = "User ID")
    @NotNull(message = "User ID can not be null")
    @TableField("user_id")
    private Long userId;

    /**
     * Role ID
     * #bbs_role
     */
    @ApiModelProperty(value = "Role ID")
    @NotNull(message = "Role ID can not be null")
    @TableField("role_id")
    private Long roleId;

    @Builder
    public UserRole(Long id, Long createUser, LocalDateTime createTime,
                    Long roleId, Long userId) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.roleId = roleId;
        this.userId = userId;
    }

}
