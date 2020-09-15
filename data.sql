DROP TABLE IF EXISTS  face_storage;
CREATE TABLE `face_storage`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件的唯一索引',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件名',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件类型',
  `size` int(11) NOT NULL COMMENT '文件大小',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件访问链接',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `isLocal` tinyint(1) NULL DEFAULT 0 COMMENT '是否本地上传0-false,1-true',
  `localPath` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '本地文件路径，当isLocal为1时有效',
  PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS  face_user;
CREATE TABLE `face_user`  (
  `user_id` int(9) NOT NULL AUTO_INCREMENT COMMENT '1000开始',
  `user_nick` varchar(50) NOT NULL default '' COMMENT '用户昵称',
  `user_real` varchar(16) NULL COMMENT '用户真实姓名',
  `user_sex` int(1) NULL COMMENT '用户性别',
  `user_head` varchar(255) NULL COMMENT '用户头像',
  `user_bir` datetime NULL COMMENT '用户生日',
  `user_phone` varchar(11) NULL COMMENT '用户电话',
  `group_id` int NOT NULL COMMENT '用户组id',
  `user_email` varchar(255) NULL COMMENT '用户邮箱',
  `login_ip` varchar(20)  NULL COMMENT '登录IP地址',
  `create_time` datetime NULL COMMENT '创建时间',
  `user_pwd` varchar(255)  NOT NULL COMMENT '用户登录密码',
  `access_token` varchar(500)  NULL COMMENT '登录授权token',
  `expires_time` datetime  NULL COMMENT 'token过期时间',
  `prohibit` int(1) NOT NULL DEFAULT 0 COMMENT  '是否禁止用户登录  0-false、1-true',
  PRIMARY KEY (`user_id`)
)auto_increment=1000;



DROP TABLE IF EXISTS  face_manager;
CREATE TABLE `face_manager`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，唯一标识，自增，步长1',
  `user_nick` varchar(50) NOT NULL COMMENT '用户昵称',
  `user_pwd` varchar(255)  NOT NULL COMMENT '用户登录密码',
  `access_token` varchar(500)  NULL COMMENT '登录授权token',
  `expires_time` datetime  NULL COMMENT 'token过期时间',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS  face_group;
CREATE TABLE `face_group`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，唯一标识，自增，步长1,组id',
  `group_name` varchar(20)  NOT NULL COMMENT '组名',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS  face_library;
CREATE TABLE `face_library`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，唯一标识，自增，步长1',
  `user_id` int(9) NOT NULL COMMENT '用户id',
  `file_id` int(11) NOT NULL COMMENT '文件id',
  `group_id` int NOT NULL COMMENT '用户组id',
  `face_token` varchar(100)  NOT NULL COMMENT '人脸图片token',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS  face_clock_record;
CREATE TABLE `face_clock_record`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，唯一标识，自增，步长1',
  `today` varchar(20) NOT NULL comment '今天日期',
  `user_id` int(9) NOT NULL COMMENT '用户id',
  `startTime` datetime NULL comment '上班时间',
  `endTime` datetime NULL comment '下班时间',
  PRIMARY KEY (`id`)
);
