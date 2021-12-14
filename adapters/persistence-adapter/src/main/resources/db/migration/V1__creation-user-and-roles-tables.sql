CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table if not exists roles(
	id bigserial not null,
	name varchar(50) not null,
	creation_date timestamp not null,
	update_date timestamp,
	constraint roles_pk primary key(id)
);

create table if not exists users(
	id uuid not null default uuid_generate_v4(),
	name varchar(255) not null,
	document varchar(14) not null,
	login varchar(20) not null unique,
	email varchar(50) not null unique,
	password varchar(255) not null,
	active boolean not null,
	creation_date timestamp not null,
	update_date timestamp,
	constraint users_pk primary key(id)
);

create table if not exists roles_users(
	id bigserial not null,
	userid uuid not null,
	roleid bigint not null,
	creation_date timestamp not null,
	update_date timestamp,
	constraint roles_users_pk primary key(id),
	constraint users_fk foreign key(userid)
		references users(id),
	constraint roles_fk foreign key(roleid)
		references roles(id)
);