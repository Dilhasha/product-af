-- Migration Script for AF 2.1.0-M6 to AF 2.1.0-M7 --

-- START
ALTER TABLE AF_APPLICATION 
ADD COLUMN APPLICATION_NAME VARCHAR(250) NOT NULL;

CREATE TABLE TEMP_AF_APPLICATION(
	APPLICATION_KEY VARCHAR(100) NOT NULL,
	TENANT_ID INTEGER NOT NULL,
	APPLICATION_NAME VARCHAR(1000)
);

INSERT INTO TEMP_AF_APPLICATION(APPLICATION_KEY,TENANT_ID,APPLICATION_NAME)
SELECT RP.REG_PATH_VALUE, R.REG_TENANT_ID,C.REG_CONTENT_DATA FROM dbGovernanceCloud.REG_CONTENT C, dbGovernanceCloud.REG_RESOURCE R, dbGovernanceCloud.REG_PATH RP WHERE C.REG_CONTENT_ID = R.REG_CONTENT_ID AND C.REG_TENANT_ID = R.REG_TENANT_ID AND R.REG_MEDIA_TYPE = 'application/vnd.wso2-application+xml' AND R.REG_NAME IS NOT NULL AND RP.REG_PATH_ID=R.REG_PATH_ID;

UPDATE TEMP_AF_APPLICATION
SET APPLICATION_NAME = SUBSTRING(APPLICATION_NAME,INSTR(APPLICATION_NAME,'<name>') + 6);

UPDATE TEMP_AF_APPLICATION
SET APPLICATION_NAME = REPLACE(APPLICATION_NAME,SUBSTRING(APPLICATION_NAME,INSTR(APPLICATION_NAME,'</name>')),'');

UPDATE TEMP_AF_APPLICATION
SET APPLICATION_KEY = SUBSTRING_INDEX(APPLICATION_KEY,'/',-1);

UPDATE AF_APPLICATION A, TEMP_AF_APPLICATION TA
SET A.APPLICATION_NAME = TA.APPLICATION_NAME
WHERE A.APPLICATION_KEY = TA.APPLICATION_KEY AND A.TENANT_ID = TA.TENANT_ID;
-- DONE with AF_APPLICATION

-- REMOVEING TEMP_TABLES
DROP TABLE TEMP_AF_APPLICATION;
-- DONE

-- START : create new table schema for single tenant deployed url feature
CREATE TABLE IF NOT EXISTS AF_CARTRIDGE_CLUSTER (
        ID INTEGER AUTO_INCREMENT,
        CLUSTER_ID VARCHAR(100) NOT NULL,
        LB_CLUSTER_ID VARCHAR(100),
        ACTIVE_IP VARCHAR(100) NOT NULL,
        PRIMARY KEY(ID),
        UNIQUE(CLUSTER_ID)
);
-- DONE
