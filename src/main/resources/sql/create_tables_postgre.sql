set schema 'sptransp';

drop table if exists ST_AGR_RULE_VISA;
drop table if exists ST_AGREEMENT_RULE;

drop table if exists ST_REQUEST_AGR_VISA;
drop table if exists ST_REQUEST;

drop table if exists ST_AGR_RULE_VISA_AUD;
drop table if exists ST_AGREEMENT_RULE_AUD;

drop table if exists ST_EMPLOYEE_AUD;
drop table if exists ST_EMPLOYEE;

drop table if exists ST_REQUEST_NOT_ALLOWED;

drop table if exists ST_DESTINATION;
drop table if exists ST_GOODS;
drop table if exists ST_DEPARTMENT;
drop table if exists ST_CUSTOMER;

DROP TABLE IF EXISTS ST_USER;

-- Généré par Oracle SQL Developer Data Modeler 4.1.1.888
--   à :        2015-10-05 15:39:44 CEST
--   site :      Oracle Database 11g
--   type :      Oracle Database 11g




CREATE TABLE sptransp.ST_AGREEMENT_RULE
  (
    ID             NUMERIC NOT NULL ,
    VERSION        NUMERIC NOT NULL ,
    ID_DESTINATION NUMERIC NOT NULL ,
    ID_GOODS       NUMERIC NOT NULL ,
    CREATION_DATE  Timestamp NOT NULL ,
    CREATION_USER  Character Varying (50) NOT NULL ,
    UPDATE_DATE    Timestamp NOT NULL ,
    UPDATE_USER    Character Varying (50) NOT NULL
  ) ;
ALTER TABLE sptransp.ST_AGREEMENT_RULE ADD CONSTRAINT ST_AGREEMENT_RULE_PK PRIMARY KEY ( ID ) ;


CREATE TABLE sptransp.ST_AGREEMENT_RULE_AUD
  (
    ID_RULE        NUMERIC NOT NULL ,
    VERSION        NUMERIC NOT NULL ,
    ID_DESTINATION NUMERIC NOT NULL ,
    ID_GOODS       NUMERIC NOT NULL ,
    VERSION_DATE   Timestamp NOT NULL ,
    VERSION_USER   Character Varying (50) NOT NULL
  ) ;
ALTER TABLE sptransp.ST_AGREEMENT_RULE_AUD ADD CONSTRAINT ST_AGREEMENT_RULE_AUD_PK PRIMARY KEY ( ID_RULE, VERSION ) ;


CREATE TABLE sptransp.ST_AGR_RULE_VISA
  (
    ID            NUMERIC NOT NULL ,
    ID_RULE       NUMERIC NOT NULL ,
    RANK          NUMERIC NOT NULL ,
    ID_DEPARTMENT NUMERIC NOT NULL ,
    SENIORITY     NUMERIC NOT NULL
  ) ;
ALTER TABLE sptransp.ST_AGR_RULE_VISA ADD CONSTRAINT ST_AGR_RULE_VISA_PK PRIMARY KEY ( ID ) ;


CREATE TABLE sptransp.ST_AGR_RULE_VISA_AUD
  (
    ID_RULE       NUMERIC NOT NULL ,
    RULE_VERSION  NUMERIC NOT NULL ,
    RANK          NUMERIC NOT NULL ,
    ID_DEPARTMENT NUMERIC NOT NULL ,
    SENIORITY     NUMERIC NOT NULL
  ) ;
ALTER TABLE sptransp.ST_AGR_RULE_VISA_AUD ADD CONSTRAINT ST_AGR_RULE_VISA_AUD_PK PRIMARY KEY ( ID_RULE, RULE_VERSION, RANK ) ;


CREATE TABLE sptransp.ST_CUSTOMER
  (
    ID        NUMERIC NOT NULL ,
    FULL_NAME Character Varying (200) NOT NULL
  ) ;
ALTER TABLE sptransp.ST_CUSTOMER ADD CONSTRAINT ST_CUSTOMER_PK PRIMARY KEY ( ID ) ;


CREATE TABLE sptransp.ST_DEPARTMENT
  (
    ID   NUMERIC NOT NULL ,
    CODE Character Varying (50) NOT NULL ,
    NAME Character Varying (200) NOT NULL
  ) ;
