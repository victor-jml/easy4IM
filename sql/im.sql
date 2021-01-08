/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : im

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 05/01/2021 14:31:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_local_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_local_auth`;
CREATE TABLE `tb_local_auth` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` varchar(60) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `user_email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `user_name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '昵称',
  `user_password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录密码',
  `user_phone` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of tb_local_auth
-- ----------------------------
BEGIN;
INSERT INTO `tb_local_auth` VALUES (1, 'enoch', NULL, 'Always Be Coding', 'e10adc3949ba59abbe56e057f20f883e', NULL);
INSERT INTO `tb_local_auth` VALUES (2, 'enoch1', NULL, 'Always Be Coding', 'e10adc3949ba59abbe56e057f20f883e', NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_offline_msg
-- ----------------------------
DROP TABLE IF EXISTS `tb_offline_msg`;
CREATE TABLE `tb_offline_msg` (
  `msg_id` bigint NOT NULL COMMENT '消息ID',
  `msg_from` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息发送者',
  `msg_to` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息接收者',
  `group_id` bigint DEFAULT NULL COMMENT '群组id',
  `cmd_id` int DEFAULT NULL COMMENT 'tcp包头命令号',
  `msg_seq` int DEFAULT NULL COMMENT 'tcp包头seq',
  `msg_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息内容',
  `send_time` datetime NOT NULL COMMENT '接收时间',
  `msg_type` int NOT NULL COMMENT '消息类型(0-文字，1-图片，2-语音)',
  `delivered` int NOT NULL COMMENT '0-未送达，1-送达',
  PRIMARY KEY (`msg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of tb_offline_msg
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_operation_log`;
CREATE TABLE `tb_operation_log` (
  `operation_log_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `log_description` varchar(64) DEFAULT NULL COMMENT '日志描述',
  `action_args` varchar(300) DEFAULT NULL COMMENT '方法参数',
  `user_no` varchar(50) DEFAULT NULL COMMENT '用户主键',
  `class_name` varchar(300) DEFAULT NULL COMMENT '类名称',
  `method_name` varchar(64) DEFAULT NULL COMMENT '方法名称',
  `ip` varchar(32) DEFAULT NULL,
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `model_name` varchar(50) DEFAULT NULL COMMENT '模块名称',
  `action` varchar(50) DEFAULT NULL COMMENT '操作',
  `succeed` int DEFAULT NULL COMMENT '是否成功 1:成功 2异常',
  `message` longtext COMMENT '异常堆栈信息',
  PRIMARY KEY (`operation_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志表';

-- ----------------------------
-- Records of tb_operation_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_send_msg
-- ----------------------------
DROP TABLE IF EXISTS `tb_send_msg`;
CREATE TABLE `tb_send_msg` (
  `msg_id` bigint NOT NULL COMMENT '消息ID',
  `msg_from` varchar(60) COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息发送者',
  `msg_to` varchar(60) COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息接收者',
  `group_id` bigint DEFAULT NULL COMMENT '群组id',
  `cmd_id` int DEFAULT NULL COMMENT 'tcp包头命令号',
  `msg_seq` int DEFAULT NULL COMMENT 'tcp包头seq',
  `msg_content` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息内容',
  `send_time` datetime NOT NULL COMMENT '接收时间',
  `msg_type` int NOT NULL COMMENT '消息类型(0-文字，1-图片，2-语音)',
  PRIMARY KEY (`msg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of tb_send_msg
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_users
-- ----------------------------
DROP TABLE IF EXISTS `tb_users`;
CREATE TABLE `tb_users` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` varchar(60) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `user_name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `user_icon` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `user_age` int DEFAULT NULL COMMENT '年龄',
  `user_status` int NOT NULL COMMENT '用户状态(0-正常，1-禁用，2-删除)',
  `user_signature` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '个性签名',
  `online` int DEFAULT NULL COMMENT '在线状态',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `last_login` bigint DEFAULT NULL COMMENT '上次登录时间戳',
  `last_logout` bigint DEFAULT NULL COMMENT '上次登出时间戳',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`) USING BTREE COMMENT 'userId索引'
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of tb_users
-- ----------------------------
BEGIN;
INSERT INTO `tb_users` VALUES (5, 'enoch', 'Always Be Coding', NULL, NULL, 0, NULL, 1, '2021-01-05 14:30:16', '2021-01-05 14:30:16', NULL, NULL);
INSERT INTO `tb_users` VALUES (6, 'enoch1', 'Always Be Coding', NULL, NULL, 0, NULL, 1, '2021-01-05 14:30:38', '2021-01-05 14:30:38', NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
