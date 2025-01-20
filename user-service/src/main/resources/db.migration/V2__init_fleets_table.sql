CREATE TABLE fleets (

    id BIGINT GENERATED BY DEFAULT AS IDENTITY ,
    registration VARCHAR(20) NOT NULL UNIQUE ,
    model VARCHAR(50) NOT NULL ,
    capacity INTEGER NOT NULL ,
    PRIMARY KEY (id)
);