INSERT INTO roles (id, name)
VALUES

    (1, 'ROLE_USER'),
    (2, 'ROLE_DOCTOR'),
    (3, 'ROLE_NURSE');

INSERT INTO users (id, username, first_name, last_name, email, password, pesel)
VALUES
    ('ebb6dede-9cef-40db-97ee-b83fa720e262', 'kusmi', 'Janek', 'Kowalski', 'jankowalski@gmail.com', '$2a$10$FIU4yo53FAi1PM6yahouLOCUwYAhXrLM8Lay2abPH0tu5jfhhcCnO', '9801234568'),
    ('bbcadb41-a42f-4dfc-9f06-ce61dc853a75', 'kusmi2', 'Szymek', 'Kowa', 'jankowalski2@gmail.com', '$2a$10$FIU4yo53FAi1PM6yahouLOCUwYAhXrLM8Lay2abPH0tu5jfhhcCnO', '9701284568'),
    ('ac763672-ffd0-4162-92f1-49fa1768beab', 'lekarz', 'Robert', 'Wiśniewski', 'lekarz@gmail.com', '$2a$10$FIU4yo53FAi1PM6yahouLOCUwYAhXrLM8Lay2abPH0tu5jfhhcCnO', '9908234768'),
    ('1803befb-f480-4d28-89c5-73bade5d2fed', 'pielegniarka', 'Anna', 'Szymczyk', 'pielegniarka@gmail.com', '$2a$10$FIU4yo53FAi1PM6yahouLOCUwYAhXrLM8Lay2abPH0tu5jfhhcCnO','9501234778');
/*
login:username
Haslo: Haslo1234!
*/

INSERT INTO user_roles (role_id, user_id)
VALUES
    (1,'ebb6dede-9cef-40db-97ee-b83fa720e262'),
    (1,'bbcadb41-a42f-4dfc-9f06-ce61dc853a75'),
    (2,'ac763672-ffd0-4162-92f1-49fa1768beab'),
    (3,'1803befb-f480-4d28-89c5-73bade5d2fed');

INSERT INTO user_account(user_id, balance)
VALUES
    ('ebb6dede-9cef-40db-97ee-b83fa720e262',111),
    ('bbcadb41-a42f-4dfc-9f06-ce61dc853a75',222),
    ('7349908d-ffd6-41de-b83a-e029805c1359',222),
    ('1803befb-f480-4d28-89c5-73bade5d2fed',222);


INSERT INTO _doctor (doctor_id, name, surname)
VALUES
    ('4c25b7bd-2f0f-4f62-b0fb-2e6473119a58', 'Jan', 'Kowalski'),
    ('b7b76322-8b07-497f-8337-03794010acbb', 'Anna', 'Nowak'),
    ('ac763672-ffd0-4162-92f1-49fa1768beab', 'Robert', 'Wiśniewski'),
    ('4d61e70f-c58f-4d19-a57b-2ed8eae3405e', 'Emilia', 'Dąbrowska'),
    ('d1289922-0118-40c6-8e2a-add01abdb419', 'Michał', 'Lewandowski'),
    ('c595959e-c1bf-403c-b632-d9f1573bc599', 'Olga', 'Wójcik'),
    ('775f5d25-ef42-4722-a6d6-9a4bd9a57aca', 'Wojciech', 'Kowalczyk'),
    ('61d3d391-82b0-4f72-930a-6daef9e85fa2', 'Zofia', 'Kamińska'),
    ('5281d26a-2f1b-4282-8113-3b6e5a700bf5', 'Jakub', 'Zieliński'),
    ('590511da-af87-4f13-b516-34f28cd8e613', 'Karolina', 'Szymańska'),
    ('246b7e23-f395-42c0-a821-ceef0c54344b', 'Bartłomiej', 'Woźniak'),
    ('4cbb5bf7-d7df-4e61-9704-657d14077938', 'Anna', 'Kowal'),
    ('5801b561-3816-4707-9208-bab4532e0923', 'Eryk', 'Harris'),
    ('e33eaa8c-77b8-4a0d-9403-67dafaf4a29f', 'Izabela', 'Mazur'),
    ('bb758cb4-eec9-45d7-8bcd-eec3739f8675', 'Mateusz', 'Nowacki'),
    ('013db291-f44e-4570-8e77-8ad6aef21b40', 'Amelia', 'Kowalewska'),
    ('c36d2269-8ec5-405a-96ba-8caea61951b0', 'Filip', 'Wójcik'),
    ('0e210261-751b-4a2d-9d39-e5ad3f6d0fa1', 'Maja', 'Turner'),
    ('6bd6a070-b58a-4ff9-9905-0c059a03a3d4', 'Norbert', 'Piotrowski'),
    ('c186f128-7954-4981-a66e-e87618763e71', 'Alicja', 'Evans');

