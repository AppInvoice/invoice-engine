CREATE TABLE invoice_rating_exchange_to_eur (
    invoice_rating_exchange_to_eur_id    SERIAL        PRIMARY KEY,
    currency_target                      INTEGER       NOT NULL REFERENCES invoice_currency(invoice_currency_id),
    percent_change                       DECIMAL(12,6) NOT NULL
);

COMMENT ON TABLE invoice_rating_exchange_to_eur IS 'This table stores the rating exchange between currencies';

COMMENT ON COLUMN invoice_rating_exchange_to_eur.invoice_rating_exchange_to_eur_id IS 'Primary sequence technical key';
COMMENT ON COLUMN invoice_rating_exchange_to_eur.currency_target IS 'Foreign key to currency';
COMMENT ON COLUMN invoice_rating_exchange_to_eur.percent_change IS 'Rating exchange currency';
