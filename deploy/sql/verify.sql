/*
SQLyog v10.2 
MySQL - 5.7.11 : Database - com_orange_verify_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`com_orange_verify_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;

USE `com_orange_verify_db`;

/*Table structure for table `t_account` */

DROP TABLE IF EXISTS `t_account`;

CREATE TABLE `t_account` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `del_flag` int(2) DEFAULT '0' COMMENT '删除判断',
  `remarks` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `blacklist` int(2) DEFAULT '0' COMMENT '是否加入了黑名单 0.不是 1.是的',
  `card_id` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '卡密绑定id',
  `code` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户电脑的机器码',
  `create_ip` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户注册的时候ip地址',
  `create_ip_info` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建时候的ip信息',
  `name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户的真实姓名',
  `password` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户密码',
  `phone_number` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户的联系手机号',
  `qq` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户的联系QQ',
  `security_code` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '安全码，找回密码用',
  `soft_id` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '软件绑定id',
  `username` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='软件用户表';

/*Data for the table `t_account` */

/*Table structure for table `t_account_login_log` */

DROP TABLE IF EXISTS `t_account_login_log`;

CREATE TABLE `t_account_login_log` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `del_flag` int(2) DEFAULT '0' COMMENT '删除判断',
  `remarks` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `account_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `ip` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `ip_info` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `soft_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户登录日志';

/*Data for the table `t_account_login_log` */

/*Table structure for table `t_account_register_log` */

DROP TABLE IF EXISTS `t_account_register_log`;

CREATE TABLE `t_account_register_log` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `del_flag` int(2) DEFAULT '0' COMMENT '删除判断',
  `remarks` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `account_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `ip` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `ip_info` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `soft_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户注册日志';

/*Data for the table `t_account_register_log` */

/*Table structure for table `t_baidu_map_api` */

DROP TABLE IF EXISTS `t_baidu_map_api`;

CREATE TABLE `t_baidu_map_api` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `del_flag` int(2) DEFAULT '0' COMMENT '删除判断',
  `remarks` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `appkey` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'appkey',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='百度地理api配置';

/*Data for the table `t_baidu_map_api` */

/*Table structure for table `t_card` */

DROP TABLE IF EXISTS `t_card`;

CREATE TABLE `t_card` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `del_flag` int(2) DEFAULT '0' COMMENT '删除判断',
  `remarks` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `account_id` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户绑定id',
  `card_number` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '充值卡号',
  `card_type_id` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '卡类绑定id',
  `closure` int(2) DEFAULT '0' COMMENT '是否封停使用 0.未封停 1.已封停',
  `end_date` bigint(20) DEFAULT NULL COMMENT '结束时间',
  `sell_status` int(2) DEFAULT '0' COMMENT '销售状态 0.未卖出 1.已卖出',
  `start_date` bigint(20) DEFAULT NULL COMMENT '开始使用时间',
  `use_status` int(2) DEFAULT '0' COMMENT '使用状态 0.未使用 1.已使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='软件充值卡';

/*Data for the table `t_card` */

/*Table structure for table `t_card_type` */

DROP TABLE IF EXISTS `t_card_type`;

CREATE TABLE `t_card_type` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `del_flag` int(2) DEFAULT '0' COMMENT '删除判断',
  `remarks` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `soft_id` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '软件绑定id',
  `unit` int(2) DEFAULT '0' COMMENT '卡类单位 0.分 1.时 2.天 3.周 4.月 5.年',
  `value` int(11) DEFAULT NULL COMMENT '卡类值 比如对应的是分填1就是1分钟 以此类推',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='软件充值卡类型';

/*Data for the table `t_card_type` */

/*Table structure for table `t_email_account` */

DROP TABLE IF EXISTS `t_email_account`;

CREATE TABLE `t_email_account` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `del_flag` int(2) DEFAULT '0' COMMENT '删除判断',
  `remarks` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `is_use` int(2) DEFAULT '0' COMMENT '是否使用 0.使用 1.不使用',
  `password` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户密码',
  `total` bigint(20) DEFAULT '0' COMMENT '被使用的次数',
  `username` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='邮箱账户';

/*Data for the table `t_email_account` */

/*Table structure for table `t_soft` */

DROP TABLE IF EXISTS `t_soft`;

CREATE TABLE `t_soft` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `del_flag` int(2) DEFAULT '0' COMMENT '删除判断',
  `remarks` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `change_strategy` int(2) DEFAULT '0' COMMENT '换绑策略 0.支持换绑定 1.不支持换绑定',
  `dosing_strategy` int(2) DEFAULT '0' COMMENT '多开策略 0.只支持单机 1.无限制',
  `email_name` varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '被通知的邮箱账户名',
  `email_notificatio` int(2) DEFAULT '0' COMMENT '软件被留言 是否邮件通知 0.通知 1.不通知',
  `name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '软件名称',
  `notice` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '公告',
  `registe_close_msg` varchar(255) COLLATE utf8mb4_bin DEFAULT '' COMMENT '关闭注册后的返回信息',
  `register_status` int(2) DEFAULT '0' COMMENT '注册状态 0.开放注册 1.关闭注册',
  `service_close_msg` varchar(255) COLLATE utf8mb4_bin DEFAULT '' COMMENT '关闭状态下的返回信息',
  `service_status` int(2) DEFAULT '0' COMMENT '服务状态 0.收费 1.免费开放 2.关闭开放使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='软件';

/*Data for the table `t_soft` */

/*Table structure for table `t_soft_leave_message` */

DROP TABLE IF EXISTS `t_soft_leave_message`;

CREATE TABLE `t_soft_leave_message` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `del_flag` int(2) DEFAULT '0' COMMENT '删除判断',
  `remarks` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `content` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户留言内容',
  `ip` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `ip_info` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `qq` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户的QQ号',
  `soft_id` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '软件绑定id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='软件留言';

/*Data for the table `t_soft_leave_message` */

/*Table structure for table `t_soft_versions` */

DROP TABLE IF EXISTS `t_soft_versions`;

CREATE TABLE `t_soft_versions` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `del_flag` int(2) DEFAULT '0' COMMENT '删除判断',
  `remarks` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `notice` varchar(255) COLLATE utf8mb4_bin DEFAULT '' COMMENT '更新公告',
  `novatio_necessaria` int(2) DEFAULT '0' COMMENT '是否强制更新 0.不强制 1.强制',
  `number` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '版本号',
  `soft_id` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '软件绑定id',
  `update_url` varchar(255) COLLATE utf8mb4_bin DEFAULT '' COMMENT '更新url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='软件版本控制';

/*Data for the table `t_soft_versions` */

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `del_flag` int(2) DEFAULT '0' COMMENT '删除判断',
  `remarks` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `flag` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '管理标识符',
  `password` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '管理用户密码',
  `username` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '管理用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='平台管理用户';

/*Data for the table `t_user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
