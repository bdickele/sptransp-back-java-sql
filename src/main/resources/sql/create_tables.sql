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


-- Généré par Oracle SQL Developer Data Modeler 4.1.1.888
--   à :        2015-10-03 20:24:36 CEST
--   site :      Oracle Database 12c
--   type :      Oracle Database 12c




CREATE TABLE sptransp.ST_AGREEMENT_RULE
  (
    ID             NUMBER NOT NULL ,
    VERSION        NUMBER NOT NULL ,
    ID_DESTINATION NUMBER NOT NULL ,
    ID_GOODS       NUMBER NOT NULL ,
    CREATION_DATE  TIMESTAMP WITH LOCAL TIME ZONE NOT NULL ,
    CREATION_USER  VARCHAR2 (50) NOT NULL ,
    UPDATE_DATE    NUMBER NOT NULL ,
    UPDATE_USER    VARCHAR2 (50) NOT NULL
  ) ;
ALTER TABLE sptransp.ST_AGREEMENT_RULE ADD CONSTRAINT ST_AGREEMENT_RULE_PK PRIMARY KEY ( ID ) ;


CREATE TABLE sptransp.ST_AGREEMENT_RULE_AUD
  (
    ID_RULE        NUMBER NOT NULL ,
    VERSION        NUMBER NOT NULL ,
    ID_DESTINATION NUMBER NOT NULL ,
    ID_GOODS       NUMBER NOT NULL ,
    VERSION_DATE   TIMESTAMP WITH LOCAL TIME ZONE NOT NULL ,
    VERSION_USER   VARCHAR2 (50) NOT NULL
  ) ;
ALTER TABLE sptransp.ST_AGREEMENT_RULE_AUD ADD CONSTRAINT ST_AGREEMENT_RULE_AUD_PK PRIMARY KEY ( ID_RULE, VERSION ) ;


CREATE TABLE sptransp.ST_AGR_RULE_VISA
  (
    ID            NUMBER NOT NULL ,
    ID_RULE       NUMBER NOT NULL ,
    RANK          NUMBER NOT NULL ,
    ID_DEPARTMENT NUMBER NOT NULL ,
    SENIORITY     NUMBER NOT NULL
  ) ;
ALTER TABLE sptransp.ST_AGR_RULE_VISA ADD CONSTRAINT ST_AGR_RULE_VISA_PK PRIMARY KEY ( ID ) ;


CREATE TABLE sptransp.ST_AGR_RULE_VISA_AUD
  (
    ID_RULE       NUMBER NOT NULL ,
    RULE_VERSION  NUMBER NOT NULL ,
    RANK          NUMBER NOT NULL ,
    ID_DEPARTMENT NUMBER NOT NULL ,
    SENIORITY     NUMBER NOT NULL
  ) ;
ALTER TABLE sptransp.ST_AGR_RULE_VISA_AUD ADD CONSTRAINT ST_AGR_RULE_VISA_AUD_PK PRIMARY KEY ( ID_RULE, RULE_VERSION, RANK ) ;


CREATE TABLE sptransp.ST_CUSTOMER
  (
    ID           NUMBER NOT NULL ,
    UID_CUSTOMER VARCHAR2 (50) NOT NULL ,
    FULL_NAME    VARCHAR2 (200) NOT NULL
  ) ;
ALTER TABLE sptransp.ST_CUSTOMER ADD CONSTRAINT ST_CUSTOMER_PK PRIMARY KEY ( ID ) ;
ALTER TABLE sptransp.ST_CUSTOMER ADD CONSTRAINT ST_CUSTOMER_UN_UID UNIQUE ( UID_CUSTOMER ) ;


CREATE TABLE sptransp.ST_DEPARTMENT
  (
    ID   NUMBER NOT NULL ,
    CODE VARCHAR2 (50) NOT NULL ,
    NAME VARCHAR2 (200) NOT NULL
  ) ;
ALTER TABLE sptransp.ST_DEPARTMENT ADD CONSTRAINT ST_DEPARTMENT_PK PRIMARY KEY ( ID ) ;
ALTER TABLE sptransp.ST_DEPARTMENT ADD CONSTRAINT ST_DEPARTMENT_UN_NAME UNIQUE ( NAME ) ;
ALTER TABLE sptransp.ST_DEPARTMENT ADD CONSTRAINT ST_DEPARTMENT_UN_CODE UNIQUE ( CODE ) ;


CREATE TABLE sptransp.ST_DESTINATION
  (
    ID   NUMBER NOT NULL ,
    CODE VARCHAR2 (50) NOT NULL ,
    NAME VARCHAR2 (200) NOT NULL
  ) ;
ALTER TABLE sptransp.ST_DESTINATION ADD CONSTRAINT ST_DESTINATION_PK PRIMARY KEY ( ID ) ;
ALTER TABLE sptransp.ST_DESTINATION ADD CONSTRAINT ST_DESTINATION_UN_NAME UNIQUE ( NAME ) ;
ALTER TABLE sptransp.ST_DESTINATION ADD CONSTRAINT ST_DESTINATION_UN_CODE UNIQUE ( CODE ) ;


