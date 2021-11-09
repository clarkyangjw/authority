package net.metaverseapp.bbs.forum.dto;


import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "BbsClassUpdateDTO", description = " update class info")
public class BbsClassUpdateDTO implements Serializable {
    private static final long serialVersionUID = -3124612657759050173L;

    @ApiModelProperty(value = "id")
    private BigInteger id;

    @ApiModelProperty(value = "parent class's id")
    @NotNull(message = "parent id cannot be null, default is 0")
    private BigInteger parentId;

    @ApiModelProperty(value = "class name")
    @NotEmpty(message = "class name cannot be null")
    @Length(max = 100, message = "maximum length cannot be more than 100")
    private String name;


    @ApiModelProperty(value = "sort sequential order")
    @NotNull(message = "sort_order cannot be 0")
    @Min(value = 0,message = "sort_order cannot be smaller than 0")
    private Integer sortOrder;

    @ApiModelProperty(value = "class introduction")
    @NotEmpty(message = "class introduction cannot be empty")
    @Length(max = 500, message = "the introduction must be shorter than 500 units")
    private String intro;

    @ApiModelProperty(value = "rules of this class 版规")
    @NotEmpty(message = "rules of the class cannot be empty")
    @Length(max = 4000, message = "the length of rule no more than 4000")
    private String rule;

    @ApiModelProperty(value = "number of topics")
    @NotNull(message = "number of topics canno tbe zero")
    @Min(value = 0,message = "topicCount cannot be smaller than 0")
    private Integer topicCount;

    @ApiModelProperty(value = "number of replies")
    @NotNull(message = "number of replies cannot be zero")
    @Min(value = 0,message = "replyCount cannot be smaller than 0")
    private Integer replyCount;


    @ApiModelProperty(value = "class theme image url")
    @Length(max = 500, message = "500 is the limit")
    private String imgUrl;

    @ApiModelProperty(value = "class linkage url")
    @NotEmpty(message = "class url cannot be null")
    @Length(max = 500, message = "the class url must be shorter than 500 units")
    private String url;

    @ApiModelProperty(value = "is_active")
    @NotNull(message = "active cannot be null")
    private Boolean isActive;
}
