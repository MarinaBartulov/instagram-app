insert into authority (name) values ('ROLE_USER');
insert into authority (name) values ('ROLE_ADMIN');

insert into user ( username, password, name, email, enabled, active, deleted, last_password_reset_date) values ('marina', '$2a$10$TTVusLso4o026WeJ66UcxueFtMRKoMgP0pztaP5my1UMr7FBAVQQG', 'Marina Bartulov', 'marina@gmail.com',true,true,true, '2020-09-19 04:00:00');
insert into user_authority(user_id, authority_id) value (1,1);

insert into user ( username, password, name, email, enabled, active, deleted, last_password_reset_date) values ('pera', '$2a$10$TTVusLso4o026WeJ66UcxueFtMRKoMgP0pztaP5my1UMr7FBAVQQG', 'Petar Petrovic', 'petar@gmail.com',true,true,true, '2020-09-19 04:00:00');
insert into user_authority(user_id, authority_id) value (2,1);

insert into user ( username, password, name, email, enabled, active, deleted, last_password_reset_date) values ('mika', '$2a$10$TTVusLso4o026WeJ66UcxueFtMRKoMgP0pztaP5my1UMr7FBAVQQG', 'Mika Mikic', 'mika@gmail.com',true,true,true, '2020-09-19 04:00:00');
insert into user_authority(user_id, authority_id) value (3,1);

insert into user ( username, password, name, email, enabled, active, deleted, last_password_reset_date) values ('admin', '$2a$10$TTVusLso4o026WeJ66UcxueFtMRKoMgP0pztaP5my1UMr7FBAVQQG', 'Admin', 'admin@gmail.com',true,true,true, '2020-09-19 04:00:00');
insert into user_authority(user_id, authority_id) value (4,2);

insert into photo (path, mime_type, extension) values ('assets\\images\\image1.jpg', 'image/jpeg','jpg');
insert into post (description, photo_id, user_id, date_time, deleted) values ('This is my post.', 1, 1, '2020-09-19 04:00:00', false);
insert into photo (path, mime_type, extension) values ('assets\\images\\image2.jpg', 'image/jpeg','jpg');
insert into post (description, photo_id, user_id, date_time, deleted) values ('Photo from Paris.', 2, 1, '2020-09-20 04:00:00', false);
insert into photo (path, mime_type, extension) values ('assets\\images\\image3.jpg', 'image/jpeg','jpg');
insert into post (description, photo_id, user_id, date_time, deleted) values ('Photo from Paris!!!', 3, 2, '2020-09-21 04:00:00', false);
insert into photo (path, mime_type, extension) values ('assets\\images\\image4.jpg', 'image/jpeg','jpg');
insert into post (description, photo_id, user_id, date_time, deleted) values ('New photo!', 4, 3, '2020-09-22 04:00:00', false);

insert into comment (text, post_id, user_id, date_time, deleted) values ('Great photo!', 1, 2,'2020-09-19 04:00:00', false);

insert into follow (follower_id, followed_id, date_time_followed) values (2,1,'2020-09-19 04:00:00');
insert into follow (follower_id, followed_id, date_time_followed) values (1,2,'2020-09-19 05:00:00');