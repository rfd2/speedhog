

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

CREATE INDEX acc_idx ON sh.neighborhoods(account_id);
CREATE TYPE speed_unit AS ENUM ('mph', 'kmh');

-- speedhog is an installation (e.g raspberry pi camera set-up)
-- at a specific location within a neighborhood
create table if not exists sh.speedhogs(
  speedhog_id serial primary key,
  neighborhood_id integer not null,
  speedhog_name varchar(50) not null,
  speed_limit smallint not null,
  offense_threshold smallint not null,
  unit speed_unit not null,
  geohash varchar(12) not null,
  street_address varchar(50) null,
  capture_version varchar(10) not null,
  pi_version varchar(10) null,
  show_location boolean not null,
  active boolean not null,
  created timestamp not null
);

CREATE UNIQUE INDEX idx_hood_hogname ON sh.speedhogs(neighborhood_id, speedhog_name);
CREATE TYPE direction_enum AS ENUM ('north', 'south', 'east', 'west');

-- event persists traffic data, append-only
create table if not exists sh.events(
  event_id bigserial primary key,
  speedhog_id integer not null,
  speed smallint not null,
  exceeds_threshold_by smallint not null,
  direction direction_enum not null,
  has_image boolean not null,
  captured timestamp not null,
  inserted timestamp default now() not null
);
CREATE INDEX sh_idx ON sh.events(speedhog_id, captured);

-- offenses store violations / speeding events according to speedhog admin configuration
create table if not exists sh.offenses(
  offense_id bigserial primary key,
  vehicle_id integer not null,
  event_id bigint not null,
  speedhog_id integer not null,
  speed smallint not null,
  direction direction_enum not null,
  captured timestamp not null
);
CREATE INDEX off_sh_idx ON sh.offenses(speedhog_id, captured);

alter table sh.events replica identity full;

create publication sequin_pub for all tables;
select pg_create_logical_replication_slot('sequin_slot', 'pgoutput');
