CREATE TABLE invoice_currency (
  invoice_currency_id   SERIAL        PRIMARY KEY,
  iso                   VARCHAR(3)    NOT NULL UNIQUE,
  code                  VARCHAR(3)    NOT NULL,
  name                  VARCHAR(55)   NOT NULL
);

COMMENT ON TABLE invoice_currency IS 'This table stores users';

COMMENT ON COLUMN invoice_currency.invoice_currency_id IS 'Primary sequence technical key';
COMMENT ON COLUMN invoice_currency.iso IS 'ISO 4217 Numeric code';
COMMENT ON COLUMN invoice_currency.code IS 'ISO 4217 Alphanumeric code';
COMMENT ON COLUMN invoice_currency.name IS 'Currency name';