INSERT INTO _specialization (specialization_id, name)
VALUES
    (1, 'Chirurg'),
    (2, 'Kardiolog'),
    (3, 'Dermatolog'),
    (4, 'Pediatra'),
    (5, 'Ortopeda'),
    (6, 'Ginekolog'),
    (7, 'Neurolog'),
    (8, 'Okulista'),
    (9, 'Psychiatra'),
    (10, 'Urolog'),
    (11, 'Internista');


INSERT INTO doctor_specialization (doctor_id, specialization_id)
VALUES
    ('4c25b7bd-2f0f-4f62-b0fb-2e6473119a58', 1),
    ('b7b76322-8b07-497f-8337-03794010acbb', 2),
    ('ac763672-ffd0-4162-92f1-49fa1768beab', 3),
    ('4d61e70f-c58f-4d19-a57b-2ed8eae3405e', 4),
    ('d1289922-0118-40c6-8e2a-add01abdb419', 5),
    ('c595959e-c1bf-403c-b632-d9f1573bc599', 6),
    ('775f5d25-ef42-4722-a6d6-9a4bd9a57aca', 7),
    ('61d3d391-82b0-4f72-930a-6daef9e85fa2', 8),
    ('5281d26a-2f1b-4282-8113-3b6e5a700bf5', 9),
    ('590511da-af87-4f13-b516-34f28cd8e613', 10),
    ('246b7e23-f395-42c0-a821-ceef0c54344b', 11),
    ('4cbb5bf7-d7df-4e61-9704-657d14077938', 1),
    ('5801b561-3816-4707-9208-bab4532e0923', 2),
    ('e33eaa8c-77b8-4a0d-9403-67dafaf4a29f', 3),
    ('bb758cb4-eec9-45d7-8bcd-eec3739f8675', 4),
    ('013db291-f44e-4570-8e77-8ad6aef21b40', 5),
    ('c36d2269-8ec5-405a-96ba-8caea61951b0', 6),
    ('0e210261-751b-4a2d-9d39-e5ad3f6d0fa1', 7),
    ('6bd6a070-b58a-4ff9-9905-0c059a03a3d4', 8),
    ('c186f128-7954-4981-a66e-e87618763e71', 9);




INSERT INTO medical_clinic (medical_clinic_id, city, house_no, name, postal_code, street)
VALUES
    ('1','Warszawa','125','Medical','04-100','Puławska'),
    ('2','Warszawa','89','Przychodnia','04-123','Piękna'),
    ('3','Puławy','17','Zdrowie','01-080','Krótka');

