package net.metaverseapp.bbs.authority.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 实体类
 * 角色分配
 * 账号角色绑定
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
@ApiModel(value = "UserRoleSaveDTO", description = "role assignment, user role binding")
public class UserRoleSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * roleId
     * #bbs_role
     */
    @ApiModelProperty(value = "roleId")
    @NotNull(message = "roleId can not be null")
    private Long roleId;
    /**
     * userIdList
     */
    @ApiModelProperty(value = "userIdList")
    @Size(min = 1, message = "userIdList can not be null")
    private List<Long> userIdList;
}
