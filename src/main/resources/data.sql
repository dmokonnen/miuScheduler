REPLACE INTO `roles` VALUES (1,'ADMIN');
REPLACE INTO `roles` VALUES (2,'STUDENT');
REPLACE INTO `roles` VALUES (3,'FACULTY');
/*
-- Query: SELECT * FROM mumscheddb.users
LIMIT 0, 50000

-- Date: 2020-05-12 01:21
*/
-- REPLACE INTO `users` (`user_id`,`active`,`email`,`last_name`,`name`,`password`,`user_name`) VALUES (6,'1','admin@miu.edu','MIU','Admin','$2a$10$NS.N2k/YbhL6k0eMC9fwM.3Xp2zcvQODaogbogdg/fTf4AO8NrZVe','admin');
-- REPLACE INTO `users` (`user_id`,`active`,`email`,`last_name`,`name`,`password`,`user_name`) VALUES (8,'1','denkossa@miu.edu','Enkossa','Dereje','$2a$10$/izqx5cgQXip5syYg.otQeKUBlXTq2Es5TR/KogcV1W4Dn8a2HAxe','dereje');
-- REPLACE INTO `users` (`user_id`,`active`,`email`,`last_name`,`name`,`password`,`user_name`) VALUES (9,'1','ekhan@miu.edu','Khan','Emdad','$2a$10$sJvPXX524jNGKqInoyv4iOytqN907ghbVTSux1U5UqBw14LUZFC0u','ekhan');

/*
-- Query: SELECT * FROM mumscheddb.user_role
LIMIT 0, 50000

-- Date: 2020-05-12 01:22
*/
REPLACE INTO `user_role` (`user_id`,`role_id`) VALUES (6,1);
REPLACE INTO `user_role` (`user_id`,`role_id`) VALUES (8,2);
REPLACE INTO `user_role` (`user_id`,`role_id`) VALUES (9,3);
/*
-- Query: SELECT * FROM mumscheddb.hibernate_sequence
LIMIT 0, 50000

-- Date: 2020-05-12 01:27
*/
REPLACE INTO `hibernate_sequence` (`next_val`) VALUES (10);
REPLACE INTO `hibernate_sequence` (`next_val`) VALUES (10);
