CREATE TABLE `p2p_user`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `username`    varchar(200) NOT NULL COMMENT '用户名',
    `password`    varchar(255) NOT NULL COMMENT '密码',
    `nickname`    varchar(255) NOT NULL COMMENT '昵称',
    `deleted`     int          NOT NULL DEFAULT '0' COMMENT '逻辑删',
    `ver`         int          NOT NULL DEFAULT '0' COMMENT '版本号',
    `create_user` varchar(255) NOT NULL COMMENT '创建用户',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `update_user` varchar(255) NOT NULL COMMENT '更新用户',
    `update_time` datetime     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;