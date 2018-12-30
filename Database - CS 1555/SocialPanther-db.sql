DROP TABLE profile CASCADE CONSTRAINTS;
DROP TABLE friends CASCADE CONSTRAINTS;
DROP TABLE pendingFriends CASCADE CONSTRAINTS;
DROP TABLE messages CASCADE CONSTRAINTS;
DROP TABLE messageRecipient CASCADE CONSTRAINTS;
DROP TABLE groups CASCADE CONSTRAINTS;
DROP TABLE groupMembership CASCADE CONSTRAINTS;
DROP TABLE pendingGroupmembers CASCADE CONSTRAINTS;

--Profile and login info for earch user registered in system
CREATE TABLE profile(
	userID VARCHAR2(20),
	name VARCHAR2(50),
	email VARCHAR2(15),
	password VARCHAR2(50),
	date_of_birth DATE,
	lastlogin TIMESTAMP,
	CONSTRAINT profile_PK PRIMARY KEY (userID) DEFERRABLE INITIALLY IMMEDIATE
);

--Friends lists of every user 
--JDate is when they became friends
--Message is message of friend request
CREATE TABLE friends(
	userID1 VARCHAR2(20),
	userID2 VARCHAR2(20),
	JDate DATE,
	message VARCHAR2(200),
	CONSTRAINT friends_PK PRIMARY KEY (userID1, userID2) DEFERRABLE INITIALLY IMMEDIATE,
	CONSTRAINT FK_user1 FOREIGN KEY (userID1) REFERENCES profile(userID) DEFERRABLE INITIALLY IMMEDIATE,
	CONSTRAINT FK_user2 FOREIGN KEY (userID2) REFERENCES profile(userID) DEFERRABLE INITIALLY IMMEDIATE
);
	
--Stores pending friend requests that haven't been confirmed yet
CREATE TABLE pendingFriends(
	fromID VARCHAR2(20),
	toID VARCHAR2(20),
	message VARCHAR2(200),
	CONSTRAINT pendingFriends_PK PRIMARY KEY (fromID, toID) DEFERRABLE INITIALLY IMMEDIATE,
	CONSTRAINT FK_fromID FOREIGN KEY (fromID) REFERENCES profile(userID) DEFERRABLE INITIALLY IMMEDIATE,
	CONSTRAINT FK_toID FOREIGN KEY (toID) REFERENCES profile(userID) DEFERRABLE INITIALLY IMMEDIATE
);

--Stores information about groups in the system
CREATE TABLE groups(
	gID NUMBER,
	name VARCHAR2(50),
	description VARCHAR2(200),
	member_limit NUMBER DEFAULT 30,
	CONSTRAINT groups_PK PRIMARY KEY (gID) DEFERRABLE INITIALLY IMMEDIATE
);

--Stores every message sent by users in system
CREATE TABLE messages(
	msgID NUMBER,
	fromID VARCHAR2(20),
	message VARCHAR2(200),
	toUserID VARCHAR2(20) DEFAULT NULL,
	toGroupID NUMBER DEFAULT NULL,
	dateSent DATE,
	CONSTRAINT messages_PK PRIMARY KEY (msgID) DEFERRABLE INITIALLY IMMEDIATE,
	CONSTRAINT FK_fromMsg FOREIGN KEY (fromID) REFERENCES profile(userID) DEFERRABLE INITIALLY IMMEDIATE,
	CONSTRAINT FK_toUserID FOREIGN KEY (toUserID) REFERENCES profile(userID) DEFERRABLE INITIALLY IMMEDIATE,
	CONSTRAINT FK_groupID FOREIGN KEY (toGroupID) REFERENCES groups(gID) DEFERRABLE INITIALLY IMMEDIATE
);

--Stores recipients of each message stored in the system
CREATE TABLE messageRecipient(
	msgID NUMBER,
	userID VARCHAR2(20),
	CONSTRAINT messageRecipient_PK PRIMARY KEY (msgID) DEFERRABLE INITIALLY IMMEDIATE,
	CONSTRAINT FK_msgID FOREIGN KEY (msgID) REFERENCES messages(msgID) DEFERRABLE INITIALLY IMMEDIATE,
	CONSTRAINT FK_userID FOREIGN KEY (userID) REFERENCES profile(userID) DEFERRABLE INITIALLY IMMEDIATE
);

--Stores users who are members of each group in system
--Role indicates whether user is a manager of a group (who can accept joining group request) or not
CREATE TABLE groupMembership(
	gID NUMBER,
	userID VARCHAR2(20),
	role VARCHAR2(20),
	CONSTRAINT groupMembership_PK PRIMARY KEY (gID, userID) DEFERRABLE INITIALLY IMMEDIATE,
	CONSTRAINT FK_group FOREIGN KEY (gID) REFERENCES groups(gID) DEFERRABLE INITIALLY IMMEDIATE,
	CONSTRAINT FK_groupUserID FOREIGN KEY (userID) REFERENCES profile(userID) DEFERRABLE INITIALLY IMMEDIATE
);

--Stores pending joining group requests that have yet to be accepted/rejected by manager of group
CREATE TABLE pendingGroupmembers(
	gID NUMBER,
	userID VARCHAR2(20),
	message VARCHAR2(200),
	CONSTRAINT pendingGroupmembers_PK PRIMARY KEY (gID, userID) DEFERRABLE INITIALLY IMMEDIATE,
	CONSTRAINT FK_groupPend FOREIGN KEY (gID) REFERENCES groups(gID) DEFERRABLE INITIALLY IMMEDIATE,
	CONSTRAINT FK_groupUserIDPend FOREIGN KEY (userID) REFERENCES profile(userID) DEFERRABLE INITIALLY IMMEDIATE
);

CREATE OR REPLACE TRIGGER MESSAGE_USER
AFTER INSERT ON messages
for each row
WHEN(new.toGroupID IS NULL)
BEGIN
	INSERT into messageRecipient values (:new.msgID, :new.toUserID);
END;
/

CREATE OR REPLACE TRIGGER MESSAGE_GROUP
Before INSERT ON messages
for each row
WHEN (new.toGroupID IS NOT NULL)
BEGIN
	INSERT INTO messageRecipient(SELECT :new.msgID, userID FROM groupMembership WHERE gID = :new.toGroupID);
END;
/

CREATE OR REPLACE TRIGGER DROP_USER
AFTER DELETE ON profile
FOR EACH ROW
BEGIN
	DELETE FROM groupMembership WHERE userID = :old.userID;
	DELETE FROM friends WHERE userID1 = :old.userID OR userID2 = :old.userID;
	DELETE FROM pendingFriends WHERE fromID = :old.userID OR toID = :old.userID;
	DELETE FROM messageRecipient WHERE userID = :old.userID;
	
	UPDATE messages SET toUserID = NULL WHERE toUserID = :old.userID;
	UPDATE messages SET fromID = NULL WHERE fromID = :old.userID;
	
	DELETE FROM messages WHERE fromID = NULL AND toUserID = NULL;
END;
/
