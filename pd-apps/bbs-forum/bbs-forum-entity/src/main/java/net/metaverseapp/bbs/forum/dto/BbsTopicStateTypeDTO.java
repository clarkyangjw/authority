package net.metaverseapp.bbs.forum.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

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
@ApiModel(value = "BbsTopicStateTypeDTO", description = "topic state info")
public class BbsTopicStateTypeDTO implements Serializable {
    private static final long serialVersionUID = -3124612657759050173L;

    @ApiModelProperty(value = "topic_id")
    @NotNull(message = "topic_id cannot be null")
    private BigInteger topicId;

    @ApiModelProperty(value = "contect 内容")
    @Min(value = 0,message = "sortOrder cannot be smaller than 0")
    @NotNull(message = "sortOrder cannot be null")
    private BigInteger sortOrder;
}
