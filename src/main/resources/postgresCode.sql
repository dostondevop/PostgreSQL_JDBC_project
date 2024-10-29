CREATE OR REPLACE FUNCTION adduser(
    p_name character varying,
    p_username character varying,
    p_password character varying
)
    RETURNS TABLE(
                     r_id integer,
                     r_name varchar,
                     r_username varchar,
                     r_created_date timestamp,
                     r_updated_date timestamp,
                     r_active boolean
                 )
    LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
    INSERT INTO users_exam (name, username, password)
            VALUES (p_name, p_username, p_password)
            RETURNING id, name, username, created_date, updated_date, active;
END;
$$;

create function addcard(p_owner_id integer, p_card_number character varying, p_card_name character varying, p_card_expiry_date character varying, p_card_cvv integer)
    returns TABLE(id integer, owner_id integer, card_number character varying, card_name character varying, card_expiry_date character varying, created_date timestamp without time zone, updated_date timestamp without time zone, active boolean)
    language plpgsql
as
$$
BEGIN
RETURN QUERY
    INSERT INTO card (
                          owner_id,
                          card_number,
                          card_name,
                          card_expiry_date,
                          card_cvv
            ) VALUES (
                         p_owner_id,
                         p_card_number,
                         p_card_name,
                         p_card_expiry_date,
                         p_card_cvv
                     )
            RETURNING
                card.id,
                card.owner_id,
                card.card_number,
                card.card_name,
                card.card_expiry_date,
                card.created_date,
                card.updated_date,
                card.active;
END;
$$;

CREATE OR REPLACE FUNCTION deletecard(p_card_id integer)
    RETURNS TABLE(
                     id integer,
                     owner_id integer,
                     card_number varchar,
                     card_name varchar,
                     card_expiry_date varchar,
                     created_date timestamp,
                     updated_date timestamp,
                     active boolean
                 )
    LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY
        UPDATE card c
            SET active = false
            WHERE c.id = p_card_id
            RETURNING
                c.id,
                c.owner_id,
                c.card_number,
                c.card_name,
                c.card_expiry_date,
                c.created_date,
                c.updated_date,
                c.active;
END;
$$;

create or replace function getcards(p_owner_id integer)
    returns TABLE(id integer, owner_id integer, card_number character varying, card_name character varying, card_expiry_date character varying, created_date timestamp without time zone, updated_date timestamp without time zone, active boolean)
    language plpgsql
as
$$
begin
    return query
        select c.id, c.owner_id, c.card_number, c.card_name, c.card_expiry_date, c.created_date, c.updated_date, c.active
        from card c where c.owner_id = p_owner_id
        order by created_date desc;
end;
$$;

CREATE FUNCTION getuserbyid(p_id integer)
    RETURNS TABLE(
                     r_id integer,
                     r_name varchar,
                     r_username varchar,
                     r_created_date timestamp,
                     r_updated_date timestamp,
                     r_active boolean
                 )
    LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY
        SELECT id, name, username, created_date, updated_date, active
        FROM users_exam
        WHERE id = p_id;
END;
$$;

create function getusers()
    returns TABLE(r_id integer,
                  r_name varchar,
                  r_username varchar,
                  r_created_date timestamp,
                  r_updated_date timestamp,
                  r_active boolean)
    language plpgsql
as
$$
begin
    return query
        select id, name, username, created_date, updated_date, active
        from users_exam;
end;
$$;

create or replace function signin(p_username character varying, p_password character varying) returns boolean
    language plpgsql
as
$$
declare
    v_active boolean;
begin
    select active
    into v_active
    from users_exam
    where username ilike p_username and password = p_password;

    return coalesce(v_active, false);
end;
$$;