package cn.goduck.kl.gateway.controller;

import cn.goduck.kl.common.core.base.R;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Desc: 网关流量规则控制器
 * Author: Kon
 * Date: 2021/6/3 17:39
 */
@RestController
public class GatewayFlowRuleController {

    /**
     * 获取当前系统的限流策略
     */
    @GetMapping("/gw/flow/rules")
    public R<Set<GatewayFlowRule>> getCurrentGatewayFlowRules() {
        return R.ok(GatewayRuleManager.getRules());
    }

    /**
     * 获取自定义的api分组
     */
    @GetMapping("/gw/api/groups")
    public R<Set<ApiDefinition>> getApiGroups() {
        return R.ok(GatewayApiDefinitionManager.getApiDefinitions());
    }

}