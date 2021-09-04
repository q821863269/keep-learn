package cn.goduck.kl.admin.vo;

import lombok.Data;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/8/30 9:31
 */
@Data
public class DeptVO {

    private Integer id;

    private String name;

    private Integer parentId;

    private String treePath;

    private Integer sort;

    private Integer status;

    private List<DeptVO> children;

}
