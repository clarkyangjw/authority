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
@TableName("bbs_subject")
@ApiModel(value = "BbsSubject", description = "subClass theme 板块细分")
public class BbsSubject extends Entity<Long> {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty(value = "id number")
//    @NotEmpty(message = "id cannot be empty")
//    @TableField(value = "id", condition = LIKE)
//    private BigInteger id;


    @ApiModelProperty(value = "parent class's id")
    @NotNull(message = "parent id cannot be null, default is 0")
    @TableField(value = "class_id", condition = LIKE)
    private BigInteger classId;

    @ApiModelProperty(value = "subject name")
    @NotEmpty(message = "subject name cannot be null")
    @Length(max = 100, message = "maximum length cannot be more than 100")
    @TableField(value = "name", condition = LIKE)
    private String name;


    @ApiModelProperty(value = "sort sequential order")
    @NotNull(message = "sort_order cannot be 0")
    @TableField(value = "sort_order", condition = LIKE)
    @Min(value = 0,message = "sortOrder cannot be smaller than 0")
    private Integer sortOrder;

    @ApiModelProperty(value = "number of topics")
    @NotNull(message = "number of topics cannot tbe zero")
    @TableField(value = "topic_count", condition = LIKE)
    @Min(value = 0,message = "topicCount cannot be smaller than 0")
    private Integer topicCount;

    @ApiModelProperty(value = "number of replies")
    @NotNull(message = "number of replies cannot be zero")
    @TableField(value = "reply_count", condition = LIKE)
    @Min(value = 0,message = "replyCount cannot be smaller than 0")
    private Integer replyCount;

    @ApiModelProperty(value = "class linkage url")
    @NotEmpty(message = "class url cannot be null")
    @Length(max = 500, message = "the class url must be shorter than 500 units")
    @TableField(value = "class linkage url", condition = LIKE)
    private String url;


    @ApiModelProperty(value = "lastest update id")
    @NotNull(message = "last_topic_id cannot be null")
    @TableField(value = "last_topic_id", condition = LIKE)
    private BigInteger lastTopicId;


    @ApiModelProperty(value = "is_active")
    @NotNull(message = "active cannot be null")
    @TableField(value = "is_active", condition = LIKE)
    private Boolean isActive;


}