ALTER TABLE sptransp.ST_DEPARTMENT ADD CONSTRAINT ST_DEPARTMENT_PK PRIMARY KEY ( ID ) ;
ALTER TABLE sptransp.ST_DEPARTMENT ADD CONSTRAINT ST_DEPARTMENT_UN_CODE UNIQUE ( CODE ) ;
ALTER TABLE sptransp.ST_DEPARTMENT ADD CONSTRAINT ST_DEPARTMENT_UN_NAME UNIQUE ( NAME ) ;


CREATE TABLE sptransp.ST_DESTINATION
  (
    ID   NUMERIC NOT NULL ,
    CODE Character Varying (50) NOT NULL ,
    NAME Character Varying (200) NOT NULL
  ) ;
ALTER TABLE sptransp.ST_DESTINATION ADD CONSTRAINT ST_DESTINATION_PK PRIMARY KEY ( ID ) ;
ALTER TABLE sptransp.ST_DESTINATION ADD CONSTRAINT ST_DESTINATION_UN_CODE UNIQUE ( CODE ) ;
ALTER TABLE sptransp.ST_DESTINATION ADD CONSTRAINT ST_DESTINATION_UN_NAME UNIQUE ( NAME ) ;


CREATE TABLE sptransp.ST_EMPLOYEE
  (
    ID            NUMERIC NOT NULL ,
    FULL_NAME     Character Varying (200) NOT NULL ,
    ID_DEPARTMENT NUMERIC NOT NULL ,
    SENIORITY     NUMERIC NOT NULL
  ) ;
ALTER TABLE sptransp.ST_EMPLOYEE ADD CONSTRAINT ST_EMPLOYEE_PK PRIMARY KEY ( ID ) ;


CREATE TABLE sptransp.ST_EMPLOYEE_AUD
  (
    ID            NUMERIC NOT NULL ,
    VERSION       NUMERIC NOT NULL ,
    PROFILE       Character Varying (200) NOT NULL ,
    UID_EMPLOYEE  Character Varying (50) NOT NULL ,
    FULL_NAME     Character Varying (200) NOT NULL ,
    ID_DEPARTMENT NUMERIC NOT NULL ,
    SENIORITY     NUMERIC NOT NULL ,
    VERSION_DATE  Timestamp NOT NULL ,
    VERSION_USER  Character Varying (50) NOT NULL
  ) ;
ALTER TABLE sptransp.ST_EMPLOYEE_AUD ADD CONSTRAINT ST_EMPLOYEE_AUD_PK PRIMARY KEY ( ID, VERSION ) ;


CREATE TABLE sptransp.ST_GOODS
  (
    ID   NUMERIC NOT NULL ,
    CODE Character Varying (50) NOT NULL ,
    NAME Character Varying (200) NOT NULL
  ) ;
ALTER TABLE sptransp.ST_GOODS ADD CONSTRAINT ST_GOOD_PK PRIMARY KEY ( ID ) ;
ALTER TABLE sptransp.ST_GOODS ADD CONSTRAINT ST_GOOD_UN_CODE UNIQUE ( CODE ) ;
ALTER TABLE sptransp.ST_GOODS ADD CONSTRAINT ST_GOOD_UN_NAME UNIQUE ( NAME ) ;


CREATE TABLE sptransp.ST_REQUEST
  (
    ID                       NUMERIC NOT NULL ,
    VERSION                  NUMERIC NOT NULL ,
    REFERENCE                Character Varying (50) NOT NULL ,
    ID_CUSTOMER              NUMERIC NOT NULL ,
    ID_GOODS                 NUMERIC NOT NULL ,
    ID_DEPARTURE             NUMERIC NOT NULL ,
    ID_ARRIVAL               NUMERIC NOT NULL ,
    OVERALL_STATUS           Character Varying (50) NOT NULL ,
    ID_RULE                  NUMERIC NOT NULL ,
    RULE_VERSION             NUMERIC NOT NULL ,
    AGREEMENT_STATUS         Character Varying (50) NOT NULL ,
    CANCELLATION_COMMENT     Character Varying (1024) ,
    NEXT_AGREEMENT_VISA_RANK NUMERIC NOT NULL ,
    CREATION_DATE            Timestamp NOT NULL ,
    CREATION_USER            Character Varying (50) NOT NULL ,
    UPDATE_DATE              Timestamp NOT NULL ,
    UPDATE_USER              Character Varying (50) NOT NULL
  ) ;
