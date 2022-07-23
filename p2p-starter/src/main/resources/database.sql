/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : 127.0.0.1:3306
 Source Schema         : p2p_cheat

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 23/07/2022 10:46:55
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for p2p_auth
-- ----------------------------
DROP TABLE IF EXISTS `p2p_auth`;
CREATE TABLE `p2p_auth`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '' 主键 '',
    `username`    varchar(200) NOT NULL COMMENT '' 用户名 '',
    `app_type`    varchar(64)  NOT NULL COMMENT '' 三方类型 '',
    `open_id`     bigint       NOT NULL COMMENT '' 认证id '',
    `deleted`     int          NOT NULL DEFAULT '' 0 '' COMMENT '' 逻辑删 '',
    `ver`         int          NOT NULL DEFAULT '' 0 '' COMMENT '' 版本号 '',
    `create_user` varchar(255) NOT NULL COMMENT '' 创建用户 '',
    `create_time` datetime     NOT NULL COMMENT '' 创建时间 '',
    `update_user` varchar(255) NOT NULL COMMENT '' 更新用户 '',
    `update_time` datetime     NOT NULL COMMENT '' 更新时间 '',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Table structure for p2p_user
-- ----------------------------
DROP TABLE IF EXISTS `p2p_user`;
CREATE TABLE `p2p_user`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '' 主键 '',
    `username`    varchar(200) NOT NULL COMMENT '' 用户名 '',
    `password`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '' 密码 '',
    `nickname`    varchar(255) NOT NULL COMMENT '' 昵称 '',
    `deleted`     int          NOT NULL                                   DEFAULT '' 0 '' COMMENT '' 逻辑删 '',
    `ver`         int          NOT NULL                                   DEFAULT '' 0 '' COMMENT '' 版本号 '',
    `create_user` varchar(255) NOT NULL COMMENT '' 创建用户 '',
    `create_time` datetime     NOT NULL COMMENT '' 创建时间 '',
    `update_user` varchar(255) NOT NULL COMMENT '' 更新用户 '',
    `update_time` datetime     NOT NULL COMMENT '' 更新时间 '',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

SET
FOREIGN_KEY_CHECKS = 1;
