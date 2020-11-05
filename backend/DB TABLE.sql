CREATE TABLE mall_member (
member_postnum INT AUTO_INCREMENT PRIMARY KEY,
member_name VARCHAR(100) NOT NULL,
member_id VARCHAR(100) NOT NULL,
member_pwd VARCHAR(200) NOT NULL,
check_login BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE noticeboard (
notice_postnum INT AUTO_INCREMENT PRIMARY KEY,
notice_title VARCHAR(300) NOT NULL,
notice_content TEXT,
notice_regdate DATETIME NOT NULL DEFAULT NOW(),
notice_hit INT NOT NULL DEFAULT '0',
member_id VARCHAR(100)
);

CREATE TABLE eventboard (
event_postnum INT AUTO_INCREMENT PRIMARY KEY,
event_title VARCHAR(300) NOT NULL,
event_content TEXT,
event_regdate DATETIME NOT NULL DEFAULT NOW(),
event_hit INT NOT NULL,
member_id VARCHAR(100)
);

CREATE TABLE itemboard (
item_postnum INT AUTO_INCREMENT PRIMARY KEY,
item_title VARCHAR(300) NOT NULL,
item_content TEXT,
item_price INT NOT NULL,
item_img VARCHAR(500) NOT NULL,
item_group VARCHAR(100) NOT NULL,
item_regdate DATETIME NOT NULL DEFAULT NOW(),
member_id VARCHAR(100)
);

CREATE TABLE reviewboard (
review_postnum INT AUTO_INCREMENT PRIMARY KEY,
item_postnum INT,
review_img VARCHAR(500),
review_title VARCHAR(300) NOT NULL,
review_content TEXT,
review_hit INT NOT NULL,
review_regdate DATETIME NOT NULL DEFAULT NOW(),
member_id VARCHAR(100),
FOREIGN KEY (item_postnum) REFERENCES itemboard (item_postnum)
);

CREATE TABLE mall_basket (
basket_postnum INT AUTO_INCREMENT PRIMARY KEY,
item_postnum INT,
member_id VARCHAR(100),
basket_result BOOLEAN DEFAULT FALSE,
FOREIGN KEY (item_postnum) REFERENCES itemboard (item_postnum)
);