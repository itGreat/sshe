/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.6.14 : Database - sshe
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sshe` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sshe`;

/*Table structure for table `t_sys_dept` */

DROP TABLE IF EXISTS `t_sys_dept`;

CREATE TABLE `t_sys_dept` (
  `id` varchar(40) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `parent_id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2B4CA3E24C58413A` (`parent_id`),
  CONSTRAINT `FK2B4CA3E24C58413A` FOREIGN KEY (`parent_id`) REFERENCES `t_sys_dept` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_sys_dept` */

insert  into `t_sys_dept`(`id`,`code`,`name`,`remark`,`parent_id`) values ('1','01','开发部','remark','4'),('2','02','设计部','remark','4'),('3','03','销售部','remark','4'),('4','04','公司','remark',NULL),('40283a8148a68e0b0148a6e29d850000',NULL,'SSS',NULL,'3');

/*Table structure for table `t_sys_entity` */

DROP TABLE IF EXISTS `t_sys_entity`;

CREATE TABLE `t_sys_entity` (
  `id` varchar(40) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_sys_entity` */

insert  into `t_sys_entity`(`id`,`name`,`remark`,`type`,`value`) values ('2','功能列表','remark','menu','/sys/entity_list.action'),('3','菜单列表','remark','menu','/sys/menu_list.action'),('4','角色列表','remark','menu','/sys/role_list.action'),('5','用户部门列表','remark','menu','/sys/userdept/main.action');

/*Table structure for table `t_sys_node` */

DROP TABLE IF EXISTS `t_sys_node`;

CREATE TABLE `t_sys_node` (
  `id` varchar(40) NOT NULL,
  `layer` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `seq` int(11) DEFAULT NULL,
  `entity_id` varchar(40) DEFAULT NULL,
  `parent_id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2B51539F30E05FBF` (`entity_id`),
  KEY `FK2B51539F4C5CF0F7` (`parent_id`),
  CONSTRAINT `FK2B51539F30E05FBF` FOREIGN KEY (`entity_id`) REFERENCES `t_sys_entity` (`id`),
  CONSTRAINT `FK2B51539F4C5CF0F7` FOREIGN KEY (`parent_id`) REFERENCES `t_sys_node` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_sys_node` */

insert  into `t_sys_node`(`id`,`layer`,`name`,`seq`,`entity_id`,`parent_id`) values ('1',0,'用户部门管理',1,'5','4'),('2',0,'功能管理',1,'2','4'),('3',0,'菜单管理',1,'3','4'),('4',0,'系统配置',1,'4',NULL);

/*Table structure for table `t_sys_role` */

DROP TABLE IF EXISTS `t_sys_role`;

CREATE TABLE `t_sys_role` (
  `id` varchar(40) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_sys_role` */

insert  into `t_sys_role`(`id`,`name`,`remark`) values ('1','系统管理员',NULL);

/*Table structure for table `t_sys_role_entity` */

DROP TABLE IF EXISTS `t_sys_role_entity`;

CREATE TABLE `t_sys_role_entity` (
  `role_id` varchar(40) NOT NULL,
  `entity_id` varchar(40) NOT NULL,
  PRIMARY KEY (`role_id`,`entity_id`),
  KEY `FK77BA4F8F30E05FBF` (`entity_id`),
  KEY `FK77BA4F8F2309A81F` (`role_id`),
  CONSTRAINT `FK77BA4F8F2309A81F` FOREIGN KEY (`role_id`) REFERENCES `t_sys_role` (`id`),
  CONSTRAINT `FK77BA4F8F30E05FBF` FOREIGN KEY (`entity_id`) REFERENCES `t_sys_entity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_sys_role_entity` */

insert  into `t_sys_role_entity`(`role_id`,`entity_id`) values ('1','2'),('1','3'),('1','4'),('1','5');

/*Table structure for table `t_sys_user` */

DROP TABLE IF EXISTS `t_sys_user`;

CREATE TABLE `t_sys_user` (
  `id` varchar(40) NOT NULL,
  `deleted` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `dept_id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2B5491682D9AA1BF` (`dept_id`),
  CONSTRAINT `FK2B5491682D9AA1BF` FOREIGN KEY (`dept_id`) REFERENCES `t_sys_dept` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_sys_user` */

insert  into `t_sys_user`(`id`,`deleted`,`name`,`password`,`username`,`dept_id`) values ('1',0,'gc','111111','gongchang','1'),('2',0,'tmm','111111','tmm','1'),('3',0,'fly','111111','fly','1'),('40283a8148a17ac50148a17bd6310000',NULL,'ffs','','fff','1'),('40283a8148a5fd330148a6043f6b0002',0,'ss','111111','ss',NULL),('40283a8148a653cc0148a6590aa50000',0,'张三','111111','zhagnsan',NULL),('40283a8148a653cc0148a6595a8c0001',0,'王五','111111','wangwu','2'),('40283a8148a68e0b0148a6e366520001',0,'asdf','111111','af','3');

/*Table structure for table `t_sys_user_role` */

DROP TABLE IF EXISTS `t_sys_user_role`;

CREATE TABLE `t_sys_user_role` (
  `user_id` varchar(40) NOT NULL,
  `role_id` varchar(40) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKEEC648ED2309A81F` (`role_id`),
  KEY `FKEEC648EDC8346BFF` (`user_id`),
  CONSTRAINT `FKEEC648ED2309A81F` FOREIGN KEY (`role_id`) REFERENCES `t_sys_role` (`id`),
  CONSTRAINT `FKEEC648EDC8346BFF` FOREIGN KEY (`user_id`) REFERENCES `t_sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_sys_user_role` */

insert  into `t_sys_user_role`(`user_id`,`role_id`) values ('1','1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
