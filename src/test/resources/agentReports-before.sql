DELETE
FROM agent_reports;
INSERT INTO agent_reports(id, comitent_sum, commentary, document_type, number, paid, printed, remuniration_sum, sent,
                          status, sum, time, company_id, contractor_id)
VALUES (1, 1, 'Комментарий 1', '.doc', '1', 1, 1, 1, 1, 'ok', 1, '2015-10-06T06:37:59', 1, 1),
       (2, 2, 'Комментарий 2', '.doc', '2', 2, 2, 2, 2, 'ok', 2, '2015-10-06T06:37:59', 2, 2),
       (3, 1, 'Комментарий 3', '.doc', '1', 1, 1, 10, 10, 'ok', 10, '2015-10-06T06:37:59', 3, 3);