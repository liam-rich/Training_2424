"""
Week 2 Exercise — CSV processing with context managers.

TODO:
1. Read starter_code/data/sales.csv using csv.DictReader and with open(...).
2. Compute rows count, grand total (sum of units * unit_price), average line revenue.
3. Find SKU with max line revenue (tie: first in file).
4. Write output/summary.txt using with open(..., "w", encoding="utf-8").
"""

from __future__ import annotations
import csv, os


def main() -> None:
    with open("data/sales.csv", "r", encoding="utf-8", newline="") as f:
        reader = csv.DictReader(f)
        rows =[]
        for row in reader:
            units = int(row["units"])
            unit_price = float(row["unit_price"])
            line_revenue = units * unit_price
            rows.append({"sku": row["sku"], "line_revenue": line_revenue})
        
    #number of rows
    row_count = len(rows)
    #grand total of sales
    grand_total = sum(r["line_revenue"] for r in rows)
    #average revenue per line
    avr_rev = grand_total / row_count
    #sku with highest line of revenue, if tied pick whichever one is first in file
    top = max(rows, key=lambda r: r["line_revenue"])
    
    with open("data/output.txt", "w", encoding="utf-8") as out:
        out.write(f"rows={row_count}\n")
        out.write(f"grand_total={grand_total:.2f}\n")
        out.write(f"average_line_revenue={avr_rev:.2f}\n")
        out.write(f"top_sku={top['sku']}\n")
        out.write(f"top_line_revenue={top['line_revenue']:.2f}\n")    
        #write this to output.py
    




if __name__ == "__main__":
    main()
