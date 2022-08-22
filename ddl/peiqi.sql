/*
 Navicat Premium Data Transfer

 Source Server         : docker_mysql_time
 Source Server Type    : MySQL
 Source Server Version : 50738 (5.7.38)
 Source Host           : localhost:3307
 Source Schema         : peiqi

 Target Server Type    : MySQL
 Target Server Version : 50738 (5.7.38)
 File Encoding         : 65001

 Date: 23/08/2022 00:01:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app_table
-- ----------------------------
DROP TABLE IF EXISTS `app_table`;
CREATE TABLE `app_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'primary key',
  `application_name` varchar(32) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `create_by` varchar(16) DEFAULT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for deploy_app_involuntary_record
-- ----------------------------
DROP TABLE IF EXISTS `deploy_app_involuntary_record`;
CREATE TABLE `deploy_app_involuntary_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'primary key',
  `mtar_upload_flag` int(11) NOT NULL DEFAULT '0' COMMENT '0 -- Involuntary upload  1 -- voluntary upload',
  `application_id` int(11) NOT NULL COMMENT 'id in app_table ',
  `app_version` varchar(32) NOT NULL,
  `api_end_point` varchar(32) NOT NULL,
  `org_name` varchar(32) NOT NULL,
  `space_name` varchar(32) NOT NULL,
  `extension_file_uid` int(11) NOT NULL COMMENT 'uid in upload file info',
  `deploy_account` varchar(32) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `create_by` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for deploy_app_log
-- ----------------------------
DROP TABLE IF EXISTS `deploy_app_log`;
CREATE TABLE `deploy_app_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deploy_app_id` int(11) NOT NULL COMMENT 'id in deploy_app_record',
  `status` int(11) NOT NULL COMMENT '0 -- success  1 -- failed',
  `log_info` varchar(255) DEFAULT NULL,
  `log_file_path` varchar(128) NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for deploy_app_voluntary_record
-- ----------------------------
DROP TABLE IF EXISTS `deploy_app_voluntary_record`;
CREATE TABLE `deploy_app_voluntary_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'primary key',
  `mtar_upload_flag` int(11) NOT NULL DEFAULT '1' COMMENT '0 -- Involuntary upload  1 -- voluntary upload',
  `mtar_file_uid` varchar(32) NOT NULL COMMENT 'uid in upload file info',
  `extension_file_uid` varchar(32) NOT NULL COMMENT 'uid in upload file info',
  `api_end_point` varchar(32) NOT NULL,
  `org_name` varchar(32) NOT NULL,
  `space_name` varchar(32) NOT NULL,
  `deploy_account` varchar(32) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `create_by` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for upload_file_info
-- ----------------------------
DROP TABLE IF EXISTS `upload_file_info`;
CREATE TABLE `upload_file_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'primary key',
  `file_type` int(11) NOT NULL DEFAULT '0' COMMENT '0 - mtar ；1 - extension ; 8 -- example',
  `file_uid` varchar(32) NOT NULL,
  `file_name` varchar(64) DEFAULT NULL,
  `file_url` varchar(64) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_file_id` (`file_uid`) USING BTREE,
  KEY `idx_file_type` (`file_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
