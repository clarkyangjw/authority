package net.metaverseapp.bbs.authority.entity.common;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itheima.pinda.base.entity.SuperEntity;
import com.itheima.pinda.common.enums.HttpMethod;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import net.metaverseapp.bbs.authority.enumeration.common.LogType;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 系统日志
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bbs_opt_log")
@ApiModel(value = "OptLog", description = "OptLog")
public class OptLog extends SuperEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * request ip
     */
    @ApiModelProperty(value = "request ip")
    @Length(max = 50, message = "The length of request ip can not exceed 50")
    @TableField(value = "request_ip", condition = LIKE)
    private String requestIp;

    /**
     * LogType
     * #LogType{OPT:operation;EX:exception}
     */
    @ApiModelProperty(value = "LogType")
    @TableField("type")
    private LogType type;

    /**
     * userName
     */
    @ApiModelProperty(value = "userName")
    @Length(max = 50, message = "The length of userName can not exceed 50")
    @TableField(value = "user_name", condition = LIKE)
    private String userName;

    /**
     * description
     */
    @ApiModelProperty(value = "description")
    @Length(max = 255, message = "The length of description can not exceed 255")
    @TableField(value = "description", condition = LIKE)
    private String description;

    /**
     * classPath
     */
    @ApiModelProperty(value = "classPath")
    @Length(max = 255, message = "The length of classPath can not exceed 255")
    @TableField(value = "class_path", condition = LIKE)
    private String classPath;

    /**
     * actionMethod
     */
    @ApiModelProperty(value = "actionMethod")
    @Length(max = 50, message = "The length of classPath can not exceed 50")
    @TableField(value = "action_method", condition = LIKE)
    private String actionMethod;

    /**
     * requestUri
     */
    @ApiModelProperty(value = "requestUri")
    @Length(max = 50, message = "The length of requestUri can not exceed 50")
    @TableField(value = "request_uri", condition = LIKE)
    private String requestUri;

    /**
     * httpMethod
     * #HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}
     */
    @ApiModelProperty(value = "httpMethod")
    @TableField("http_method")
    private HttpMethod httpMethod;

    /**
     * parameters
     */
    @ApiModelProperty(value = "parameters")
    @TableField("params")
    private String params;

    /**
     * result
     */
    @ApiModelProperty(value = "result")
    @TableField("result")
    private String result;

    /**
     * Exception description
     */
    @ApiModelProperty(value = "Exception description")
    @TableField("ex_desc")
    private String exDesc;

    /**
     * Exception detail
     */
    @ApiModelProperty(value = "Exception detail")
    @TableField("ex_detail")
    private String exDetail;

    /**
     * startTime
     */
    @ApiModelProperty(value = "startTime")
    @TableField("start_time")
    private LocalDateTime startTime;

    /**
     * finishTime
     */
    @ApiModelProperty(value = "finishTime")
    @TableField("finish_time")
    private LocalDateTime finishTime;

    /**
     * consumingTime
     */
    @ApiModelProperty(value = "consumingTime")
    @TableField("consuming_time")
    private Long consumingTime;

    /**
     * Browser
     */
    @ApiModelProperty(value = "Browser")
    @Length(max = 500, message = "The length of ua can not exceed 50")
    @TableField(value = "ua", condition = LIKE)
    private String ua;


    @Builder
    public OptLog(Long id, LocalDateTime createTime, Long createUser,
                  String requestIp, LogType type, String userName, String description, String classPath,
                  String actionMethod, String requestUri, HttpMethod httpMethod, String params, String result, String exDesc,
                  String exDetail, LocalDateTime startTime, LocalDateTime finishTime, Long consumingTime, String ua) {
        this.id = id;
        this.createTime = createTime;
        this.createUser = createUser;
        this.requestIp = requestIp;
        this.type = type;
        this.userName = userName;
        this.description = description;
        this.classPath = classPath;
        this.actionMethod = actionMethod;
        this.requestUri = requestUri;
        this.httpMethod = httpMethod;
        this.params = params;
        this.result = result;
        this.exDesc = exDesc;
        this.exDetail = exDetail;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.consumingTime = consumingTime;
        this.ua = ua;
    }

}
