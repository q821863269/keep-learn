package cn.goduck.kl.test.boot.controller;

import cn.goduck.kl.test.boot.domain.DemoData;
import cn.goduck.kl.test.boot.domain.Test;
import cn.goduck.kl.test.boot.service.TestService;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
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

    private final RedisTemplate<String, Object> redisTemplate;
    private final RedissonClient redissonClient;
    private final TestService testService;

    @GetMapping("/get")
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @GetMapping("/set")
    public Object set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        return "ok";
    }

    @GetMapping("/redisson")
    public Object redisson() {
        Iterable<String> keys = redissonClient.getKeys().getKeys();
        ArrayList<String> list = Lists.newArrayList();
        keys.forEach(list::add);
        return list;
    }

    @GetMapping("/list")
    public Object list() {
        return testService.list();
    }

    @GetMapping
    public Object page(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize
    ) {
        Page<Test> page = testService.page(new Page<>(pageNum, pageSize));
        return page.getRecords();
    }

    @PostMapping
    public Object insert() {
        List<Test> testList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String name = RandomUtil.randomString(5);
            Test test = new Test();
            test.setName(name);
            testList.add(test);
        }
        testService.saveBatch(testList, 50);
        return "ok";
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable("id") Long id, String name) {
        Test test = new Test();
        test.setId(id);
        test.setName(name);
        return testService.updateById(test);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") Long id) {
        return testService.removeById(id);
    }

    /**
     * 大数据量Excel写出测试
     */
    @SneakyThrows
    @GetMapping("/download")
    public void download(HttpServletResponse response) {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("大数据量Excel导出测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        ExcelWriter excelWriter = null;
        try {
            // 这里 需要指定写用哪个class去写
            excelWriter = EasyExcel.write(response.getOutputStream(), DemoData.class).build();
            // 这里注意 如果同一个sheet只要创建一次
            WriteSheet writeSheet = null;
            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
            for (int i = 0; i < 10; i++) {
                if (i % 2 == 0) {
                    writeSheet = EasyExcel.writerSheet("my-sheet-" + ((i / 2) + 1)).build();
                }
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                List<DemoData> data = DemoData.data();
                excelWriter.write(data, writeSheet);
            }
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

}