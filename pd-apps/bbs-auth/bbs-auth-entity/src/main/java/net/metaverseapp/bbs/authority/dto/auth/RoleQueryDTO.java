package net.metaverseapp.bbs.authority.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 实体类
 * 角色
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
@ApiModel(value = "RoleQueryDTO", description = "角色")
public class RoleQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "isActive")
    private Boolean isActive;

    /**
     * 数据权限类型
     * #DataScopeType{ALL:1,全部;THIS_LEVEL:2,本级;THIS_LEVEL_CHILDREN:3,本级以及子级;CUSTOMIZE:4,自定义;SELF:5,个人;}
     */
    /*@ApiModelProperty(value = "数据权限类型")
    private DataScopeType dsType;*/
//    /**
//     * 关联的组织id
//     */
//    @ApiModelProperty(value = "关联的组织id")
//    private List<Long> orgList;
}
