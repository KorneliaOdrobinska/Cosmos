create table MISSIONS (
    ID int primary key auto_increment,
    NAME varchar(100) not null,
    MANNED bit,
    CELESTIALBODYCORRELATION int,
    SATELLITECORRELATION int,
    MISSIONSTARTDATE date,
    ISFINISHED bit,
    CORRELATEDTOCELESTIALBODY bit not null
);