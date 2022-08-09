```mysql
DROP DATABASE IF EXISTS article_manager;
CREATE DATABASE article_manager;

CREATE TABLE article(
id INT(10) UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
title CHAR(100) NOT NULL,
`body` TEXT NOT NULL
);

DESC article;


INSERT INTO article 
SET regDate = NOW(),
updateDate = NOW(),
title = CONCAT('제목',RAND()),
`body` = CONCAT('내용',RAND());

SELECT * FROM article;
TRUNCATE article;
```

