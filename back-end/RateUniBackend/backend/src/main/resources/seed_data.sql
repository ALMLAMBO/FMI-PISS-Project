use piss_project;

insert into universities (uni_name, rector, hq_address)
values
    ('Софийски университет "Св. Климент Охридски"', 'проф. д-р ГЕОРГИ ВЪЛЧЕВ', 'София 1504 бул. "Цар Освободител" 15'),
    ('Технически Университет - София', 'проф. дн инж. Иван Кралов', 'София 1000  бул."Кл. Охридски" 8'),
    ('Медицински Университет София', 'АКАД. ЛЪЧЕЗАР ТРАЙКОВ ДМН', 'София 1431 бул. „Акад. Иван Гешов" № 15 /в сградата на Центъра по хигиена/'),
    ('Университет по архитектура, строителство и геодезия', 'проф. д-р инж. Иван Марков', 'София 1046 бул. Христо Смирненски № 1'),
    ('Нов български университет', 'проф. Пламен Дойнов, д.н.', 'София 1618 ж.к. Овча купел ул. „Монтевидео“ №21');


insert into faculties (faculty_name, address, dean)
values
    ('ФАКУЛТЕТ ПО МАТЕМАТИКА И ИНФОРМАТИКА', 'София 1164 бул. Джеймс Баучър 5', 'проф. д-р МАЯ СТОЯНОВА'),
    ('ФАКУЛТЕТ ПО ХИМИЯ И ФАРМАЦИЯ', 'София 1164 бул. Джеймс Баучър 1', 'проф. д-р Анела Иванова'),
    ('ФИЗИЧЕСКИ ФАКУЛТЕТ', 'София 1164 бул. Джеймс Баучър 5', 'Проф. дфзн Георги Райновски'),
    ('Факултет по телекомуникации', 'София 1756 бул."Кл. Охридски" 8 Блок 1', 'Доц. д-р  Агата Манолова'),
    ('Факултет Компютърни системи и технологии', 'София 1000  бул."Кл. Охридски" 8 Блок 1', 'проф. д-р инж.  Даниела Гоцева'),
    ('ФАРМАЦЕВТИЧЕН ФАКУЛТЕТ', 'София 1000 ул. Дунав №2', 'Проф. Александър Златков, дфн'),
    ('Факултета по дентална медицина', 'София бул. ''Св. Георги Софийски'' 1', 'проф. д-р Божидар Иванов Йорданов, доктор'),
    ('Медицински факултет', 'София 1431  ул. "Св. Георги Софийски" № 1', 'Проф. д-р Димитър Иванов Буланов, дм'),
    ('Геодезически факултет', 'София 1046 бул. Христо Смирненски 1', 'проф. д-р инж. Елена Пенева-Златкова'),
    ('Архитектурен факултет', 'София 1046 бул. Христо Смирненски 1', 'проф. д-р арх. Орлин Давчев'),
    ('Бакалавърски факултет  (БФ)', 'София 1618 ж.к. Овча купел ул. „Монтевидео“ №21 кампус 1', 'Доц. д-р Илия Кожухаров'),
    ('Магистърски факултет (МФ)', 'София 1618 ж.к. Овча купел ул. „Монтевидео“ №21 кампус 1', 'Проф. д-р Борис Сергинов');

insert into universities_faculties (university_id, faculty_id)
values
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 4),
    (2, 5),
    (3, 6),
    (3, 7),
    (3, 8),
    (4, 9),
    (4, 10),
    (5, 11),
    (5, 12);