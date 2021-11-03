package com.metaverseapp.bbs.authority.enumeration.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itheima.pinda.base.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Authorize Type
 * </p>
 *
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AuthorizeType", description = "AuthorizeType-enumeration")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AuthorizeType implements BaseEnum {

    /**
     * MENU="MENU"
     */
    MENU("MENU"),
    /**
     * RESOURCE="RESOURCE"
     */
    RESOURCE("RESOURCE"),
    ;

    @ApiModelProperty(value = "description")
    private String desc;


    public static AuthorizeType match(String val, AuthorizeType def) {
        for (AuthorizeType enm : AuthorizeType.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }

    public static AuthorizeType get(String val) {
        return match(val, null);
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(AuthorizeType val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "getCode", allowableValues = "MENU,RESOURCE", example = "MENU")
    public String getCode() {
        return this.name();
    }

}
