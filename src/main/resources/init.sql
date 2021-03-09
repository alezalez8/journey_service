create table schedule_service (
id BIGSERIAL,
city_from VARCHAR (50),
city_to  VARCHAR (50),
departure_time DATE ,
arrival_time DATA ,
);

insert into schedule_service (id, city_from, city_to, departure_time, arrival_time)
values