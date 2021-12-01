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
@TableName("bbs_topic")
@ApiModel(value = "BbsTopic", description = "post 帖子")
public class BbsTopic extends Entity<Long> {
    private static final long serialVersionUID = 1L;

//    @ApiModelProperty(value = "id number")
//    @NotEmpty(message = "id cannot be empty")
//    @TableField(value = "id", condition = LIKE)
//    private BigInteger id;


    @ApiModelProperty(value = "subject id")
    @NotNull(message = "subject id cannot be null")
    @TableField(value = "subject_id", condition = LIKE)
    private BigInteger subjectId;

    @ApiModelProperty(value = "topic title")
    @NotEmpty(message = "title cannot be null")
    @Length(max = 100, message = "maximum length cannot be more than 100")
    @TableField(value = "title", condition = LIKE)
    private String title;

    @ApiModelProperty(value = "contect 内容")
    @NotEmpty(message = "contect cannot be null")
    @Length(max = 8000, message = "maximum length cannot be more than 8000")
    @TableField(value = "body", condition = LIKE)
    private String body;

    @ApiModelProperty(value = "enable comment or not")
    @NotEmpty(message = "is_comment cannot be null")
    @TableField(value = "is_comment", condition = LIKE)
    private Boolean isComment;



    @ApiModelProperty(value = "hits 点击")
    @Min(value = 0,message = "hits cannot be smaller than 0")
    @NotNull(message = "hits cannot be null")
    @TableField(value = "hits", condition = LIKE)
    private Integer hits;

    @ApiModelProperty(value = "reply_count 回复数")
    @NotNull(message = "reply_count cannot be null")
    @Min(value = 0,message = "replyCount cannot be smaller than 0")
    @TableField(value = "reply_count", condition = LIKE)
    private BigInteger replyCount;


    @ApiModelProperty(value = "class theme image url")
    @Length(max = 500, message = "500 is the limit")
    @TableField(value = "img_url", condition = LIKE)
    private String imgUrl;

    @ApiModelProperty(value = "last_reply_by")
    @TableField(value = "last_reply_by", condition = LIKE)
    private BigInteger lastReplyBy;

    @ApiModelProperty(value = "last_replied_time")
    @TableField(value = "last_replied_time", condition = LIKE)
    private DateTime lastRepliedTime;

    @ApiModelProperty(value = "is_active")
    @NotNull(message = "active cannot be null")
    @TableField(value = "is_active", condition = LIKE)
    private Boolean isActive;
}
