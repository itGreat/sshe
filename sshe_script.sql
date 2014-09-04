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


	 
	
	

