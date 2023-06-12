create table SATELLITES (
    ID int primary key auto_increment,
    NAME varchar(100) not null,
    ISNATURAL bit,
    CELESTIALBODYCORRELATION int,
    DISCOVERYDATE date
);