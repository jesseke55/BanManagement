CREATE TABLE `bans` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `uuid` VARCHAR(36) NOT NULL,
    `bantype` SET('PLAYER','IP') NOT NULL DEFAULT 'PLAYER',
    `reason` VARCHAR(100) NOT NULL,
    `expire` TIMESTAMP NOT NULL,
    `issuer` VARCHAR(16) NOT NULL,
    PRIMARY KEY (`id`)
);