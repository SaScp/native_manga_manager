CREATE TABLE IF NOT EXISTS t_chapter(
                                        chapter_id uuid PRIMARY KEY,
                                        manga_id VARCHAR(255) REFERENCES t_manga(id),
    chapter_url VARCHAR(255) NOT NULL
    );
