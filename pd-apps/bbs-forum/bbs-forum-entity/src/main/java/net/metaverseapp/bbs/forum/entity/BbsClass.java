package net.metaverseapp.bbs.forum.entity;


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
public class BbsClass extends Entity<Long> {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty(value = "id number")
//    @NotEmpty(message = "id cannot be empty")
//    @TableField(value = "id", condition = LIKE)
//    private BigInteger id;


    @ApiModelProperty(value = "parent class's id")
    @NotNull(message = "parent id cannot be null, default is 0")
    @TableField(value = "parent_id", condition = LIKE)
    private BigInteger parentId;

    @ApiModelProperty(value = "class name")
    @NotEmpty(message = "class name cannot be null")
    @Length(max = 100, message = "maximum length cannot be more than 100")
    @TableField(value = "name", condition = LIKE)
    private String name;


    @ApiModelProperty(value = "sort sequential order")
    @NotNull(message = "sort_order cannot be 0")
    @Min(value = 0,message = "sortOrder cannot be smaller than 0")
    @TableField(value = "sort_order", condition = LIKE)
    private Integer sortOrder;

    @ApiModelProperty(value = "class introduction")
    @NotEmpty(message = "class introduction cannot be empty")
    @Length(max = 500, message = "the introduction must be shorter than 500 units")
    @TableField(value = "intro", condition = LIKE)
    private String intro;

    @ApiModelProperty(value = "rules of this class 版规")
    @NotEmpty(message = "rules of the class cannot be empty")
    @Length(max = 4000, message = "the length of rule no more than 4000")
    @TableField(value = "rule", condition = LIKE)
    private String rule;

    @ApiModelProperty(value = "number of topics")
    @NotNull(message = "number of topics canno tbe zero")
    @TableField(value = "topic_count", condition = LIKE)
    @Min(value = 0,message = "topicCount cannot be smaller than 0")
    private Integer topicCount;

    @ApiModelProperty(value = "number of replies")
    @NotNull(message = "number of replies cannot be zero")
    @TableField(value = "reply_count", condition = LIKE)
    @Min(value = 0,message = "replyCount cannot be smaller than 0")
    private Integer replyCount;


    @ApiModelProperty(value = "class theme image url")
    @Length(max = 500, message = "500 is the limit")
    @TableField(value = "img_url", condition = LIKE)
    private String imgUrl;

    @ApiModelProperty(value = "class linkage url")
    @NotEmpty(message = "class url cannot be null")
    @Length(max = 500, message = "the class url must be shorter than 500 units")
    @TableField(value = "url", condition = LIKE)
    private String url;

    @ApiModelProperty(value = "is_active")
    @NotNull(message = "active cannot be null")
    @TableField(value = "is_active", condition = LIKE)
    private Boolean isActive;




}
