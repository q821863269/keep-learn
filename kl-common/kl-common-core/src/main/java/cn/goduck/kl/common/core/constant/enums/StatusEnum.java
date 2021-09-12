package cn.goduck.kl.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/9/12 9:35
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum StatusEnum {

    ENABLE("启用", false),
    DISABLE("禁用", true);

    private String name;

    private Boolean value;

    public static String getName(Boolean value) {
        for (StatusEnum e : values()) {
            if (e.getValue().equals(value)) {
                return e.getName();
            }
        }
        return null;
    }

    public static Boolean getValue(String name) {
        for (StatusEnum e : values()) {
            if (e.getName().equals(name)) {
                return e.getValue();
            }
        }
        return null;
    }

    public static StatusEnum getEnum(String name) {
        for (StatusEnum e : values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

}
