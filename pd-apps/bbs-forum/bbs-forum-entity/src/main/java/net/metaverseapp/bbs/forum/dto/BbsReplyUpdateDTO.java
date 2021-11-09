package net.metaverseapp.bbs.forum.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

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
@ApiModel(value = "MenuUpdateDTO", description = "update login info")
public class BbsReplyUpdateDTO implements Serializable {
    private static final long serialVersionUID = -3124612657759050173L;

    @ApiModelProperty(value = "id")
    private BigInteger id;

    @ApiModelProperty(value = "parent topic id")
    @NotNull(message = "topic_id cannot be null")
    private BigInteger topicId;

    @ApiModelProperty(value = "parent topic title")
    @NotEmpty(message = "title cannot be null")
    @Length(max = 100, message = "maximum length cannot be more than 100")
    private String title;

    @ApiModelProperty(value = "contect 内容")
    @NotEmpty(message = "contect cannot be null")
    @Length(max = 8000, message = "maximum length cannot be more than 8000")
    private String body;

    @ApiModelProperty(value = "is_active")
    @NotNull(message = "active cannot be null")
    private Boolean isActive;
}
