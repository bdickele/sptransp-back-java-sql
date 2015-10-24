SET SCHEMA 'sptransp';

DELETE FROM ST_AGR_RULE_VISA_AUD;
DELETE FROM ST_AGREEMENT_RULE_AUD;
DELETE FROM ST_AGR_RULE_VISA;
DELETE FROM ST_AGREEMENT_RULE;

DELETE FROM ST_REQUEST_AGR_VISA;
DELETE FROM ST_REQUEST;


-- Goods
/*
1,OIL,Oil
2,FOOD,Food
3,MACHINE_TOOL,"Machine tool"
4,MEDICINE,Medicine
5,WEAPON,Weapon
*/

-- Departments
/*
1,LAW_COMPLIANCE,"Law compliance"
2,SHUTTLE_COMPLIANCE,"Shuttle compliance"
3,GOOD_INSPECTION,"Good inspection"
4,JOURNEY_SUPERVISION,"Journey supervision"
5,HR,"Human resources"
 */

-- Rule Earth - Food

INSERT INTO ST_AGREEMENT_RULE(ID, VERSION, ID_DESTINATION, ID_GOODS, REQ_ALLOWED, CREATION_DATE, CREATION_USER, UPDATE_DATE, UPDATE_USER)
VALUES(1, 1, 1, 2, '1', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin');

INSERT INTO ST_AGR_RULE_VISA(ID, ID_RULE, VISA_RANK, ID_DEPARTMENT, SENIORITY)
VALUES(1, 1, 0, 1, 20);
INSERT INTO ST_AGR_RULE_VISA(ID, ID_RULE, VISA_RANK, ID_DEPARTMENT, SENIORITY)
VALUES(2, 1, 1, 2, 40);
INSERT INTO ST_AGR_RULE_VISA(ID, ID_RULE, VISA_RANK, ID_DEPARTMENT, SENIORITY)
VALUES(3, 1, 2, 3, 20);
INSERT INTO ST_AGR_RULE_VISA(ID, ID_RULE, VISA_RANK, ID_DEPARTMENT, SENIORITY)
VALUES(4, 1, 3, 4, 30);

INSERT INTO ST_AGREEMENT_RULE_AUD(ID_RULE, VERSION, ID_DESTINATION, ID_GOODS, REQ_ALLOWED, VERSION_DATE, VERSION_USER)
VALUES(1, 1, 1, 2, '1', CURRENT_TIMESTAMP, 'admin');

INSERT INTO ST_AGR_RULE_VISA_AUD(ID_RULE, RULE_VERSION, VISA_RANK, ID_DEPARTMENT, SENIORITY)
VALUES(1, 1, 0, 1, 20);
INSERT INTO ST_AGR_RULE_VISA_AUD(ID_RULE, RULE_VERSION, VISA_RANK, ID_DEPARTMENT, SENIORITY)
VALUES(1, 1, 1, 2, 40);
INSERT INTO ST_AGR_RULE_VISA_AUD(ID_RULE, RULE_VERSION, VISA_RANK, ID_DEPARTMENT, SENIORITY)
VALUES(1, 1, 2, 3, 20);
INSERT INTO ST_AGR_RULE_VISA_AUD(ID_RULE, RULE_VERSION, VISA_RANK, ID_DEPARTMENT, SENIORITY)
VALUES(1, 1, 3, 4, 30);

-- Rule Earth - Oil
INSERT INTO ST_AGREEMENT_RULE(ID, VERSION, ID_DESTINATION, ID_GOODS, REQ_ALLOWED, CREATION_DATE, CREATION_USER, UPDATE_DATE, UPDATE_USER)
VALUES(2, 1, 1, 1, '1', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin');

INSERT INTO ST_AGR_RULE_VISA(ID, ID_RULE, VISA_RANK, ID_DEPARTMENT, SENIORITY)
VALUES(5, 2, 0, 1, 60);
INSERT INTO ST_AGR_RULE_VISA(ID, ID_RULE, VISA_RANK, ID_DEPARTMENT, SENIORITY)
VALUES(6, 2, 1, 2, 40);
INSERT INTO ST_AGR_RULE_VISA(ID, ID_RULE, VISA_RANK, ID_DEPARTMENT, SENIORITY)
VALUES(7, 2, 2, 3, 60);
INSERT INTO ST_AGR_RULE_VISA(ID, ID_RULE, VISA_RANK, ID_DEPARTMENT, SENIORITY)
VALUES(8, 2, 3, 4, 40);

INSERT INTO ST_AGREEMENT_RULE_AUD(ID_RULE, VERSION, ID_DESTINATION, ID_GOODS, REQ_ALLOWED, VERSION_DATE, VERSION_USER)
VALUES(2, 1, 1, 1, '1', CURRENT_TIMESTAMP, 'admin');

INSERT INTO ST_AGR_RULE_VISA_AUD(ID_RULE, RULE_VERSION, VISA_RANK, ID_DEPARTMENT, SENIORITY)
VALUES(2, 1, 0, 1, 60);
INSERT INTO ST_AGR_RULE_VISA_AUD(ID_RULE, RULE_VERSION, VISA_RANK, ID_DEPARTMENT, SENIORITY)
VALUES(2, 1, 1, 2, 40);
INSERT INTO ST_AGR_RULE_VISA_AUD(ID_RULE, RULE_VERSION, VISA_RANK, ID_DEPARTMENT, SENIORITY)
VALUES(2, 1, 2, 3, 60);
INSERT INTO ST_AGR_RULE_VISA_AUD(ID_RULE, RULE_VERSION, VISA_RANK, ID_DEPARTMENT, SENIORITY)
VALUES(2, 1, 3, 4, 40);

-- Rule Moon - Oil

INSERT INTO ST_AGREEMENT_RULE(ID, VERSION, ID_DESTINATION, ID_GOODS, REQ_ALLOWED, CREATION_DATE, CREATION_USER, UPDATE_DATE, UPDATE_USER)
VALUES(10, 1, 2, 1, '1', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin');

INSERT INTO ST_AGR_RULE_VISA(ID, ID_RULE, VISA_RANK, ID_DEPARTMENT, SENIORITY)
VALUES(20, 10, 0, 1, 40);
INSERT INTO ST_AGR_RULE_VISA(ID, ID_RULE, VISA_RANK, ID_DEPARTMENT, SENIORITY)
VALUES(21, 10, 1, 2, 60);
INSERT INTO ST_AGR_RULE_VISA(ID, ID_RULE, VISA_RANK, ID_DEPARTMENT, SENIORITY)
VALUES(22, 10, 2, 3, 40);
INSERT INTO ST_AGR_RULE_VISA(ID, ID_RULE, VISA_RANK, ID_DEPARTMENT, SENIORITY)
VALUES(23, 10, 3, 4, 50);

INSERT INTO ST_AGREEMENT_RULE_AUD(ID_RULE, VERSION, ID_DESTINATION, ID_GOODS, REQ_ALLOWED, VERSION_DATE, VERSION_USER)
VALUES(10, 1, 2, 1, '1', CURRENT_TIMESTAMP, 'admin');

INSERT INTO ST_AGR_RULE_VISA_AUD(ID_RULE, RULE_VERSION, VISA_RANK, ID_DEPARTMENT, SENIORITY)
VALUES(10, 1, 0, 1, 40);
INSERT INTO ST_AGR_RULE_VISA_AUD(ID_RULE, RULE_VERSION, VISA_RANK, ID_DEPARTMENT, SENIORITY)
VALUES(10, 1, 1, 2, 60);
INSERT INTO ST_AGR_RULE_VISA_AUD(ID_RULE, RULE_VERSION, VISA_RANK, ID_DEPARTMENT, SENIORITY)
VALUES(10, 1, 2, 3, 40);
INSERT INTO ST_AGR_RULE_VISA_AUD(ID_RULE, RULE_VERSION, VISA_RANK, ID_DEPARTMENT, SENIORITY)
VALUES(10, 1, 3, 4, 50);

-- Rules not allowed

INSERT INTO ST_AGREEMENT_RULE(ID, VERSION, ID_DESTINATION, ID_GOODS, REQ_ALLOWED, CREATION_DATE, CREATION_USER, UPDATE_DATE, UPDATE_USER)
VALUES(60, 1, 1, 5, '0', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin');
INSERT INTO ST_AGREEMENT_RULE_AUD(ID_RULE, VERSION, ID_DESTINATION, ID_GOODS, REQ_ALLOWED, VERSION_DATE, VERSION_USER)
VALUES(60, 1, 1, 5, '0', CURRENT_TIMESTAMP, 'admin');

INSERT INTO ST_AGREEMENT_RULE(ID, VERSION, ID_DESTINATION, ID_GOODS, REQ_ALLOWED, CREATION_DATE, CREATION_USER, UPDATE_DATE, UPDATE_USER)
VALUES(61, 1, 2, 5, '0', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin');
INSERT INTO ST_AGREEMENT_RULE_AUD(ID_RULE, VERSION, ID_DESTINATION, ID_GOODS, REQ_ALLOWED, VERSION_DATE, VERSION_USER)
VALUES(61, 1, 2, 5, '0', CURRENT_TIMESTAMP, 'admin');