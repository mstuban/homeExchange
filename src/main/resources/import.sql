INSERT INTO USERS(USERID, USERNAME, EMAIL, PASSWORD, ENABLED)VALUES (1, 'user','user@abc.com','$2a$04$DAAyO2IdFWHxGLWLzVbRuuE0pbMvSlt11zCKrRUj7sjU77hmDWgn6', true);
INSERT INTO USERS(USERID, USERNAME, EMAIL, PASSWORD, ENABLED) VALUES (2, 'admin','admin@def.com','$2a$04$9VL2GYxiYhEZbHl.M84Oa./5IYPfpUcvcl7pHjszfEJ3N6tqZ98nu', true);

INSERT INTO USER_ROLES (USER_ROLE_ID, USERID, ROLE) VALUES (1, 1, 'ROLE_USER');
INSERT INTO USER_ROLES (USER_ROLE_ID, USERID, ROLE) VALUES (2, 2, 'ROLE_ADMIN');

INSERT INTO HOME(HOME_ID, AVAILABLE, DESCRIPTION, SIZE_IN_SQUARE_METERS, TYPE) VALUES(1, true, 'Stan u centru grada, ugodan i prostran.', 120, 'Stan');
INSERT INTO ADDRESS(ADDRESS_ID, CITY, COUNTRY, POSTAL_CODE, STREET, FRN_HOME_ID) VALUES (1,'Zagreb','Croatia', '10000', 'Vlaska 70C',1);
INSERT INTO HOME(HOME_ID, AVAILABLE, DESCRIPTION, SIZE_IN_SQUARE_METERS, TYPE) VALUES (2, true, 'Obiteljska kuca u centru Ljubljane. Velika i prostrana.', 250, 'Obiteljska kuca');
INSERT INTO ADDRESS(ADDRESS_ID, CITY, COUNTRY, POSTAL_CODE, STREET, FRN_HOME_ID) VALUES (2, 'Ljubljana', 'Slovenia', 1532, 'Poljanska cesta 25', 2);
INSERT INTO HOME(HOME_ID, AVAILABLE, DESCRIPTION, SIZE_IN_SQUARE_METERS, TYPE) VALUES (3, true, 'Garsonijera u Sarajevu.', 150, 'Garsonijera');
INSERT INTO ADDRESS(ADDRESS_ID, CITY, COUNTRY, POSTAL_CODE, STREET, FRN_HOME_ID) VALUES (3, 'Sarajevo', 'Bosnia And Herzegovina', 71000, 'Bascarsija 42', 3);