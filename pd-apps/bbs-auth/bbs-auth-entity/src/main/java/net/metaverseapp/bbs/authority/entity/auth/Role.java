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
 * Role
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bbs_role")
@ApiModel(value = "Role", description = "Role")
public class Role extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Role name
     */
    @ApiModelProperty(value = "Role name")
    @NotEmpty(message = "Role name can not be null")
    @Length(max = 100, message = "The length of Menu name can not exceed 100")
    @TableField(value = "name", condition = LIKE)
    private String name;

    /**
     * isActive
     */
    @ApiModelProperty(value = "isActive 1-enabled 0-disabled")
    @TableField("is_active")
    private Boolean isActive;


    @Builder
    public Role(Long id, Long createUser, LocalDateTime createTime, Long updateUser, LocalDateTime updateTime,
                String name, Boolean isActive) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.name = name;
        this.isActive = isActive;
    }

}
