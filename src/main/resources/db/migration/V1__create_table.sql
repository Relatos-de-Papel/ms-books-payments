CREATE TABLE `payments_type`
(
    `id`   bigint       NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `status`
(
    `id`   bigint       NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `orders`
(
    `id`              bigint       NOT NULL AUTO_INCREMENT,
    `order_number`    bigint       NOT NULL,
    `date_created`    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `user_id`         bigint       NOT NULL,
    `status_id`       bigint       NOT NULL,
    `payment_type_id` bigint       NOT NULL,
    `total_price`     double(5, 2) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_payments_type_id` FOREIGN KEY (`payment_type_id`) REFERENCES `payments_type` (`id`),
    CONSTRAINT `fk_status_id` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
);

CREATE TABLE `order_detail`
(
    `id`       bigint NOT NULL AUTO_INCREMENT,
    `order_id` bigint NOT NULL,
    `book_id`  bigint NOT NULL,
    `quantity` bigint NOT NULL,
    `price`    bigint NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_orders_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
);

