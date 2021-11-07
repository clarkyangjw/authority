package net.metaverseapp.bbs.authority.enumeration.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itheima.pinda.base.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Gender
 * </p>
 *
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Gender", description = "Gender-enumeration")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Gender implements BaseEnum {

    F("Female"),
    M("Male"),
    L("Lesbian"),
    G("Gay"),
    B("Bisexual"),
    T("Transgender"),
    Q("Queer"),
    S("Secret"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;


    public static Gender match(String val, Gender def) {
        for (Gender enm : Gender.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }

    public static Gender get(String val) {
        return match(val, null);
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(Gender val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "F,M,L,G,B,T,Q,S", example = "S")
    public String getCode() {
        return this.name();
    }

}
