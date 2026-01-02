-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.5.12-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 my_demo_cli 的数据库结构
DROP DATABASE IF EXISTS `my_demo_cli`;
CREATE DATABASE IF NOT EXISTS `my_demo_cli` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `my_demo_cli`;

-- 导出  表 my_demo_cli.sms_admin 结构
DROP TABLE IF EXISTS `sms_admin`;
CREATE TABLE IF NOT EXISTS `sms_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `title` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '职务',
  `photo_pic` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `login_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登录账号',
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `phone` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号',
  `delete_type` int(2) DEFAULT 2 COMMENT '是否删除1->是;2->否;',
  `disable_type` int(2) DEFAULT 1 COMMENT '1->启用;2->禁用;',
  `create_time` datetime DEFAULT current_timestamp() COMMENT '创建时间',
  `update_time` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '修改时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='管理人员信息';

-- 正在导出表  my_demo_cli.sms_admin 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `sms_admin` DISABLE KEYS */;
INSERT INTO `sms_admin` (`id`, `user_name`, `title`, `photo_pic`, `login_name`, `password`, `phone`, `delete_type`, `disable_type`, `create_time`, `update_time`, `delete_time`) VALUES
	(1, '超级管理员', '', 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', 'admin', '$1$100000$se8rFqjZUsijuWsXcqrSig==$RzW2Q35IscgZMAmpUOHfTc14MFFhNFJAql1UBbseXYE=', '13012345678', 2, 1, '2025-06-29 12:25:46', '2025-08-13 09:16:46', '2025-07-06 10:52:09'),
	(2, '测试用户', '开发', 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', 'test', '$1$100000$cWnVKGUt4+D5LCdeJBNgAg==$JwnG0EQ054AVcQ3GZFNLQfOlKamtROkO8NtGPic5GIo=', '18877665544', 2, 1, '2025-07-30 10:11:13', '2025-08-12 09:19:22', NULL);
/*!40000 ALTER TABLE `sms_admin` ENABLE KEYS */;

-- 导出  表 my_demo_cli.sms_member_role_relation 结构
DROP TABLE IF EXISTS `sms_member_role_relation`;
CREATE TABLE IF NOT EXISTS `sms_member_role_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `relation_type` int(2) DEFAULT 2 COMMENT '1->用户；2->管理员；',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='资源关联角色表';

-- 正在导出表  my_demo_cli.sms_member_role_relation 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sms_member_role_relation` DISABLE KEYS */;
INSERT INTO `sms_member_role_relation` (`id`, `member_id`, `role_id`, `relation_type`) VALUES
	(1, 1, 1, 2),
	(2, 2, 1, 2);
/*!40000 ALTER TABLE `sms_member_role_relation` ENABLE KEYS */;

-- 导出  表 my_demo_cli.sms_menu 结构
DROP TABLE IF EXISTS `sms_menu`;
CREATE TABLE IF NOT EXISTS `sms_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `menu_parent_id` bigint(20) DEFAULT NULL COMMENT '父级id',
  `menu_level_str` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '菜单层级',
  `menu_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '菜单名称',
  `menu_icon` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图标',
  `menu_path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '路径',
  `menu_description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `menu_sort` int(3) DEFAULT 1 COMMENT '排序',
  `menu_status` int(3) DEFAULT 1 COMMENT '1->启用;2->禁用;',
  `menu_show_status` int(3) DEFAULT 1 COMMENT '显示状态:1->显示;2->隐藏;',
  `delete_type` int(2) DEFAULT 2 COMMENT '是否删除1->是;2->否;',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `create_time` datetime DEFAULT current_timestamp() COMMENT '创建时间',
  `update_time` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='管理系统菜单表';

-- 正在导出表  my_demo_cli.sms_menu 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `sms_menu` DISABLE KEYS */;
INSERT INTO `sms_menu` (`id`, `menu_parent_id`, `menu_level_str`, `menu_name`, `menu_icon`, `menu_path`, `menu_description`, `menu_sort`, `menu_status`, `menu_show_status`, `delete_type`, `delete_time`, `create_time`, `update_time`) VALUES
	(1, 0, '', '系统管理', 'el-icon-key', '/sms', '系统管理菜单', 1, 1, 1, 2, NULL, '2025-08-01 15:14:12', '2025-08-01 16:48:38'),
	(2, 1, '1', '管理员列表', 'el-icon-s-custom', '/sms/admin', '管理员列表', 1, 1, 1, 2, NULL, '2025-08-01 15:25:42', '2025-08-01 16:24:15'),
	(3, 1, '1', '角色列表', 'el-icon-menu', '/sms/role', '角色列表', 2, 1, 1, 2, NULL, '2025-08-01 16:50:18', '2025-08-01 16:50:18'),
	(4, 1, '1', '菜单列表', 'el-icon-menu', '/sms/menu', '菜单列表', 3, 1, 1, 2, NULL, '2025-08-01 16:55:42', '2025-08-01 16:55:42'),
	(5, 1, '1', '资源列表', 'el-icon-s-platform', '/sms/resource', '资源列表', 4, 1, 1, 2, NULL, '2025-08-08 15:58:22', '2025-08-08 15:58:22'),
	(6, 1, '1', '资源分类列表', 'el-icon-s-platform', '/sms/resourceCategory', '资源分类列表', 5, 1, 1, 2, NULL, '2025-08-13 15:35:00', '2025-08-13 15:36:12');
/*!40000 ALTER TABLE `sms_menu` ENABLE KEYS */;

-- 导出  表 my_demo_cli.sms_menu_resource_relation 结构
DROP TABLE IF EXISTS `sms_menu_resource_relation`;
CREATE TABLE IF NOT EXISTS `sms_menu_resource_relation` (
  `menu_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `resource_id` bigint(20) DEFAULT NULL COMMENT '资源ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单和资源关系表';

-- 正在导出表  my_demo_cli.sms_menu_resource_relation 的数据：~33 rows (大约)
/*!40000 ALTER TABLE `sms_menu_resource_relation` DISABLE KEYS */;
INSERT INTO `sms_menu_resource_relation` (`menu_id`, `resource_id`) VALUES
	(2, 2),
	(2, 3),
	(2, 4),
	(2, 5),
	(2, 6),
	(2, 7),
	(5, 22),
	(5, 23),
	(5, 24),
	(5, 25),
	(5, 26),
	(5, 27),
	(5, 28),
	(5, 29),
	(4, 14),
	(4, 15),
	(4, 16),
	(4, 17),
	(4, 18),
	(4, 19),
	(4, 20),
	(4, 21),
	(3, 8),
	(3, 9),
	(3, 10),
	(3, 11),
	(3, 12),
	(3, 13),
	(4, 30),
	(NULL, 23),
	(NULL, 29),
	(NULL, 27),
	(NULL, 26);
/*!40000 ALTER TABLE `sms_menu_resource_relation` ENABLE KEYS */;

-- 导出  表 my_demo_cli.sms_resource 结构
DROP TABLE IF EXISTS `sms_resource`;
CREATE TABLE IF NOT EXISTS `sms_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '资源名称',
  `url` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '资源URL',
  `description` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
  `category_id` bigint(20) DEFAULT NULL COMMENT '资源分类ID',
  `create_time` datetime DEFAULT current_timestamp() COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='资源接口路径表';

-- 正在导出表  my_demo_cli.sms_resource 的数据：~29 rows (大约)
/*!40000 ALTER TABLE `sms_resource` DISABLE KEYS */;
INSERT INTO `sms_resource` (`id`, `name`, `url`, `description`, `category_id`, `create_time`) VALUES
	(1, '所有接口', '/**', '所有接口的权限(谨慎赋值)', 1, '2025-07-12 11:32:45'),
	(2, '添加管理员', '/sms/admin/add/addAdmin', '添加管理员', 2, '2025-08-08 14:54:07'),
	(3, '删除管理员', '/sms/admin/delete/deleteAdmin', '删除管理员', 2, '2025-08-08 14:56:36'),
	(4, '获取管理员列表', '/sms/admin/get/getAdminList', '获取管理员列表', 2, '2025-08-08 14:57:14'),
	(5, '管理员详情', '/sms/admin/get/getAdminDetail', '管理员详情', 2, '2025-08-08 14:57:37'),
	(6, '修改管理员', '/sms/admin/update/updateAdmin', '修改管理员', 2, '2025-08-08 14:59:58'),
	(7, '改管理员启用状态', '/sms/admin/update/updateAdminDisableType', '改管理员启用状态', 2, '2025-08-08 15:00:09'),
	(8, '新增角色', '/sms/roleResource/add/addRole', '新增角色', 3, '2025-08-08 15:05:04'),
	(9, '获取角色详情', '/sms/roleResource/get/roleDetail', '获取角色详情', 3, '2025-08-08 15:23:52'),
	(10, '获取角色列表', '/sms/roleResource/get/roleList', '获取角色列表', 3, '2025-08-08 15:31:04'),
	(11, '获取角色类型列表', '/sms/roleResource/get/roleTypeList', '获取角色类型列表', 3, '2025-08-08 15:32:40'),
	(12, '修改角色', '/sms/roleResource/update/updateRole', '修改角色', 3, '2025-08-08 15:32:54'),
	(13, '修改角色启用禁用状态', '/sms/roleResource/update/updateRoleStateType', '修改角色启用禁用状态', 3, '2025-08-08 15:33:17'),
	(14, '新增菜单', '/sms/menu/add/addMenu', '新增菜单', 4, '2025-08-08 15:37:33'),
	(15, '删除菜单', '/sms/menu/delete/deleteMenu', '删除菜单', 4, '2025-08-08 15:37:56'),
	(16, '获取菜单详情', '/sms/menu/get/menuDetail', '获取菜单详情', 4, '2025-08-08 15:38:06'),
	(17, '获取菜单列表', '/sms/menu/get/menuList', '获取菜单列表', 4, '2025-08-08 15:38:19'),
	(18, '获取菜单树', '/sms/menu/get/menuTree', '获取菜单树', 4, '2025-08-08 15:38:29'),
	(19, '修改菜单', '/sms/menu/update/updateMenu', '修改菜单', 4, '2025-08-08 15:38:40'),
	(20, '菜单绑定资源接口', '/sms/menu/update/updateMenuResource', '菜单绑定资源接口', 4, '2025-08-08 15:38:57'),
	(21, '修改菜单启用状态', '/sms/menu/update/updateMenuStateType', '修改菜单启用状态', 4, '2025-08-08 15:39:07'),
	(22, '添加资源接口', '/sms/roleResource/add/addResourceApi', '添加资源接口', 5, '2025-08-08 15:42:12'),
	(23, '添加资源分类', '/sms/roleResource/add/addResourceCategory', '添加资源分类', 5, '2025-08-08 15:42:35'),
	(24, '获取资源接口详情', '/sms/roleResource/get/resourceApiDetail', '获取资源接口详情', 5, '2025-08-08 15:42:49'),
	(25, '获取资源接口列表', '/sms/roleResource/get/resourceApiList', '获取资源接口列表', 5, '2025-08-08 15:43:04'),
	(26, '获取资源接口分类详情', '/sms/roleResource/get/resourceCategoryDetail', '获取资源接口分类详情', 5, '2025-08-08 15:43:34'),
	(27, '获取资源接口分类列表', '/sms/roleResource/get/resourceCategoryList', '获取资源接口分类列表', 5, '2025-08-08 15:43:44'),
	(28, '修改资源接口', '/sms/roleResource/update/updateResourceApi', '修改资源接口', 5, '2025-08-08 15:44:00'),
	(29, '修改资源分类', '/sms/roleResource/update/updateResourceCategory', '修改资源分类', 5, '2025-08-08 15:44:14'),
	(30, '资源分类和资源接口关联关系', '/sms/roleResource/get/resourceCategoryAndResourceApiRelation', '获取资源分类和资源接口关联关系列表', 4, '2025-08-13 14:43:37');
/*!40000 ALTER TABLE `sms_resource` ENABLE KEYS */;

-- 导出  表 my_demo_cli.sms_resource_category 结构
DROP TABLE IF EXISTS `sms_resource_category`;
CREATE TABLE IF NOT EXISTS `sms_resource_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '分类名称',
  `sort` int(3) DEFAULT 1 COMMENT '排序',
  `create_time` datetime DEFAULT current_timestamp() COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='资源分类表';

-- 正在导出表  my_demo_cli.sms_resource_category 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `sms_resource_category` DISABLE KEYS */;
INSERT INTO `sms_resource_category` (`id`, `name`, `sort`, `create_time`) VALUES
	(1, '其他', 99, '2025-07-12 11:33:00'),
	(2, '管理员管理', 1, '2025-08-07 18:07:57'),
	(3, '角色管理', 2, '2025-08-08 15:00:39'),
	(4, '菜单管理', 3, '2025-08-08 15:36:40'),
	(5, '资源接口管理', 4, '2025-08-08 15:39:53');
/*!40000 ALTER TABLE `sms_resource_category` ENABLE KEYS */;

-- 导出  表 my_demo_cli.sms_role 结构
DROP TABLE IF EXISTS `sms_role`;
CREATE TABLE IF NOT EXISTS `sms_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色名称',
  `description` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
  `status` int(2) DEFAULT 1 COMMENT '启用状态：2->禁用；1->启用',
  `sort` int(3) DEFAULT 1 COMMENT '排序',
  `role_type` int(2) DEFAULT 1 COMMENT '角色类型：1->用户角色；2->后台角色；',
  `delete_type` int(2) DEFAULT 2 COMMENT '是否删除1->是;2->否;',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `create_time` datetime DEFAULT current_timestamp() COMMENT '创建时间',
  `update_time` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户角色表';

-- 正在导出表  my_demo_cli.sms_role 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `sms_role` DISABLE KEYS */;
INSERT INTO `sms_role` (`id`, `name`, `description`, `status`, `sort`, `role_type`, `delete_type`, `delete_time`, `create_time`, `update_time`) VALUES
	(1, '超级管理员', '超级管理员', 1, 1, 2, 2, NULL, '2025-07-12 10:02:44', '2025-07-31 10:45:56'),
	(2, '测试角色', 'test测试角色', 1, 1, 2, 2, NULL, '2025-07-31 10:47:34', '2025-07-31 10:47:34');
/*!40000 ALTER TABLE `sms_role` ENABLE KEYS */;

-- 导出  表 my_demo_cli.sms_role_menu_relation 结构
DROP TABLE IF EXISTS `sms_role_menu_relation`;
CREATE TABLE IF NOT EXISTS `sms_role_menu_relation` (
  `role_id` bigint(20) DEFAULT NULL,
  `menu_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色和菜单关联表';

-- 正在导出表  my_demo_cli.sms_role_menu_relation 的数据：~6 rows (大约)
/*!40000 ALTER TABLE `sms_role_menu_relation` DISABLE KEYS */;
INSERT INTO `sms_role_menu_relation` (`role_id`, `menu_id`) VALUES
	(1, 1),
	(1, 2),
	(1, 3),
	(1, 4),
	(1, 5),
	(1, 6);
/*!40000 ALTER TABLE `sms_role_menu_relation` ENABLE KEYS */;

-- 导出  表 my_demo_cli.ums_member_user 结构
DROP TABLE IF EXISTS `ums_member_user`;
CREATE TABLE IF NOT EXISTS `ums_member_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `user_code` varchar(1024) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '唯一编码',
  `username` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `phone` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话',
  `status` int(2) DEFAULT NULL COMMENT '状态：0->禁用；1->正常；',
  `delete_type` int(2) DEFAULT 2 COMMENT '是否删除1->是;2->否;',
  `create_time` datetime DEFAULT current_timestamp() COMMENT '创建时间',
  `update_time` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '修改时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- 正在导出表  my_demo_cli.ums_member_user 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `ums_member_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `ums_member_user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
