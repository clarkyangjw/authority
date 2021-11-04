package net.metaverseapp.bbs.authority.entity.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itheima.pinda.base.entity.Entity;
import net.metaverseapp.bbs.authority.enumeration.auth.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;
/**
 * <p>
 * Entity
 * User
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bbs_user")
@ApiModel(value = "Username", description = "Username")
public class User extends Entity<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * username
     */
    @ApiModelProperty(value = "username")
    @NotEmpty(message = "Username can not be null")
    @Length(max = 30, message = "The length of username can not exceed 30")
    @TableField(value = "username", condition = LIKE)
    private String username;

    /**
     * nickName
     */
    @ApiModelProperty(value = "nickname")
    @NotEmpty(message = "Nickname can not be null")
    @Length(max = 50, message = "The length of nickname can not exceed 50")
    @TableField(value = "nick_name", condition = LIKE)
    private String nickName;

    /**
     * password
     */
    @ApiModelProperty(value = "password")
    @NotEmpty(message = "Password can not be null")
    @Length(max = 64, message = "The length of password can not exceed 64")
    @TableField(value = "password", condition = LIKE)
    private String password;

    /**
     * email
     */
    @ApiModelProperty(value = "email")
    @Length(max = 50, message = "The length of email can not exceed 50")
    @TableField(value = "email", condition = LIKE)
    private String email;

    /**
     * 头像
     */
    @ApiModelProperty(value = "portraitUrl")
    @Length(max = 400, message = "The length of email can not exceed 400")
    @TableField(value = "portrait_url", condition = LIKE)
    private String portraitUrl;

    /**
     * gender
     * #gender{F:Female;M:Male;S:Secret;LGBTQ}
     */
    @ApiModelProperty(value = "gender")
    @TableField("gender")
    private Gender gender;

    /**
     * introduction
     */
    @ApiModelProperty(value = "introduction")
    @Length(max = 500, message = "The length of introduction can not exceed 500")
    @TableField(value = "intro", condition = LIKE)
    private String intro;

    /**
     * signature
     */
    @ApiModelProperty(value = "signature")
    @Length(max = 500, message = "The length of signature can not exceed 500")
    @TableField(value = "signature", condition = LIKE)
    private String signature;

    /**
     * topicCount
     * total number of published the topic
     */
    @ApiModelProperty(value = "topicCount")
    @TableField("topic_count")
    private Integer topicCount;

    /**
     * replyCount
     * total number of replying
     */
    @ApiModelProperty(value = "replyCount")
    @TableField("reply_count")
    private Integer replyCount;

    /**
     * bestTopicCount
     * total number of best topic
     */
    @ApiModelProperty(value = "bestTopicCount")
    @TableField("best_topic_count")
    private Integer bestTopicCount;

    /**
     * lastTopicId
     */
    @ApiModelProperty(value = "lastTopicId")
    @TableField("last_topic_id")
    private Long lastTopicId;

    /**
     * lastLogin
     */
    @ApiModelProperty(value = "lastLogin")
    @TableField("last_login")
    private LocalDateTime lastLogin;

    /**
     * amount
     */
    @ApiModelProperty(value = "amount")
    @TableField("amount")
    private Integer amount;

    /**
     * isActive
     */
    @ApiModelProperty(value = "isActive 1-enabled 0-disabled")
    @TableField("is_active")
    private Boolean isActive;


    @Builder
    public User(Long id,
                String username,
                String nickName,
                String password,
                String email,
                String portraitUrl,
                Gender gender,
                String intro,
                String signature,
                Integer topicCount,
                Integer replyCount,
                Integer bestTopicCount,
                Long lastTopicId,
                LocalDateTime lastLogin,
                Integer amount,
                Long createUser,
                LocalDateTime createTime,
                Long updateUser,
                LocalDateTime updateTime,
                Boolean isActive) {
        this.id = id;
        this.username = username;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.portraitUrl = portraitUrl;
        this.gender = gender;
        this.intro = intro;
        this.signature = signature;
        this.topicCount = topicCount;
        this.replyCount = replyCount;
        this.bestTopicCount = bestTopicCount;
        this.lastTopicId = lastTopicId;
        this.lastLogin = lastLogin;
        this.amount = amount;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.isActive = isActive;
    }


}
