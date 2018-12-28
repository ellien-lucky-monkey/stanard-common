CREATE TABLE `user`
(
  `id`                  bigint(20) NOT NULL AUTO_INCREMENT,
  `identity`            varchar(32)  NOT NULL DEFAULT '' COMMENT '身份证号码',
  `is_deleted`          tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 1是 0否',
  `mobile`              varchar(32)  NOT NULL DEFAULT '' COMMENT '手机号',
  `name`                varchar(64)  NOT NULL DEFAULT '' COMMENT '姓名',
  `password`            varchar(64)  NOT NULL COMMENT '登录密码',
  `status`              tinyint(4) NOT NULL DEFAULT '1' COMMENT '用户状态 1 启用 2停用',
  `username`            varchar(64)  NOT NULL DEFAULT '' COMMENT '用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户信息表';