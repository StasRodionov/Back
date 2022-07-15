create table if not exists retail_event_log
(
    id bigserial not null,
    doc_type   varchar(255),
    operation_id  bigserial,
    action_type varchar(255),
    sell_point varchar(255),
    initiator varchar(255),
    details varchar(255),
    api varchar(255),
    primary key (id)
);

insert into retail_event_log (id, doc_type, operation_id, action_type, sell_point, initiator, details, api)
values (1, 'Отчет', 1, 'Выполнен', '№1', 'Менеджер', 'Квартальный отчет', 'API1'),
       (2, 'Доклад', 2, 'В процессе', '№2', 'Старший ассистент', 'Ежегодный доклад', 'API2');
select setval('retail_event_log_id_seq', max(id))
from retail_event_log;