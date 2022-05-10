--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2 (Debian 14.2-1.pgdg110+1)
-- Dumped by pg_dump version 14.2 (Debian 14.2-1.pgdg110+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: address; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.address (
                                id bigint NOT NULL,
                                street character varying(255)
);


ALTER TABLE public.address OWNER TO "user";

--
-- Name: address_sequence; Type: SEQUENCE; Schema: public; Owner: user
--

CREATE SEQUENCE public.address_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.address_sequence OWNER TO "user";

--
-- Name: customer; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.customer (
                                 id bigint NOT NULL,
                                 credit_card character varying(255),
                                 hashed_password character varying(255),
                                 name character varying(255),
                                 phone character varying(255)
);


ALTER TABLE public.customer OWNER TO "user";

--
-- Name: customer_sequence; Type: SEQUENCE; Schema: public; Owner: user
--

CREATE SEQUENCE public.customer_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customer_sequence OWNER TO "user";

--
-- Name: customer_to_address; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.customer_to_address (
                                            customer_id bigint NOT NULL,
                                            address_id bigint NOT NULL
);


ALTER TABLE public.customer_to_address OWNER TO "user";

--
-- Name: menu; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.menu (
                             id bigint NOT NULL,
                             created_at timestamp without time zone,
                             last_updated_at timestamp without time zone,
                             valid_until timestamp without time zone
);


ALTER TABLE public.menu OWNER TO "user";

--
-- Name: menu_item; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.menu_item (
                                  id bigint NOT NULL,
                                  description character varying(255),
                                  price bigint
);


ALTER TABLE public.menu_item OWNER TO "user";

--
-- Name: menu_to_menu_items; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.menu_to_menu_items (
                                           menu_id bigint NOT NULL,
                                           menu_item_id bigint NOT NULL
);


ALTER TABLE public.menu_to_menu_items OWNER TO "user";

--
-- Name: product; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.product (
                                product_type integer NOT NULL,
                                id bigint NOT NULL,
                                description character varying(255),
                                price bigint NOT NULL,
                                quantity integer NOT NULL,
                                type integer,
                                name character varying(255)
);


ALTER TABLE public.product OWNER TO "user";

--
-- Name: product_sequence; Type: SEQUENCE; Schema: public; Owner: user
--

CREATE SEQUENCE public.product_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_sequence OWNER TO "user";

--
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.address (id, street) FROM stdin;
1	John's street
\.


--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.customer (id, credit_card, hashed_password, name, phone) FROM stdin;
1	\N	\N	John	012345678
\.


--
-- Data for Name: customer_to_address; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.customer_to_address (customer_id, address_id) FROM stdin;
1	1
\.


--
-- Data for Name: menu; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.menu (id, created_at, last_updated_at, valid_until) FROM stdin;
\.


--
-- Data for Name: menu_item; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.menu_item (id, description, price) FROM stdin;
\.


--
-- Data for Name: menu_to_menu_items; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.menu_to_menu_items (menu_id, menu_item_id) FROM stdin;
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.product (product_type, id, description, price, quantity, type, name) FROM stdin;
1	1	Papa Joe's Quattro Stagioni	10	25	1	\N
1	2	Papa Joe's Capriciosa	12	10	1	\N
2	3	The Classic Coke	2	50	\N	Coca Cola
\.


--
-- Name: address_sequence; Type: SEQUENCE SET; Schema: public; Owner: user
--

SELECT pg_catalog.setval('public.address_sequence', 1, true);


--
-- Name: customer_sequence; Type: SEQUENCE SET; Schema: public; Owner: user
--

SELECT pg_catalog.setval('public.customer_sequence', 1, true);


--
-- Name: product_sequence; Type: SEQUENCE SET; Schema: public; Owner: user
--

SELECT pg_catalog.setval('public.product_sequence', 3, true);


--
-- Name: address address_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);


--
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);


--
-- Name: menu_item menu_item_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.menu_item
    ADD CONSTRAINT menu_item_pkey PRIMARY KEY (id);


--
-- Name: menu menu_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.menu
    ADD CONSTRAINT menu_pkey PRIMARY KEY (id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: menu_to_menu_items fk3wmxt0jo42khntpmnshogx9rg; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.menu_to_menu_items
    ADD CONSTRAINT fk3wmxt0jo42khntpmnshogx9rg FOREIGN KEY (menu_id) REFERENCES public.menu_item(id);


--
-- Name: customer_to_address fkgr5jqsuyc1lh4focsdaeindmu; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.customer_to_address
    ADD CONSTRAINT fkgr5jqsuyc1lh4focsdaeindmu FOREIGN KEY (customer_id) REFERENCES public.customer(id);


--
-- Name: customer_to_address fkrjijm2neogv5ver0goqff95oe; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.customer_to_address
    ADD CONSTRAINT fkrjijm2neogv5ver0goqff95oe FOREIGN KEY (address_id) REFERENCES public.address(id);


--
-- Name: menu_to_menu_items fksii5dul1t7aq43i4dmj3bmdwm; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.menu_to_menu_items
    ADD CONSTRAINT fksii5dul1t7aq43i4dmj3bmdwm FOREIGN KEY (menu_item_id) REFERENCES public.menu(id);


--
-- PostgreSQL database dump complete
--

