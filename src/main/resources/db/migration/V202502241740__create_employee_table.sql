CREATE TABLE employees(
    id BIGINT not null auto_increment,
    name VARCHAR(155) not null,
    salary decimal(10,2) not null ,
    birthday TIMESTAMP not null,
    PRIMARY KEY(id)
)engine=InnoDB default charset=utf8;