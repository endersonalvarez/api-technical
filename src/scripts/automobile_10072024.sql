--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

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
-- Name: brand; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.brand (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    name character varying(255),
    status integer,
    updated_at timestamp without time zone
);


--
-- Name: brand_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.brand_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: brand_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.brand_id_seq OWNED BY public.brand.id;


--
-- Name: model; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.model (
    id bigint NOT NULL,
    brand_id bigint,
    created_at timestamp without time zone,
    name character varying(255),
    status integer,
    updated_at timestamp without time zone
);


--
-- Name: model_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.model_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: model_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.model_id_seq OWNED BY public.model.id;


--
-- Name: model_view; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.model_view AS
 SELECT m.id,
    b.name AS name_brand,
    m.name AS name_model,
    m.status
   FROM (public.model m
     JOIN public.brand b ON ((b.id = m.brand_id)))
  WHERE (m.status = 1);


--
-- Name: vehicle; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.vehicle (
    id bigint NOT NULL,
    annio character varying(255),
    color character varying(255) NOT NULL,
    created_at timestamp without time zone NOT NULL,
    model_id bigint NOT NULL,
    status integer,
    tuition character varying(255) NOT NULL,
    updated_at timestamp without time zone
);


--
-- Name: vehicle_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.vehicle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: vehicle_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.vehicle_id_seq OWNED BY public.vehicle.id;


--
-- Name: vehicle_view; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.vehicle_view AS
 SELECT v.id,
    v.tuition,
    v.annio,
    v.color,
    v.created_at,
    v.updated_at,
    v.status,
    b.name AS name_brand,
    m.name AS name_model
   FROM ((public.vehicle v
     JOIN public.model m ON ((m.id = v.model_id)))
     JOIN public.brand b ON ((b.id = m.brand_id)));


--
-- Name: brand id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.brand ALTER COLUMN id SET DEFAULT nextval('public.brand_id_seq'::regclass);


--
-- Name: model id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.model ALTER COLUMN id SET DEFAULT nextval('public.model_id_seq'::regclass);


--
-- Name: vehicle id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.vehicle ALTER COLUMN id SET DEFAULT nextval('public.vehicle_id_seq'::regclass);


--
-- Data for Name: brand; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.brand (id, created_at, name, status, updated_at) FROM stdin;
1	\N	Toyota	1	\N
2	\N	Honda	1	\N
3	\N	Ford	1	\N
4	\N	Chevrolet	1	\N
\.


