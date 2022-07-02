create table if not exists accounts(
    id          bigserial not null,
    primary key(id)
);

create table accounts_companies(
     account_id      int8 not null,
     company_id      int8 not null
);

INSERT INTO accounts_companies(account_id, company_id)
values  (1, 1), (1, 2), (1, 3), (1, 4), (1, 5);

create table accounts_contractors(
                                   account_id      int8 not null,
                                   contractor_id      int8 not null
);

INSERT INTO accounts_contractors(account_id, contractor_id)
values  (1, 1), (1, 2), (1, 3), (1, 4), (1, 5);