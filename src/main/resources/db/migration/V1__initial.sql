-- create user table
create table users (
                         id bigint not null,
                         user_name varchar(150) not null,
                         password varchar(150) not null,
                         email varchar(150) not null,
                         creation_date timestamp without time zone,
                         deleted boolean NOT NULL DEFAULT false,
                         primary key (id)
);

-- create a sequence for user id
create sequence user_sequence as integer increment 1;


create table rol (
                        id integer not null,
                        name varchar(100) not null,
                        deleted boolean NOT NULL DEFAULT false,
                        primary key (id)
);
create sequence rol_sequence as integer increment 1;

-- create user_rol table
create table user_rol (
                            id integer not null,
                            active boolean not null,
                            created_at timestamp not null,
                            user_id bigint,
                            rol_id integer,
                            deleted boolean NOT NULL DEFAULT false,
                            primary key (id)
);
create sequence user_rol_sequence as integer increment 1;

alter table user_rol add constraint FK_User_Rol_Ref_User foreign key (user_id)
    references users (id) on delete restrict on update restrict;

alter table user_rol add constraint FK_User_Rol_Ref_Rol foreign key (rol_id)
    references rol (id) on delete restrict on update restrict;


create table user_detail (
                                 id bigint not null,
                                 first_name varchar (100) not null,
                                 last_name varchar (100) not null,
                                 age integer not null,
                                 birth_day Date,
                                 user_id bigint,
                                 deleted boolean NOT NULL DEFAULT false,
                                 primary key (id)
);
create sequence detail_sequence as integer increment 1;
alter table user_detail add constraint fk_user_detail_Ref_User foreign key (user_id)
    references users (id) on delete restrict on update restrict;