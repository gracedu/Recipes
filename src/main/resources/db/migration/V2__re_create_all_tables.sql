CREATE TABLE recipes (
	id BIGSERIAL PRIMARY KEY,
	name varchar NOT NULL,
	ingredient varchar NOT NULL,
	description varchar NOT NULL,
	publisher varchar NOT NULL,
	cuisine varchar NOT NULL,
	creation_date date default CURRENT_DATE
);

CREATE TABLE users (
	id BIGSERIAL PRIMARY KEY,
	name varchar NOT NULL UNIQUE,
	email varchar NOT NULL,
	password varchar NOT NULL
);

ALTER TABLE recipes ADD CONSTRAINT recipe_user_fk FOREIGN KEY (publisher) REFERENCES users(name);

CREATE TABLE comments (
    id BIGSERIAL PRIMARY KEY,
	content varchar NOT NULL,
	comment_date date default CURRENT_DATE,
	user_id BIGSERIAL NOT NULL,
	recipe_id BIGSERIAL NOT NULL
);

ALTER TABLE comments ADD CONSTRAINT comment_user_fk FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE comments ADD CONSTRAINT comment_recipe_fk FOREIGN KEY (recipe_id) REFERENCES recipes(id);