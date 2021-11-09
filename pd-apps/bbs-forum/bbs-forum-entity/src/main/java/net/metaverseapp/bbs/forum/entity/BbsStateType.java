package net.metaverseapp.bbs.forum.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itheima.pinda.base.entity.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bbs_state_type")
@ApiModel(value = "BbsStateType", description = "topic state 帖子状态：hot, popular")
public class BbsStateType extends Entity<Long> {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty(value = "id number")
//    @NotEmpty(message = "id cannot be empty")
//    @TableField(value = "id", condition = LIKE)
//    private BigInteger id;

    @ApiModelProperty(value = "class name")
    @NotEmpty(message = "class name cannot be null")
    @Length(max = 100, message = "maximum length cannot be more than 100")
    @TableField(value = "name", condition = LIKE)
    private String name;


    @ApiModelProperty(value = "is_active")
    @NotNull(message = "active cannot be null")
    @TableField(value = "is_active", condition = LIKE)
    private Boolean isActive;
}
