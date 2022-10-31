CREATE OR REPLACE FUNCTION random_between(low INT ,high INT)
   RETURNS INT AS
$$
BEGIN
   RETURN floor(random()* (high-low + 1) + low);
END;
$$ language 'plpgsql' STRICT;

INSERT INTO public.items(
	item_id, created_by, created_date, description, item_name, quantity, catalog_id)
SELECT *,
	'root@gmail.com',
	now(),
	substr(md5(random()::text), 1, 10),
   	substr(md5(random()::text), 1, 10),
	10,
    random_between(1,5)
FROM generate_series(1, 10000000);