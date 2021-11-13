SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `parent_id` bigint(0) NULL DEFAULT 0 COMMENT '父节点id',
  `tree_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '父节点id路径',
  `sort` int(0) NULL DEFAULT 0 COMMENT '显示顺序',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态（0-正常 1-禁用）',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识（0未删除 1已删除）',
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(0) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, '冲鸭技术', 0, '0', 3, 0, 0, 0, '2021-06-20 17:20:00', 2, '2021-09-14 17:55:36');
INSERT INTO `sys_dept` VALUES (2, '研发部门', 1, '0,1', 1, 0, 0, 0, '2021-06-20 17:20:00', 0, '2021-06-20 17:20:00');
INSERT INTO `sys_dept` VALUES (3, '产品部门', 1, '0,1', 2, 0, 0, 0, '2021-06-20 17:20:00', 0, '2021-06-20 17:20:00');
INSERT INTO `sys_dept` VALUES (4, '测试部门', 1, '0,1', 3, 0, 0, 0, '2021-06-20 17:20:00', 0, '2021-06-20 17:20:00');
INSERT INTO `sys_dept` VALUES (1437714290332786689, '人事部门', 1, '0,1', 4, 1, 0, 2, '2021-09-14 17:46:13', 2, '2021-09-15 08:56:50');
INSERT INTO `sys_dept` VALUES (1437716181355692033, '简简单单', 0, '0', 1, 1, 0, 2, '2021-09-14 17:53:44', 2, '2021-09-14 18:08:41');
INSERT INTO `sys_dept` VALUES (1437716635431043073, '轻轻松松', 0, '0', 2, 0, 1, 2, '2021-09-14 17:55:32', 2, '2021-09-14 18:09:26');
INSERT INTO `sys_dept` VALUES (1437718927500738562, '测试公司', 0, '0', 4, 0, 0, 2, '2021-09-14 18:04:38', 2, '2021-09-14 18:04:39');
INSERT INTO `sys_dept` VALUES (1437719145570992129, '平台研发部', 1437718927500738562, '0,1437718927500738562', 1, 0, 0, 2, '2021-09-14 18:05:30', 2, '2021-09-14 18:05:30');
INSERT INTO `sys_dept` VALUES (1437719196292710401, '应用开发部', 1437718927500738562, '0,1437718927500738562', 2, 0, 0, 2, '2021-09-14 18:05:43', 2, '2021-09-14 18:05:43');
INSERT INTO `sys_dept` VALUES (1437719239296909314, '测试部', 1437718927500738562, '0,1437718927500738562', 3, 0, 0, 2, '2021-09-14 18:05:53', 2, '2021-09-14 18:05:53');
INSERT INTO `sys_dept` VALUES (1437719308255461378, '产品规划部', 1437718927500738562, '0,1437718927500738562', 4, 0, 0, 2, '2021-09-14 18:06:09', 2, '2021-09-14 18:06:09');
INSERT INTO `sys_dept` VALUES (1437719393139785729, '产品设计部', 1437718927500738562, '0,1437718927500738562', 5, 0, 0, 2, '2021-09-14 18:06:30', 2, '2021-09-14 18:06:30');
INSERT INTO `sys_dept` VALUES (1437719444960411650, '视觉设计部', 1437718927500738562, '0,1437718927500738562', 6, 0, 0, 2, '2021-09-14 18:06:42', 2, '2021-09-14 18:06:42');
INSERT INTO `sys_dept` VALUES (1437719882245963778, '总经办', 1437718927500738562, '0,1437718927500738562', 7, 0, 0, 2, '2021-09-14 18:08:26', 2, '2021-09-14 18:08:26');
INSERT INTO `sys_dept` VALUES (1437720222282383362, '嘻嘻哈哈', 1437716635431043073, '0,1437716635431043073', 1, 0, 1, 2, '2021-09-14 18:09:47', 2, '2021-09-14 18:09:47');
INSERT INTO `sys_dept` VALUES (1440198368919916545, '轻轻松松', 0, '0', 1, 1, 1, 2, '2021-09-21 14:17:03', 2, '2021-09-21 14:17:08');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint(0) NOT NULL COMMENT '主键 ',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '类型名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '类型编码',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态（0-正常 ,1-停用）',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识（0未删除 1已删除）',
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(0) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, '性别', 'gender', 0, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (2, '授权方式', 'grant_type', 0, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (3, '微服务列表', 'micro_service', 0, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (4, '请求方式', 'request_method', 0, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1446103301863084033, '数据权限', 'data_permission', 0, '备注', 0, 2, '2021-10-07 21:21:09', 2, '2021-10-07 21:21:09');

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典项名称',
  `value` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典项值',
  `dict_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典编码',
  `sort` int(0) NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态（0-正常 ,1-停用）',
  `defaulted` tinyint(1) NULL DEFAULT 0 COMMENT '是否默认（0-否 ,1-是）',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(0) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES (1446469783671357441, '男', '1', 'gender', 2, 0, 1, '', 2, '2021-10-08 21:37:25', 2, '2021-10-08 21:37:25');
