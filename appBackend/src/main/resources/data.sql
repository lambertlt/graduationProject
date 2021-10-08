-- INSERT INTO `t_user` VALUES (1, b'1', b'1', b'1', b'1', '$2a$10$Flc0poGrIaUP.Wef3Mi6xOcU4voDHtM7w.Eke2/7LT0U3pSsfwa.O', 'admin', 1);
-- INSERT INTO `t_user_roles` VALUES (1, 1);
-- INSERT INTO `t_role` VALUES (3, 'ROLE_media', '审核员');

-- 创建root用户 admin
INSERT INTO `t_user`
SELECT 1, b'1', b'1', b'1', b'1', '$2a$10$Flc0poGrIaUP.Wef3Mi6xOcU4voDHtM7w.Eke2/7LT0U3pSsfwa.O', 'admin', 1
FROM dual
WHERE not exists (select * from `t_user`
where id = 1);

INSERT INTO `t_user_roles`
SELECT 1, 1
FROM dual
WHERE not exists (select * from `t_user_roles`
where t_user_id = 1);

-- 创建用户lambert、liulinboyi

INSERT INTO `t_user`
SELECT 2, b'1', b'1', b'1', b'1', '$2a$10$Flc0poGrIaUP.Wef3Mi6xOcU4voDHtM7w.Eke2/7LT0U3pSsfwa.O', 'lambert', 1
FROM dual
WHERE not exists (select * from `t_user`
where id = 2);

INSERT INTO `t_user_roles`
SELECT 2, 1
FROM dual
WHERE not exists (select * from `t_user_roles`
where t_user_id = 2);

INSERT INTO `t_user`
SELECT 3, b'1', b'1', b'1', b'1', '$2a$10$Flc0poGrIaUP.Wef3Mi6xOcU4voDHtM7w.Eke2/7LT0U3pSsfwa.O', 'liulinboyi', 1
FROM dual
WHERE not exists (select * from `t_user`
where id = 3);

INSERT INTO `t_user_roles`
SELECT 3, 1
FROM dual
WHERE not exists (select * from `t_user_roles`
where t_user_id = 3);

-- 添加角色审核员

INSERT INTO `t_role`
SELECT 3, 'ROLE_media', '审核员'
FROM dual
WHERE not exists (select * from `t_role`
where id = 3);