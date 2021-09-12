## 项目介绍

`keep-learn` 保持学习

``` lua
keep-learn
├── docs
    ├── nacos -- Nacos配置文件
    ├── sql   -- mysql数据库脚本
├── kl-admin 
    ├── kl-admin-api  -- 系统管理服务Feign接口客户端
    ├── kl-admin-boot -- 系统管理服务（8100）
├── kl-auth           -- 认证中心服务（8000）【OAuth2认证服务器】
├── kl-common         -- 公共模块
├── kl-gateway        -- Gateway网关（10101）【OAuth2资源服务器】
└── kl-test-boot      -- boot单体服务（9999）
```