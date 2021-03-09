drop table IF EXISTS schedule_service;

create table schedule_service (
id BIGSERIAL,
city_from VARCHAR (50) NOT NULL,
city_to  VARCHAR (50) NOT NULL,
departure_time DATE  NOT NULL,
arrival_time DATE NOT NULL );

insert into schedule_service (id, city_from, city_to, departure_time, arrival_time)
values ('Odessa', 'Kiev', '09.40', '20.15' );
values ('Lviv', 'Kiev', '09.40', '20.15' );
values ('Odessa', 'Kiev', '09.40', '20.15' );
values ('Odessa', 'Kiev', '09.40', '20.15' );
values ('Odessa', 'Kiev', '09.40', '20.15' );
values ('Odessa', 'Kiev', '09.40', '20.15' );
values ('Odessa', 'Kiev', '09.40', '20.15' );
values ('Odessa', 'Kiev', '09.40', '20.15' );
values ('Odessa', 'Kiev', '09.40', '20.15' );
values ('Odessa', 'Kiev', '09.40', '20.15' );
values ('Odessa', 'Kiev', '09.40', '20.15' );
values ('Odessa', 'Kiev', '09.40', '20.15' );

select * from schedule_service;