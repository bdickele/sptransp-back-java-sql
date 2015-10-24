SET SCHEMA 'sptransp';

DELETE FROM ST_CUSTOMER;
DELETE FROM ST_EMPLOYEE;
DELETE FROM ST_EMPLOYEE_AUD;
DELETE FROM ST_USER;

-- #################################################################
-- EMPLOYEES
-- #################################################################
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (999, 1, 'E', 'dev', 'dev', 'ADMIN_ALL', '2015-04-10 19:11:57', 'script', '2015-04-10 19:11:57', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (1, 1, 'E', 'changeme', 'kvcquz31', 'AGR_VISA_APPLIER', '2015-04-10 19:11:57', 'script', '2015-04-10 19:11:57', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (2, 1, 'E', 'changeme', 'whlofu42', 'AGR_VISA_APPLIER', '2015-03-08 10:08:53', 'script', '2015-03-08 10:08:53', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (3, 1, 'E', 'changeme', 'xhtqyi65', 'AGR_VISA_APPLIER', '2015-03-30 09:44:37', 'script', '2015-03-30 09:44:37', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (4, 1, 'E', 'changeme', 'xzjwsm38', 'AGR_VISA_APPLIER', '2015-08-17 00:47:25', 'script', '2015-08-17 00:47:25', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (5, 1, 'E', 'changeme', 'loncvj78', 'AGR_VISA_APPLIER', '2015-02-27 10:45:32', 'script', '2015-02-27 10:45:32', 'script');
/*
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (6, 1, 'E', 'changeme', 'tbikem91', 'AGR_VISA_APPLIER', '2015-08-27 16:37:36', 'script', '2015-08-27 16:37:36', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (7, 1, 'E', 'changeme', 'dryiwn36', 'AGR_VISA_APPLIER', '2015-03-16 15:33:15', 'script', '2015-03-16 15:33:15', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (8, 1, 'E', 'changeme', 'rajoqm34', 'AGR_VISA_APPLIER', '2015-09-16 16:42:37', 'script', '2015-09-16 16:42:37', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (9, 1, 'E', 'changeme', 'gjixqn84', 'AGR_VISA_APPLIER', '2015-05-19 08:32:46', 'script', '2015-05-19 08:32:46', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (10, 1, 'E', 'changeme', 'pytlvi73', 'AGR_VISA_APPLIER', '2015-04-15 04:51:50', 'script', '2015-04-15 04:51:50', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (11, 1, 'E', 'changeme', 'qlomny06', 'AGR_VISA_APPLIER', '2015-03-10 11:17:00', 'script', '2015-03-10 11:17:00', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (12, 1, 'E', 'changeme', 'xkmvis52', 'AGR_VISA_APPLIER', '2015-03-19 06:34:01', 'script', '2015-03-19 06:34:01', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (13, 1, 'E', 'changeme', 'kvieon14', 'AGR_VISA_APPLIER', '2015-07-22 17:26:54', 'script', '2015-07-22 17:26:54', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (14, 1, 'E', 'changeme', 'figlva46', 'AGR_VISA_APPLIER', '2015-04-23 22:20:40', 'script', '2015-04-23 22:20:40', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (15, 1, 'E', 'changeme', 'ponley99', 'AGR_VISA_APPLIER', '2015-09-29 04:44:46', 'script', '2015-09-29 04:44:46', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (16, 1, 'E', 'changeme', 'mytlas83', 'ADMIN_ALL', '2015-05-10 22:40:10', 'script', '2015-05-10 22:40:10', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (17, 1, 'E', 'changeme', 'dfgbkr92', 'ADMIN_READER', '2015-04-22 20:42:49', 'script', '2015-04-22 20:42:49', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (18, 1, 'E', 'changeme', 'dsejil08', 'RULE_WRITER', '2015-01-08 13:26:31', 'script', '2015-01-08 13:26:31', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (19, 1, 'E', 'changeme', 'obdegf15', 'HR_READER', '2015-07-26 08:31:51', 'script', '2015-07-26 08:31:51', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (20, 1, 'E', 'changeme', 'oanzsf85', 'HR_WRITER', '2015-02-05 19:24:24', 'script', '2015-02-05 19:24:24', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (21, 1, 'E', 'changeme', 'jvrboi41', 'ADMIN_ALL', '2015-02-14 20:22:17', 'script', '2015-02-14 20:22:17', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (22, 1, 'E', 'changeme', 'rewvbo47', 'ADMIN_READER', '2015-04-04 12:35:16', 'script', '2015-04-04 12:35:16', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (23, 1, 'E', 'changeme', 'dfylzm70', 'RULE_WRITER', '2015-09-11 02:37:03', 'script', '2015-09-11 02:37:03', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (24, 1, 'E', 'changeme', 'vqtljf38', 'HR_READER', '2015-01-07 05:23:00', 'script', '2015-01-07 05:23:00', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (25, 1, 'E', 'changeme', 'iflsbe25', 'HR_WRITER', '2015-01-02 03:17:20', 'script', '2015-01-02 03:17:20', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (26, 1, 'E', 'changeme', 'ixqdat90', 'ADMIN_ALL', '2015-04-14 21:18:27', 'script', '2015-04-14 21:18:27', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (27, 1, 'E', 'changeme', 'vwfshr99', 'ADMIN_READER', '2014-10-07 11:56:19', 'script', '2014-10-07 11:56:19', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (28, 1, 'E', 'changeme', 'jzbrck04', 'RULE_WRITER', '2015-05-16 03:17:51', 'script', '2015-05-16 03:17:51', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (29, 1, 'E', 'changeme', 'aliejv72', 'HR_READER', '2015-02-11 01:00:01', 'script', '2015-02-11 01:00:01', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (30, 1, 'E', 'changeme', 'ulesgh33', 'HR_WRITER', '2015-06-12 07:15:58', 'script', '2015-06-12 07:15:58', 'script');
*/
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (999, 'Bertrand DICKELE', 5, 50);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (1, 'Kathleen Carpenter', 4, 20);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (2, 'Helen Cox', 1, 20);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (3, 'Paula Spencer', 2, 50);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (4, 'Juan Hughes', 2, 10);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (5, 'Ashley Wheeler', 3, 10);
/*
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (6, 'Ruby Rogers', 2, 50);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (7, 'Joyce Cruz', 3, 30);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (8, 'Jack Henry', 1, 60);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (9, 'Denise Rodriguez', 4, 10);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (10, 'Justin Williamson', 2, 30);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (11, 'Gregory Bradley', 2, 50);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (12, 'Julie Lopez', 2, 20);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (13, 'Maria Dunn', 4, 20);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (14, 'Randy Richards', 4, 30);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (15, 'Russell Burke', 4, 50);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (16, 'Adam Pierce', 5, 60);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (17, 'Gloria Sims', 5, 60);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (18, 'Judith Gardner', 5, 80);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (19, 'William Ramirez', 5, 80);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (20, 'Helen Bennett', 5, 60);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (21, 'Jeffrey Thompson', 5, 10);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (22, 'Brian Elliott', 5, 20);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (23, 'Henry Thompson', 5, 70);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (24, 'Roger Freeman', 5, 60);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (25, 'Jane Rice', 5, 20);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (26, 'Joan Greene', 5, 40);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (27, 'Joe Gray', 5, 30);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (28, 'Virginia Fernandez', 5, 70);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (29, 'Willie Medina', 5, 60);
insert into ST_EMPLOYEE (id, full_name, id_department, seniority) values (30, 'Clarence Robinson', 5, 30);
*/
-- #################################################################
-- CUSTOMERS
-- #################################################################

insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (31, 1, 'C', 'changeme', 'timulf70', 'CUSTOMER', '2015-07-01 15:26:03', 'script', '2015-07-01 15:26:03', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (32, 1, 'C', 'changeme', 'bxcegf67', 'CUSTOMER', '2014-12-01 22:54:59', 'script', '2014-12-01 22:54:59', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (33, 1, 'C', 'changeme', 'pvdauc04', 'CUSTOMER', '2014-12-12 16:13:20', 'script', '2014-12-12 16:13:20', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (34, 1, 'C', 'changeme', 'qirtjp96', 'CUSTOMER', '2014-12-02 15:55:08', 'script', '2014-12-02 15:55:08', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (35, 1, 'C', 'changeme', 'spzgde71', 'CUSTOMER', '2015-07-26 11:46:50', 'script', '2015-07-26 11:46:50', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (36, 1, 'C', 'changeme', 'mleots02', 'CUSTOMER', '2015-02-18 12:50:44', 'script', '2015-02-18 12:50:44', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (37, 1, 'C', 'changeme', 'rxokeh92', 'CUSTOMER', '2015-07-03 06:53:02', 'script', '2015-07-03 06:53:02', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (38, 1, 'C', 'changeme', 'hadkrx11', 'CUSTOMER', '2015-09-05 23:36:29', 'script', '2015-09-05 23:36:29', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (39, 1, 'C', 'changeme', 'hybmgk57', 'CUSTOMER', '2015-08-17 00:01:52', 'script', '2015-08-17 00:01:52', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (40, 1, 'C', 'changeme', 'pfdxtv63', 'CUSTOMER', '2015-06-10 10:26:42', 'script', '2015-06-10 10:26:42', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (41, 1, 'C', 'changeme', 'ckgqhi62', 'CUSTOMER', '2014-12-30 05:14:38', 'script', '2014-12-30 05:14:38', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (42, 1, 'C', 'changeme', 'rkpvxy15', 'CUSTOMER', '2015-07-13 09:19:21', 'script', '2015-07-13 09:19:21', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (43, 1, 'C', 'changeme', 'raqlew87', 'CUSTOMER', '2014-10-14 16:00:12', 'script', '2014-10-14 16:00:12', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (44, 1, 'C', 'changeme', 'bauhqr22', 'CUSTOMER', '2015-05-12 21:21:52', 'script', '2015-05-12 21:21:52', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (45, 1, 'C', 'changeme', 'jgzlrm63', 'CUSTOMER', '2015-03-07 08:11:34', 'script', '2015-03-07 08:11:34', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (46, 1, 'C', 'changeme', 'laehrn93', 'CUSTOMER', '2015-03-23 14:55:20', 'script', '2015-03-23 14:55:20', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (47, 1, 'C', 'changeme', 'lkpmeh63', 'CUSTOMER', '2015-09-25 08:40:15', 'script', '2015-09-25 08:40:15', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (48, 1, 'C', 'changeme', 'ejasof61', 'CUSTOMER', '2015-03-11 23:46:44', 'script', '2015-03-11 23:46:44', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (49, 1, 'C', 'changeme', 'uqhjdk29', 'CUSTOMER', '2015-08-22 00:14:34', 'script', '2015-08-22 00:14:34', 'script');
insert into ST_USER (id, version, user_type, user_password, uid_user, user_profile, creation_date, creation_user, update_date, update_user) values (50, 1, 'C', 'changeme', 'xtbhuf68', 'CUSTOMER', '2014-12-24 02:05:40', 'script', '2014-12-24 02:05:40', 'script');

insert into ST_CUSTOMER (id, full_name) values (31, 'Jabberstorm');
insert into ST_CUSTOMER (id, full_name) values (32, 'Quamba');
insert into ST_CUSTOMER (id, full_name) values (33, 'Viva');
insert into ST_CUSTOMER (id, full_name) values (34, 'Topicstorm');
insert into ST_CUSTOMER (id, full_name) values (35, 'Babbleset');
insert into ST_CUSTOMER (id, full_name) values (36, 'Skinix');
insert into ST_CUSTOMER (id, full_name) values (37, 'Feedmix');
insert into ST_CUSTOMER (id, full_name) values (38, 'Zoombeat');
insert into ST_CUSTOMER (id, full_name) values (39, 'Fanoodle');
insert into ST_CUSTOMER (id, full_name) values (40, 'Bubbletube');
insert into ST_CUSTOMER (id, full_name) values (41, 'Izio');
insert into ST_CUSTOMER (id, full_name) values (42, 'Jaxnation');
insert into ST_CUSTOMER (id, full_name) values (43, 'Wikizz');
insert into ST_CUSTOMER (id, full_name) values (44, 'Jabberstorm');
insert into ST_CUSTOMER (id, full_name) values (45, 'Skidoo');
insert into ST_CUSTOMER (id, full_name) values (46, 'Kare');
insert into ST_CUSTOMER (id, full_name) values (47, 'Zoonoodle');
insert into ST_CUSTOMER (id, full_name) values (48, 'Jamia');
insert into ST_CUSTOMER (id, full_name) values (49, 'Skiptube');
insert into ST_CUSTOMER (id, full_name) values (50, 'Plajo');

-- #################################################################
-- REQUEST
-- #################################################################
INSERT INTO ST_REQUEST(ID, VERSION, REFERENCE, ID_CUSTOMER, ID_GOODS, ID_DEPARTURE, ID_ARRIVAL, OVERALL_STATUS,
  ID_RULE, RULE_VERSION, AGREEMENT_STATUS, NEXT_AGREEMENT_VISA_RANK, CREATION_DATE, CREATION_USER, UPDATE_DATE, UPDATE_USER)
VALUES(1, 1, 'REFERE0001', 31, 2, 2, 1, 'P', 1, 1, 'P', 0, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin');