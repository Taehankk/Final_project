INSERT INTO users (userId, password, userName, nickName, regDate)
VALUES ("ssafy", md5("1234"), "ssafy", "kim", now());

INSERT INTO users (userId, password, userName, nickName, regDate)
VALUES ("admin", md5("admin"), "admin", "admin", now());

INSERT INTO arenas (field, starter, interest, openDay, content)
VALUES ("사이트가 이게 뭐냐", "두둥탁", 25, now(), "퀴즈 노잼ㅋ 더 만들어와라");

INSERT INTO arenas (field, starter, interest, openDay, content)
VALUES ("운영자 나와", "kim", 63, now(), "운영자가 문BS 라던데...");

INSERT INTO arenas (field, starter, interest, openDay, content) 
VALUES ("푸바오 사육", "sonoisy", 172, now(), "푸바오 솔직히 좀 많이 먹긴 함ㅎ");

INSERT INTO fights (arenaId, content, fighter, fightDay) 
VALUES (4, "범수는 맹구인 것인가", "kim", now());

INSERT INTO fights (arenaId, content, fighter, fightDay) 
VALUES (4, "그는 왜 맹구를 택하였는가", "kim", now());

INSERT INTO fights (arenaId, content, fighter, fightDay) 
VALUES (4, "범수야 맹구는 중간이 딱이야", "sonoisy", now());

INSERT INTO fights (arenaId, content, fighter, fightDay) 
VALUES (4, "예본아 내가 사실...", "두둥탁", now());

INSERT INTO fights (arenaId, content, fighter, fightDay) 
VALUES (2, "예본아 내가 사실...", "두둥탁", now());

INSERT INTO fights (arenaId, content, fighter, fightDay) 
VALUES (3, "예본아 내가 사실...", "kim", now());

INSERT INTO fights (arenaId, content, fighter, fightDay) 
VALUES (3, "밥 더 줘", "두둥탁", now());

INSERT INTO fights (arenaId, content, fighter, fightDay) 
VALUES (3, "그만먹어 탁바오", "sonoisy", now());


SELECT * FROM users;
SELECT * FROM userdata;
SELECT * FROM arenas;
SELECT * FROM fights;

SELECT * FROM country;
SELECT * FROM etc;
SELECT * FROM exercise;
SELECT * FROM number;
SELECT * FROM person;

SELECT * FROM basic;
SELECT * FROM champs;
SELECT * FROM esports;
SELECT * FROM kleague;
SELECT * FROM kbo;
SELECT * FROM forever;