--
-- Data for Name: model; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.model (id, brand_id, created_at, name, status, updated_at) FROM stdin;
103	1	\N	Toyota Corolla	1	\N
104	1	\N	Toyota Camry	1	\N
105	1	\N	Toyota RAV4	1	\N
106	1	\N	Toyota Highlander	1	\N
107	1	\N	Toyota Tacoma	1	\N
108	1	\N	Toyota Sienna	1	\N
109	1	\N	Toyota 4Runner	1	\N
110	1	\N	Toyota Tundra	1	\N
111	1	\N	Toyota Avalon	1	\N
112	1	\N	Toyota Prius	1	\N
113	1	\N	Toyota Yaris	1	\N
114	1	\N	Toyota Sequoia	1	\N
115	1	\N	Toyota Land Cruiser	1	\N
116	1	\N	Toyota 86	1	\N
117	1	\N	Toyota Mirai	1	\N
118	1	\N	Toyota Supra	1	\N
119	1	\N	Toyota Venza	1	\N
120	1	\N	Toyota C-HR	1	\N
121	1	\N	Toyota Sienna Hybrid	1	\N
122	1	\N	Toyota Prius Prime	1	\N
123	2	\N	Honda Civic	1	\N
124	2	\N	Honda Accord	1	\N
125	2	\N	Honda CR-V	1	\N
126	2	\N	Honda Pilot	1	\N
127	2	\N	Honda Odyssey	1	\N
128	2	\N	Honda Ridgeline	1	\N
129	2	\N	Honda HR-V	1	\N
130	2	\N	Honda Passport	1	\N
131	2	\N	Honda Insight	1	\N
132	2	\N	Honda Clarity	1	\N
133	2	\N	Honda Fit	1	\N
134	2	\N	Honda Prelude	1	\N
135	2	\N	Honda S2000	1	\N
136	2	\N	Honda Element	1	\N
137	2	\N	Honda Crosstour	1	\N
138	2	\N	Honda Ascent	1	\N
139	2	\N	Honda Pilot Hybrid	1	\N
140	2	\N	Honda CR-V Hybrid	1	\N
141	2	\N	Honda Accord Hybrid	1	\N
142	2	\N	Honda Insight Hybrid	1	\N
143	3	\N	Ford F-150	1	\N
144	3	\N	Ford Mustang	1	\N
145	3	\N	Ford Explorer	1	\N
146	3	\N	Ford Escape	1	\N
147	3	\N	Ford Expedition	1	\N
148	3	\N	Ford Super Duty	1	\N
149	3	\N	Ford Transit	1	\N
150	3	\N	Ford Ranger	1	\N
151	3	\N	Ford Maverick	1	\N
152	3	\N	Ford Bronco	1	\N
153	3	\N	Ford Fusion	1	\N
154	3	\N	Ford Taurus	1	\N
155	3	\N	Ford Focus	1	\N
156	3	\N	Ford Fiesta	1	\N
157	3	\N	Ford EcoSport	1	\N
158	3	\N	Ford Flex	1	\N
159	3	\N	Ford Transit Connect	1	\N
160	3	\N	Ford Mustang Mach-E	1	\N
161	3	\N	Ford E-Transit	1	\N
162	3	\N	Ford F-150 Lightning	1	\N
163	4	\N	Chevrolet Blazer	1	\N
164	4	\N	Chevrolet Blazer EV	1	\N
165	4	\N	Chevrolet Bolt EUV	1	\N
166	4	\N	Chevrolet Bolt EV	1	\N
167	4	\N	Chevrolet Camaro	1	\N
168	4	\N	Chevrolet Colorado	1	\N
169	4	\N	Chevrolet Corvette	1	\N
170	4	\N	Chevrolet Corvette E-Ray	1	\N
171	4	\N	Chevrolet Equinox	1	\N
172	4	\N	Chevrolet Equinox EV	1	\N
173	4	\N	Chevrolet Express 2500	1	\N
174	4	\N	Chevrolet Express 3500	1	\N
175	4	\N	Chevrolet Malibu	1	\N
176	4	\N	Chevrolet Silverado 1500	1	\N
177	4	\N	Chevrolet Silverado 1500 Limited	1	\N
178	4	\N	Chevrolet Silverado 2500	1	\N
179	4	\N	Chevrolet Silverado 3500	1	\N
180	4	\N	Chevrolet Silverado EV	1	\N
181	4	\N	Chevrolet Spark	1	\N
182	4	\N	Chevrolet Suburban	1	\N
\.


--
-- Data for Name: vehicle; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.vehicle (id, annio, color, created_at, model_id, status, tuition, updated_at) FROM stdin;
47	2007	green	2024-07-10 12:00:38.137	1	1	ADSF	2024-07-10 12:18:08.974
48	2009	purple	2024-07-10 12:04:50.36	7	1	AADSS	\N
49	2024	red	2024-07-10 13:49:50.621	106	1	XASDK2	2024-07-10 13:50:25.644
51	2011	orange	2024-07-10 13:52:21.876	108	1	sdsfks	2024-07-10 13:51:09.256
50	2014	purple	2024-07-10 13:50:43.358	105	0	DSDII12K	2024-07-10 13:53:01.445
\.


--
-- Name: brand_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.brand_id_seq', 41, true);


--
-- Name: model_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.model_id_seq', 182, true);


--
-- Name: vehicle_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.vehicle_id_seq', 51, true);


--
-- Name: brand brand_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.brand
    ADD CONSTRAINT brand_pkey PRIMARY KEY (id);


--
-- Name: model model_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.model
    ADD CONSTRAINT model_pkey PRIMARY KEY (id);


--
-- Name: vehicle vehicle_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.vehicle
    ADD CONSTRAINT vehicle_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

