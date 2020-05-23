CREATE TABLE invoice_account (
  invoice_account_id  SERIAL          PRIMARY KEY,
  holder              INTEGER         NOT NULL REFERENCES invoice_user(invoice_user_id),
  currency            INTEGER         NOT NULL REFERENCES invoice_currency(invoice_currency_id),
  balance             DECIMAL(25,7)   NOT NULL DEFAULT 0
);

COMMENT ON TABLE invoice_account IS 'This table stores users';

COMMENT ON COLUMN invoice_account.invoice_account_id IS 'Primary sequence technical key';
COMMENT ON COLUMN invoice_account.holder IS 'Account holder';
COMMENT ON COLUMN invoice_account.currency IS 'Account currency';
COMMENT ON COLUMN invoice_account.balance IS 'Account current balance';
