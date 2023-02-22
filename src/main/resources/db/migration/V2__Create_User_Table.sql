CREATE TABLE IF NOT EXISTS `User`(
    email varchar(255) NOT NULL,
    governmentId varchar(255) DEFAULT NULL,
    `name` varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    `type` varchar(255) DEFAULT NULL,
    phone varchar(255) DEFAULT NULL,
    cellPhone varchar(255) DEFAULT NULL,
    version int DEFAULT NULL,
    status varchar(255) DEFAULT NULL,
    createdAt datetime DEFAULT NULL,
    createUserId varchar(255) DEFAULT NULL,
    updatedAt datetime DEFAULT NULL,
    lastUserId varchar(255) DEFAULT NULL,
    PRIMARY KEY(email)
);