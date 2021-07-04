package cn.goduck.kl.test.boot.service.impl;

import cn.goduck.kl.test.boot.domain.Test;
import cn.goduck.kl.test.boot.mapper.TestMapper;
import cn.goduck.kl.test.boot.service.TestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/18 9:05
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

}