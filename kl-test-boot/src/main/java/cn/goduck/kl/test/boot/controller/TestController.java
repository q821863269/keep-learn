package cn.goduck.kl.test.boot.controller;

import cn.goduck.kl.common.core.base.R;
import cn.goduck.kl.test.boot.domain.Test;
import cn.goduck.kl.test.boot.service.TestService;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/17 16:41
 */
@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping
    public R<Page<Test>> page(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize
    ) {
        Page<Test> page = testService.page(new Page<>(pageNum, pageSize));
        return R.ok(page);
    }

    @PostMapping
    public R<Boolean> insert() {
        List<Test> testList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String name = RandomUtil.randomString(5);
            Test test = new Test();
            test.setName(name);
            testList.add(test);
        }
        return R.judge(testService.saveBatch(testList, 50));
    }

    @PutMapping("/{id}")
    public R<Boolean> update(@PathVariable("id") Long id, String name) {
        Test test = new Test();
        test.setId(id);
        test.setName(name);
        return R.judge(testService.updateById(test));
    }

    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable("id") Long id) {
        return R.judge(testService.removeById(id));
    }

    @GetMapping("/list")
    public R<List<Test>> list() {
        return R.ok(testService.list());
    }

    @GetMapping("/null")
    public Object returnNull(){
        return null;
    }

}