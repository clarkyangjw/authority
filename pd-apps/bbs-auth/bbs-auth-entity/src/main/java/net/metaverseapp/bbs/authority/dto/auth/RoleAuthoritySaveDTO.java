package net.metaverseapp.bbs.authority.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

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
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "RoleAuthoritySaveDTO", description = "Resources for roles")
public class RoleAuthoritySaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * menuIdList
     * #bbs_menu
     */
    @ApiModelProperty(value = "menuIdList")
    private List<Long> menuIdList;

    /**
     * resourceIdList
     * #bbs_resource
     */
    @ApiModelProperty(value = "resourceIdList")
    private List<Long> resourceIdList;

    /**
     * roleId
     * #bbs_role
     */
    @ApiModelProperty(value = "roleId")
    @NotNull(message = "roleId can not be null")
    private Long roleId;
}
