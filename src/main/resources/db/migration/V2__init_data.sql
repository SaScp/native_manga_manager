
INSERT INTO t_role values (1, 'ROLE_USER');
INSERT INTO t_role values (2, 'ROLE_ADMIN');
INSERT INTO t_role values (3, 'ROLE_BLOCK');
INSERT INTO t_role values (4, 'ROLE_MUTE');
DO $$
 declare
genre_row record;
    genre varchar(255);
BEGIN
FOR genre_row IN SELECT * FROM (VALUES
                   ('Боевые искусства'), ('Гарем'), ('Гендерная интрига'), ('Героический фэнтези'), ('Детектив'),
                   ('Дзёсэй'), ('Додзинси'), ('Драма'), ('История'), ('Киберпанк'), ('Кодомо'), ('Комедия'),
                   ('Махо-сёдзё'), ('Меха'), ('Мистика'), ('Научная фантастика'), ('Повседневность'),  ('Постапокалиптика'),
                   ('Приключения'), ('Психология'), ('Романтика'),('Сёдзё'),  ('Сёдзё-ай'), ('Сёнэн'), ('Спорт'), ('Сэйнэн'),
                   ('Трагедия'), ('Триллер'), ('Ужасы'), ('Фантастика'), ('Фэнтези'), ('Школьная жизнь'), ('Экшен'),
                   ('Элементы юмора'), ('Эротика'), ('Этти'), ('Юри'), ('Сверхъестественное')) AS t(genre) LOOP
INSERT INTO t_genre(genre) VALUES (genre_row.genre);
END LOOP;
END $$;


INSERT INTO t_type(id, c_type) VALUES (1, 'Манга');
INSERT INTO t_type(id, c_type) VALUES (2, 'Манхва');
INSERT INTO t_type(id, c_type) VALUES (3, 'Маньхуа');
INSERT INTO t_type(id, c_type) VALUES (4, 'Западный комикс');
INSERT INTO t_type(id, c_type) VALUES (5, 'Рукомикс');
INSERT INTO t_type(id, c_type) VALUES (6, 'Индонезийский комикс');
INSERT INTO t_type(id, c_type) VALUES (7, 'Другое');

INSERT INTO t_manga(id, main_name, secondary_name, issue_year, avg_rating, c_type, is_yaoi, is_erotic, img, rus_name, en_name, count_chapters)
VALUES ('d49a0c1e-8b4a-4f84-84e5-982bc5c6f0e1', 'I Am A NPC In VR-Game','Я - NPC в VR-Игре',2021 , 8.3, 3, false, false, 'none', 'Я - NPC в VR-Игре', 'I Am A NPC In VR-Game', 0);
INSERT INTO t_manga(id, main_name, secondary_name, issue_year, avg_rating, c_type, is_yaoi, is_erotic, img, rus_name, en_name, count_chapters)
VALUES ('73e27398-6e3c-42a3-ba21-9c4bcb29e13f', 'My big sword','Мой большой меч',2021 ,7.9, 3, false, false, 'none', 'Мой большой меч', 'My big sword', 0);
INSERT INTO t_manga(id, main_name, secondary_name, issue_year, avg_rating, c_type, is_yaoi, is_erotic, img, rus_name, en_name, count_chapters)
VALUES ('fbebbf8a-ae65-4b61-97c3-4a2f36a27dfc', 'Sword Art Online: Fairy Dance','Мастера меча онлайн: Танец фей',2012 , 5.5, 1, false, false, 'none', 'Мастера меча онлайн: Танец фей', 'Sword Art Online: Fairy Dance', 0);
INSERT INTO t_manga(id, main_name, secondary_name, issue_year, avg_rating, c_type, is_yaoi, is_erotic, img, rus_name, en_name, count_chapters)
    VALUES ('1b9d6bcd-bbfd-4b2d-9b5d-ab8dfbbd4bed', 'The strongest level 1','Самый сильный уровень 1',2024 , 0.0, 2, false, false, 'none', 'Самый сильный уровень 1', 'The strongest level 1', 0);
