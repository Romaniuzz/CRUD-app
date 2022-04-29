CREATE TABLE ebdb.`authenticated_users` (
                   `id` bigint NOT NULL AUTO_INCREMENT,
                   `authenticated_user_email` varchar(255) NOT NULL,
                   `authenticated_user_name` varchar(30) NOT NULL,
                   `authenticated_user_surname` varchar(30) NOT NULL,
                   `authenticated_user_password` varchar(255) NOT NULL,
                   `authenticated_user_role` varchar(20) NOT NULL DEFAULT 'USER',
                   `status` varchar(15) NOT NULL DEFAULT 'ACTIVE',
                   PRIMARY KEY (`id`),
                   UNIQUE KEY `authenticated_users_authenticated_user_email_uindex` (`authenticated_user_email`),
                   UNIQUE KEY `authenticated_users_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE ebdb.`users` (
                   `id` bigint NOT NULL AUTO_INCREMENT,
                   `first_name` varchar(30) NOT NULL,
                   `last_name` varchar(30) NOT NULL,
                   `email` varchar(100) NOT NULL,
                   PRIMARY KEY (`id`),
                   UNIQUE KEY `users_email_uindex` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

