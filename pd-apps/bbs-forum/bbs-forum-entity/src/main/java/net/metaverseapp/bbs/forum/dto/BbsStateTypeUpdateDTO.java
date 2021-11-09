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
@ApiModel(value = "BbsStateTypeUpdateDTO", description = "statetype update")
public class BbsStateTypeUpdateDTO implements Serializable {
    private static final long serialVersionUID = -3124612657759050173L;
    @ApiModelProperty(value = "id")
    private BigInteger id;



    @ApiModelProperty(value = "class name")
    @NotEmpty(message = "class name cannot be null")
    @Length(max = 100, message = "maximum length cannot be more than 100")
    private String name;


    @ApiModelProperty(value = "is_active")
    @NotNull(message = "active cannot be null")
    private Boolean isActive;
}
