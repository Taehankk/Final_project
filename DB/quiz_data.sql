

ALTER TABLE users ADD COLUMN regDate DATETIME;

INSERT INTO users (userId, password, userName, nickName, regDate)
VALUES ("ssafy", md5("1234"), "ssafy", "kim", now());

INSERT INTO users (userId, password, userName, nickName, regDate)
VALUES ("admin", md5("admin"), "admin", "admin", now());

INSERT INTO arenas (field, starter, interest, openDay, content)
VALUES ("사이트가 이게 뭐냐", "ansmoon", 25, now(), "퀴즈 노잼ㅋ 더 만들어와라");

INSERT INTO arenas (field, starter, interest, openDay, content)
VALUES ("운영자 나와", "yebon", 63, now(), "운영자가 문BS 라던데...");

INSERT INTO arenas (field, starter, interest, openDay, content) 
VALUES ("푸바오 사육", "fubao", 172, now(), "푸바오 솔직히 좀 많이 먹긴 함ㅎ");

DELETE FROM users
WHERE userId = "ssaf";


SELECT * FROM users;
SELECT * FROM userdata;
SELECT * FROM arenas;