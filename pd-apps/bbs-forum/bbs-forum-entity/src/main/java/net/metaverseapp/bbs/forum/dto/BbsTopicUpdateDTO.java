package net.metaverseapp.bbs.forum.dto;


import cn.hutool.core.date.DateTime;
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
@ApiModel(value = "BbsTopicUpdateDTO", description = "topic update")
public class BbsTopicUpdateDTO implements Serializable {

    private static final long serialVersionUID = -3124612657759050173L;

    @ApiModelProperty(value = "id")
    private BigInteger id;


    @ApiModelProperty(value = "subject id")
    @NotNull(message = "subject id cannot be null")
    private BigInteger subjectId;

    @ApiModelProperty(value = "topic title")
    @NotEmpty(message = "title cannot be null")
    @Length(max = 100, message = "maximum length cannot be more than 100")
    private String title;

    @ApiModelProperty(value = "contect 内容")
    @NotEmpty(message = "contect cannot be null")
    @Length(max = 8000, message = "maximum length cannot be more than 8000")
    private String body;

    @ApiModelProperty(value = "enable comment or not")
    @NotEmpty(message = "is_comment cannot be null")
    private Boolean isComment;



    @ApiModelProperty(value = "hits 点击")
    @Min(value = 0,message = "hits cannot be smaller than 0")
    @NotNull(message = "hits cannot be null")
    private Integer hits;

    @ApiModelProperty(value = "reply_count 回复数")
    @NotNull(message = "reply_count cannot be null")
    @Min(value = 0,message = "replyCount cannot be smaller than 0")
    private BigInteger replyCount;


    @ApiModelProperty(value = "class theme image url")
    @Length(max = 500, message = "500 is the limit")
    private String imgUrl;

    @ApiModelProperty(value = "last_reply_by")
    private BigInteger lastReplyBy;

    @ApiModelProperty(value = "last_replied_time")
    private DateTime lastRepliedTime;

    @ApiModelProperty(value = "is_active")
    @NotNull(message = "active cannot be null")
    private Boolean isActive;
}
