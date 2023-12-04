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

DROP TABLE IF EXISTS `sms_member_role_relation`;
CREATE TABLE `sms_member_role_relation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `member_id` bigint DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  `relation_type` int DEFAULT '1' COMMENT '1->管理员；2->用户；',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


DROP TABLE IF EXISTS `sms_resource`;
CREATE TABLE `sms_resource` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资源名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资源URL',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `category_id` bigint DEFAULT NULL COMMENT '资源分类ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='资源接口路径表';


DROP TABLE IF EXISTS `sms_resource_category`;
CREATE TABLE `sms_resource_category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '分类名称',
  `sort` int DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='资源分类表';


DROP TABLE IF EXISTS `sms_role`;
CREATE TABLE `sms_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色名称',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  `sort` int DEFAULT '0' COMMENT '排序',
  `role_type` int DEFAULT '1' COMMENT '角色类型：1->用户角色；2->后台角色；',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户角色表';


DROP TABLE IF EXISTS `sms_role_resource_relation`;
CREATE TABLE `sms_role_resource_relation` (
  `role_id` bigint DEFAULT NULL COMMENT '角色ID',
  `resource_id` bigint DEFAULT NULL COMMENT '资源ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色和资源关系表';




-- 导出  表 my_demo_cli.ums_member_user_info 结构
DROP TABLE IF EXISTS `ums_member_user_info`;
CREATE TABLE `ums_member_user_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话',
  `user_id` bigint DEFAULT NULL COMMENT '用户表id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户基础信息表';


-- 导出  表 my_demo_cli.ums_member_user 结构
DROP TABLE IF EXISTS `ums_member_user`;
CREATE TABLE `ums_member_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_info_id` bigint DEFAULT NULL COMMENT '用户信息表id',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `user_code` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '唯一编码',
  `status` int DEFAULT NULL COMMENT '状态：0->禁用；1->正常；',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- 数据导出被取消选择。

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