ALTER TABLE sptransp.ST_REQUEST ADD CONSTRAINT ST_REQUEST_PK PRIMARY KEY ( ID ) ;
ALTER TABLE sptransp.ST_REQUEST ADD CONSTRAINT ST_REQUEST_UN_REF UNIQUE ( REFERENCE ) ;


CREATE TABLE sptransp.ST_REQUEST_AGR_VISA
  (
    ID            NUMERIC NOT NULL ,
    ID_REQUEST    NUMERIC NOT NULL ,
    ID_EMPLOYEE   NUMERIC NOT NULL ,
    STATUS        Character Varying (50) NOT NULL ,
    VISA_RANK     NUMERIC NOT NULL ,
    ID_DEPARTMENT NUMERIC NOT NULL ,
    SENIORITY     NUMERIC NOT NULL ,
    VISA_COMMENT  Character Varying (1024) NOT NULL ,
    CREATION_DATE Timestamp NOT NULL
  ) ;
ALTER TABLE sptransp.ST_REQUEST_AGR_VISA ADD CONSTRAINT ST_REQUEST_AGR_VISA_PK PRIMARY KEY ( ID ) ;


CREATE TABLE sptransp.ST_REQUEST_NOT_ALLOWED
  (
    ID             NUMERIC NOT NULL ,
    ID_DESTINATION NUMERIC NOT NULL ,
    ID_GOODS       NUMERIC NOT NULL
  ) ;
ALTER TABLE sptransp.ST_REQUEST_NOT_ALLOWED ADD CONSTRAINT ST_REQUEST_NOT_ALLOWED_PK PRIMARY KEY ( ID ) ;


CREATE TABLE sptransp.ST_USER
  (
    ID            NUMERIC NOT NULL ,
    VERSION       NUMERIC NOT NULL ,
    USER_TYPE     Character Varying (1) NOT NULL ,
    USER_PASSWORD Character Varying (1024) NOT NULL ,
    UID_USER      Character Varying (50) NOT NULL ,
    USER_PROFILE  Character Varying (200) NOT NULL ,
    CREATION_DATE Timestamp NOT NULL ,
    CREATION_USER Character Varying (50) NOT NULL ,
    UPDATE_DATE   Timestamp NOT NULL ,
    UPDATE_USER   Character Varying (50) NOT NULL
  ) ;
