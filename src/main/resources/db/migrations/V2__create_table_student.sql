create table student(
    id bigint not null auto_increment,
    name varchar(50) not null,
    email varchar(50) not null,
    primary key(id)
);

insert into student values(1, 'Thomas','thomas@oshima.com');