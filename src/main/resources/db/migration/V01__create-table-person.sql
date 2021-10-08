create table person (
id bigint primary key auto_increment,
firstname varchar(100) not null,
lastname varchar(100) not null,
cpf varchar(11)  not null,
birthday date not null

)