COMMENT ON COLUMN sptransp.ST_USER.USER_TYPE
IS
  '''C'' pour CUSTOMER
''E'' pour EMPLOYEE' ;
ALTER TABLE sptransp.ST_USER ADD CONSTRAINT ST_EMPLOYEE_AUTH_PK PRIMARY KEY ( ID ) ;
ALTER TABLE sptransp.ST_USER ADD CONSTRAINT UK_ST_USER UNIQUE ( UID_USER ) ;


ALTER TABLE sptransp.ST_CUSTOMER ADD CONSTRAINT FK_ST_CUSTOMER_USER FOREIGN KEY ( ID ) REFERENCES sptransp.ST_USER ( ID ) ;

ALTER TABLE sptransp.ST_EMPLOYEE ADD CONSTRAINT FK_ST_EMPLOYEE_USER FOREIGN KEY ( ID ) REFERENCES sptransp.ST_USER ( ID ) ;

ALTER TABLE sptransp.ST_AGREEMENT_RULE ADD CONSTRAINT ST_AGR_RULE_FK_DESTINATION FOREIGN KEY ( ID_DESTINATION ) REFERENCES sptransp.ST_DESTINATION ( ID ) ;

ALTER TABLE sptransp.ST_AGREEMENT_RULE ADD CONSTRAINT ST_AGR_RULE_FK_GOODS FOREIGN KEY ( ID_GOODS ) REFERENCES sptransp.ST_GOODS ( ID ) ;

ALTER TABLE sptransp.ST_AGR_RULE_VISA_AUD ADD CONSTRAINT ST_AGR_RULE_VISA_AUD_FK_RULE FOREIGN KEY ( ID_RULE, RULE_VERSION ) REFERENCES sptransp.ST_AGREEMENT_RULE_AUD ( ID_RULE, VERSION ) ;

ALTER TABLE sptransp.ST_AGR_RULE_VISA ADD CONSTRAINT ST_AGR_RULE_VISA_FK_DPT FOREIGN KEY ( ID_DEPARTMENT ) REFERENCES sptransp.ST_DEPARTMENT ( ID ) ;

ALTER TABLE sptransp.ST_AGR_RULE_VISA ADD CONSTRAINT ST_AGR_RULE_VISA_FK_RULE FOREIGN KEY ( ID_RULE ) REFERENCES sptransp.ST_AGREEMENT_RULE ( ID ) ;

ALTER TABLE sptransp.ST_EMPLOYEE ADD CONSTRAINT ST_EMPLOYEE_FK_DPT FOREIGN KEY ( ID_DEPARTMENT ) REFERENCES sptransp.ST_DEPARTMENT ( ID ) ;

ALTER TABLE sptransp.ST_REQUEST ADD CONSTRAINT ST_REQUEST_AGR_RULE_AUD_FK FOREIGN KEY ( ID_RULE, RULE_VERSION ) REFERENCES sptransp.ST_AGREEMENT_RULE_AUD ( ID_RULE, VERSION ) ;

ALTER TABLE sptransp.ST_REQUEST_AGR_VISA ADD CONSTRAINT ST_REQUEST_AGR_VISA_FK_DPT FOREIGN KEY ( ID_DEPARTMENT ) REFERENCES sptransp.ST_DEPARTMENT ( ID ) ;

ALTER TABLE sptransp.ST_REQUEST_AGR_VISA ADD CONSTRAINT ST_REQUEST_AGR_VISA_FK_EMPL FOREIGN KEY ( ID_EMPLOYEE ) REFERENCES sptransp.ST_EMPLOYEE ( ID ) ;

ALTER TABLE sptransp.ST_REQUEST_AGR_VISA ADD CONSTRAINT ST_REQUEST_AGR_VISA_FK_REQUEST FOREIGN KEY ( ID_REQUEST ) REFERENCES sptransp.ST_REQUEST ( ID ) ;

ALTER TABLE sptransp.ST_REQUEST ADD CONSTRAINT ST_REQUEST_FK_ARRIVAL FOREIGN KEY ( ID_ARRIVAL ) REFERENCES sptransp.ST_DESTINATION ( ID ) ;

ALTER TABLE sptransp.ST_REQUEST ADD CONSTRAINT ST_REQUEST_FK_CUSTOMER FOREIGN KEY ( ID_CUSTOMER ) REFERENCES sptransp.ST_CUSTOMER ( ID ) ;

ALTER TABLE sptransp.ST_REQUEST ADD CONSTRAINT ST_REQUEST_FK_DEPARTURE FOREIGN KEY ( ID_DEPARTURE ) REFERENCES sptransp.ST_DESTINATION ( ID ) ;

ALTER TABLE sptransp.ST_REQUEST ADD CONSTRAINT ST_REQUEST_FK_GOODS FOREIGN KEY ( ID_GOODS ) REFERENCES sptransp.ST_GOODS ( ID ) ;

ALTER TABLE sptransp.ST_REQUEST_NOT_ALLOWED ADD CONSTRAINT ST_REQ_NOT_ALL_FK_DEST FOREIGN KEY ( ID_DESTINATION ) REFERENCES sptransp.ST_DESTINATION ( ID ) ;

ALTER TABLE sptransp.ST_REQUEST_NOT_ALLOWED ADD CONSTRAINT ST_REQ_NOT_ALL_FK_GOODS FOREIGN KEY ( ID_GOODS ) REFERENCES sptransp.ST_GOODS ( ID ) ;


-- Rapport récapitulatif d'Oracle SQL Developer Data Modeler :
--
-- CREATE TABLE                            14
-- CREATE INDEX                             0
-- ALTER TABLE                             40
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
--
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
--
-- REDACTION POLICY                         0
--
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
--
-- ERRORS                                   0
-- WARNINGS                                 0
