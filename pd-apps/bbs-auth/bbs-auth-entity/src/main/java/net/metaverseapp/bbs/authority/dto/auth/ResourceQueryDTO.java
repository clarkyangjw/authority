package net.metaverseapp.bbs.authority.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * resource query DTO
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "ResourceQueryDTO", description = "ResourceQueryDTO")
public class ResourceQueryDTO {

    /**
     * parent menu Id, used to query button
     */
    @ApiModelProperty(value = "menuId", notes = "menuId")
    private Long menuId;
    /**
     * userId
     */
    @ApiModelProperty(value = "userId", notes = "userId，auto inject if front end does not pass")
    private Long userId;
}
