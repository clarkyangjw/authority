package net.metaverseapp.bbs.authority.dto.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import com.itheima.pinda.base.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * ResourceUpdateDTO
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "ResourceUpdateDTO", description = "ResourceUpdateDTO")
public class ResourceUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @NotNull(message = "id can not be null", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * resource name
     */
    @ApiModelProperty(value = "Resource name")
    @NotEmpty(message = "Resource name can not be null")
    @Length(max = 100, message = "The length of resource name can not exceed 100")
    private String name;

    /**
     * Menu Id
     * #bbs_menu
     */
    @ApiModelProperty(value = "Menu Id")
    private Long menuId;
}
