INSERT INTO users (id, email, first_name, last_name, image_url, active, password)
VALUES (1, 'lachezar.balev@gmail.com', 'Lucho', 'Balev', null, 1,
        '26bf518d364cf1df9426e6c6f8d2f56b862f9d1d6e2a285dc5512be4f97c2dde5dded32ebb6dad0abd1c0db3c0502576'),
       (2, 'pesho@mail.com', 'Pesho', 'Peshev', null, 1,
        '26bf518d364cf1df9426e6c6f8d2f56b862f9d1d6e2a285dc5512be4f97c2dde5dded32ebb6dad0abd1c0db3c0502576'),
       (3, 'user@example.com', 'User', 'Userov', null, 1,
        '26bf518d364cf1df9426e6c6f8d2f56b862f9d1d6e2a285dc5512be4f97c2dde5dded32ebb6dad0abd1c0db3c0502576');

INSERT INTO roles(id, role)
VALUES (1, 'ADMIN'),
       (2, 'USER');

INSERT INTO users_roles(user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (2, 2),
       (3, 2);

INSERT INTO brands (id, name)
VALUES (1, 'Toyota'),
       (2, 'Ford'),
       (3, 'Trabant');

INSERT INTO models (id, name, category, brand_id)
VALUES (1, 'Camry', 'CAR', 1),
       (2, 'Corolla', 'CAR', 1),
       (3, 'Focus', 'CAR', 2),
       (4, 'Fiesta', 'CAR', 2),
       (5, '601', 'CAR', 3),
       (6, '601S', 'CAR', 3);

INSERT INTO offers (id, description, engine, image_url, mileage, price, transmission, uuid, year, model_id, seller_id)
VALUES (1, 'Top Trabi 1!', 'GASOLINE',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/Trabant_601_Mulhouse_FRA_001.JPG/1280px-Trabant_601_Mulhouse_FRA_001.JPG',
        24000, 2223.00, 'MANUAL', 'd2e388f5-1a46-4253-b7e3-a0f74b36f47e', 1987, 5, 1),
       (2, 'Top Trabi 1!', 'GASOLINE',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/Trabant_601S_Universal_1984_II.jpg/1280px-Trabant_601S_Universal_1984_II.jpg',
        25000, 2000.00, 'MANUAL', 'be8cd8ac-6ab4-4a3f-b192-7ce2fd6c8cd3', 1985, 6, 1),
       (3, 'Top Trabi 2!', 'GASOLINE',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/Trabant_601S_Universal_1984_II.jpg/1280px-Trabant_601S_Universal_1984_II.jpg',
        25000, 2000.00, 'MANUAL', '40b6b119-705a-4df3-b39a-1f68655f3854', 1986, 6, 2),
       (4, 'Top Trabi 2!', 'GASOLINE',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/Trabant_601S_Universal_1984_II.jpg/1280px-Trabant_601S_Universal_1984_II.jpg',
        25000, 2000.00, 'MANUAL', 'be114147-e607-4558-bb7e-ffce834b8fbe', 1987, 6, 2),
       (5, 'Top Trabi 2!', 'GASOLINE',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/Trabant_601S_Universal_1984_II.jpg/1280px-Trabant_601S_Universal_1984_II.jpg',
        25000, 2000.00, 'MANUAL', 'b8ad5bf1-392b-4426-b8d8-ca4026ede090', 1988, 6, 2);