INSERT INTO t_manga(id, main_name, secondary_name, issue_year, avg_rating, c_type, is_yaoi, is_erotic, img, en_name, rus_name, count_chapters)
VALUES ('75f3c1f3-5bb8-4a6a-8450-25d6b7c98c6b', 'Dorei o Choukyou Shite Harem Tsukuru','Тренировка рабынь для личного гарема',2022 , 7.5, 1, false, false, 'none','Dorei o Choukyou Shite Harem Tsukuru','Тренировка рабынь для личного гарема', 0);
INSERT INTO t_manga(id, main_name, secondary_name, issue_year, avg_rating, c_type, is_yaoi, is_erotic, img, rus_name, en_name, count_chapters)
VALUES ('c773e535-0499-4b0c-bf14-e20d8a3d31d1', 'Extraordinary Museum','Необычный музей',2024 , 0.0, 3, false, false, 'none', 'Extraordinary Museum','Необычный музей',0);
INSERT INTO t_manga(id, main_name, secondary_name, issue_year, avg_rating, c_type, is_yaoi, is_erotic, img, en_name, rus_name, count_chapters)
VALUES ('a2c7e8d0-3f8f-4c98-a727-1e3c2c7ee7d9', 'Alice','Алиса',2023 , 0.0, 2, false, false, 'none', 'Alice','Алиса', 0);
INSERT INTO t_manga(id, main_name, secondary_name, issue_year, avg_rating, c_type, is_yaoi, is_erotic, img, en_name, rus_name, count_chapters)
VALUES ('86d0652e-23b0-4d22-b8e6-295e5747bea1', 'Sword Art Online: Kirito''s Gun Gale Wars','Мастера Меча Онлайн: Война Призрачной пули Кирито',2018 , 7.5, 1, false, false, 'none', 'Sword Art Online: Kirito s Gun Gale Wars','Мастера Меча Онлайн: Война Призрачной пули Кирито',0);
INSERT INTO t_manga(id, main_name, secondary_name, issue_year, avg_rating, c_type, is_yaoi, is_erotic, img, en_name, rus_name, count_chapters)
VALUES ('451511a9-e19d-4f9a-9e84-8e9ef38b8974', 'Butterfly kill','Убийство бабочки',2024 , 0.0, 3, false, false, 'none', 'Butterfly kill','Убийство бабочки',0);
INSERT INTO t_manga(id, main_name, secondary_name, issue_year, avg_rating, c_type, is_yaoi, is_erotic, img, en_name, rus_name, count_chapters)
VALUES ('0d051e54-63a8-4ec3-940f-7d8fbf334f7b', 'Yuna from a world without love','Юна из мира без любви',2025 , 0.0, 3, false, false, 'none', 'Yuna from a world without love','Юна из мира без любви',0);
INSERT INTO t_manga(id, main_name, secondary_name, issue_year, avg_rating, c_type, is_yaoi, is_erotic, img, en_name, rus_name, count_chapters)
VALUES ('3a421b7b-13a1-4c82-8b1e-894b7585de48', 'my male dog','мой кобель',2024 , 0.0, 2, false, false, 'none', 'my male dog','мой кобель',0);
INSERT INTO t_manga(id, main_name, secondary_name, issue_year, avg_rating, c_type, is_yaoi, is_erotic, img, en_name, rus_name, count_chapters)
VALUES ('d1a335db-79f4-4c14-8a02-b64d76c9143b', 'Even the CEO can''t control her.','Даже генеральный директор не может ее контролировать',2024 , 0.0, 2, false, false, 'none', 'Even the CEO can''t control her.','Даже генеральный директор не может ее контролировать',0);
INSERT INTO t_manga(id, main_name, secondary_name, issue_year, avg_rating, c_type, is_yaoi, is_erotic, img, en_name, rus_name, count_chapters)
VALUES ('8a8b7167-8b8e-4dd4-baae-08c1486bf86f', 'Black frost','Чёрный иней',2024 , 7.4, 5, false, false,'none','Black frost','Чёрный иней',0);
INSERT INTO t_manga(id, main_name, secondary_name, issue_year, avg_rating, c_type, is_yaoi, is_erotic, img, en_name, rus_name, count_chapters)
VALUES ('59df05bf-e30f-4b42-8ba2-1f9816214fe3', 'I''ll give you so much love that it suffocates you','Я дам тебе столько любви, что задохнёшься',2022 , 0.0, 2, false, false, 'none', 'I''ll give you so much love that it suffocates you','Я дам тебе столько любви, что задохнёшься',0);
INSERT INTO t_manga(id, main_name, secondary_name, issue_year, avg_rating, c_type, is_yaoi, is_erotic, img, en_name, rus_name, count_chapters)
VALUES ('4e8e6b1b-88b5-432f-8d5a-2e29f4c601b4', 'I took the throne after eating monster flesh','Я захватил трон, поглотив плоть монстра',2024 , 0.0, 2, false, false, 'none', 'I took the throne after eating monster flesh','Я захватил трон, поглотив плоть монстра',0);
INSERT INTO t_manga(id, main_name, secondary_name, issue_year, avg_rating, c_type, is_yaoi, is_erotic, img, en_name, rus_name, count_chapters)
VALUES ('afb9580c-8b6e-4a7f-a415-99ec64cb12e1', 'Boku ga Koisuru Cosmic Star','Космическая звезда, в которую я влюблен.',2023 , 0.0, 1, false, false, 'none', 'Boku ga Koisuru Cosmic Star','Космическая звезда, в которую я влюблен.', 0);
INSERT INTO t_manga(id, main_name, secondary_name, issue_year, avg_rating, c_type, is_yaoi, is_erotic, img, en_name, rus_name, count_chapters)
VALUES ('6a32e8b1-c397-4c72-ba97-bb50f30c6e4e', 'Slime Saint','Слизь, ставшая святой',2023 , 0.0, 1, false, false, 'none', 'Slime Saint','Слизь, ставшая святой', 0);
INSERT INTO t_manga(id, main_name, secondary_name, issue_year, avg_rating, c_type, is_yaoi, is_erotic, img, en_name, rus_name, count_chapters)
VALUES ('9bf4c0c2-2a53-4aa0-8c6d-1e868d52d685', 'Okрылённыe','Окрылённые',2023 , 0.0, 5, false, false, 'none', 'Okрылённыe','Окрылённые', 0);
INSERT INTO t_manga(id, main_name, secondary_name, issue_year, avg_rating, c_type, is_yaoi, is_erotic, img,  en_name, rus_name, count_chapters)
VALUES ('7e8532b9-84d4-4fe5-80ce-3d2e69ff7935', 'Sword Art Online: Kirito''s Aincrad Night','Мастера Меча Онлайн: Айнкрадская ночь Кирито',2016 , 4.7, 1, false, false, 'none', 'Sword Art Online: Kirito''s Aincrad Night','Мастера Меча Онлайн: Айнкрадская ночь Кирито', 0);


INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('d49a0c1e-8b4a-4f84-84e5-982bc5c6f0e1', 33);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('d49a0c1e-8b4a-4f84-84e5-982bc5c6f0e1', 2);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('d49a0c1e-8b4a-4f84-84e5-982bc5c6f0e1', 3);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('d49a0c1e-8b4a-4f84-84e5-982bc5c6f0e1', 4);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('d49a0c1e-8b4a-4f84-84e5-982bc5c6f0e1', 19);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('d49a0c1e-8b4a-4f84-84e5-982bc5c6f0e1', 36);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('d49a0c1e-8b4a-4f84-84e5-982bc5c6f0e1', 12);

INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('73e27398-6e3c-42a3-ba21-9c4bcb29e13f', 33);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('73e27398-6e3c-42a3-ba21-9c4bcb29e13f', 1);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('73e27398-6e3c-42a3-ba21-9c4bcb29e13f', 2);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('73e27398-6e3c-42a3-ba21-9c4bcb29e13f', 3);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('73e27398-6e3c-42a3-ba21-9c4bcb29e13f', 4);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('73e27398-6e3c-42a3-ba21-9c4bcb29e13f', 34);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('73e27398-6e3c-42a3-ba21-9c4bcb29e13f', 16);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('73e27398-6e3c-42a3-ba21-9c4bcb29e13f', 19);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('73e27398-6e3c-42a3-ba21-9c4bcb29e13f', 26);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('73e27398-6e3c-42a3-ba21-9c4bcb29e13f', 31);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('73e27398-6e3c-42a3-ba21-9c4bcb29e13f', 12);



INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('fbebbf8a-ae65-4b61-97c3-4a2f36a27dfc', 1);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('fbebbf8a-ae65-4b61-97c3-4a2f36a27dfc', 16);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('fbebbf8a-ae65-4b61-97c3-4a2f36a27dfc', 19);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('fbebbf8a-ae65-4b61-97c3-4a2f36a27dfc', 21);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('fbebbf8a-ae65-4b61-97c3-4a2f36a27dfc', 26);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('fbebbf8a-ae65-4b61-97c3-4a2f36a27dfc', 31);


INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('1b9d6bcd-bbfd-4b2d-9b5d-ab8dfbbd4bed', 2);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('1b9d6bcd-bbfd-4b2d-9b5d-ab8dfbbd4bed', 7);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('1b9d6bcd-bbfd-4b2d-9b5d-ab8dfbbd4bed', 17);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('1b9d6bcd-bbfd-4b2d-9b5d-ab8dfbbd4bed', 21);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('1b9d6bcd-bbfd-4b2d-9b5d-ab8dfbbd4bed', 31);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('1b9d6bcd-bbfd-4b2d-9b5d-ab8dfbbd4bed', 36);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('1b9d6bcd-bbfd-4b2d-9b5d-ab8dfbbd4bed', 12);


INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('75f3c1f3-5bb8-4a6a-8450-25d6b7c98c6b', 2);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('75f3c1f3-5bb8-4a6a-8450-25d6b7c98c6b', 7);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('75f3c1f3-5bb8-4a6a-8450-25d6b7c98c6b', 17);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('75f3c1f3-5bb8-4a6a-8450-25d6b7c98c6b', 21);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('75f3c1f3-5bb8-4a6a-8450-25d6b7c98c6b', 31);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('75f3c1f3-5bb8-4a6a-8450-25d6b7c98c6b', 36);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('75f3c1f3-5bb8-4a6a-8450-25d6b7c98c6b', 12);


INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('c773e535-0499-4b0c-bf14-e20d8a3d31d1', 15);


INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('a2c7e8d0-3f8f-4c98-a727-1e3c2c7ee7d9', 19);


INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('86d0652e-23b0-4d22-b8e6-295e5747bea1', 2);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('86d0652e-23b0-4d22-b8e6-295e5747bea1', 16);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('86d0652e-23b0-4d22-b8e6-295e5747bea1', 36);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('86d0652e-23b0-4d22-b8e6-295e5747bea1', 12);


INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('451511a9-e19d-4f9a-9e84-8e9ef38b8974', 8);


INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('0d051e54-63a8-4ec3-940f-7d8fbf334f7b', 19);


INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('3a421b7b-13a1-4c82-8b1e-894b7585de48', 19);


INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('9bf4c0c2-2a53-4aa0-8c6d-1e868d52d685', 9);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('9bf4c0c2-2a53-4aa0-8c6d-1e868d52d685', 17);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('9bf4c0c2-2a53-4aa0-8c6d-1e868d52d685', 19);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('9bf4c0c2-2a53-4aa0-8c6d-1e868d52d685', 20);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('9bf4c0c2-2a53-4aa0-8c6d-1e868d52d685', 21);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('9bf4c0c2-2a53-4aa0-8c6d-1e868d52d685', 38);


INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('4e8e6b1b-88b5-432f-8d5a-2e29f4c601b4', 8);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('4e8e6b1b-88b5-432f-8d5a-2e29f4c601b4', 19);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('4e8e6b1b-88b5-432f-8d5a-2e29f4c601b4', 24);


INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('59df05bf-e30f-4b42-8ba2-1f9816214fe3', 8);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('59df05bf-e30f-4b42-8ba2-1f9816214fe3', 21);


INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('d1a335db-79f4-4c14-8a02-b64d76c9143b', 19);


INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('8a8b7167-8b8e-4dd4-baae-08c1486bf86f', 8);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('8a8b7167-8b8e-4dd4-baae-08c1486bf86f', 16);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('8a8b7167-8b8e-4dd4-baae-08c1486bf86f', 18);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('8a8b7167-8b8e-4dd4-baae-08c1486bf86f', 19);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('8a8b7167-8b8e-4dd4-baae-08c1486bf86f', 26);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('8a8b7167-8b8e-4dd4-baae-08c1486bf86f', 27);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('8a8b7167-8b8e-4dd4-baae-08c1486bf86f', 28);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('8a8b7167-8b8e-4dd4-baae-08c1486bf86f', 30);


INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('afb9580c-8b6e-4a7f-a415-99ec64cb12e1', 21);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('afb9580c-8b6e-4a7f-a415-99ec64cb12e1', 12);

INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('7e8532b9-84d4-4fe5-80ce-3d2e69ff7935', 2);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('7e8532b9-84d4-4fe5-80ce-3d2e69ff7935', 16);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('7e8532b9-84d4-4fe5-80ce-3d2e69ff7935', 36);
INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('7e8532b9-84d4-4fe5-80ce-3d2e69ff7935', 12);


INSERT INTO t_manga_t_genre(manga_id, genre_id) VALUES ('6a32e8b1-c397-4c72-ba97-bb50f30c6e4e', 12);

/*INSERT INTO t_user(id, email, password, username, full_name, date_of_birth, registration_date) VALUES ('e214c0db-196d-4653-8601-7784178fb6d5', '3048453@gmail.com', '11111', 'alex', 'Alexander', now(), now()),
                                                                                                      ('2c11cf89-0e4a-4f67-aa54-2d104d4eb6a8', '2048453@gmail.com', '11111', 'gleb', 'gleb', now(), now());

    INSERT INTO t_comment(id, manga_id, user_id, text, create_at, update_at, parent_id)
VALUES ('971e13b1-5fc2-48ec-8d53-1a57ef63e875', '73e27398-6e3c-42a3-ba21-9c4bcb29e13f', 'e214c0db-196d-4653-8601-7784178fb6d5', 'gleb loh', now(), now(), null),
    ('d6b85cb7-9bb5-4bf2-8c69-22b6fb09e298', '73e27398-6e3c-42a3-ba21-9c4bcb29e13f', '2c11cf89-0e4a-4f67-aa54-2d104d4eb6a8', 'sam loh', now(), now(), '971e13b1-5fc2-48ec-8d53-1a57ef63e875'),
    ('7e0a2d2d-4a8f-4919-8b8e-62da63d3275d', '73e27398-6e3c-42a3-ba21-9c4bcb29e13f', 'e214c0db-196d-4653-8601-7784178fb6d5', 'poshel nahui', now(), now(), '971e13b1-5fc2-48ec-8d53-1a57ef63e875'),
    ('5f20f5d7-5769-4a12-8e8a-09f1ef8a785d', '73e27398-6e3c-42a3-ba21-9c4bcb29e13f','e214c0db-196d-4653-8601-7784178fb6d5', 'and i want sayt that you are gay', now(), now(),'971e13b1-5fc2-48ec-8d53-1a57ef63e875'),
    ('87ec4a4c-3d85-4b1c-8b5e-b5c37a5d17a2', '73e27398-6e3c-42a3-ba21-9c4bcb29e13f', '2c11cf89-0e4a-4f67-aa54-2d104d4eb6a8', 'fuck you', now(), now(), '971e13b1-5fc2-48ec-8d53-1a57ef63e875'),
    ('a4cc0e37-2d75-4cc1-8469-ae5b235df7d6', '73e27398-6e3c-42a3-ba21-9c4bcb29e13f', 'e214c0db-196d-4653-8601-7784178fb6d5', 'fuck you', now(), now(), '971e13b1-5fc2-48ec-8d53-1a57ef63e875')*/