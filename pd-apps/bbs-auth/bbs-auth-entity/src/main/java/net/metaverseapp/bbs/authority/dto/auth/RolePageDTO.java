package net.metaverseapp.bbs.authority.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 实体类
 * RoleSaveDTO
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
@ApiModel(value = "RolePageDTO", description = "RoleSaveDTO")
public class RolePageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "name")
    @Length(max = 30, message = "The length of Menu name can not exceed 30")
    private String name;

    /**
     * 状态
     */
    @ApiModelProperty(value = "isActive")
    private Boolean isActive;

    /**
     * 数据权限类型
     * #DataScopeType{ALL:1,全部;THIS_LEVEL:2,本级;THIS_LEVEL_CHILDREN:3,本级以及子级;CUSTOMIZE:4,自定义;SELF:5,个人;}
     */
    /*@ApiModelProperty(value = "数据权限类型")
    private DataScopeType dsType;*/
    @ApiModelProperty(value = "startCreateTime")
    private LocalDateTime startCreateTime;
    @ApiModelProperty(value = "endCreateTime")
    private LocalDateTime endCreateTime;
}
