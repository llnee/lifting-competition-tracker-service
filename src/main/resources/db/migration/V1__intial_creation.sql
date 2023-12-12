
CREATE TABLE IF NOT EXISTS competition (
    competition_id integer primary key not null,
    facility_name char(50) not null,
    comp_date date not null,
    address_one char(100),
    address_two char(100),
    city char(50),
    state char(30),
    zip int,
    finalized boolean
);

ALTER TABLE competition
    ALTER COLUMN finalized
        SET DEFAULT FALSE;

CREATE TABLE IF NOT EXISTS competitor (
                             member_id integer primary key not null,
                             first_name char(50),
                             last_name char(50) not null,
                             birth_date double precision,
                             gender char(15)
);

CREATE TABLE IF NOT EXISTS competition_join (
                            comp_join_id integer primary key not null,
                            competition_id integer not null,
                            member_id integer not null,
                            weight double precision,
                            age integer
);

CREATE TABLE IF NOT EXISTS address (
                            member_id integer primary key not null,
                            address_one char(100) not null,
                            address_two char(100) not null,
                            city char(50),
                            state char(30),
                            zip integer
);

CREATE TABLE IF NOT EXISTS email (
                            member_id integer primary key not null,
                            email char(70) not null
);

CREATE TABLE IF NOT EXISTS results (
                            comp_join_id integer primary key not null,
                            snatch_attempt_one integer not null,
                            snatch_attempt_two integer not null,
                            snatch_attempt_three integer not null,
                            jerk_attempt_one integer not null,
                            jerk_attempt_two integer not null,
                            jert_attempt_three integer not null
);