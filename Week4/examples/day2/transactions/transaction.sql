SELECT * FROM product;


--Rollback
BEGIN;

UPDATE product SET stock_qty = stock_qty -1 WHERE sku = 'BASE-A' AND stock_qty >=1;

ROLLBACK;

--COMMIT persists a multi-step business action

SELECT * FROM customer;

BEGIN;
INSERT INTO customer (email, full_name) VALUES ('txn-demo@example.com','Txn Demo') ON 
CONFLICT (email) DO NOTHING;

UPDATE product
SET stock_qty =stock_qty -1
WHERE sku='BASE-A' AND stock_qty>=1;

COMMIT;

SELECT * FROM order_header;
--SAVEPOINT
BEGIN;
SAVEPOINT before_insert;

INSERT INTO order_header(customer_id, status)
SELECT customer_id, 'OPEN' FROM customer WHERE email = 'txn-demo@example.com';

ROLLBACK TO SAVEPOINT before_insert;

SAVEPOINT before_order
