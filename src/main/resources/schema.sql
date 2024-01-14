CREATE TABLE IF NOT EXISTS t_genre
(
    id    SERIAL primary key,
    genre VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS t_role
(
    id     SERIAL primary key,
    c_role VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS t_type
(
    id     SERIAL primary key,
    c_type VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS t_user
(
    id                VARCHAR(255) primary key,
    email             VARCHAR(255) UNIQUE NOT NULL,
    password          VARCHAR(255)        NOT NULL,
    username          VARCHAR(255)        NOT NULL,
    full_name         VARCHAR(255)        NOT NULL,
    date_of_birth     DATE                NOT NULL,
    registration_date DATE                NOT NULL
);

CREATE TABLE IF NOT EXISTS t_manga
(
    id             VARCHAR(255) primary key,
    main_name      VARCHAR(255)                   NOT NULL,
    secondary_name VARCHAR(255)                   NOT NULL,
    issue_year     INT                            NOT NULL,
    avg_rating     NUMERIC(10, 2)                 NOT NULL,
    c_type         INTEGER REFERENCES t_type (id) not null,
    is_yaoi        BOOLEAN                        NOT NULL,
    is_erotic      BOOLEAN                        NOT NULL,
    img            VARCHAR(255)                   NOT NULL,
    rus_name       VARCHAR(255)                   NOT NULL,
    en_name        VARCHAR(255)                   NOT NULL,
    count_chapters INTEGER                        NOT NULL
);
CREATE TABLE IF NOT EXISTS t_contact
(
    id           VARCHAR(255) primary key,
    number_phone VARCHAR(255) NOT NULL,
    description  TEXT         NOT NULL
);
CREATE TABLE IF NOT EXISTS t_user_t_role
(
    user_id VARCHAR(255) REFERENCES t_user (id),
    role_id INT REFERENCES t_role (id),
    PRIMARY KEY (user_id, role_id)
);
CREATE TABLE IF NOT EXISTS t_manga_t_genre
(
    manga_id VARCHAR(255) REFERENCES t_manga (id),
    genre_id INT REFERENCES t_genre (id),
    PRIMARY KEY (manga_id, genre_id)
);
CREATE TABLE IF NOT EXISTS t_user_t_manga
(
    user_id  VARCHAR(255) REFERENCES t_user (id),
    manga_id VARCHAR(255) REFERENCES t_manga (id),
    PRIMARY KEY (user_id, manga_id)
);
CREATE TABLE IF NOT EXISTS t_logout
(
    id           VARCHAR(255) PRIMARY KEY,
    c_keep_until timestamp not null check ( c_keep_until > now() )
);


