package cn.goduck.kl.admin.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/8/30 9:31
 */
@Data
public class DeptVO {

    private Long id;

    private String name;

    private Long parentId;

    private String treePath;

    private Integer sort;

    private Boolean status;

    private LocalDateTime updateTime;

    private List<DeptVO> children;

}
