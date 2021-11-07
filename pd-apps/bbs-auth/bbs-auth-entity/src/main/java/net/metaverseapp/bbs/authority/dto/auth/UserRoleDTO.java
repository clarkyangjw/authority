package net.metaverseapp.bbs.authority.dto.auth;
/**
 * 用户角色DTO
 *
 */

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import net.metaverseapp.bbs.authority.entity.auth.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "UserRoleDTO", description = "UserRoleDTO")
public class UserRoleDTO implements Serializable {
    @ApiModelProperty(value = "idList")
    private List<Long> idList;
    @ApiModelProperty(value = "userList")
    private List<User> userList;
}
