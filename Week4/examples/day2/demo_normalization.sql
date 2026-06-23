-- Unnormalized / non - 1NF : "tags" is a list is one cell (repeating group)
DROP TABLE IF EXISTS tmp_orders_denorm;
CREATE TEMP TABLE tmp_orders_denorm (
	order_ref TEXT PRIMARY KEY,
	customer TEXT NOT NULL,
	tags TEXT NOT NULL,  -- e.g 'retail;priority'
	product_a TEXT,
	qty_a INTEGER,
	product_b TEXT,
	qty_b INTEGER
	);

INSERT INTO tmp_orders_denorm VALUES 
	('SO-100','ada@example.com','retail;priority','SKU-1',2,'SKU-2',1),
	('SO-101','bob@example.com','wholesale','SKU-3',5,NULL, NULL);

SELECT * FROM tmp_orders_denorm;

-- 1NF: atomic tags (one tag per fow) an line-level grain for products
DROP TABLE IF EXISTS tmp_order_tags_1nf;
CREATE TEMP TABLE tmp_order_tags_1nf(
	order_ref TEXT NOT NULL,
	tag TEXT NOT NULL,
	PRIMARY KEY (order_ref, tag)
);

INSERT INTO tmp_order_tags_1nf VALUES
('SO-100','retail'),
('SO-100','priority'),
('SO-101','wholesale');

DROP TABLE IF EXISTS tmp_order_lines_1nf;
CREATE TEMP TABLE tmp_order_lines_1nf (
	order_ref TEXT NOT NULL,
	line_no INTEGER NOT NULL,
	sku TEXT NOT NULL,
	qty INTEGER NOT NULL,
	PRIMARY KEY (order_ref,line_no)
	);

INSERT INTO tmp_order_lines_1nf VALUES
('SO-100',1,'SKU-1',2),
('SO-100',2,'SKU-2',1),
('SO-101',1,'SKU-3',5);

SELECT * FROM tmp_order_tags_1nf;
SELECT * FROM tmp_order_lines_1nf;


	