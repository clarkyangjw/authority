# CREATE DATABASE `bbs`;
#
# USE `bbs`;
#
# SET FOREIGN_KEY_CHECKS=0;
#
# DELETE DATABASE `bbs`;

-- ----------------------------
-- Table structure for `bbs_user`
-- ----------------------------
DROP TABLE IF EXISTS `bbs_user`;
CREATE TABLE `bbs_user` (
  `id` bigint NOT NULL PRIMARY KEY,
  `username` varchar(30) NOT NULL,
  `nick_name` varchar(50) DEFAULT NULL,
  `password` varchar(64) NOT NULL,
  `email` varchar(50) NOT NULL,
  `portrait_url` varchar(400) DEFAULT NULL,
  `gender` varchar(1) DEFAULT 'S',
  `intro` varchar(500) DEFAULT NULL,
  `signature` varchar(500) DEFAULT NULL,
  `topic_count` int DEFAULT '0',
  `reply_count` int DEFAULT '0',
  `best_topic_count` int DEFAULT '0',
  `last_topic_id` bigint DEFAULT NULL,
  `last_login` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `amount` int DEFAULT '0',
  `create_user` bigint NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` bigint NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_active` bit(1) NOT NULL DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

INSERT INTO `bbs_user`
(`id`, `username`, `nick_name`, `password`, `email`, `portrait_url`, `gender`, `intro`, `signature`, `topic_count`, `reply_count`, `best_topic_count`, `last_topic_id`, `last_login`, `amount`, `create_user`, `create_time`, `update_user`, `update_time`, `is_active`)
VALUES
('1', 'pinda', 'pinda', 'cea87ef1cb2e47570020bf7c014e1074', 'ricardo14@example.org', 'http://dietrichgulgowski.info/', 'L', NULL, NULL, 0, 0, 0, '0', '2021-10-30 22:26:35', 0, '0', '2021-10-30 22:26:35', '0', '2021-10-30 22:26:35', ''),
('2', 'Clark', 'Clark', 'cea87ef1cb2e47570020bf7c014e1074', 'josie37@example.net', 'http://www.fadelosinski.com/', 'M', NULL, NULL, 0, 0, 0, '0', '2021-10-30 22:26:35', 0, '0', '2021-10-30 22:26:35', '0', '2021-10-30 22:26:35', ''),
('3', 'Stanley', 'Stanley', 'cea87ef1cb2e47570020bf7c014e1074', 'courtney46@example.org', 'http://abbott.biz/', 'S', NULL, NULL, 0, 0, 0, '0', '2021-10-30 22:26:35', 0, '0', '2021-10-30 22:26:35', '0', '2021-10-30 22:26:35', ''),
('4', 'Mary', 'Mary', 'cea87ef1cb2e47570020bf7c014e1074', 'ylindgren@example.com', 'http://www.ritchie.net/', 'F', NULL, NULL, 0, 0, 0, '0', '2021-10-30 22:26:35', 0, '0', '2021-10-30 22:26:35', '0', '2021-10-30 22:26:35', '')
;


-- ----------------------------
-- Table structure for `bbs_role`
-- ----------------------------
DROP TABLE IF EXISTS `bbs_role`;
CREATE TABLE `bbs_role` (
  `id` bigint NOT NULL PRIMARY KEY,
  `name` varchar(100) NOT NULL,
  `create_user` bigint NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` bigint NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_active` bit(1) NOT NULL DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

INSERT INTO `bbs_role`
(`id`, `name`, `create_user`, `create_time`, `update_user`, `update_time`, `is_active`)
VALUES
('1', 'Admin', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('2', 'Moderator', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('3', 'User', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '')
;

-- ----------------------------
-- Table structure for `bbs_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `bbs_user_role`;
CREATE TABLE `bbs_user_role` (
  `id` bigint NOT NULL PRIMARY KEY,
  `user_id` bigint not null,
  `role_id` bigint not null,
  `create_user` bigint NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

INSERT INTO `bbs_user_role`
(`id`, `user_id`, `role_id`, `create_user`, `create_time`)
VALUES
('1', '1', '1', '1', '2021-10-30 22:26:35'),
('2', '2', '2', '1', '2021-10-30 22:26:35'),
('3', '3', '3', '1', '2021-10-30 22:26:35'),
('4', '4', '4', '1', '2021-10-30 22:26:35')
;


-- ----------------------------
-- Table structure for `bbs_menu`
-- ----------------------------
DROP TABLE IF EXISTS `bbs_menu`;
CREATE TABLE `bbs_menu` (
  `id` bigint NOT NULL PRIMARY KEY,
  `name` varchar(100) NOT NULL,
  `is_public` bit(1) NOT NULL DEFAULT b'1',
  `path` varchar(100) NOT NULL,
  `component` varchar(100) NOT NULL,
  `sort_order` int DEFAULT '0',
  `icon` varchar(100) DEFAULT NULL,
  `parent_id` bigint NOT NULL,
  `create_user` bigint NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` bigint NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_active` bit(1) NOT NULL DEFAULT b'1'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

INSERT INTO `bbs_menu`
(`id`, `name`, `is_public`, `path`, `component`, `sort_order`, `icon`, `parent_id`, `create_user`, `create_time`, `update_user`, `update_time`, `is_active`)
VALUES
('1', 'BBS User Management', '', '/user', '/bbs/pages/user', 1, 'http://www.skiles.net/', '0', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('2', 'BBS Role Management', '', '/role', '/bbs/pages/role', 2, 'http://www.pacochamraz.biz/', '0', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('3', 'BBS Resource Management', '', '/resource', '/bbs/pages/resource', 3, 'http://wiegandernser.com/', '0', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('4', 'BBS Menu Management', '', '/menu', '/bbs/pages/menu', 4, 'http://wiegandernser.com/', '0', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('5', 'BBS Class Management', '', '/class', '/bbs/pages/class', 5, 'http://wiegandernser.com/', '0', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('6', 'BBS Subject Management', '', '/subject', '/bbs/pages/subject', 6, 'http://wiegandernser.com/', '0', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('7', 'BBS Topic Management', '', '/topic', '/bbs/pages/topic', 7, 'http://wiegandernser.com/', '0', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('8', 'BBS Reply Management', '', '/reply', '/bbs/pages/reply', 8, 'http://wiegandernser.com/', '0', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('9', 'BBS State Type Management', '', '/stateType', '/bbs/pages/stateType', 9, 'http://wiegandernser.com/', '0', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '')
;


-- ----------------------------
-- Table structure for `bbs_resource`
-- ----------------------------
DROP TABLE IF EXISTS `bbs_resource`;
CREATE TABLE `bbs_resource` (
  `id` bigint NOT NULL PRIMARY KEY,
  `code` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `menu_id` bigint NOT NULL,
  `method` varchar(10) NOT NULL,
  `url` varchar(100) NOT NULL,
  `create_user` bigint NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` bigint NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_active` bit(1) NOT NULL DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

INSERT INTO `bbs_resource`
(`id`, `code`, `name`, `menu_id`, `method`, `url`, `create_user`, `create_time`, `update_user`, `update_time`, `is_active`)
VALUES
('1', 'user:add', 'add', '1', 'POST', '/user', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('2', 'user:update', 'update', '1', 'PUT', '/user', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('3', 'user:delete', 'delete', '1', 'DELETE', '/user', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('4', 'user:view', 'view', '1', 'GET', '/user/page', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('5', 'user:import', 'import', '1', 'POST', '/user', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('6', 'user:export', 'export', '1', 'GET', '/user', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('7', 'role:add', 'add', '2', 'POST', '/role', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('8', 'role:update', 'update', '2', 'PUT', '/role', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('9', 'role:delete', 'delete', '2', 'DELETE', '/role', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('10', 'role:view', 'view', '2', 'GET', '/role/page', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('11', 'role:import', 'import', '2', 'POST', '/role', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('12', 'role:export', 'export', '2', 'GET', '/role', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('13', 'role:auth', 'authorize', '2', 'POST', '/role/user', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('14', 'role:config', 'configurate', '2', 'POST', '/role/authorize', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('15', 'resource:add', 'add', '3', 'POST', '/resource', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('16', 'resource:update', 'update', '3', 'PUT', '/resource', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('17', 'resource:delete', 'delete', '3', 'DELETE', '/resource', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('18', 'resource:view', 'view', '3', 'GET', '/resource/page', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('19', 'resource:import', 'import', '3', 'POST', '/resource', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('20', 'resource:export', 'export', '3', 'GET', '/resource', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('21', 'menu:add', 'add', '4', 'POST', '/menu', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('22', 'menu:update', 'update', '4', 'PUT', '/menu', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('23', 'menu:delete', 'delete', '4', 'DELETE', '/menu', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('24', 'menu:view', 'view', '4', 'GET', '/menu/page', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('25', 'menu:import', 'import', '4', 'POST', '/menu', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('26', 'menu:export', 'export', '4', 'GET', '/menu', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('27', 'class:add', 'add', '5', 'POST', '/class', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('28', 'class:update', 'update', '5', 'PUT', '/class', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('29', 'class:delete', 'delete', '5', 'DELETE', '/class', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('30', 'class:view', 'view', '5', 'GET', '/class/page', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('31', 'class:import', 'import', '5', 'POST', '/class', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('32', 'class:export', 'export', '5', 'GET', '/class', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('33', 'subject:add', 'add', '6', 'POST', '/subject', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('34', 'subject:update', 'update', '6', 'PUT', '/subject', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('35', 'subject:delete', 'delete', '6', 'DELETE', '/subject', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('36', 'subject:view', 'view', '6', 'GET', '/subject/page', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('37', 'subject:import', 'import', '6', 'POST', '/subject', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('38', 'subject:export', 'export', '6', 'GET', '/subject', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('39', 'topic:add', 'add', '7', 'POST', '/topic', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('40', 'topic:update', 'update', '7', 'PUT', '/topic', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('41', 'topic:delete', 'delete', '7', 'DELETE', '/topic', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('42', 'topic:view', 'view', '7', 'GET', '/topic/page', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('43', 'topic:import', 'import', '7', 'POST', '/topic', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('44', 'topic:export', 'export', '7', 'GET', '/topic', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('45', 'reply:add', 'add', '8', 'POST', '/reply', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('46', 'reply:update', 'update', '8', 'PUT', '/reply', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('47', 'reply:delete', 'delete', '8', 'DELETE', '/reply', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('48', 'reply:view', 'view', '8', 'GET', '/reply/page', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('49', 'reply:import', 'import', '8', 'POST', '/reply', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('50', 'reply:export', 'export', '8', 'GET', '/reply', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('51', 'stateType:add', 'add', '9', 'POST', '/stateType', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('52', 'stateType:update', 'update', '9', 'PUT', '/stateType', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('53', 'stateType:delete', 'delete', '9', 'DELETE', '/stateType', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('54', 'stateType:view', 'view', '9', 'GET', '/stateType/page', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('55', 'stateType:import', 'import', '9', 'POST', '/stateType', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('56', 'stateType:export', 'export', '9', 'GET', '/stateType', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),

('57', 'topic:manage', 'manage', '7', 'DELETE', '/topic', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('58', 'reply:manage', 'manage', '8', 'DELETE', '/reply', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '')
;



-- ----------------------------
-- Table structure for `bbs_group_authority`
-- ----------------------------
DROP TABLE IF EXISTS `bbs_role_authority`;
CREATE TABLE `bbs_role_authority` (
  `id` bigint NOT NULL PRIMARY KEY,
  `role_id` bigint NOT NULL,
  `authority_id` bigint NOT NULL,
  `authority_type` varchar(50) NOT NULL,
  `create_user` bigint NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

INSERT INTO `bbs_role_authority`
(`id`, `role_id`, `authority_id`, `authority_type`, `create_user`, `create_time`)
VALUES
('1', '1', '1', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('2', '1', '2', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('3', '1', '3', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('4', '1', '1', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('5', '1', '2', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('6', '1', '3', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('7', '1', '1', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('8', '1', '2', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('9', '1', '3', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('10', '1', '10', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('11', '1', '11', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('12', '1', '12', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('13', '1', '13', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('14', '1', '14', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('15', '1', '15', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('16', '1', '16', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('17', '1', '17', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('18', '1', '18', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('19', '1', '19', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('20', '1', '20', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('21', '1', '21', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('22', '1', '22', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('23', '1', '23', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('24', '1', '24', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('25', '1', '25', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('26', '1', '26', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('27', '1', '27', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('28', '1', '28', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('29', '1', '29', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('30', '1', '30', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('31', '1', '31', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('32', '1', '32', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('33', '1', '33', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('34', '1', '34', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('35', '1', '35', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('36', '1', '36', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('37', '1', '37', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('38', '1', '38', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('39', '1', '39', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('40', '1', '40', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('41', '1', '41', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('42', '1', '42', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('43', '1', '43', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('44', '1', '44', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('45', '1', '45', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('46', '1', '46', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('47', '1', '47', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('48', '1', '48', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('49', '1', '49', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('50', '1', '50', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('51', '1', '51', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('52', '1', '52', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('53', '1', '53', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('54', '1', '54', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('55', '1', '55', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('56', '1', '56', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('57', '1', '1', 'MENU', '1', '2021-10-30 22:26:35'),
('58', '1', '2', 'MENU', '1', '2021-10-30 22:26:35'),
('59', '1', '3', 'MENU', '1', '2021-10-30 22:26:35'),
('60', '1', '4', 'MENU', '1', '2021-10-30 22:26:35'),
('61', '1', '5', 'MENU', '1', '2021-10-30 22:26:35'),
('62', '1', '6', 'MENU', '1', '2021-10-30 22:26:35'),
('63', '1', '7', 'MENU', '1', '2021-10-30 22:26:35'),
('64', '1', '8', 'MENU', '1', '2021-10-30 22:26:35'),
('65', '1', '9', 'MENU', '1', '2021-10-30 22:26:35'),
('66', '1', '57', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('67', '1', '58', 'RESOURCE', '1', '2021-10-30 22:26:35'),

('68', '2', '30', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('69', '2', '33', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('70', '2', '34', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('71', '2', '35', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('72', '2', '36', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('73', '2', '39', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('74', '2', '40', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('75', '2', '41', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('76', '2', '42', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('77', '2', '45', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('78', '2', '46', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('79', '2', '47', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('80', '2', '48', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('81', '2', '57', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('82', '2', '58', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('83', '2', '7', 'MENU', '1', '2021-10-30 22:26:35'),
('84', '2', '8', 'MENU', '1', '2021-10-30 22:26:35'),

('85', '3', '39', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('86', '3', '40', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('87', '3', '41', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('88', '3', '42', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('89', '3', '45', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('90', '3', '46', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('91', '3', '47', 'RESOURCE', '1', '2021-10-30 22:26:35'),
('92', '3', '48', 'RESOURCE', '1', '2021-10-30 22:26:35')
;

-- ----------------------------
-- Table structure for `bbs_class`
-- ----------------------------
DROP TABLE IF EXISTS `bbs_class`;
CREATE TABLE `bbs_class` (
  `id` bigint NOT NULL PRIMARY KEY,
  `parent_id` bigint DEFAULT 0,
  `name` varchar(100) NOT NULL,
  `sort_order` int DEFAULT '0',
  `intro` varchar(500) DEFAULT NULL,
  `rule` varchar(8000) DEFAULT NULL,
  `topic_count` int DEFAULT '0',
  `reply_count` int DEFAULT '0',
  `img_url` varchar(400) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `create_user` bigint NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` bigint NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_active` bit(1) NOT NULL DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

INSERT INTO `bbs_class`
(`id`, `parent_id`, `name`, `sort_order`, `intro`, `rule`, `topic_count`, `reply_count`, `img_url`, `url`, `create_user`, `create_time`, `update_user`, `update_time`, `is_active`)
VALUES
('1', '0', 'class1', 1, NULL, NULL, 0, 0, 'http://thiel.biz/', '/class1', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('2', '0', 'class2', 2, NULL, NULL, 0, 0, 'http://www.farrell.com/', '/class2', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('3', '0', 'class3', 3, NULL, NULL, 0, 0, 'http://www.gleasonebert.org/', '/class3', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('4', '0', 'class4', 4, NULL, NULL, 0, 0, 'http://www.skiles.net/', '/class4', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '')
;

-- ----------------------------
-- Table structure for `bbs_subject`
-- ----------------------------
DROP TABLE IF EXISTS `bbs_subject`;
CREATE TABLE `bbs_subject` (
  `id` bigint NOT NULL PRIMARY KEY,
  `class_id` bigint NOT NULL,
  `name` varchar(100) NOT NULL,
  `intro` varchar(500) DEFAULT NULL,
  `sort_order` int DEFAULT '0',
  `topic_count` int DEFAULT '0',
  `reply_count` int DEFAULT '0',
  `url` varchar(100) DEFAULT NULL,
  `last_topic_id` bigint DEFAULT NULL,
  `create_user` bigint NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` bigint NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_active` bit(1) NOT NULL DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

INSERT INTO `bbs_subject`
(`id`, `class_id`, `name`, `intro`, `sort_order`, `topic_count`, `reply_count`, `url`, `last_topic_id`, `create_user`, `create_time`, `update_user`, `update_time`, `is_active`)
VALUES
('1', '1', 'subject1', NULL, 1, 0, 0, '/subject1', '0', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('2', '1', 'subject2', NULL, 2, 0, 0, '/subject1', '0', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('3', '1', 'subject3', NULL, 3, 0, 0, '/subject1', '0', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('4', '1', 'subject4', NULL, 4, 0, 0, '/subject1', '0', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('5', '2', 'subject5', NULL, 5, 0, 0, '/subject1', '0', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('6', '2', 'subject6', NULL, 6, 0, 0, '/subject1', '0', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('7', '2', 'subject7', NULL, 7, 0, 0, '/subject1', '0', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('8', '3', 'subject8', NULL, 8, 0, 0, '/subject1', '0', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('9', '3', 'subject9', NULL, 9, 0, 0, '/subject1', '0', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('10', '4', 'subject10', NULL, 10, 0, 0, '/subject1', '0', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '')
;

-- ----------------------------
-- Table structure for `bbs_topic`
-- ----------------------------
DROP TABLE IF EXISTS `bbs_topic`;
CREATE TABLE `bbs_topic` (
  `id` bigint NOT NULL PRIMARY KEY,
  `subject_id` bigint NOT NULL,
  `title` varchar(100) NOT NULL,
  `body` varchar(8000) NOT NULL,
  `is_comment` bit(1) NOT NULL DEFAULT b'1',
  `hits` int DEFAULT '0',
  `reply_count` int DEFAULT '0',
  `img_url` varchar(400) DEFAULT NULL,
  `last_replied_by` bigint DEFAULT NULL,
  `last_replied_time` datetime DEFAULT NULL,
  `create_user` bigint NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` bigint NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_active` bit(1) NOT NULL DEFAULT b'1'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

INSERT INTO `bbs_topic`
(`id`, `subject_id`, `title`, `body`, `is_comment`, `hits`, `reply_count`, `img_url`, `last_replied_by`, `last_replied_time`, `create_user`, `create_time`, `update_user`, `update_time`, `is_active`)
VALUES
('1', '1', 'Iusto', 'Ut corrupti minus ut aut quo accusamus laboriosam.', '', 0, 0, 'http://www.purdyzemlak.com/', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('2', '1', 'Iusto', 'Ut corrupti minus ut aut quo accusamus laboriosam.', '', 0, 0, 'http://www.purdyzemlak.com/', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('3', '1', 'Iusto', 'Ut corrupti minus ut aut quo accusamus laboriosam.', '\0', 0, 0, 'http://www.purdyzemlak.com/', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('4', '1', 'Iusto', 'Ut corrupti minus ut aut quo accusamus laboriosam.', '', 0, 0, 'http://www.purdyzemlak.com/', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('5', '1', 'Iusto', 'Ut corrupti minus ut aut quo accusamus laboriosam.', '\0', 0, 0, 'http://www.purdyzemlak.com/', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('6', '1', 'Iusto', 'Ut corrupti minus ut aut quo accusamus laboriosam.', '', 0, 0, 'http://www.purdyzemlak.com/', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('7', '2', 'Iusto', 'Ut corrupti minus ut aut quo accusamus laboriosam.', '', 0, 0, 'http://www.purdyzemlak.com/', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('8', '2', 'Iusto', 'Ut corrupti minus ut aut quo accusamus laboriosam.', '\0', 0, 0, 'http://www.purdyzemlak.com/', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('9', '2', 'Iusto', 'Ut corrupti minus ut aut quo accusamus laboriosam.', '', 0, 0, 'http://www.purdyzemlak.com/', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('10', '2','Iusto', 'Ut corrupti minus ut aut quo accusamus laboriosam.', '', 0, 0, 'http://www.purdyzemlak.com/', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('11', '3', 'Iusto', 'Ut corrupti minus ut aut quo accusamus laboriosam.', '', 0, 0, 'http://www.purdyzemlak.com/', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('12', '3', 'Iusto', 'Ut corrupti minus ut aut quo accusamus laboriosam.', '\0', 0, 0, 'http://www.purdyzemlak.com/', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('13', '3', 'Iusto', 'Ut corrupti minus ut aut quo accusamus laboriosam.', '', 0, 0, 'http://www.purdyzemlak.com/', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('14', '3', 'Iusto', 'Ut corrupti minus ut aut quo accusamus laboriosam.', '', 0, 0, 'http://www.purdyzemlak.com/', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('15', '3', 'Iusto', 'Ut corrupti minus ut aut quo accusamus laboriosam.', '\0', 0, 0, 'http://www.purdyzemlak.com/', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('16', '3', 'Iusto', 'Ut corrupti minus ut aut quo accusamus laboriosam.', '', 0, 0, 'http://www.purdyzemlak.com/', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('17', '3', 'Iusto', 'Ut corrupti minus ut aut quo accusamus laboriosam.', '', 0, 0, 'http://www.purdyzemlak.com/', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('18', '4', 'Iusto', 'Ut corrupti minus ut aut quo accusamus laboriosam.', '\0', 0, 0, 'http://www.purdyzemlak.com/', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('19', '4', 'Iusto', 'Ut corrupti minus ut aut quo accusamus laboriosam.', '', 0, 0, 'http://www.purdyzemlak.com/', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('20', '4', 'Iusto', 'Ut corrupti minus ut aut quo accusamus laboriosam.', '', 0, 0, 'http://www.purdyzemlak.com/', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '')
;


-- ----------------------------
-- Table structure for `bbs_reply`
-- ----------------------------
DROP TABLE IF EXISTS `bbs_reply`;
CREATE TABLE `bbs_reply` (
  `id` bigint NOT NULL PRIMARY KEY,
  `topic_id` bigint NOT NULL,
  `topic_title` varchar(100) NOT NULL,
  `body` varchar(2000) NOT NULL,
  `create_user` bigint NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` bigint NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_active` bit(1) NOT NULL DEFAULT b'1'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

INSERT INTO `bbs_reply`
(`id`, `topic_id`, `topic_title`, `body`, `create_user`, `create_time`, `update_user`, `update_time`, `is_active`)
VALUES
('1', '1', 'Iusto', 'Molestiae et architecto excepturi quos sunt molestiae consequuntur omnis.', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('2', '1', 'Iusto', 'Molestiae et architecto excepturi quos sunt molestiae consequuntur omnis.', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('3', '1', 'Iusto', 'Molestiae et architecto excepturi quos sunt molestiae consequuntur omnis.', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('4', '1', 'Iusto', 'Molestiae et architecto excepturi quos sunt molestiae consequuntur omnis.', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('5', '1', 'Iusto', 'Molestiae et architecto excepturi quos sunt molestiae consequuntur omnis.', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('6', '1', 'Iusto', 'Molestiae et architecto excepturi quos sunt molestiae consequuntur omnis.', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('7', '2', 'Iusto', 'Molestiae et architecto excepturi quos sunt molestiae consequuntur omnis.', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('8', '2', 'Iusto', 'Molestiae et architecto excepturi quos sunt molestiae consequuntur omnis.', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('9', '2', 'Iusto', 'Molestiae et architecto excepturi quos sunt molestiae consequuntur omnis.', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('10', '3', 'Iusto', 'Molestiae et architecto excepturi quos sunt molestiae consequuntur omnis.', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('11', '3', 'Iusto', 'Molestiae et architecto excepturi quos sunt molestiae consequuntur omnis.', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('12', '3', 'Iusto', 'Molestiae et architecto excepturi quos sunt molestiae consequuntur omnis.', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('13', '4', 'Iusto', 'Molestiae et architecto excepturi quos sunt molestiae consequuntur omnis.', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('14', '4', 'Iusto', 'Molestiae et architecto excepturi quos sunt molestiae consequuntur omnis.', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('15', '4', 'Iusto', 'Molestiae et architecto excepturi quos sunt molestiae consequuntur omnis.', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('16', '5', 'Iusto', 'Molestiae et architecto excepturi quos sunt molestiae consequuntur omnis.', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('17', '5', 'Iusto', 'Molestiae et architecto excepturi quos sunt molestiae consequuntur omnis.', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '')
;

-- ----------------------------
-- Table structure for `bbs_state_type`
-- ----------------------------
DROP TABLE IF EXISTS `bbs_state_type`;
CREATE TABLE `bbs_state_type` (
	`id` BIGINT NOT NULL PRIMARY KEY,
	`name` VARCHAR(100) NOT NULL,
	`create_user` BIGINT NOT NULL,
	`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_user` bigint NOT NULL,
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `is_active` bit(1) NOT NULL DEFAULT b'1'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

INSERT INTO `bbs_state_type`
(`id`, `name`, `create_user`, `create_time`, `update_user`, `update_time`, `is_active`)
VALUES
('1', 'HOT', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('2', 'GUIDE', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('3', 'Q&A', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', ''),
('4', 'TOP', '1', '2021-10-30 22:26:35', '1', '2021-10-30 22:26:35', '')
;

-- ----------------------------
-- Table structure for `bbs_topic_state_type`
-- ----------------------------
DROP TABLE IF EXISTS `bbs_topic_state_type`;
CREATE TABLE `bbs_topic_state_type` (
	`id` BIGINT NOT NULL PRIMARY KEY,
	`topic_id` BIGINT NOT NULL,
	`state_type_id` BIGINT NOT NULL,
	`sort_order` INT DEFAULT '0',
	`create_user` BIGINT NOT NULL,
	`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

INSERT INTO `bbs_topic_state_type`
(`id`, `topic_id`, `state_type_id`, `sort_order`, `create_user`, `create_time`)
VALUES
('1', '1', '1', 1, '1', '2021-10-30 22:26:35'),
('2', '1', '2', 2, '1', '2021-10-30 22:26:35'),
('3', '2', '1', 1, '1', '2021-10-30 22:26:35'),
('4', '3', '2', 1, '1', '2021-10-30 22:26:35'),
('5', '4', '3', 1, '1', '2021-10-30 22:26:35')
;




