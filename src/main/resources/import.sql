INSERT INTO users(userid, username, email, password, enabled) VALUES (1, 'user','user@abc.com','$2a$04$DAAyO2IdFWHxGLWLzVbRuuE0pbMvSlt11zCKrRUj7sjU77hmDWgn6', 1);
INSERT INTO users(userid, username, email, password, enabled) VALUES (2, 'admin','admin@def.com','$2a$04$9VL2GYxiYhEZbHl.M84Oa./5IYPfpUcvcl7pHjszfEJ3N6tqZ98nu', 1);

INSERT INTO user_roles (user_role_id, userid, role) VALUES (1, 1, 'ROLE_USER');
INSERT INTO user_roles (user_role_id, userid, role) VALUES (2, 2, 'ROLE_ADMIN');


INSERT INTO address