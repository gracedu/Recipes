CREATE TABLE images (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR,
    url VARCHAR,
    extension VARCHAR,
    uuid VARCHAR,
    recipe_id BIGSERIAL
);

ALTER TABLE images ADD CONSTRAINT image_recipe_fk FOREIGN KEY (recipe_id) REFERENCES recipes(id);