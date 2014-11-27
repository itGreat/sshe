/**
  * sshe script
  */


/***** select *****/
SELECT * FROM t_sys_user;
SELECT * FROM t_sys_role;
SELECT * FROM t_sys_entity t WHERE t.id = '1';
SELECT * FROM t_sys_dept;
SELECT * FROM t_sys_node;

SELECT * FROM t_sys_role_entity;
DELETE FROM t_sys_role_entity

SELECT * FROM t_sys_node t WHERE t.id  
IN ('2','402881eb49dcb7b70149dcd51d6f0001','3','5','402881eb49dc84ad0149dc87567e0000','402881eb49dc84ad0149dc8756b50001','402881eb49dc84ad0149dc8758300002')


/***** insert *****/
-- insert user
INSERT INTO t_sys_user(id,deleted,NAME,PASSWORD,username) VALUES ('1',0,'gc','111111','gongchang');
INSERT INTO t_sys_user(id,deleted,NAME,PASSWORD,username) VALUES ('2',0,'tmm','111111','tmm');
INSERT INTO t_sys_user(id,deleted,NAME,PASSWORD,username) VALUES ('3',0,'fly','111111','gongchang');

-- insert dept
INSERT INTO sshe.t_sys_dept (id, CODE, NAME, remark, parent_id	)VALUES	('1', '01', '开发部', 'remark', NULL);
INSERT INTO sshe.t_sys_dept (id, CODE, NAME, remark, parent_id	)VALUES	('2', '02', '设计部', 'remark', NULL);
INSERT INTO sshe.t_sys_dept (id, CODE, NAME, remark, parent_id	)VALUES	('3', '03', '销售部', 'remark', NULL);
INSERT INTO sshe.t_sys_dept (id, CODE, NAME, remark, parent_id	)VALUES	('4', '04', '公司', 'remark', NULL);
-- insert entity
DELETE FROM t_sys_entity
SELECT * FROM t_sys_entity;
INSERT INTO sshe.t_sys_entity (id, NAME, remark, TYPE, VALUE)VALUES('5', '用户部门列表', 'remark', 'menu', '/sys/userdept_list.action');
INSERT INTO sshe.t_sys_entity (id, NAME, remark, TYPE, VALUE)VALUES('2', '功能列表', 'remark', 'menu', '/sys/entity_list.action');
INSERT INTO sshe.t_sys_entity (id, NAME, remark, TYPE, VALUE)VALUES('3', '菜单列表', 'remark', 'menu', '/sys/menu_list.action');
INSERT INTO sshe.t_sys_entity (id, NAME, remark, TYPE, VALUE)VALUES('4', '角色列表', 'remark', 'menu', '/sys/role_list.action');


-- insert node
INSERT INTO sshe.t_sys_node (id,layer,NAME,seq,entity_id,parent_id) VALUES ('1',0,'用户部门管理',1,5,NULL);
INSERT INTO sshe.t_sys_node (id,layer,NAME,seq,entity_id,parent_id) VALUES ('2',0,'功能管理',1,2,NULL);
INSERT INTO sshe.t_sys_node (id,layer,NAME,seq,entity_id,parent_id) VALUES ('3',0,'菜单管理',1,3,NULL);
INSERT INTO sshe.t_sys_node (id,layer,NAME,seq,entity_id,parent_id) VALUES ('4',0,'菜单管理',1,4,NULL);

INSERT INTO sshe.t_sys_node (id,layer,NAME,seq,entity_id,parent_id) VALUES ('5',0,'角色管理',2,4,NULL);
SELECT * FROM t_sys_node;
SELECT * FROM t_sys_entity;
-- insert role
INSERT INTO t_sys_role(id,NAME,remark) VALUES ('1','系统管理员',NULL);

SELECT
        node0_.id AS id2_,
        node0_.entity_id AS entity5_2_,
        node0_.layer AS layer2_,
        node0_.name AS name2_,
        node0_.parent_id AS parent6_2_,
        node0_.seq AS seq2_ 
    FROM
        t_sys_node node0_ 
    WHERE
        node0_.parent_id IS NULL
        
        SELECT
        node0_.id AS id2_,
        node0_.entity_id AS entity5_2_,
        node0_.layer AS layer2_,
        node0_.name AS name2_,
        node0_.parent_id AS parent6_2_,
        node0_.seq AS seq2_ 
    FROM
        t_sys_node node0_ 
    WHERE
        node0_.parent_id ='4'

-- 菜单关联 功能
-- 角色 管理 功能

INSERT INTO t_sys_role_entity (role_id,entity_id) VALUES ('1','5');
INSERT INTO t_sys_role_entity (role_id,entity_id) VALUES ('1','2');
INSERT INTO t_sys_role_entity (role_id,entity_id) VALUES ('1','3');
INSERT INTO t_sys_role_entity (role_id,entity_id) VALUES ('1','4');

-- 用户 关联 角色
INSERT INTO t_sys_user_role (user_id,role_id) VALUES ('1','1');


-- 系统管理员
	
SELECT t.id FROM t_sys_user t WHERE t.username = 'gongchang' AND t.password = '111111'

SELECT * FROM t_sys_user_role ur WHERE 1=1
AND ur.user_id = (SELECT t.id FROM t_sys_user t WHERE t.username = 'gongchang' AND t.password = '111111')

SELECT * FROM t_sys_role_entity re WHERE 1=1
AND re.role_id = (
	SELECT ur.role_id FROM t_sys_user_role ur WHERE 1=1
		AND ur.user_id = (SELECT t.id FROM t_sys_user t WHERE t.username = 'gongchang' AND t.password = '111111'))

SELECT * FROM t_sys_role_entity re WHERE 1=1
AND re.role_id = (
	SELECT ur.role_id FROM t_sys_user_role ur WHERE 1=1
		AND ur.user_id = 1)


SELECT DISTINCT t.* FROM t_sys_entity t,t_sys_role_entity re,t_sys_user_role ur
WHERE  ur.role_id = re.role_id AND ur.user_id = 1

SELECT * FROM t_sys_node t WHERE t.parent_id IS NULL

SELECT
        node0_.id AS id2_,
        node0_.entity_id AS entity5_2_,
        node0_.layer AS layer2_,
        node0_.name AS name2_,
        node0_.parent_id AS parent6_2_,
        node0_.seq AS seq2_ 
    FROM
        t_sys_node node0_ 
    WHERE
        node0_.parent_id IS NULL

/sys/entity/list.action


    SELECT
        entity0_.id AS col_0_0_,
        entity0_.type AS col_1_0_,
        entity0_.name AS col_2_0_,
        entity0_.value AS col_3_0_,
        entity0_.remark AS col_4_0_ 
    FROM
        t_sys_entity entity0_ 
    ORDER BY
        entity0_.id DESC
SELECT node0_.id AS col_0_0_, node0_.name AS col_1_0_ FROM t_sys_node node0_ CROSS JOIN t_sys_entity entity1_ WHERE node0_.entity_id=entity1_.id AND entity1_.type='menu' ORDER BY node0_.id desc