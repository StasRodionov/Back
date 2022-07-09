create table if not exists accounts(
    id          bigserial not null,
    primary key(id)
);
create table accounts_employees(
    account_id       int8 not null,
    employee_id      int8 not null
);
INSERT INTO accounts(id)
values (1);

INSERT INTO accounts_employees(account_id, employee_id)
values  (1, 1), (1, 2), (1, 3), (1, 4), (1, 5);