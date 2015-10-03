--set SCHEMA 'sptransp';

delete from ST_CUSTOMER;
delete from ST_EMPLOYEE;
delete from ST_EMPLOYEE_AUD;

-- #################################################################
-- CUSTOMERS
-- #################################################################
insert into ST_CUSTOMER (id, uid_customer, full_name) values (1, 'vwjsoq011', 'Skalith');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (2, 'oznrxc887', 'Shufflester');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (3, 'yobazt222', 'Quinu');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (4, 'xgdkqm939', 'Wikizz');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (5, 'mldhbu961', 'Zoonoodle');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (6, 'zdimuk213', 'Leenti');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (7, 'cfusei030', 'Quatz');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (8, 'fsqmgj114', 'Katz');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (9, 'gzkadl461', 'Yamia');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (10, 'jlgkhr017', 'Cogidoo');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (11, 'peyrno188', 'Devpulse');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (12, 'vszgpa130', 'Twinte');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (13, 'huntjq889', 'Edgeblab');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (14, 'wkdlrq119', 'Jayo');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (15, 'qyhres230', 'Kwilith');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (16, 'bcovnz346', 'Jabbercube');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (17, 'fyzura382', 'Avaveo');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (18, 'jrtepk909', 'Trupe');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (19, 'guntir732', 'Rhycero');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (20, 'glntej546', 'Zoonoodle');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (21, 'ybvizh597', 'Myworks');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (22, 'mbaysx660', 'Trudoo');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (23, 'fckrip513', 'Leenti');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (24, 'lpxjrv389', 'Skalith');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (25, 'airhvs280', 'Kwideo');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (26, 'qckbdg863', 'Bluejam');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (27, 'kzxrvu025', 'Zoombox');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (28, 'blmgoh541', 'Feednation');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (29, 'mnfuld681', 'Thoughtblab');
insert into ST_CUSTOMER (id, uid_customer, full_name) values (30, 'xuilcr924', 'Topicware');

