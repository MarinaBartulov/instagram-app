insert into user ( username, password, name, email, enabled, active, deleted, last_password_reset_date) values ('marina', '$2a$10$TTVusLso4o026WeJ66UcxueFtMRKoMgP0pztaP5my1UMr7FBAVQQG', 'Marina Bartulov', 'marina@gmail.com',true,true,true, '2020-09-19 04:00:00');
insert into authority (name) values ('ROLE_USER');
insert into user_authority(user_id, authority_id) value (1,1);
