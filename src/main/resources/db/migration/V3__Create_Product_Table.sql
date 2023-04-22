CREATE TABLE IF NOT EXISTS Category(
    id varchar(255) primary key,
    `name` varchar(255) NOT NULL,
    `createdAt` datetime,
    `createUserId` varchar(255),
    `updatedAt` datetime,
    `lastUserId` varchar(255)
);

CREATE TABLE IF NOT EXISTS Product(
    `id` int primary key auto_increment,
    `name` varchar(255) NOT NULL,
    `description` varchar(255),
    `image` varchar(255),
    `value` decimal(19,2),
    `discount` decimal(19,2),
    `categoryId` varchar(255),
    `shopId` varchar(255),
    `createdAt` datetime,
    `createUserId` varchar(255),
    `updatedAt` datetime,
    `lastUserId` varchar(255),
    KEY `FK_CategoryId` (categoryId),
    CONSTRAINT `FK_CategoryId` FOREIGN KEY (`categoryId`) REFERENCES `Category` (`id`)
);