-- #################################################################
-- EMPLOYEES
-- #################################################################
insert into ST_EMPLOYEE (id, version, uid_employee, full_name, id_department, seniority, creation_date, creation_user, update_date, update_user) values (1, 1, 'grjulb709', 'Ronald Montgomery', 2, 70, '2015-07-22 01:29:03', 'script', '2015-07-22 01:29:03', 'script');
insert into ST_EMPLOYEE (id, version, uid_employee, full_name, id_department, seniority, creation_date, creation_user, update_date, update_user) values (2, 1, 'ivjwhl049', 'Fred Taylor', 2, 80, '2015-07-08 20:26:09', 'script', '2015-07-08 20:26:09', 'script');
insert into ST_EMPLOYEE (id, version, uid_employee, full_name, id_department, seniority, creation_date, creation_user, update_date, update_user) values (3, 1, 'rtwndb410', 'Gloria Daniels', 3, 10, '2015-06-18 06:25:01', 'script', '2015-06-18 06:25:01', 'script');
insert into ST_EMPLOYEE (id, version, uid_employee, full_name, id_department, seniority, creation_date, creation_user, update_date, update_user) values (4, 1, 'hxcpzo464', 'Catherine Hall', 3, 80, '2015-02-06 02:27:17', 'script', '2015-02-06 02:27:17', 'script');
insert into ST_EMPLOYEE (id, version, uid_employee, full_name, id_department, seniority, creation_date, creation_user, update_date, update_user) values (5, 1, 'broqui372', 'Peter Watkins', 2, 20, '2015-06-03 02:16:14', 'script', '2015-06-03 02:16:14', 'script');
insert into ST_EMPLOYEE (id, version, uid_employee, full_name, id_department, seniority, creation_date, creation_user, update_date, update_user) values (6, 1, 'umphqv688', 'Paul Cole', 1, 50, '2015-09-24 05:29:55', 'script', '2015-09-24 05:29:55', 'script');
insert into ST_EMPLOYEE (id, version, uid_employee, full_name, id_department, seniority, creation_date, creation_user, update_date, update_user) values (7, 1, 'aljots017', 'Angela Ellis', 3, 80, '2015-01-26 23:41:17', 'script', '2015-01-26 23:41:17', 'script');
insert into ST_EMPLOYEE (id, version, uid_employee, full_name, id_department, seniority, creation_date, creation_user, update_date, update_user) values (8, 1, 'oldyvt984', 'Kathryn Richards', 3, 10, '2014-10-14 12:35:08', 'script', '2014-10-14 12:35:08', 'script');
insert into ST_EMPLOYEE (id, version, uid_employee, full_name, id_department, seniority, creation_date, creation_user, update_date, update_user) values (9, 1, 'fugbpz492', 'Eugene Smith', 4, 80, '2014-11-16 06:05:00', 'script', '2014-11-16 06:05:00', 'script');
insert into ST_EMPLOYEE (id, version, uid_employee, full_name, id_department, seniority, creation_date, creation_user, update_date, update_user) values (10, 1, 'ykjlor025', 'Kelly George', 3, 60, '2015-05-31 10:35:36', 'script', '2015-05-31 10:35:36', 'script');
insert into ST_EMPLOYEE (id, version, uid_employee, full_name, id_department, seniority, creation_date, creation_user, update_date, update_user) values (11, 1, 'fzhtkw631', 'Irene Hill', 1, 50, '2015-03-04 09:36:26', 'script', '2015-03-04 09:36:26', 'script');
insert into ST_EMPLOYEE (id, version, uid_employee, full_name, id_department, seniority, creation_date, creation_user, update_date, update_user) values (12, 1, 'dpwqle606', 'Diana Schmidt', 3, 10, '2015-02-21 21:42:12', 'script', '2015-02-21 21:42:12', 'script');
insert into ST_EMPLOYEE (id, version, uid_employee, full_name, id_department, seniority, creation_date, creation_user, update_date, update_user) values (13, 1, 'ewhdsk397', 'Angela Wallace', 1, 80, '2014-10-08 17:07:14', 'script', '2014-10-08 17:07:14', 'script');
insert into ST_EMPLOYEE (id, version, uid_employee, full_name, id_department, seniority, creation_date, creation_user, update_date, update_user) values (14, 1, 'sdvkbl958', 'Diana Ramirez', 3, 60, '2014-10-06 01:02:03', 'script', '2014-10-06 01:02:03', 'script');
insert into ST_EMPLOYEE (id, version, uid_employee, full_name, id_department, seniority, creation_date, creation_user, update_date, update_user) values (15, 1, 'erujbq030', 'Matthew Bishop', 4, 30, '2015-01-03 00:36:23', 'script', '2015-01-03 00:36:23', 'script');
insert into ST_EMPLOYEE (id, version, uid_employee, full_name, id_department, seniority, creation_date, creation_user, update_date, update_user) values (16, 1, 'otdpkw041', 'Christopher Elliott', 2, 20, '2015-07-20 04:22:02', 'script', '2015-07-20 04:22:02', 'script');
insert into ST_EMPLOYEE (id, version, uid_employee, full_name, id_department, seniority, creation_date, creation_user, update_date, update_user) values (17, 1, 'jbcmie723', 'Raymond Hayes', 2, 90, '2015-06-09 01:30:00', 'script', '2015-06-09 01:30:00', 'script');
insert into ST_EMPLOYEE (id, version, uid_employee, full_name, id_department, seniority, creation_date, creation_user, update_date, update_user) values (18, 1, 'fumdgw047', 'Harry Hansen', 1, 10, '2015-03-03 21:07:48', 'script', '2015-03-03 21:07:48', 'script');
insert into ST_EMPLOYEE (id, version, uid_employee, full_name, id_department, seniority, creation_date, creation_user, update_date, update_user) values (19, 1, 'qxzcjv346', 'Frances Nichols', 4, 30, '2015-09-02 08:24:31', 'script', '2015-09-02 08:24:31', 'script');
insert into ST_EMPLOYEE (id, version, uid_employee, full_name, id_department, seniority, creation_date, creation_user, update_date, update_user) values (20, 1, 'jznbpm372', 'Elizabeth Wilson', 3, 30, '2015-09-03 20:15:18', 'script', '2015-09-03 20:15:18', 'script');

