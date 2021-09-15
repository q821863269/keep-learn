package cn.goduck.kl.admin.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeVO {

    private Long id;

    private String label;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private List<TreeVO> children;

}