INSERT INTO _visit (id, available, hour, price, visit_date, doctor_id, user_account_id, medical_clinic_id)
VALUES
    ('d7cec8c7-8545-4b0c-9a94-1100b65e1d8e', false, '10:00', 50.0, '2024-12-16 10:00', '4c25b7bd-2f0f-4f62-b0fb-2e6473119a58', 'ebb6dede-9cef-40db-97ee-b83fa720e262', 1),
    ('44fd03f8-9a86-4f35-be47-fe79bf4c58b4', false, '14:30', 75.0, '2024-12-17 14:30', '4c25b7bd-2f0f-4f62-b0fb-2e6473119a58', 'ebb6dede-9cef-40db-97ee-b83fa720e262', 1),
    ('f52fe66e-90ef-4bb5-a58f-d9c22a505bad', false, '11:15', 100.0, '2024-12-18 11:15', '4c25b7bd-2f0f-4f62-b0fb-2e6473119a58', 'ebb6dede-9cef-40db-97ee-b83fa720e262', 1),
    ('b947f415-f04c-4931-abd7-f729c94eef9f', false, '09:30', 50.0, '2024-12-19 09:30', '4c25b7bd-2f0f-4f62-b0fb-2e6473119a58', 'ebb6dede-9cef-40db-97ee-b83fa720e262', 1),
    ('21ff0507-affe-460d-a295-49949f47c674', false, '16:45', 75.0, '2024-01-20 16:45', 'ac763672-ffd0-4162-92f1-49fa1768beab', 'ebb6dede-9cef-40db-97ee-b83fa720e262', 1),
    ('fc80fb76-54ed-4ec4-aa23-d4debf7b6454', false, '13:00', 100.0, '2024-01-21 13:00', 'ac763672-ffd0-4162-92f1-49fa1768beab', 'bbcadb41-a42f-4dfc-9f06-ce61dc853a75', 1),
    ('68c3385b-619d-462b-9a55-0d6a7e517fdb', false, '10:30', 50.0, '2024-01-22 10:30', 'ac763672-ffd0-4162-92f1-49fa1768beab', 'bbcadb41-a42f-4dfc-9f06-ce61dc853a75', 1),
    ('312a72a9-d114-4db8-854e-9892cb166f4d', false, '15:15', 75.0, '2024-01-23 15:15', 'ac763672-ffd0-4162-92f1-49fa1768beab', 'bbcadb41-a42f-4dfc-9f06-ce61dc853a75', 1),
    ('c46560ff-51c8-4b7e-b3a4-ac92fe81ca57', false, '12:00', 100.0, '2023-11-24 12:00', 'ac763672-ffd0-4162-92f1-49fa1768beab', 'ebb6dede-9cef-40db-97ee-b83fa720e262', 1),
    ('c60f9cdc-b88e-42ae-853b-1e258d9c0b5e', false, '09:45', 50.0, '2023-11-25 09:45', 'ac763672-ffd0-4162-92f1-49fa1768beab', 'ebb6dede-9cef-40db-97ee-b83fa720e262', 2),
    ('ce190c1c-328d-4341-9fdf-19e70d05e121', false, '17:30', 75.0, '2023-11-26 17:30', 'ac763672-ffd0-4162-92f1-49fa1768beab', 'ebb6dede-9cef-40db-97ee-b83fa720e262', 2),
    ('ad142669-21fe-412b-8a3d-90c995c35d1e', false, '14:15', 100.0, '2023-11-27 14:15', 'ac763672-ffd0-4162-92f1-49fa1768beab', 'ebb6dede-9cef-40db-97ee-b83fa720e262', 2),
    ('ae1cad58-3f97-4a9d-be41-f7a4078d40bb', false, '11:45', 50.0, '2023-11-28 11:45', 'ac763672-ffd0-4162-92f1-49fa1768beab', 'ebb6dede-9cef-40db-97ee-b83fa720e262', 2),
    ('cb03451c-45bf-49e5-be78-f8ab1e6d7e35', false, '16:00', 75.0, '2023-11-29 16:00', 'ac763672-ffd0-4162-92f1-49fa1768beab', 'ebb6dede-9cef-40db-97ee-b83fa720e262', 2),
    ('ade66e96-fdc7-4981-88d1-890c3feae35c', false, '12:45', 100.0, '2023-11-30 12:45', '4c25b7bd-2f0f-4f62-b0fb-2e6473119a58', 'bbcadb41-a42f-4dfc-9f06-ce61dc853a75', 2),
    ('fc03cfca-a015-45ab-b7eb-e35b69ccdd41', false, '12:00', 100.0, '2023-12-24'::date, '4c25b7bd-2f0f-4f62-b0fb-2e6473119a58', 'bbcadb41-a42f-4dfc-9f06-ce61dc853a75', 2),
    ('65b9c8cb-e112-45f6-a1b5-dc35d3301773', false, '09:45', 50.0, '2023-12-24'::date, 'd1289922-0118-40c6-8e2a-add01abdb419', 'bbcadb41-a42f-4dfc-9f06-ce61dc853a75', 2),
    ('e0fa1a3e-79ff-4c36-87bc-fe4fdb8222fb', false, '17:30', 75.0, '2023-12-25'::date, 'd1289922-0118-40c6-8e2a-add01abdb419', 'bbcadb41-a42f-4dfc-9f06-ce61dc853a75', 2),
    ('e4fbcaf2-fb5e-4d19-b0a0-8e799b094a9c', false, '14:15', 100.0, '2023-12-26'::date, 'd1289922-0118-40c6-8e2a-add01abdb419', 'ebb6dede-9cef-40db-97ee-b83fa720e262', 2),
    ('73f7bccb-09d7-40cb-9d7f-97924ac82f39', false, '11:45', 50.0, '2023-12-27'::date, 'd1289922-0118-40c6-8e2a-add01abdb419', 'ebb6dede-9cef-40db-97ee-b83fa720e262', 2),
    ('20191085-cff5-4213-9c1a-c505a1910daf', false, '16:00', 75.0, '2023-12-28'::date, 'd1289922-0118-40c6-8e2a-add01abdb419', 'ebb6dede-9cef-40db-97ee-b83fa720e262', 3),
    ('8144f92c-264d-428c-b8a9-301df8e8048e', false, '12:45', 100.0, '2023-12-29'::date, 'ac763672-ffd0-4162-92f1-49fa1768beab', 'bbcadb41-a42f-4dfc-9f06-ce61dc853a75', 3),
    ('1a55eafb-8e09-433d-b5a3-7ab4f51915b6', false, '10:15', 50.0, '2023-12-30'::date, 'ac763672-ffd0-4162-92f1-49fa1768beab', 'bbcadb41-a42f-4dfc-9f06-ce61dc853a75', 3),
    ('13b75863-5f65-4dfd-af99-cc2c13c0b943', false, '14:45', 75.0, '2023-12-31'::date, 'ac763672-ffd0-4162-92f1-49fa1768beab', 'bbcadb41-a42f-4dfc-9f06-ce61dc853a75', 3),
    ('52c9efab-c4dd-4ee4-bebc-26079685c1cf', true, '11:00', 100.0, '2024-01-01'::date, 'ac763672-ffd0-4162-92f1-49fa1768beab', 'ebb6dede-9cef-40db-97ee-b83fa720e262', 3),
    ('eeaf1558-ff5e-43a5-b7ac-ec16e7ca3e05', true, '15:30', 50.0, '2024-01-02'::date, '4c25b7bd-2f0f-4f62-b0fb-2e6473119a58', null, 3),
    ('999900cf-31cc-4caf-be9f-6286ac997022', true, '12:30', 75.0, '2024-01-03'::date, 'd1289922-0118-40c6-8e2a-add01abdb419', null, 3),
    ('89ad4176-b370-467f-9c1c-3c43bed14c2b', true, '09:00', 100.0, '2024-01-04'::date, 'ac763672-ffd0-4162-92f1-49fa1768beab', null, 3),
    ('f19770bb-1963-4a93-8c7e-9fec5358f03c', true, '13:15', 50.0, '2024-01-05'::date, 'ac763672-ffd0-4162-92f1-49fa1768beab', null, 3),
    ('94662ac7-de6c-4976-9b2e-72531d4c45b0', true, '10:30', 75.0, '2024-01-06'::date, 'd1289922-0118-40c6-8e2a-add01abdb419', null, 3),
    ('5f28cdff-2d12-4194-9b40-8a47fc31756f', true, '15:30', 50.0, '2024-02-22'::date, '4c25b7bd-2f0f-4f62-b0fb-2e6473119a58', null, 3),
    ('d53f863f-79c7-45fa-bacc-5c8dd69a0962', true, '12:30', 75.0, '2024-02-03'::date, 'd1289922-0118-40c6-8e2a-add01abdb419', null, 3),
    ('b39aac7f-3f99-4ea2-8483-af102a75082c', true, '09:00', 100.0, '2024-02-14'::date, 'ac763672-ffd0-4162-92f1-49fa1768beab', null, 3),
    ('d2e7981a-1981-4393-8ac6-c7dcc91699f1', true, '13:15', 50.0, '2024-02-15'::date, 'd1289922-0118-40c6-8e2a-add01abdb419', null, 2),
    ('298da0dc-a59a-4e94-a318-e500ae42a850', true, '10:30', 75.0, '2024-02-16'::date, 'd1289922-0118-40c6-8e2a-add01abdb419', null, 2),
    ('a08fc813-50a7-488e-9b08-309997ffa24f', true, '15:30', 50.0, '2024-02-22'::date, '4c25b7bd-2f0f-4f62-b0fb-2e6473119a58', null, 3),
    ('6e32b6ba-1c93-4857-aa68-4d94e3ef2805', true, '12:30', 75.0, '2024-02-13'::date, 'd1289922-0118-40c6-8e2a-add01abdb419', null, 1),
    ('fa9b1a5e-c9c4-4b23-b0c4-66260d98cb2a', true, '09:00', 100.0, '2024-02-24'::date, 'ac763672-ffd0-4162-92f1-49fa1768beab', null, 1),
    ('0a0604db-dd23-450f-9b4f-14789504f378', true, '13:15', 50.0, '2024-02-25'::date, 'ac763672-ffd0-4162-92f1-49fa1768beab', null, 1),
    ('44a8365e-d2a2-4fcd-a7e9-1588c396b384', true, '10:30', 75.0, '2024-02-26'::date, 'd1289922-0118-40c6-8e2a-add01abdb419', null, 1),
    ('9bbafc2a-f529-4edc-be02-c27333c51a9e', true, '11:00', 100.0, '2024-01-01'::date, 'ac763672-ffd0-4162-92f1-49fa1768beab', 'bbcadb41-a42f-4dfc-9f06-ce61dc853a75', 3),
    ('cb1ba9d9-f7dd-4bbf-ab64-4d7f9f75a72b', true, '15:30', 50.0, '2024-01-02'::date, '4c25b7bd-2f0f-4f62-b0fb-2e6473119a58', null, 3),
    ('950c8650-0252-4ee2-8495-47fa2ee3d1be', true, '12:30', 75.0, '2024-01-03'::date, 'd1289922-0118-40c6-8e2a-add01abdb419', null, 3),
    ('081961fc-cf25-44b1-a1c2-f55d4b6d0390', true, '09:00', 100.0, '2024-01-04'::date, 'ac763672-ffd0-4162-92f1-49fa1768beab', null, 3),
    ('1bde1356-be5f-4c26-9a07-edf5bb69e1fb', true, '13:15', 50.0, '2024-01-05'::date, 'ac763672-ffd0-4162-92f1-49fa1768beab', null, 3),
    ('06f72f45-f51a-4d58-a0e2-4ecf82066ff9', true, '10:30', 75.0, '2024-01-06'::date, 'ac763672-ffd0-4162-92f1-49fa1768beab', null, 3),
    ('ad0bb883-3725-4c5d-9ec0-55587b7a111b', true, '15:30', 50.0, '2024-02-22'::date, '4c25b7bd-2f0f-4f62-b0fb-2e6473119a58', null, 3),
    ('ea27e630-3211-4be1-a91c-0c279b9c0804', true, '12:30', 75.0, '2024-02-03'::date, 'd1289922-0118-40c6-8e2a-add01abdb419', null, 3),
    ('32432b9c-144c-424e-b8fa-ea50a667e993', true, '09:00', 100.0, '2024-02-14'::date, 'ac763672-ffd0-4162-92f1-49fa1768beab', null, 3),
    ('8cc3f338-42ed-4d21-8140-bbb8e16fa15f', true, '13:15', 50.0, '2024-02-15'::date, 'ac763672-ffd0-4162-92f1-49fa1768beab', null, 2),
    ('7c991edc-2231-41cb-89b3-36f5c40d8b77', true, '10:30', 75.0, '2024-02-16'::date, 'd1289922-0118-40c6-8e2a-add01abdb419', null, 2),
    ('9329c51c-53e4-4d2b-91f6-b4039e93922b', true, '16:30', 50.0, '2024-02-22'::date, '4c25b7bd-2f0f-4f62-b0fb-2e6473119a58', null, 3),
    ('e293eca6-8b9f-48cd-abc0-9bc7665d1c54', true, '10:30', 75.0, '2024-02-29'::date, 'd1289922-0118-40c6-8e2a-add01abdb419', null, 1),
    ('59a158a5-fbc5-4f50-87b5-960c8c9191e8', true, '19:00', 100.0, '2024-02-29'::date, 'd1289922-0118-40c6-8e2a-add01abdb419', null, 1),
    ('59a158a5-fbc5-4f50-87b5-960c8c919100', true, '13:15', 50.0, '2024-02-29'::date, 'ac763672-ffd0-4162-92f1-49fa1768beab', null, 1),
    ('cecd47a2-15a9-4629-a561-ad90f5090fff', true, '11:30', 75.0, '2024-02-29'::date, 'd1289922-0118-40c6-8e2a-add01abdb419', null, 1);