insert into ST_EMPLOYEE_AUD (id, version, uid_employee, full_name, id_department, seniority, version_date, version_user) values (1, 1, 'grjulb709', 'Ronald Montgomery', 2, 70, '2015-07-22 01:29:03', 'script');
insert into ST_EMPLOYEE_AUD (id, version, uid_employee, full_name, id_department, seniority, version_date, version_user) values (2, 1, 'ivjwhl049', 'Fred Taylor', 2, 80, '2015-07-08 20:26:09', 'script');
insert into ST_EMPLOYEE_AUD (id, version, uid_employee, full_name, id_department, seniority, version_date, version_user) values (3, 1, 'rtwndb410', 'Gloria Daniels', 3, 10, '2015-06-18 06:25:01', 'script');
insert into ST_EMPLOYEE_AUD (id, version, uid_employee, full_name, id_department, seniority, version_date, version_user) values (4, 1, 'hxcpzo464', 'Catherine Hall', 3, 80, '2015-02-06 02:27:17', 'script');
insert into ST_EMPLOYEE_AUD (id, version, uid_employee, full_name, id_department, seniority, version_date, version_user) values (5, 1, 'broqui372', 'Peter Watkins', 2, 20, '2015-06-03 02:16:14', 'script');
insert into ST_EMPLOYEE_AUD (id, version, uid_employee, full_name, id_department, seniority, version_date, version_user) values (6, 1, 'umphqv688', 'Paul Cole', 1, 50, '2015-09-24 05:29:55', 'script');
insert into ST_EMPLOYEE_AUD (id, version, uid_employee, full_name, id_department, seniority, version_date, version_user) values (7, 1, 'aljots017', 'Angela Ellis', 3, 80, '2015-01-26 23:41:17', 'script');
insert into ST_EMPLOYEE_AUD (id, version, uid_employee, full_name, id_department, seniority, version_date, version_user) values (8, 1, 'oldyvt984', 'Kathryn Richards', 3, 10, '2014-10-14 12:35:08', 'script');
insert into ST_EMPLOYEE_AUD (id, version, uid_employee, full_name, id_department, seniority, version_date, version_user) values (9, 1, 'fugbpz492', 'Eugene Smith', 4, 80, '2014-11-16 06:05:00', 'script');
insert into ST_EMPLOYEE_AUD (id, version, uid_employee, full_name, id_department, seniority, version_date, version_user) values (10, 1, 'ykjlor025', 'Kelly George', 3, 60, '2015-05-31 10:35:36', 'script');
insert into ST_EMPLOYEE_AUD (id, version, uid_employee, full_name, id_department, seniority, version_date, version_user) values (11, 1, 'fzhtkw631', 'Irene Hill', 1, 50, '2015-03-04 09:36:26', 'script');
insert into ST_EMPLOYEE_AUD (id, version, uid_employee, full_name, id_department, seniority, version_date, version_user) values (12, 1, 'dpwqle606', 'Diana Schmidt', 3, 10, '2015-02-21 21:42:12', 'script');
insert into ST_EMPLOYEE_AUD (id, version, uid_employee, full_name, id_department, seniority, version_date, version_user) values (13, 1, 'ewhdsk397', 'Angela Wallace', 1, 80, '2014-10-08 17:07:14', 'script');
insert into ST_EMPLOYEE_AUD (id, version, uid_employee, full_name, id_department, seniority, version_date, version_user) values (14, 1, 'sdvkbl958', 'Diana Ramirez', 3, 60, '2014-10-06 01:02:03', 'script');
insert into ST_EMPLOYEE_AUD (id, version, uid_employee, full_name, id_department, seniority, version_date, version_user) values (15, 1, 'erujbq030', 'Matthew Bishop', 4, 30, '2015-01-03 00:36:23', 'script');
insert into ST_EMPLOYEE_AUD (id, version, uid_employee, full_name, id_department, seniority, version_date, version_user) values (16, 1, 'otdpkw041', 'Christopher Elliott', 2, 20, '2015-07-20 04:22:02', 'script');
insert into ST_EMPLOYEE_AUD (id, version, uid_employee, full_name, id_department, seniority, version_date, version_user) values (17, 1, 'jbcmie723', 'Raymond Hayes', 2, 90, '2015-06-09 01:30:00', 'script');
insert into ST_EMPLOYEE_AUD (id, version, uid_employee, full_name, id_department, seniority, version_date, version_user) values (18, 1, 'fumdgw047', 'Harry Hansen', 1, 10, '2015-03-03 21:07:48', 'script');
insert into ST_EMPLOYEE_AUD (id, version, uid_employee, full_name, id_department, seniority, version_date, version_user) values (19, 1, 'qxzcjv346', 'Frances Nichols', 4, 30, '2015-09-02 08:24:31', 'script');
insert into ST_EMPLOYEE_AUD (id, version, uid_employee, full_name, id_department, seniority, version_date, version_user) values (20, 1, 'jznbpm372', 'Elizabeth Wilson', 3, 30, '2015-09-03 20:15:18', 'script');
