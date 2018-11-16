/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2018/11/16 13:25:02                          */
/*==============================================================*/


DROP TABLE T_PERM_PERM CASCADE CONSTRAINTS;

DROP TABLE T_PERM_ROLE CASCADE CONSTRAINTS;

DROP TABLE T_SEC_USER CASCADE CONSTRAINTS;

DROP TABLE T_SEC_USERGROUP CASCADE CONSTRAINTS;

/*==============================================================*/
/* Table: T_PERM_PERM                                           */
/*==============================================================*/
CREATE TABLE T_PERM_PERM 
(
   PK_ID                NUMBER(20)           NOT NULL,
   FNUMBER              VARCHAR2(60 CHAR)    NOT NULL,
   FNAME                VARCHAR2(80 CHAR)    NOT NULL,
   FSTATUS              CHAR(1 CHAR)         NOT NULL,
   CONSTRAINT PK_T_PERM_PERM PRIMARY KEY (PK_ID)
);

/*==============================================================*/
/* Table: T_PERM_ROLE                                           */
/*==============================================================*/
CREATE TABLE T_PERM_ROLE 
(
   PK_ID                NUMBER(20)           NOT NULL,
   FNUMBER              VARCHAR2(60 CHAR)    NOT NULL,
   FNAME                VARCHAR2(80 CHAR)    NOT NULL,
   FSTATUS              CHAR(1 CHAR)         NOT NULL,
   CONSTRAINT PK_T_PERM_ROLE PRIMARY KEY (PK_ID)
);

/*==============================================================*/
/* Table: T_SEC_USER                                            */
/*==============================================================*/
CREATE TABLE T_SEC_USER 
(
   FID                  NUMBER(20)           NOT NULL,
   FNAME                VARCHAR2(18 BYTE),
   FNUMNER              INTEGER,
   FPHONE               VARCHAR(13),
   FEAMAIL              VARCHAR(30),
   FSTATUS              CHAR(1 CHAR),
   FGENDER              CHAR(1 BYTE),
   FIDCARD              CHAR(18 BYTE),
   FBIRTHDAY            TIMESTAMP,
   FUSERTYPE            CHAR(1),
   CONSTRAINT PK_T_SEC_USER PRIMARY KEY (FID)
);

/*==============================================================*/
/* Table: T_SEC_USERGROUP                                       */
/*==============================================================*/
CREATE TABLE T_SEC_USERGROUP 
(
   PK_ID                NUMBER(20)           NOT NULL,
   FNUMBER              VARCHAR2(60 CHAR)    NOT NULL,
   FNAME                VARCHAR2(80 CHAR)    NOT NULL,
   FSTATUS              CHAR(1 CHAR)         NOT NULL,
   CONSTRAINT PK_T_SEC_USERGROUP PRIMARY KEY (PK_ID)
);

CREATE MATERIALIZED VIEW LOG ON T_SEC_USER;

