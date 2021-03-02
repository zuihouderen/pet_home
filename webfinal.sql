-- 1.用户--  
DROP TABLE user;
INSERT INTO user(uname,password) VALUES('haijun', '1234');
SELECT * FROM user;
create TABLE user(
uid INT PRIMARY key AUTO_INCREMENT NOT NULL,
uname VARCHAR(25) UNIQUE NOT NULL,
uage INT DEFAULT 18,
ugender INT DEFAULT 1,
password VARCHAR(12) NOT NULL,
uimg VARCHAR(255) DEFAULT '',
admin int DEFAULT 0
);
-- gender: 1:男,0:女
-- 2.宠物--  
DROP TABLE pet;
SELECT * from pet;
INSERT INTO pet(pname,phost) VALUES("he","lv");
create table pet(
pid INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
pname VARCHAR(25) UNIQUE NOT NULL,
phost VARCHAR(25),
petage INT DEFAULT 0,
pdescription VARCHAR(255),
pgender INT DEFAULT 1,
pimg VARCHAR (255) DEFAULT '',
ptype VARCHAR(25),
FOREIGN KEY (phost) REFERENCES user(uname)
);
-- pdtag : 1 :日常 0：询问
-- 3.宠物动态--  
DROP TABLE pdynamic;
SELECT * from pdynamic;
CREATE TABLE pdynamic(
  pdid INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  pdtag INT DEFAULT 1,
  pdtitle VARCHAR(25) UNIQUE NOT null,
  pdimg VARCHAR(255) DEFAULT'',
  uimg VARCHAR(255) DEFAULT'',
  bewrite VARCHAR(255) DEFAULT'', -- 描述--  
  pdowner VARCHAR(25) ,
  pdtime VARCHAR(30) DEFAULT "2020/06/05 21:00",
  FOREIGN KEY (pdowner) REFERENCES user(uname)
);

-- 4.宠物日志--  
DROP TABLE plog;
SELECT * FROM plog;
CREATE table plog(
 plid int PRIMARY KEY AUTO_INCREMENT not NULL,
 pltitle VARCHAR(25),
 plimg VARCHAR(255) DEFAULT '',
 pldate VARCHAR(30) DEFAULT "2020/06/05 21:00",
 pldescription  VARCHAR(255) DEFAULT'',
 plowner VARCHAR(25) ,
 FOREIGN KEY (plowner) REFERENCES pet(pname)
);

-- 5.评论动态--  
drop TABLE pdcomment;
SELECT * FROM pdcomment;
CREATE TABLE pdcomment(
 comid INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
 pdtitle VARCHAR(25),
 comowner VARCHAR(25),
 content VARCHAR(255),
 comdate VARCHAR(30),
 uimg VARCHAR(255) DEFAULT'',
 parent INT DEFAULT 0,
 FOREIGN KEY (comowner) REFERENCES user(uname),
 FOREIGN KEY (pdtitle) REFERENCES pdynamic(pdtitle)
);

-- 6.点赞动态--  
drop TABLE pdlove;
SELECT * FROM pdlove;
CREATE TABLE pdlove(
 pdloveid INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
 pdtitle VARCHAR(25),
 pdloveowner VARCHAR(25),
 FOREIGN KEY (pdloveowner) REFERENCES user(uname),
 FOREIGN KEY (pdtitle) REFERENCES pdynamic(pdtitle)
);

-- 7.收藏动态--  
drop TABLE pdkeep;
SELECT * FROM pdkeep;
CREATE TABLE pdkeep(
 pdkeepid INT PRIMARY KEY AUTO_INCREMENT NOT null,
 pdtitle VARCHAR(25),
 pdkeeper VARCHAR(25),
 pdkeepdata VARCHAR(30),
 FOREIGN KEY (pdkeeper) REFERENCES user(uname),
 FOREIGN KEY (pdtitle) REFERENCES pdynamic(pdtitle)
);

-- 8.宠物常识--  
DROP TABLE petknowledge;
SELECT * FROM petknowledge;
CREATE TABLE petknowledge(
pkid INT PRIMARY KEY AUTO_INCREMENT not NULL,
pettype INT , -- 宠物类型，0猫科、1犬类、2爬行类、3小宠物类、4水族类-- 
petname VARCHAR(20) UNIQUE,
characters VARCHAR (30),
nation VARCHAR(25),
easyOfDisease VARCHAR(30),
life VARCHAR (20),
feature VARCHAR (255),
characterFeature VARCHAR(255),
careKnowledge VARCHAR(255),
feedPoints VARCHAR (255),
url VARCHAR(100),
coverURL VARCHAR(255)
);

-- 9.问题反馈--
drop TABLE question;
SELECT * FROM question;
CREATE TABLE question(
 qid int  PRIMARY KEY AUTO_INCREMENT not null,
 pkid int ,
 uid int ,
 uimg VARCHAR(255),
 des VARCHAR (255),
 qtime VARCHAR(30),
 FOREIGN KEY (pkid) REFERENCES petknowledge(pkid),
 FOREIGN KEY (uid) REFERENCES user(uid)
);

-- 10.消息发送，当动态发生状态改变的时候--
DROP TABLE message;
SELECT * FROM message;
CREATE TABLE message(
	mid INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    msgtitle VARCHAR(50),
    mdate VARCHAR(30) DEFAULT "2020/06/14",
    msg VARCHAR(255) DEFAULT '',
    msgstate INT DEFAULT 0, -- 0：未读；1：已读 
    reciptant VARCHAR (25) REFERENCES user(uname)
)