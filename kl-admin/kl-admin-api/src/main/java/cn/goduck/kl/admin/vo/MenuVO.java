package cn.goduck.kl.admin.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/8/27 15:29
 */
@Data
public class MenuVO {

    private Long id;

    private String name;

    private Long parentId;

    private String routeName;

    private String routePath;

    private String component;

    private String redirect;

    private String icon;

    private Integer sort;

    private Boolean visible;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private List<MenuVO> children;

}
