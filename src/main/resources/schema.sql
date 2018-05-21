CREATE TABLE author
(
  id int(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE book
(
  id  int(11) NOT NULL AUTO_INCREMENT,
  title VARCHAR(100) NOT NULL,
  author_id int(11),
  PRIMARY KEY (id),
  foreign key (author_id) references author(id)
);
