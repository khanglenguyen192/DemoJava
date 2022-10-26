DROP SEQUENCE IF EXISTS seq_m_user_user_id;
CREATE SEQUENCE seq_m_user_user_id
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999999
    START 1
    CACHE 1
    NO CYCLE;
create or replace function user_id_generation() RETURNS text LANGUAGE plpgsql
  STRICT
as $function$
declare
  user_id_prefix text;
  seq_m_user_user_id integer;
begin
  user_id_prefix := 'K';
  seq_m_user_user_id := nextval('seq_m_user_user_id');
  return user_id_prefix || lpad(seq_m_user_user_id::text, 7, '0');
end
$function$;