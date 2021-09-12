package cn.goduck.kl.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/9/12 9:56
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum GenderEnum {

    MALE("男", 1),
    FEMALE("女", 0),
    UNKNOWN("未知", 2);

    private String name;

    private Integer value;

    public static String getName(Integer value) {
        for (GenderEnum e : values()) {
            if (e.getValue().equals(value)) {
                return e.getName();
            }
        }
        return null;
    }

    public static Integer getValue(String name) {
        for (GenderEnum e : values()) {
            if (e.getName().equals(name)) {
                return e.getValue();
            }
        }
        return null;
    }

    public static GenderEnum getEnum(String name) {
        for (GenderEnum e : values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }
    
}
