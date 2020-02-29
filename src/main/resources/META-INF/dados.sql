
INSERT INTO public.perfil VALUES (1, 'Sem Permissão');
INSERT INTO public.perfil VALUES (2, 'Administrador');
INSERT INTO public.perfil VALUES (3, 'Suporte');


INSERT INTO public.cadastroacao VALUES (1, false, false, 'USUARIO', false, false, 1);
INSERT INTO public.cadastroacao VALUES (2, false, false, 'PERFIL', false, false, 1);
INSERT INTO public.cadastroacao VALUES (3, true, true, 'USUARIO', true, true, 2);
INSERT INTO public.cadastroacao VALUES (4, true, true, 'PERFIL', true, true, 2);
INSERT INTO public.cadastroacao VALUES (5, true, true, 'USUARIO', true, true, 3);
INSERT INTO public.cadastroacao VALUES (6, true, true, 'PERFIL', true, true, 3);

INSERT INTO public.relatorioacao VALUES (1, 'USUARIO', false, 1);
INSERT INTO public.relatorioacao VALUES (2, 'PERFIL', false, 1);
INSERT INTO public.relatorioacao VALUES (3, 'USUARIO', true, 2);
INSERT INTO public.relatorioacao VALUES (4, 'PERFIL', true, 2);
INSERT INTO public.relatorioacao VALUES (5, 'USUARIO', true, 3);
INSERT INTO public.relatorioacao VALUES (6, 'PERFIL', true, 3);


INSERT INTO public.usuario VALUES (1, true, false, 'suporte@gmail.com', 'suporte', 'Suporte', 'b49228b8fa98840355de71ec58f2d714', NULL, 3);
INSERT INTO public.usuario VALUES (2, true, false, 'admin@gmail.com', 'admin', 'Administrador', '21232f297a57a5a743894a0e4a801fc3', NULL, 2);

SELECT pg_catalog.setval('public.cadastroacao_id_seq', 6, true);

SELECT pg_catalog.setval('public.perfil_id_seq', 3, true);

SELECT pg_catalog.setval('public.relatorioacao_id_seq', 6, true);

SELECT pg_catalog.setval('public.usuario_id_seq', 2, true);
