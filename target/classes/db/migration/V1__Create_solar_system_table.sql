create table SOLAR_SYSTEM (
    ID int primary key auto_increment,
    NAME varchar(100) not null,
    ISPLANET bit,
    TEMPERATURE int,
    RADIUS int,
    MEASUREMENTDATE varchar(100)
);