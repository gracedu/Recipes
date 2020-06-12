CREATE TABLE recipes (
	recipe_id BIGSERIAL NOT NULL PRIMARY KEY,
	recipe_name varchar NOT NULL,
	ingredient varchar NOT NULL,
	description varchar NOT NULL,
	publisher varchar NOT NULL,
	cuisine varchar NOT NULL,
	creation_date date default CURRENT_DATE
);

CREATE TABLE users (
	user_id BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
	user_name varchar NOT NULL UNIQUE,
	email varchar NOT NULL,
	password varchar NOT NULL
);

ALTER TABLE recipes ADD CONSTRAINT recipes_user_fk FOREIGN KEY (publisher) REFERENCES users(user_name);

CREATE TABLE comments (
	content varchar NOT NULL,
	id BIGSERIAL REFERENCES recipes(recipe_id),
	user_name varchar REFERENCES users(user_name),
	comment_date date default CURRENT_DATE
);

