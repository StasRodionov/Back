INSERT INTO buyersReturn (id, date, warehouse_id, contractor_id, company_id, sum,
                        is_sent, is_print, comment)
VALUES (1,current_date,1,1,1,50000,false,true,true,'comment one'),
       (2,current_date,1,1,1,47700,false,false,false,'comment two'),
       (3,current_date,1,1,1,935000,true,true,true,'comment three')
