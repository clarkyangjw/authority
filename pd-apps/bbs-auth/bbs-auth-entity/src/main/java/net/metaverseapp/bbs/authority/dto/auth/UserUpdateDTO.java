package net.metaverseapp.bbs.authority.dto.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import com.itheima.pinda.base.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import net.metaverseapp.bbs.authority.enumeration.auth.Gender;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 用户
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "UserUpdateDTO", description = "用户")
public class UserUpdateDTO implements Serializable {


    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @NotNull(message = "Id can not be null", groups = SuperEntity.Update.class)
    private Long id;
    /**
     * username
     */
    @ApiModelProperty(value = "username")
    @NotEmpty(message = "Username can not be null")
    @Length(max = 30, message = "The length of username can not exceed 30")
    private String username;

    /**
     * nickname
     */
    @ApiModelProperty(value = "nickname")
    @NotEmpty(message = "Nickname can not be null")
    @Length(max = 50, message = "The length of nickname can not exceed 50")
    private String nickname;

    /**
     * password
     */
    @ApiModelProperty(value = "password")
    @NotEmpty(message = "Password can not be null")
    @Length(max = 64, message = "The length of password can not exceed 64")
    private String password;

    /**
     * email
     */
    @ApiModelProperty(value = "email")
    @Length(max = 50, message = "The length of email can not exceed 50")
    private String email;

    /**
     * 头像
     */
    @ApiModelProperty(value = "portraitUrl")
    @Length(max = 400, message = "The length of email can not exceed 400")
    private String portraitUrl;

    /**
     * gender
     * #gender{F:Female;M:Male;S:Secret;LGBTQ}
     */
    @ApiModelProperty(value = "gender")
    private Gender gender;

    /**
     * introduction
     */
    @ApiModelProperty(value = "introduction")
    @Length(max = 500, message = "The length of introduction can not exceed 500")
    private String intro;

    /**
     * signature
     */
    @ApiModelProperty(value = "signature")
    @Length(max = 500, message = "The length of signature can not exceed 500")
    private String signature;

    /**
     * topicCount
     * total number of published the topic
     */
    @ApiModelProperty(value = "topicCount")
    private Integer topicCount;

    /**
     * replyCount
     * total number of replying
     */
    @ApiModelProperty(value = "replyCount")
    private Integer replyCount;

    /**
     * bestTopicCount
     * total number of best topic
     */
    @ApiModelProperty(value = "bestTopicCount")
    private Integer bestTopicCount;

    /**
     * lastTopicId
     */
    @ApiModelProperty(value = "lastTopicId")
    private Long lastTopicId;

    /**
     * amount
     */
    @ApiModelProperty(value = "amount")
    private Integer amount;

    /**
     * isActive
     */
    @ApiModelProperty(value = "isActive 1-enabled 0-disabled")
    private Boolean isActive;

}
