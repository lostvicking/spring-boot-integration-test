create database cucumber;
create user 'springuser'@'asynch-request-reader' identified by 'ThePassword';
grant all on *.* to 'springuser'@'%' identified by 'ThePassword';
create table  cucumber.request (id INT NOT NULL AUTO_INCREMENT,
                                content VARCHAR(255),
                                PRIMARY KEY (id));