/*
INSERT INTO _visit (id, available, hour, price, visit_date, doctor_id, user_account_id)
VALUES
    ('d7cec8c7-8545-4b0c-9a94-1100b65e1d8e', false, '10:00', 50.0, '2024-12-16 10:00', 3, 1),
    ('44fd03f8-9a86-4f35-be47-fe79bf4c58b4', false, '14:30', 75.0, '2024-12-17 14:30', 3, 1),
    ('f52fe66e-90ef-4bb5-a58f-d9c22a505bad', false, '11:15', 100.0, '2024-12-18 11:15', 3, 1),
    ('b947f415-f04c-4931-abd7-f729c94eef9f', false, '09:30', 50.0, '2024-12-19 09:30', 3, 1),
    ('21ff0507-affe-460d-a295-49949f47c674', false, '16:45', 75.0, '2024-01-20 16:45', 5, 1),
    ('fc80fb76-54ed-4ec4-aa23-d4debf7b6454', false, '13:00', 100.0, '2024-01-21 13:00', 6, 2),
    ('68c3385b-619d-462b-9a55-0d6a7e517fdb', false, '10:30', 50.0, '2024-01-22 10:30', 3, 2),
    ('312a72a9-d114-4db8-854e-9892cb166f4d', false, '15:15', 75.0, '2024-01-23 15:15', 3, 2),
    ('c46560ff-51c8-4b7e-b3a4-ac92fe81ca57', false, '12:00', 100.0, '2023-11-24 12:00', 9, 1),
    ('c60f9cdc-b88e-42ae-853b-1e258d9c0b5e', false, '09:45', 50.0, '2023-11-25 09:45', 10, 1),
    ('ce190c1c-328d-4341-9fdf-19e70d05e121', false, '17:30', 75.0, '2023-11-26 17:30', 1, 1),
    ('ad142669-21fe-412b-8a3d-90c995c35d1e', false, '14:15', 100.0, '2023-11-27 14:15', 3, 1),
    ('ae1cad58-3f97-4a9d-be41-f7a4078d40bb', false, '11:45', 50.0, '2023-11-28 11:45', 3, 1),
    ('cb03451c-45bf-49e5-be78-f8ab1e6d7e35', false, '16:00', 75.0, '2023-11-29 16:00', 3, 1),
    ('ade66e96-fdc7-4981-88d1-890c3feae35c', false, '12:45', 100.0, '2023-11-30 12:45', 5, 2),
    ('fc03cfca-a015-45ab-b7eb-e35b69ccdd41', false, '12:00', 100.0, '2023-12-24'::date, 1, 2),
    ('65b9c8cb-e112-45f6-a1b5-dc35d3301773', false, '09:45', 50.0, '2023-12-24'::date, 2, 2),
    ('e0fa1a3e-79ff-4c36-87bc-fe4fdb8222fb', false, '17:30', 75.0, '2023-12-25'::date, 3, 2),
    ('e4fbcaf2-fb5e-4d19-b0a0-8e799b094a9c', false, '14:15', 100.0, '2023-12-26'::date, 4, 1),
    ('73f7bccb-09d7-40cb-9d7f-97924ac82f39', false, '11:45', 50.0, '2023-12-27'::date, 5, 1),
    ('20191085-cff5-4213-9c1a-c505a1910daf', false, '16:00', 75.0, '2023-12-28'::date, 6, 1),
    ('8144f92c-264d-428c-b8a9-301df8e8048e', false, '12:45', 100.0, '2023-12-29'::date, 3, 2),
    ('1a55eafb-8e09-433d-b5a3-7ab4f51915b6', false, '10:15', 50.0, '2023-12-30'::date, 3, 2),
    ('13b75863-5f65-4dfd-af99-cc2c13c0b943', false, '14:45', 75.0, '2023-12-31'::date, 3, 2),
    ('52c9efab-c4dd-4ee4-bebc-26079685c1cf', true, '11:00', 100.0, '2024-01-01'::date, 3, 1),
    ('eeaf1558-ff5e-43a5-b7ac-ec16e7ca3e05', true, '15:30', 50.0, '2024-01-02'::date, 1, null),
    ('999900cf-31cc-4caf-be9f-6286ac997022', true, '12:30', 75.0, '2024-01-03'::date, 2, null),
    ('89ad4176-b370-467f-9c1c-3c43bed14c2b', true, '09:00', 100.0, '2024-01-04'::date, 3, null),
    ('f19770bb-1963-4a93-8c7e-9fec5358f03c', true, '13:15', 50.0, '2024-01-05'::date, 3, null),
    ('94662ac7-de6c-4976-9b2e-72531d4c45b0', true, '10:30', 75.0, '2024-01-06'::date, 5, null);
*/
INSERT INTO _visit_details (id, description, medicine)
VALUES
    ('d7cec8c7-8545-4b0c-9a94-1100b65e1d8e',  '',''),
    ('44fd03f8-9a86-4f35-be47-fe79bf4c58b4',  '',''),
    ('f52fe66e-90ef-4bb5-a58f-d9c22a505bad',  '',''),
    ('b947f415-f04c-4931-abd7-f729c94eef9f',  '',''),
    ('21ff0507-affe-460d-a295-49949f47c674',  '',''),
    ('fc80fb76-54ed-4ec4-aa23-d4debf7b6454',  '',''),
    ('68c3385b-619d-462b-9a55-0d6a7e517fdb',  '',''),
    ('312a72a9-d114-4db8-854e-9892cb166f4d',  '',''),
    ('c46560ff-51c8-4b7e-b3a4-ac92fe81ca57',  '',''),
    ('c60f9cdc-b88e-42ae-853b-1e258d9c0b5e',  '',''),
    ('ce190c1c-328d-4341-9fdf-19e70d05e121',  '',''),
    ('ad142669-21fe-412b-8a3d-90c995c35d1e',  '',''),
    ('ae1cad58-3f97-4a9d-be41-f7a4078d40bb',  '',''),
    ('cb03451c-45bf-49e5-be78-f8ab1e6d7e35',  '',''),
    ('ade66e96-fdc7-4981-88d1-890c3feae35c',  '',''),
    ('fc03cfca-a015-45ab-b7eb-e35b69ccdd41',  '',''),
    ('65b9c8cb-e112-45f6-a1b5-dc35d3301773',  '',''),
    ('e0fa1a3e-79ff-4c36-87bc-fe4fdb8222fb',  '',''),
    ('e4fbcaf2-fb5e-4d19-b0a0-8e799b094a9c',  '',''),
    ('73f7bccb-09d7-40cb-9d7f-97924ac82f39',  '',''),
    ('20191085-cff5-4213-9c1a-c505a1910daf',  '',''),
    ('8144f92c-264d-428c-b8a9-301df8e8048e',  '',''),
    ('1a55eafb-8e09-433d-b5a3-7ab4f51915b6',  '',''),
    ('13b75863-5f65-4dfd-af99-cc2c13c0b943',  '',''),
    ('52c9efab-c4dd-4ee4-bebc-26079685c1cf',  '',''),
    ('eeaf1558-ff5e-43a5-b7ac-ec16e7ca3e05',  '',''),
    ('999900cf-31cc-4caf-be9f-6286ac997022',  '',''),
    ('89ad4176-b370-467f-9c1c-3c43bed14c2b',  '',''),
    ('f19770bb-1963-4a93-8c7e-9fec5358f03c',  '',''),
    ('94662ac7-de6c-4976-9b2e-72531d4c45b0',  '','');
