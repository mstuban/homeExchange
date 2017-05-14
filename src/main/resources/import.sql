INSERT INTO users(userid, username, email, password, enabled) VALUES (1, 'user','user@abc.com','$2a$04$DAAyO2IdFWHxGLWLzVbRuuE0pbMvSlt11zCKrRUj7sjU77hmDWgn6', 1);
INSERT INTO users(userid, username, email, password, enabled) VALUES (2, 'admin','admin@def.com','$2a$04$9VL2GYxiYhEZbHl.M84Oa./5IYPfpUcvcl7pHjszfEJ3N6tqZ98nu', 1);

INSERT INTO user_roles (user_role_id, userid, role) VALUES (1, 1, 'ROLE_USER');
INSERT INTO user_roles (user_role_id, userid, role) VALUES (2, 2, 'ROLE_ADMIN');

INSERT INTO home(home_id, available, description, size_in_square_meters, type) VALUES(1, true, 'Stan u centru grada, ugodan i prostran.', 120, 'Stan');
INSERT INTO address(address_id, city, country, postal_code, street, frn_home_id) VALUES (1,'Zagreb','Croatia', '10000', 'Vlaska 70C',1);
INSERT INTO home(home_id, available, description, size_in_square_meters, type) VALUES (2, false, 'Obiteljska kuca u centru Srpske prijestolnice. Mnogo velik i prostran.', 250, 'Obiteljska kuca');
INSERT INTO address(address_id, city, country, postal_code, street, frn_home_id) VALUES (2, 'Beograd', 'Serbia', 11000, 'Kalemegdan 12', 2);