create TABLE tb_articles (id SERIAL PRIMARY KEY , title VARCHAR , category_id INTEGER , author VARCHAR , description VARCHAR , thumbnail VARCHAR ,status INTEGER  );
CREATE TABLE tb_categories (id SERIAL PRIMARY KEY , name VARCHAR , status INTEGER);