INSERT INTO `sys_dict_item` VALUES (1446470571344871425, '女', '2', 'gender', 3, 0, 0, '备注', 2, '2021-10-08 21:40:33', 2, '2021-10-08 21:40:33');
INSERT INTO `sys_dict_item` VALUES (1446470832469655554, '未知', '0', 'gender', 1, 1, 0, '', 2, '2021-10-08 21:41:35', 2, '2021-10-08 21:41:35');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单名称',
  `parent_id` bigint(0) NULL DEFAULT NULL COMMENT '父菜单id',
  `route_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由名称',
  `route_path` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '路由路径',
  `component` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `redirect` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '跳转路径',
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单图标',
  `sort` int(0) NULL DEFAULT 0 COMMENT '排序',
  `visible` tinyint(1) NULL DEFAULT 1 COMMENT '状态（0-开启 1-禁用）',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识（0未删除 1已删除）',
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(0) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 'Admin', '/admin', 'Layout', '', 'table', 1, 0, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (2, '部门管理', 1, 'Dept', 'dept', 'admin/dept/index', '', 'tree', 1, 0, 0, NULL, NULL, 1, '2021-10-04 21:37:20');
INSERT INTO `sys_menu` VALUES (3, '用户管理', 1, 'User', 'user', 'admin/user/index', '', 'user', 2, 0, 0, NULL, NULL, 2, '2021-09-20 09:08:57');
INSERT INTO `sys_menu` VALUES (4, '菜单管理', 1, 'Menu', 'menu', 'admin/menu/index', '', 'tree-table', 3, 0, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (1439758128555503617, '会员管理', 0, 'Ums', '/ums', 'Layout', '', 'people', 2, 1, 0, 2, '2021-09-20 09:07:42', 2, '2021-09-20 09:07:42');
INSERT INTO `sys_menu` VALUES (1439759513896366081, '会员列表', 1439758128555503617, 'Member', 'member', 'ums/member/index', '', 'peoples', 1, 0, 0, 2, '2021-09-20 09:13:12', 2, '2021-09-20 09:13:12');
INSERT INTO `sys_menu` VALUES (1439774084254138369, '外链', 0, 'External-Link', 'external-link', 'Layout', '', 'link', 3, 0, 0, 2, '2021-09-20 10:11:06', 2, '2021-09-20 10:11:06');
INSERT INTO `sys_menu` VALUES (1439775094007660546, '百度一下', 1439774084254138369, 'Baidu', 'https://www.baidu.com', '', '', 'search', 1, 0, 0, 2, '2021-09-20 10:15:07', 2, '2021-09-20 10:15:07');
INSERT INTO `sys_menu` VALUES (1439884466654294018, '商品管理', 0, 'Goods', '/goods', 'Layout', '', 'guide', 4, 0, 1, 2, '2021-09-20 17:29:43', 2, '2021-09-20 17:29:43');
INSERT INTO `sys_menu` VALUES (1440211249107406849, 'Nacos', 1439774084254138369, 'Nacos', 'http://nacos-server:8848/nacos', NULL, '', 'monitor', 2, 0, 0, 2, '2021-09-21 15:08:14', 2, '2021-09-21 15:08:14');
INSERT INTO `sys_menu` VALUES (1440211374445793282, 'Sentinel', 1439774084254138369, 'Sentinel', 'http://sentinel-server:8088/', NULL, '', 'dashboard', 3, 0, 0, 2, '2021-09-21 15:08:44', 2, '2021-09-21 15:08:44');
INSERT INTO `sys_menu` VALUES (1440231683861348353, '角色管理', 1, 'Role', 'role', 'admin/role/index', '', 'theme', 4, 0, 0, 2, '2021-09-21 16:29:26', 2, '2021-09-21 16:29:26');
INSERT INTO `sys_menu` VALUES (1440232302433107970, '字典管理', 1, 'Dict', 'dict', 'admin/dict/index', '', 'education', 5, 0, 0, 2, '2021-09-21 16:31:54', 2, '2021-09-21 16:31:54');
INSERT INTO `sys_menu` VALUES (1440232648731623426, '客户端管理', 1, 'Client', 'client', 'admin/client/index', '', 'tab', 6, 0, 0, 2, '2021-09-21 16:33:16', 2, '2021-09-21 16:33:16');

-- ----------------------------
-- Table structure for sys_oauth_client
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client`;
CREATE TABLE `sys_oauth_client`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `client_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端id',
  `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源id列表',
  `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端密钥',
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '域',
  `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权方式',
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限列表',
  `access_token_validity` int(0) NULL DEFAULT NULL COMMENT '认证令牌时效',
  `refresh_token_validity` int(0) NULL DEFAULT NULL COMMENT '刷新令牌时效',
  `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展信息',
  `auto_approve` tinyint(1) NULL DEFAULT NULL COMMENT '是否自动放行',
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(0) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户端表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oauth_client
-- ----------------------------
INSERT INTO `sys_oauth_client` VALUES (1, 'admin', NULL, '123456', 'all', 'password,client_credentials,refresh_token,authorization_code', NULL, NULL, 36000, 7200, NULL, 1, 0, '2021-06-20 17:20:00', 0, '2021-06-20 17:20:00');
INSERT INTO `sys_oauth_client` VALUES (2, 'client', NULL, '123456', 'all', 'authorization_code,password,refresh_token,implicit', NULL, NULL, 36000, 7200, NULL, 1, 0, '2021-06-20 17:20:00', 0, '2021-06-20 17:20:00');
INSERT INTO `sys_oauth_client` VALUES (3, 'wx_app', NULL, '123456', 'all', 'password,client_credentials,refresh_token,authorization_code', NULL, NULL, 36000, 7200, NULL, 1, NULL, NULL, 2, '2021-10-08 20:44:46');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `menu_id` bigint(0) NULL DEFAULT NULL COMMENT '菜单模块id\r\n',
  `url_perm` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'URL权限标识',
  `btn_perm` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '按钮权限标识',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识（0未删除 1已删除）',
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(0) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, '查看用户', 3, 'GET:/admin/users/*', 'admin:users:view', 0, NULL, NULL, 2, '2021-09-20 16:58:56');
INSERT INTO `sys_permission` VALUES (2, '新增用户', 3, 'POST:/admin/users', 'admin:users:add', 0, NULL, NULL, 2, '2021-09-20 16:57:24');
INSERT INTO `sys_permission` VALUES (3, '修改用户', 3, 'PUT:/admin/users/*', 'admin:users:edit', 0, NULL, NULL, 2, '2021-09-20 16:58:48');
INSERT INTO `sys_permission` VALUES (4, '删除用户', 3, 'DELETE:/admin/users/*', 'admin:users:delete', 0, NULL, NULL, 2, '2021-09-20 17:05:13');
INSERT INTO `sys_permission` VALUES (5, '导入用户', 3, 'POST:/admin/users/excelImport', 'admin:users:import', 0, NULL, NULL, 2, '2021-09-20 17:05:19');
INSERT INTO `sys_permission` VALUES (6, '导出用户', 3, 'POST:/admin/users/excelExport', 'admin:users:export', 0, NULL, NULL, 2, '2021-09-20 17:05:22');
INSERT INTO `sys_permission` VALUES (7, '重置密码', 3, NULL, 'admin:users:reset', 0, 2, '2021-09-20 17:15:15', 2, '2021-09-20 17:15:15');
INSERT INTO `sys_permission` VALUES (1445009626793873409, '用户分页', 3, 'GET:/admin/users', 'admin:users:page', 0, 1, '2021-10-04 20:55:17', 1, '2021-10-04 20:55:17');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(0) NOT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '角色名称',
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `sort` int(0) NULL DEFAULT NULL COMMENT '显示顺序',
  `data_scope` int(0) NULL DEFAULT NULL COMMENT '数据权限',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '角色状态（0正常 1停用）',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识（0未删除 1已删除）',
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(0) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'ROOT', 1, 0, 0, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (2, '系统管理员', 'ADMIN', 3, 1, 0, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (3, '测试', 'TEST', 2, 2, 0, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (4, '游客', 'GUEST', 4, 4, 0, 0, NULL, NULL, 1, '2021-10-04 16:11:56');
INSERT INTO `sys_role` VALUES (1444938689088065537, '哈哈角色', 'HAHA', 5, 3, 0, 0, 1, '2021-10-04 16:13:24', 1, '2021-10-04 16:13:24');
INSERT INTO `sys_role` VALUES (1444938787834564610, '嘻嘻角色', 'XIXI', 6, 2, 0, 0, 1, '2021-10-04 16:13:47', 1, '2021-10-04 16:13:47');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `role_id` bigint(0) NOT NULL COMMENT '角色id',
  `menu_id` bigint(0) NOT NULL COMMENT '菜单id',
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(0) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色-菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (2, 1, 2, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (3, 1, 3, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (7, 1, 4, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1444957193505955842, 2, 1, 1, '2021-10-04 17:26:56', 1, '2021-10-04 17:26:56');
INSERT INTO `sys_role_menu` VALUES (1444957193526927361, 2, 2, 1, '2021-10-04 17:26:56', 1, '2021-10-04 17:26:56');
INSERT INTO `sys_role_menu` VALUES (1444957193526927362, 2, 3, 1, '2021-10-04 17:26:56', 1, '2021-10-04 17:26:56');
INSERT INTO `sys_role_menu` VALUES (1444957193526927363, 2, 4, 1, '2021-10-04 17:26:56', 1, '2021-10-04 17:26:56');
INSERT INTO `sys_role_menu` VALUES (1444957193526927364, 2, 1440231683861348353, 1, '2021-10-04 17:26:56', 1, '2021-10-04 17:26:56');
INSERT INTO `sys_role_menu` VALUES (1444957193526927365, 2, 1440232302433107970, 1, '2021-10-04 17:26:56', 1, '2021-10-04 17:26:56');
INSERT INTO `sys_role_menu` VALUES (1444957193526927366, 2, 1440232648731623426, 1, '2021-10-04 17:26:56', 1, '2021-10-04 17:26:56');
INSERT INTO `sys_role_menu` VALUES (1444957193526927369, 2, 1439774084254138369, 1, '2021-10-04 17:26:56', 1, '2021-10-04 17:26:56');
INSERT INTO `sys_role_menu` VALUES (1444957193526927370, 2, 1439775094007660546, 1, '2021-10-04 17:26:56', 1, '2021-10-04 17:26:56');
INSERT INTO `sys_role_menu` VALUES (1444957193526927371, 2, 1440211249107406849, 1, '2021-10-04 17:26:56', 1, '2021-10-04 17:26:56');
INSERT INTO `sys_role_menu` VALUES (1444957193526927372, 2, 1440211374445793282, 1, '2021-10-04 17:26:56', 1, '2021-10-04 17:26:56');
INSERT INTO `sys_role_menu` VALUES (1444960040360435714, 2, 1439758128555503617, 1, '2021-10-04 17:38:14', 1, '2021-10-04 17:38:14');
INSERT INTO `sys_role_menu` VALUES (1444960040360435715, 2, 1439759513896366081, 1, '2021-10-04 17:38:14', 1, '2021-10-04 17:38:14');
INSERT INTO `sys_role_menu` VALUES (1445000335869448193, 4, 1439774084254138369, 1, '2021-10-04 20:18:22', 1, '2021-10-04 20:18:22');
INSERT INTO `sys_role_menu` VALUES (1445001411670675457, 4, 1440211249107406849, 1, '2021-10-04 20:22:38', 1, '2021-10-04 20:22:38');
INSERT INTO `sys_role_menu` VALUES (1445008564913541121, 1444938787834564610, 1, 1, '2021-10-04 20:51:04', 1, '2021-10-04 20:51:04');
INSERT INTO `sys_role_menu` VALUES (1445008564913541122, 1444938787834564610, 3, 1, '2021-10-04 20:51:04', 1, '2021-10-04 20:51:04');
INSERT INTO `sys_role_menu` VALUES (1445008607640915970, 1444938689088065537, 1, 1, '2021-10-04 20:51:14', 1, '2021-10-04 20:51:14');
INSERT INTO `sys_role_menu` VALUES (1445008607640915971, 1444938689088065537, 2, 1, '2021-10-04 20:51:14', 1, '2021-10-04 20:51:14');
INSERT INTO `sys_role_menu` VALUES (1445012085926891522, 1, 1439758128555503617, 1, '2021-10-04 21:05:03', 1, '2021-10-04 21:05:03');
INSERT INTO `sys_role_menu` VALUES (1445012085926891523, 1, 1439759513896366081, 1, '2021-10-04 21:05:03', 1, '2021-10-04 21:05:03');
INSERT INTO `sys_role_menu` VALUES (1445012085926891524, 1, 1439774084254138369, 1, '2021-10-04 21:05:03', 1, '2021-10-04 21:05:03');
INSERT INTO `sys_role_menu` VALUES (1445012085926891525, 1, 1439775094007660546, 1, '2021-10-04 21:05:03', 1, '2021-10-04 21:05:03');
INSERT INTO `sys_role_menu` VALUES (1445012085926891526, 1, 1440211249107406849, 1, '2021-10-04 21:05:03', 1, '2021-10-04 21:05:03');
INSERT INTO `sys_role_menu` VALUES (1445012085926891527, 1, 1440211374445793282, 1, '2021-10-04 21:05:03', 1, '2021-10-04 21:05:03');
INSERT INTO `sys_role_menu` VALUES (1445012085926891528, 1, 1440231683861348353, 1, '2021-10-04 21:05:03', 1, '2021-10-04 21:05:03');
INSERT INTO `sys_role_menu` VALUES (1445012085926891529, 1, 1440232302433107970, 1, '2021-10-04 21:05:03', 1, '2021-10-04 21:05:03');
INSERT INTO `sys_role_menu` VALUES (1445012085989806081, 1, 1440232648731623426, 1, '2021-10-04 21:05:03', 1, '2021-10-04 21:05:03');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `role_id` bigint(0) NOT NULL COMMENT '角色id',
  `permission_id` bigint(0) NOT NULL COMMENT '资源id',
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(0) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色-权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `dept_id` bigint(0) NULL DEFAULT NULL COMMENT '部门id',
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `nickname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `gender` tinyint(1) NULL DEFAULT 0 COMMENT '性别',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户头像',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '用户状态',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识（0未删除 1已删除）',
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(0) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 1, 'root', '超级管理员', 1, '$2a$10$P97nHj/AVu6JBVCxmj5qEOwsI7rUhFeyu.DrK4ER7sebzv8jp7R5S', 'https://img2.baidu.com/it/u=1134120401,739791189&fm=26&fmt=auto&gp=0.jpg', '18650477443', '821863269@qq.com', 0, 0, 0, '2021-06-20 17:20:00', 0, '2021-05-20 17:20:00');
INSERT INTO `sys_user` VALUES (2, 2, 'admin', '系统管理员', 1, '$2a$10$yJSqqr6sTxNuYtA6EKcVUe2I4USFCzJ29sNcRrBvtAkSYcNg5ydQ6', 'https://img2.baidu.com/it/u=1134120401,739791189&fm=26&fmt=auto&gp=0.jpg', '18650477443', '821863269@qq.com', 0, 0, 0, '2021-06-20 17:20:00', 0, '2021-06-20 17:20:00');
INSERT INTO `sys_user` VALUES (3, 3, 'test', '测试用户', 0, '$2a$10$MPJkNw.hKT/fZOgwYP8q9eu/rFJJDsNov697AmdkHNJkpjIpVSw2q', 'https://img2.baidu.com/it/u=1134120401,739791189&fm=26&fmt=auto&gp=0.jpg', '18650477443', '821863269@qq.com', 0, 0, 0, '2021-06-20 17:20:00', 0, '2021-07-20 17:20:00');
INSERT INTO `sys_user` VALUES (1435778528146096129, 4, 'hello', '哈喽', 0, '$2a$10$Er6Q9HyM/tXLMbrpRp3I..M95T6xQXD.zBFv.Jkl4DzoMOYg8dR1G', '', '18650477444', 'hello@qq.com', 0, 0, 2, '2021-09-09 09:34:11', 1, '2021-09-10 16:49:29');
INSERT INTO `sys_user` VALUES (1435791598834724865, 3, 'haha', '哈哈哈哈', 1, '$2a$10$Lb0G1X6NtnnElEa64v2ZmO2P45OgR971R4uNaodSoXge5VybtHUOS', '', '18650477777', 'haha@qq.com', 0, 0, 2, '2021-09-09 10:26:02', 1, '2021-09-10 16:48:50');
INSERT INTO `sys_user` VALUES (1436902728088948737, 4, 'xixi', '嘻嘻', 1, '$2a$10$YXsMjiPQG708pLjE9NLvwO0QUS6scGgIP/GTf0Lm8vdEtLRRiTrUu', '', '18650477777', 'xixi@qq.com', 0, 0, 2, '2021-09-12 12:01:21', 2, '2021-09-12 12:01:21');
INSERT INTO `sys_user` VALUES (1437011023453847554, 4, 'boy', '男孩', 1, '$2a$10$fFCFVnl1BUQOXMshLTM6leLHyZL/UKiEMsTmEsxGMOnbqoc5QcT3O', '', '18650123456', 'boy@qq.com', 0, 0, 2, '2021-09-12 19:11:41', 2, '2021-09-12 19:11:41');
INSERT INTO `sys_user` VALUES (1437011024145907713, 2, 'girl', '女孩', 0, '$2a$10$3pV5/JlBdQRl1aiUHYfna.hxfvBiLdzyewXobJYaQWr2K113TYDK.', '', '18650123456', 'girl@qq.com', 1, 0, 2, '2021-09-12 19:11:41', 2, '2021-09-12 19:11:41');
INSERT INTO `sys_user` VALUES (1440199311174504449, 4, 'haizi', '孩子', 2, '$2a$10$rKbnIMP5zn/fTC4Hej3ceeUN02MfOZtIykRnOHHLqshGvDRaviBpG', '', '18650477777', 'haizi@qq.com', 0, 0, 2, '2021-09-21 14:20:48', 2, '2021-09-21 14:21:04');
INSERT INTO `sys_user` VALUES (1440207592504791042, 4, 'shuaige', '帅哥', 1, '$2a$10$pSp4uCu8Vve5YiIYWEo8ouUtWSYEmAgLZbfp/whidgkXef3gDsfAy', '', '18650666666', 'shuaige@qq.com', 0, 0, 2, '2021-09-21 14:53:42', 2, '2021-09-21 14:53:42');
INSERT INTO `sys_user` VALUES (1444952559781416961, 1437719882245963778, 'zjb', '总经办', 1, '$2a$10$QdH7eq/7MKKiFt9OcNriKeB3K0dv2xqcZb/xbSKIORp/yYVG8LtPC', '', '13023880201', 'zjb@qq.com', 0, 0, 1, '2021-10-04 17:08:31', 1, '2021-10-04 17:11:23');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `user_id` bigint(0) NOT NULL COMMENT '用户id',
  `role_id` bigint(0) NOT NULL COMMENT '角色id',
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(0) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户-角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` VALUES (2, 2, 2, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` VALUES (3, 3, 3, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` VALUES (1435778528406142977, 1435778528146096129, 2, 2, '2021-09-09 09:34:11', 2, '2021-09-09 09:34:11');
INSERT INTO `sys_user_role` VALUES (1435778528414531585, 1435778528146096129, 3, 2, '2021-09-09 09:34:11', 2, '2021-09-09 09:34:11');
INSERT INTO `sys_user_role` VALUES (1435791722596052993, 1435791598834724865, 2, 2, '2021-09-09 10:26:31', 2, '2021-09-09 10:26:37');
INSERT INTO `sys_user_role` VALUES (1436902428854718466, 1436902428645003265, 4, 2, '2021-09-12 12:00:10', 2, '2021-09-12 12:00:10');
INSERT INTO `sys_user_role` VALUES (1436902728273498113, 1436902728088948737, 4, 2, '2021-09-12 12:01:21', 2, '2021-09-12 12:01:21');
INSERT INTO `sys_user_role` VALUES (1436918320380583938, 1436918320158285826, 3, NULL, '2021-09-12 13:03:19', NULL, '2021-09-12 13:03:19');
INSERT INTO `sys_user_role` VALUES (1436918321005535233, 1436918320841957378, 3, NULL, '2021-09-12 13:03:19', NULL, '2021-09-12 13:03:19');
INSERT INTO `sys_user_role` VALUES (1437008316408102914, 1437008316198387713, 3, 2, '2021-09-12 19:00:56', 2, '2021-09-12 19:00:56');
INSERT INTO `sys_user_role` VALUES (1437008317041442818, 1437008316865282050, 3, 2, '2021-09-12 19:00:56', 2, '2021-09-12 19:00:56');
INSERT INTO `sys_user_role` VALUES (1437010221951713282, 1437010221792329729, 3, 2, '2021-09-12 19:08:30', 2, '2021-09-12 19:08:30');
INSERT INTO `sys_user_role` VALUES (1437010222555693058, 1437010222396309506, 3, 2, '2021-09-12 19:08:30', 2, '2021-09-12 19:08:30');
INSERT INTO `sys_user_role` VALUES (1437010361949192193, 1437010361781420034, 3, 2, '2021-09-12 19:09:03', 2, '2021-09-12 19:09:03');
INSERT INTO `sys_user_role` VALUES (1437010362553171969, 1437010362402177025, 3, 2, '2021-09-12 19:09:03', 2, '2021-09-12 19:09:03');
INSERT INTO `sys_user_role` VALUES (1437011023630008321, 1437011023453847554, 3, 2, '2021-09-12 19:11:41', 2, '2021-09-12 19:11:41');
INSERT INTO `sys_user_role` VALUES (1437011024326262785, 1437011024145907713, 3, 2, '2021-09-12 19:11:41', 2, '2021-09-12 19:11:41');
INSERT INTO `sys_user_role` VALUES (1440199311434551297, 1440199311174504449, 3, 2, '2021-09-21 14:20:48', 2, '2021-09-21 14:20:48');
INSERT INTO `sys_user_role` VALUES (1440207592764837889, 1440207592504791042, 3, 2, '2021-09-21 14:53:43', 2, '2021-09-21 14:53:43');
INSERT INTO `sys_user_role` VALUES (1444952560104378369, 1444952559781416961, 1444938689088065537, 1, '2021-10-04 17:08:31', 1, '2021-10-04 17:08:31');
INSERT INTO `sys_user_role` VALUES (1444953282258714625, 1444952559781416961, 1444938787834564610, 1, '2021-10-04 17:11:23', 1, '2021-10-04 17:11:23');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `id` bigint(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_by` bigint(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_by` bigint(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES (1445199777142235138, 'y02uu', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777293230082, '0nxu4', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777293230083, '59yye', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777297424386, 'v1cfs', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777297424387, 'kuhpt', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777297424388, 'hzy8k', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777301618689, '05gs7', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777301618690, 'm5pxf', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777305812994, '1g5ck', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777310007298, 'vx4s3', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777310007299, 'wyy4p', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777314201602, 'yhj8t', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777314201603, 'jzhs6', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777314201604, '63qqp', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777318395905, 'azhws', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777318395906, '48jhp', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777322590209, '2rp62', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777326784514, 'dak2z', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777326784515, 'po287', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777330978818, '2djr6', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777330978819, 'u33te', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777330978820, '3cqxw', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777335173121, 'dhisu', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777335173122, '25npk', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777335173123, 'ds9rk', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777335173124, 'a2odx', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777339367425, 'f7v7f', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777339367426, '2jl3z', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777339367427, 'cxfdn', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777343561729, 'qlnl6', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777343561730, 'os2n8', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777343561731, 'vydde', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777347756033, 'pleqc', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777347756034, '1qsjg', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777347756035, '7qojh', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777347756036, 'f4xyx', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777351950337, 'mnk59', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777351950338, 'm595s', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777351950339, 'vlp1q', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777351950340, 'a5301', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777356144641, 'j4saw', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777356144642, 'klt9b', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777356144643, 'c1ojv', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777360338946, 'phnu1', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777360338947, 'h4h4x', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777360338948, 'wprrg', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777360338949, 'of2ll', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777364533250, 'uxj5p', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777364533251, 'wzas5', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199777364533252, 'b5dps', NULL, '2021-10-05 09:30:52', NULL, '2021-10-05 09:30:52');
INSERT INTO `test` VALUES (1445199781898575873, '0260k', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781902770178, 'o22i8', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781906964481, 'kt3jq', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781906964482, 'svzpr', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781911158785, 'ci1af', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781911158786, 'n23pk', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781911158787, '0xtu0', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781915353090, 'ux8ct', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781915353091, 'ceud3', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781919547393, 'r1iaw', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781919547394, 'a5682', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781923741697, 'vbmpl', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781923741698, 'q8m95', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781927936002, '6jw4t', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781932130306, 'rwl6y', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781932130307, 'ffezh', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781936324609, 'pk387', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781936324610, 'ixk6w', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781940518914, 'oq5xi', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781940518915, '2tsvl', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781940518916, 'orvkv', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781944713217, 'iv8e7', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781944713218, 'up0rd', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781948907522, 'm7caq', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781948907523, 'j5njv', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781953101826, '61r19', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781953101827, 's4ph0', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781957296129, '2kj8s', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781961490433, 'u17pd', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781961490434, 'hg30c', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781961490435, 'gotp8', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781961490436, '8941f', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781965684738, '3day1', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781965684739, 'f5qkd', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781969879041, 'vz7lm', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781969879042, 'c3yef', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781974073346, 'w11wl', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781974073347, 'n5634', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781978267650, 'd1ec0', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781978267651, 'atiy4', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781982461954, 'ow2rz', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781982461955, 'v8yzu', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781982461956, 'rzfom', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781986656258, '667kp', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781986656259, 'qrya5', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781990850562, '0oyp3', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781990850563, 'bdad7', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781995044865, 'bbs8a', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781995044866, 'ry0cb', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');
INSERT INTO `test` VALUES (1445199781999239169, 'b8mqd', NULL, '2021-10-05 09:30:53', NULL, '2021-10-05 09:30:53');

SET FOREIGN_KEY_CHECKS = 1;
