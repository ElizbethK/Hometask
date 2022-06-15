CREATE TABLE public.customers (
                                  cust_id SERIAL PRIMARY KEY,
                                  cust_surname varchar(50),
                                  cust_district varchar(50),
                                  cust_discount integer
);

CREATE TABLE public.shop (
                             sh_id SERIAL PRIMARY KEY,
                             sh_name varchar(50),
                             sh_district varchar(50),
                             sh_commission integer
);

CREATE TABLE public.books (
                              book_id SERIAL PRIMARY KEY,
                              book_name varchar(50) NOT NULL,
                              book_price integer,
                              book_warehouse varchar(50) NOT NULL,
                              book_quantity integer NOT NULL
);

CREATE TABLE public.purchase (
                                 prch_id SERIAL PRIMARY KEY,
                                 prch_date date NOT NULL,
                                 sh_id integer REFERENCES shop(sh_id),
                                 cust_id integer REFERENCES customers(cust_id),
                                 book_id integer REFERENCES books(book_id),
                                 prch_quantity integer NOT NULL,
                                 prch_amount integer NOT NULL
);