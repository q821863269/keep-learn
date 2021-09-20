package cn.goduck.kl.common.core.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/9/19 15:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeVO {

    private Long id;

    private String label;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private List<TreeVO> children;

}
