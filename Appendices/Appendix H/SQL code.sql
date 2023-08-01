CREATE SCHEMA RecipeDatabase;

SET SCHEMA 'recipedatabase';

CREATE TABLE Ingredient(
    name varchar(50) primary key
);

CREATE TABLE Person(
    username varchar(50) primary key,
    email varchar(50),
    password varchar(50)
);

CREATE TABLE Recipe(
    recipeId serial primary key,
    username varchar(50),
    title varchar(100),
    description varchar(25000),
    FOREIGN KEY (username) references person(username) ON DELETE CASCADE
);

CREATE TABLE IngredientsInRecipe(
    recipeId integer,
    ingredient_name varchar(50),
    amount decimal(3),
    amount_type varchar(50),
    PRIMARY KEY (recipeId, ingredient_name),
    FOREIGN KEY (recipeId) references Recipe(recipeId) ON DELETE CASCADE,
    FOREIGN KEY (ingredient_name) references Ingredient(name)
);

CREATE TABLE Rates(
    recipeId integer,
    username varchar(50),
    rate integer,
    PRIMARY KEY (recipeId, username),
    FOREIGN KEY (recipeId) references Recipe(recipeId) ON DELETE CASCADE,
    FOREIGN KEY (username) references Person(username) ON DELETE CASCADE
);

CREATE TABLE Favourites(
    recipeId integer,
    username varchar(50),
    PRIMARY KEY (recipeId, username),
    FOREIGN KEY (recipeId) references Recipe(recipeId) ON DELETE CASCADE,
    FOREIGN KEY (username) references Person(username) ON DELETE CASCADE
);

CREATE FUNCTION delete()
RETURNS TRIGGER
LANGUAGE plpgsql
AS
    $$
    DECLARE
        row_count INTEGER;
    BEGIN
        SELECT count(*) INTO row_count
        FROM ingredientsinrecipe
        WHERE old.ingredient_name = ingredient_name;
        IF row_count = 0 THEN
            DELETE FROM ingredient
            WHERE name = old.ingredient_name;
        end if;
        RETURN old;
    END;
    $$;

CREATE TRIGGER delete_ingredient
    AFTER DELETE
    ON ingredientsinrecipe
    FOR EACH ROW
    EXECUTE FUNCTION delete();

Insert Into Person (username, email, password) VALUES ('Administrator', 'email@via.dk', 'Password123');