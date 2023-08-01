create table ingredient
(
    name varchar(50) not null
        primary key
);

alter table ingredient
    owner to postgres;

create table person
(
    username varchar(50) not null
        primary key,
    email    varchar(50),
    password varchar(50)
);

alter table person
    owner to postgres;

create table recipe
(
    recipeid    serial
        primary key,
    username    varchar(50)
        references person
            on delete cascade,
    title       varchar(100),
    description varchar(25000)
);

alter table recipe
    owner to postgres;

create table rates
(
    recipeid integer     not null
        references recipe
            on delete cascade,
    username varchar(50) not null
        references person
            on delete cascade,
    rate     integer,
    primary key (recipeid, username)
);

alter table rates
    owner to postgres;

create table favourites
(
    recipeid integer     not null
        references recipe
            on delete cascade,
    username varchar(50) not null
        references person
            on delete cascade,
    primary key (recipeid, username)
);

alter table favourites
    owner to postgres;

create table ingredientsinrecipe
(
    recipeid        integer     not null
        references recipe
            on delete cascade,
    ingredient_name varchar(50) not null
        references ingredient,
    amount          numeric(3),
    amount_type     varchar(50),
    primary key (recipeid, ingredient_name)
);

alter table ingredientsinrecipe
    owner to postgres;

create function delete() returns trigger
    language plpgsql
as
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

alter function delete() owner to postgres;

create trigger delete_ingredient
    after delete
    on ingredientsinrecipe
    for each row
execute procedure delete();


