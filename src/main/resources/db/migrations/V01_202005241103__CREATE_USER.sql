CREATE TABLE invoice_user (
  invoice_user_id   SERIAL        PRIMARY KEY,
  name              VARCHAR(255)  NOT NULL,
  email             VARCHAR(255)  NOT NULL
);

COMMENT ON TABLE invoice_user IS 'This table stores users';

COMMENT ON COLUMN invoice_user.invoice_user_id IS 'Primary sequence technical key';
COMMENT ON COLUMN invoice_user.name IS 'User name';
COMMENT ON COLUMN invoice_user.email IS 'User email';
