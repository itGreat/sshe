/**
  * sshe script
  */


/***** select *****/
SELECT * FROM t_sys_user;
SELECT * FROM t_sys_role;
SELECT * FROM t_sys_entity;
SELECT * FROM t_sys_dept;
SELECT * FROM t_sys_node;

/***** insert *****/
-- insert user
INSERT INTO t_sys_user(id,deleted,NAME,PASSWORD,username) VALUES ('1',0,'gc','111111','gongchang');
INSERT INTO t_sys_user(id,deleted,NAME,PASSWORD,username) VALUES ('2',0,'tmm','111111','tmm');
INSERT INTO t_sys_user(id,deleted,NAME,PASSWORD,username) VALUES ('3',0,'fly','111111','gongchang');

-- insert dept
INSERT INTO sshe.t_sys_dept (id, CODE, NAME, remark, parent_id	)VALUES	('1', '01', '开发部', 'remark', NULL);
INSERT INTO sshe.t_sys_dept (id, CODE, NAME, remark, parent_id	)VALUES	('2', '02', '设计部', 'remark', NULL);
INSERT INTO sshe.t_sys_dept (id, CODE, NAME, remark, parent_id	)VALUES	('3', '03', '销售部', 'remark', NULL);

-- insert entity
INSERT INTO sshe.t_sys_entity (id, NAME, remark, TYPE, VALUE)VALUES('1', '用户管理', 'remark', 'menu', 'value');
INSERT INTO sshe.t_sys_entity (id, NAME, remark, TYPE, VALUE)VALUES('2', '部门管理', 'remark', 'menu', 'value');
INSERT INTO sshe.t_sys_entity (id, NAME, remark, TYPE, VALUE)VALUES('3', '功能管理', 'remark', 'menu', 'value');
INSERT INTO sshe.t_sys_entity (id, NAME, remark, TYPE, VALUE)VALUES('4', '角色管理', 'remark', 'menu', 'value');

-- insert node
INSERT INTO sshe.t_sys_node (id,layer,NAME,seq,entity_id,parent_id) VALUES ('1','1','name','1','entity_id','parent_id' );

	 
	
	

