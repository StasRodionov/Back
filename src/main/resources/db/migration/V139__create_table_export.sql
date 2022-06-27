create table if not exists export
(
    id     bigserial not null,
    task   varchar(255),
    begining  varchar(255),
    ending varchar(255),
    status varchar(255),
    primary key (id)
);

insert into export (id, begining, ending, status, task)
values (1, '18.02.2022', '10.01.2022', 'Completed', 'Prepare Docs'),
       (2, '23.06.2022', '25.06.2023', 'In progress', 'Make revaluation'),
       (3, '05.05.2022', '23.06.2022', 'Completed', 'Call to director'),
       (4, '11.03.2022', '25.06.2023', 'In progress', 'Tax report'),
       (5, '17.05.2022', '25.06.2023', 'In progress', 'Consulting');
select setval('export_id_seq', max(id))
from export;