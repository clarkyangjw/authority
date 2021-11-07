package net.metaverseapp.bbs.authority.entity.common;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itheima.pinda.base.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 *
 * LoginLog
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bbs_login_log")
@ApiModel(value = "LoginLog", description = "LoginLog")
public class LoginLog extends SuperEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * requestIp
     */
    @ApiModelProperty(value = "requestIp")
    @Length(max = 50, message = "The length of request ip can not exceed 50")
    @TableField(value = "request_ip", condition = LIKE)
    private String requestIp;

    /**
     * userUsername
     */
    @ApiModelProperty(value = "userUsername")
    @Length(max = 30, message = "The length of userUsername can not exceed 30")
    @TableField(value = "user_username", condition = LIKE)
    private String userUsername;

    /**
     * userNickname
     */
    @ApiModelProperty(value = "userNickname")
    @Length(max = 50, message = "The length of userNickname can not exceed 50")
    @TableField(value = "user_nickname", condition = LIKE)
    private String userNickname;

    /**
     * description
     */
    @ApiModelProperty(value = "description")
    @Length(max = 255, message = "The length of description can not exceed 255")
    @TableField(value = "description", condition = LIKE)
    private String description;

    /**
     * loginDatetime
     */
    @ApiModelProperty(value = "loginDatetime")
    @TableField("login_datetime")
    private LocalDate loginDatetime;

    /**
     * Browser request header
     */
    @ApiModelProperty(value = "Browser request header")
    @Length(max = 500, message = "The length of ua can not exceed 500")
    @TableField(value = "ua", condition = LIKE)
    private String ua;

    /**
     * Browser name
     */
    @ApiModelProperty(value = "Browser name")
    @Length(max = 100, message = "The length of ua can not exceed 100")
    @TableField(value = "browser", condition = LIKE)
    private String browser;

    /**
     * Browser version
     */
    @ApiModelProperty(value = "Browser version")
    @Length(max = 255, message = "The length of browser version can not exceed 255")
    @TableField(value = "browser_version", condition = LIKE)
    private String browserVersion;

    /**
     * Operating system
     */
    @ApiModelProperty(value = "Operating system")
    @Length(max = 255, message = "The length of browser version can not exceed 255")
    @TableField(value = "operating_system", condition = LIKE)
    private String operatingSystem;

    /**
     * Login location
     */
    @ApiModelProperty(value = "Login location")
    @Length(max = 50, message = "The length of browser version can not exceed 50")
    @TableField(value = "location", condition = LIKE)
    private String location;


    @Builder
    public LoginLog(Long id, LocalDateTime createTime, Long createUser,
                    String requestIp, String userUsername, String userNickname, String description,
                    LocalDate loginDatetime, String ua, String browser, String browserVersion, String operatingSystem, String location) {
        this.id = id;
        this.createTime = createTime;
        this.createUser = createUser;
        this.requestIp = requestIp;
        this.userUsername = userUsername;
        this.userNickname = userNickname;
        this.description = description;
        this.loginDatetime = loginDatetime;
        this.ua = ua;
        this.browser = browser;
        this.browserVersion = browserVersion;
        this.operatingSystem = operatingSystem;
        this.location = location;
    }

}
