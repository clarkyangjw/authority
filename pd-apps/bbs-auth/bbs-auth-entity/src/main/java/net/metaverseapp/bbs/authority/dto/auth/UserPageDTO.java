package net.metaverseapp.bbs.authority.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import net.metaverseapp.bbs.authority.enumeration.auth.Gender;

import java.time.LocalDateTime;

/**
 * UserPageDTO
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UserPageDTO", description = "UserPageDTO")
public class UserPageDTO {

    /**
     * username
     */
    @ApiModelProperty(value = "username")
    private String username;

    /**
     * nickname
     */
    @ApiModelProperty(value = "nickname")
    private String nickname;

    /**
     * email
     */
    @ApiModelProperty(value = "email")
    private String email;

    /**
     * gender
     * #bbs_gender
     */
    @ApiModelProperty(value = "gender")
    private Gender gender;

    /**
     * isActive
     */
    @ApiModelProperty(value = "isActive")
    private Boolean isActive;

    /**
     * portraitUrl
     */
    @ApiModelProperty(value = "portraitUrl")
    private String portraitUrl;

//    /**
//     * 登录次数
//     * 一直累计，记录了此账号总共登录次数
//     */
//    @ApiModelProperty(value = "登录次数")
//    private Integer loginCount;

    @ApiModelProperty(value = "startCreateTime")
    private LocalDateTime startCreateTime;
    @ApiModelProperty(value = "endCreateTime")
    private LocalDateTime endCreateTime;
}
