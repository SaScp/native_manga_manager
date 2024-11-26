CREATE TABLE IF NOT EXISTS t_chapter(
                                        chapter_id uuid PRIMARY KEY,
                                        manga_id VARCHAR(255) REFERENCES t_manga(id),
    c_name varchar(255) DEFAULT ''
    );
CREATE TABLE IF NOT EXISTS t_page(
                                     id BIGINT primary key,
                                     chapter_id uuid REFERENCES t_chapter(chapter_id),
                                     url varchar(255) NOT NULL
    );
