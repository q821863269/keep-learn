package cn.goduck.kl.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Desc:
 * Author: Kon
 * Date: 2021-06-20 15:48
 */
@Getter
@AllArgsConstructor
public enum PasswordEncoderTypeEnum {

    NOOP("{noop}", "无加密明文"),
    BCRYPT("{bcrypt}", "BCRYPT加密");

    private final String prefix;

    private final String desc;

}