package net.metaverseapp.bbs.forum.dto;


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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "BbsSubjectUpdateDTO", description = "subject update")
public class BbsSubjectUpdateDTO implements Serializable {

    private static final long serialVersionUID = -3124612657759050173L;

    @ApiModelProperty(value = "id")
    private BigInteger id;

    @ApiModelProperty(value = "parent class's id")
    @NotNull(message = "parent id cannot be null, default is 0")
    private BigInteger classId;

    @ApiModelProperty(value = "subject name")
    @NotEmpty(message = "subject name cannot be null")
    @Length(max = 100, message = "maximum length cannot be more than 100")
    private String name;


    @ApiModelProperty(value = "sort sequential order")
    @NotEmpty(message = "sort_order cannot be 0")
    @Min(value = 0,message = "sortOrder cannot be smaller than 0")
    private Integer sortOrder;

    @ApiModelProperty(value = "number of topics")
    @NotNull(message = "number of topics cannot tbe zero")
    @Min(value = 0,message = "topicCount cannot be smaller than 0")
    private Integer topicCount;

    @ApiModelProperty(value = "number of replies")
    @NotNull(message = "number of replies cannot be zero")
    @Min(value = 0,message = "replyCount cannot be smaller than 0")
    private Integer replyCount;

    @ApiModelProperty(value = "class linkage url")
    @NotEmpty(message = "class url cannot be null")
    @Length(max = 500, message = "the class url must be shorter than 500 units")
    private String url;


    @ApiModelProperty(value = "lastest update id")
    @NotNull(message = "last_topic_id cannot be null")
    private BigInteger lastTopicId;


    @ApiModelProperty(value = "is_active")
    @NotNull(message = "active cannot be null")
    private Boolean isActive;
}
