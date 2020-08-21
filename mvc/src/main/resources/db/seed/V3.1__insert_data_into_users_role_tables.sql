insert into role ( name, allowed_resource, allowed_read, allowed_create, allowed_update, allowed_delete) values
(1, 'Admin', '/', 'Y', 'Y', 'Y', 'Y'),
(2, 'Manager', '/depts,/departments,/employees,/ems,/acnts,/accounts', 'Y', 'Y', 'Y', 'N'),
(3, 'user', '/employees,/ems,/acnts,/accounts', 'Y', 'N', 'N', 'N')
;
commit;

insert into users (id, name, password, first_name, last_name, email) values
(1, 'dwang', '25f9e794323b453885f5181f1b624d0b', 'David', 'Wang', 'dwang@training.ascendingdc.com'),
(2, 'rhang', '25f9e794323b453885f5181f1b624d0b', 'Ryo', 'Hang', 'rhang@training.ascendingdc.com'),
(3, 'xyhuang', '25f9e794323b453885f5181f1b624d0b', 'Xinyue', 'Huang', 'xyhuang@training.ascendingdc.com')
;
commit;

insert into users_role values
(1, 1),
(2, 2),
(3, 3),
(1, 2),
(1, 3)
;
commit;