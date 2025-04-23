create database speedhog;
\c speedhog
create schema sh;

-- event persists traffic data, append-only
create table if not exists event(
  id serial primary key,
  sh_id integer not null,
  speed smallint not null,
  direction varchar(10) not null,
  captured timestamp not null,
  inserted timestamp default now() not null
);

alter table event replica identity full;

create publication sequin_pub for all tables;
select pg_create_logical_replication_slot('sequin_slot', 'pgoutput');


\set SHAPP_PASSWORD `echo $SHAPP_PWORD`
CREATE USER shapp WITH LOGIN PASSWORD :'SHAPP_PASSWORD';
GRANT ALL PRIVILEGES ON DATABASE speedhog TO shapp;
