insert into user ( username, password, name, email, enabled, active, deleted, last_password_reset_date) values ('marina', '$2a$10$TTVusLso4o026WeJ66UcxueFtMRKoMgP0pztaP5my1UMr7FBAVQQG', 'Marina Bartulov', 'marina@gmail.com',true,true,true, '2020-09-19 04:00:00');
insert into authority (name) values ('ROLE_USER');
insert into user_authority(user_id, authority_id) value (1,1);

insert into user ( username, password, name, email, enabled, active, deleted, last_password_reset_date) values ('pera', '$2a$10$TTVusLso4o026WeJ66UcxueFtMRKoMgP0pztaP5my1UMr7FBAVQQG', 'Petar Petrovic', 'petar@gmail.com',true,true,true, '2020-09-19 04:00:00');
insert into authority (name) values ('ROLE_USER');
insert into user_authority(user_id, authority_id) value (2,1);

insert into user ( username, password, name, email, enabled, active, deleted, last_password_reset_date) values ('mika', '$2a$10$TTVusLso4o026WeJ66UcxueFtMRKoMgP0pztaP5my1UMr7FBAVQQG', 'Mika Mikic', 'mika@gmail.com',true,true,true, '2020-09-19 04:00:00');
insert into authority (name) values ('ROLE_USER');
insert into user_authority(user_id, authority_id) value (3,1);


insert into post (description, photo_path, user_id, date_time, deleted) values ('This is my first post.', 'assets\\images\\image1.jpg', 1, '2020-09-19 04:00:00', false);
insert into post (description, photo_path, user_id, date_time, deleted) values ('First photo from Paris.', 'assets\\images\\image2.jpg', 1, '2020-09-20 04:00:00', false);
insert into post (description, photo_path, user_id, date_time, deleted) values ('Second photo from Paris.', 'assets\\images\\image3.jpg', 2, '2020-09-20 04:00:00', false);

insert into comment (text, post_id, user_id, date_time, deleted) values ('Great photo!', 1, 2,'2020-09-19 04:00:00', false);

insert into follow (follower_id, followed_id, date_time_followed) values (2,1,'2020-09-19 04:00:00');
insert into follow (follower_id, followed_id, date_time_followed) values (1,2,'2020-09-19 05:00:00');