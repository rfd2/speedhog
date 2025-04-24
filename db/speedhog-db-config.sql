

\set SHAPP_PASSWORD `echo $SHAPP_PWORD`
CREATE USER shapp WITH LOGIN PASSWORD :'SHAPP_PASSWORD';

create database speedhog OWNER shapp;
\c speedhog
create schema sh AUTHORIZATION shapp;

GRANT ALL PRIVILEGES ON DATABASE speedhog TO shapp;

-- account owns one or more neighborhoods
create table if not exists sh.accounts(
  account_id serial primary key,
  user_email varchar(65) not null UNIQUE,
  created timestamp not null
);

-- neighborhood is a bounded geographic area
create table if not exists sh.neighborhoods(
  neighborhood_id serial primary key,
  account_id integer not null,
  name varchar(50) not null UNIQUE,
  geohash varchar(12) not null,
  created timestamp not null
);

-- speedhog is an installation
create table if not exists sh.speedhogs(
  speedhog_id serial primary key,
  neighborhood_id integer not null,
  speedhog_name varchar(50) not null,
  geohash varchar(12) not null,
  street_address varchar(50) null,
  capture_version varchar(10) not null,
  pi_version varchar(10) null,
  active boolean not null,
  created timestamp not null
);

CREATE UNIQUE INDEX idx_hood_hogname ON sh.speedhogs(neighborhood_id, speedhog_name);

-- event persists traffic data, append-only
create table if not exists sh.events(
  event_id serial primary key,
  speedhog_id integer not null,
  speed smallint not null,
  direction varchar(10) not null,
  captured timestamp not null,
  inserted timestamp default now() not null
);

alter table sh.events replica identity full;

create publication sequin_pub for all tables;
select pg_create_logical_replication_slot('sequin_slot', 'pgoutput');
