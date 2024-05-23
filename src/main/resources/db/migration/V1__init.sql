CREATE TABLE "users"
(
    id      INTEGER NOT NULL,
    name    VARCHAR(255) NOT NULL,
    balance INTEGER NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);
