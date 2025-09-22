SET search_path TO sh;

-- account owns one or more neighborhoods
create table if not exists accounts(
  account_id varchar(31) PRIMARY KEY,
  user_email varchar(65) not null UNIQUE,
  name varchar(50) not null,
  active boolean not null,
  created timestamp not null,
  last_updated timestamp null
);

create table if not exists account_keys(
  account_key_id serial primary key,
  account_id varchar(31) not null,
  account_token varchar(31) not null,
  active boolean not null,
  created timestamp not null,
  CONSTRAINT fk_account
        FOREIGN KEY(account_id)
          REFERENCES accounts(account_id)
);
CREATE INDEX acc_keys_idx ON account_keys(account_id);

-- neighborhood is a bounded geographic area
create table if not exists neighborhoods(
  neighborhood_id varchar(31) PRIMARY KEY,
  account_id varchar(31) not null,
  name varchar(50) not null UNIQUE,
  geohash varchar(12) not null,
  created timestamp not null,
  CONSTRAINT fk_account
        FOREIGN KEY(account_id)
          REFERENCES accounts(account_id)
);

CREATE INDEX acc_idx ON neighborhoods(account_id);
CREATE TYPE speed_unit AS ENUM ('mph', 'kmh');

-- speedhog is an installation (e.g raspberry pi camera set-up)
-- at a specific location within a neighborhood
create table if not exists speedhogs(
  speedhog_id serial PRIMARY KEY,
  speedhog_id_ext varchar(31) not null,
  account_id varchar(31) not null,
  neighborhood_id varchar(31) not null,
  speedhog_name varchar(50) not null,
  speed_limit smallint not null,
  offense_threshold smallint not null,
  unit speed_unit not null,
  geohash varchar(12) not null,
  street_address varchar(50) null,
  capture_version varchar(10) not null,
  sys_version varchar(10) null,
  show_location boolean not null,
  active boolean not null,
  created timestamp not null,
  CONSTRAINT fk_neighborhood
        FOREIGN KEY(neighborhood_id)
          REFERENCES neighborhoods(neighborhood_id)
);

CREATE UNIQUE INDEX speedhog_ext_idx ON speedhogs(speedhog_id_ext);
CREATE UNIQUE INDEX idx_hood_hogname ON speedhogs(neighborhood_id, speedhog_name);
CREATE INDEX idx_unq_acct_hood ON speedhogs(account_id, neighborhood_id);
CREATE TYPE direction_enum AS ENUM ('north', 'south', 'east', 'west');

-- event persists traffic data, append-only
create table if not exists events(
  event_id bigserial primary key,
  speedhog_id integer not null,
  speed smallint not null,
  exceeds_threshold_by smallint default 0 not null,
  direction direction_enum not null,
  has_image boolean not null,
  captured timestamp not null,
  inserted timestamp default now() not null
);
CREATE INDEX sh_idx ON events(speedhog_id, captured);

-- offenses store violations / speeding events according to speedhog admin configuration
-- offense data and vehicle identification will very likely be in a separate service / DB
create table if not exists offenses(
  offense_id bigserial primary key,
  offense_id_ext varchar(31) not null,
  vehicle_id varchar(31) not null,
  event_id bigint not null,
  speedhog_id integer not null,
  speed smallint not null,
  direction direction_enum not null,
  captured timestamp not null
);
CREATE INDEX off_sh_idx ON offenses(speedhog_id, captured);
CREATE INDEX off_sh_idx_ext ON offenses(offense_id_ext);

alter table events replica identity full;

create publication sequin_pub for all tables;
select pg_create_logical_replication_slot('sequin_slot', 'pgoutput');
