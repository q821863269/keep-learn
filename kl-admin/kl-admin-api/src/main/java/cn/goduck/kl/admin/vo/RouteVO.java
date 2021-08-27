package cn.goduck.kl.admin.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/8/27 15:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
// 属性为 空（“”） 或者为 NULL 都不序列化
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouteVO {

    private String path;

    private String component;

    private String redirect;

    private Boolean alwaysShow;

    private String name;

    private Boolean hidden;

    private Meta meta;

    private List<RouteVO> children;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Meta {

        private String title;

        private String icon;

        private List<String> roles;

    }

}
