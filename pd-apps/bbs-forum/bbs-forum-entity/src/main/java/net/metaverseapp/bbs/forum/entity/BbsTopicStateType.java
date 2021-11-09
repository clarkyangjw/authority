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

import javax.validation.constraints.Min;
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
@TableName("bbs_class")
@ApiModel(value = "BbsClass", description = "板块 class")
public class BbsTopicStateType extends Entity<Long> {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty(value = "id number")
//    @NotEmpty(message = "id cannot be empty")
//    @TableField(value = "id", condition = LIKE)
//    private BigInteger id;

    @ApiModelProperty(value = "topic_id")
    @NotNull(message = "topic_id cannot be null")
    @TableField(value = "topic_id", condition = LIKE)
    private BigInteger topicId;

    @ApiModelProperty(value = "contect 内容")
    @Min(value = 0,message = "sort_order cannot be smaller than 0")
    @NotNull(message = "sort_order cannot be null")
    @TableField(value = "sort_order", condition = LIKE)
    private BigInteger sortOrder;



}