CREATE TABLE sptransp.ST_EMPLOYEE
  (
    ID            NUMBER NOT NULL ,
    VERSION       NUMBER NOT NULL ,
    UID_EMPLOYEE  VARCHAR2 (50) NOT NULL ,
    FULL_NAME     VARCHAR2 (200) NOT NULL ,
    ID_DEPARTMENT NUMBER NOT NULL ,
    SENIORITY     NUMBER NOT NULL ,
    CREATION_DATE TIMESTAMP WITH LOCAL TIME ZONE NOT NULL ,
    CREATION_USER VARCHAR2 (50) NOT NULL ,
    UPDATE_DATE   TIMESTAMP WITH LOCAL TIME ZONE NOT NULL ,
    UPDATE_USER   VARCHAR2 (50) NOT NULL
  ) ;
ALTER TABLE sptransp.ST_EMPLOYEE ADD CONSTRAINT ST_EMPLOYEE_PK PRIMARY KEY ( ID ) ;
ALTER TABLE sptransp.ST_EMPLOYEE ADD CONSTRAINT ST_EMPLOYEE_UN_UID UNIQUE ( UID_EMPLOYEE ) ;


CREATE TABLE sptransp.ST_EMPLOYEE_AUD
  (
    ID            NUMBER NOT NULL ,
    VERSION       NUMBER NOT NULL ,
    UID_EMPLOYEE  VARCHAR2 (50) NOT NULL ,
    FULL_NAME     VARCHAR2 (200) NOT NULL ,
    ID_DEPARTMENT NUMBER NOT NULL ,
    SENIORITY     NUMBER NOT NULL ,
    VERSION_DATE  TIMESTAMP WITH LOCAL TIME ZONE NOT NULL ,
    VERSION_USER  VARCHAR2 (50) NOT NULL
  ) ;
ALTER TABLE sptransp.ST_EMPLOYEE_AUD ADD CONSTRAINT ST_EMPLOYEE_AUD_PK PRIMARY KEY ( ID, VERSION ) ;


CREATE TABLE sptransp.ST_GOODS
  (
    ID   NUMBER NOT NULL ,
    CODE VARCHAR2 (50) NOT NULL ,
    NAME VARCHAR2 (200) NOT NULL
  ) ;
ALTER TABLE sptransp.ST_GOODS ADD CONSTRAINT ST_GOOD_PK PRIMARY KEY ( ID ) ;
ALTER TABLE sptransp.ST_GOODS ADD CONSTRAINT ST_GOOD_UN_NAME UNIQUE ( NAME ) ;
ALTER TABLE sptransp.ST_GOODS ADD CONSTRAINT ST_GOOD_UN_CODE UNIQUE ( CODE ) ;


CREATE TABLE sptransp.ST_REQUEST
  (
    ID                       NUMBER NOT NULL ,
    VERSION                  NUMBER NOT NULL ,
    REFERENCE                VARCHAR2 (50) NOT NULL ,
    ID_CUSTOMER              NUMBER NOT NULL ,
    ID_GOODS                 NUMBER NOT NULL ,
    ID_DEPARTURE             NUMBER NOT NULL ,
    ID_ARRIVAL               NUMBER NOT NULL ,
    OVERALL_STATUS           VARCHAR2 (50) NOT NULL ,
    ID_RULE                  NUMBER NOT NULL ,
    RULE_VERSION             NUMBER NOT NULL ,
    AGREEMENT_STATUS         VARCHAR2 (50) NOT NULL ,
    CANCELLATION_COMMENT     VARCHAR2 (1024) ,
    NEXT_AGREEMENT_VISA_RANK NUMBER NOT NULL ,
    CREATION_DATE            TIMESTAMP WITH LOCAL TIME ZONE NOT NULL ,
    CREATION_USER            VARCHAR2 (50) NOT NULL ,
    UPDATE_DATE              TIMESTAMP WITH LOCAL TIME ZONE NOT NULL ,
    UPDATE_USER              VARCHAR2 (50) NOT NULL
  ) ;
ALTER TABLE sptransp.ST_REQUEST ADD CONSTRAINT ST_REQUEST_PK PRIMARY KEY ( ID ) ;
ALTER TABLE sptransp.ST_REQUEST ADD CONSTRAINT ST_REQUEST_UN_REF UNIQUE ( REFERENCE ) ;


CREATE TABLE sptransp.ST_REQUEST_AGR_VISA
  (
    ID            NUMBER NOT NULL ,
    ID_REQUEST    NUMBER NOT NULL ,
    ID_EMPLOYEE   NUMBER NOT NULL ,
    STATUS        VARCHAR2 (50) NOT NULL ,
    VISA_RANK     NUMBER NOT NULL ,
    ID_DEPARTMENT NUMBER NOT NULL ,
    SENIORITY     NUMBER NOT NULL ,
    VISA_COMMENT  VARCHAR2 (1024) NOT NULL ,
    CREATION_DATE TIMESTAMP WITH LOCAL TIME ZONE NOT NULL
  ) ;
ALTER TABLE sptransp.ST_REQUEST_AGR_VISA ADD CONSTRAINT ST_REQUEST_AGR_VISA_PK PRIMARY KEY ( ID ) ;


CREATE TABLE sptransp.ST_REQUEST_NOT_ALLOWED
  (
    ID             NUMBER NOT NULL ,
    ID_DESTINATION NUMBER NOT NULL ,
    ID_GOODS       NUMBER NOT NULL
  ) ;
ALTER TABLE sptransp.ST_REQUEST_NOT_ALLOWED ADD CONSTRAINT ST_REQUEST_NOT_ALLOWED_PK PRIMARY KEY ( ID ) ;


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
-- CREATE TABLE                            13
-- CREATE INDEX                             0
-- ALTER TABLE                             38
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
-- TSDP POLICY                              0
--
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
--
-- ERRORS                                   0
-- WARNINGS                                 0
