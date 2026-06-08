"""
Week 2 Exercise — Flask API with file-backed JSON storage.

TODO:
- Implement GET /findings  -> return list from data/findings.json
- Implement POST /findings -> body: {"title": "...", "severity": "..."}
      assign id = max existing id + 1, append, save file with json.dump + with open
"""
from __future__ import annotations

import json
from pathlib import Path

from flask import Flask, jsonify, request

app = Flask(__name__)
DATA_FILE = Path(__file__).resolve().parent / "data" / "findings.json"


def load_findings():
    with open(DATA_FILE, "r", encoding="utf-8") as f:
        return json.load(f)


def save_findings(items):
    with open(DATA_FILE, "w", encoding="utf-8") as f:
        json.dump(items, f, indent=2)


@app.get("/findings")
def list_findings():
    findings = load_findings()
    return jsonify(findings)



@app.post("/findings")
def create_finding():
    body = request.get_json()  # parse JSON from POST body

    if not body or "title" not in body or "severity" not in body:
        return jsonify({"error": "title and severity required"}), 400

    findings = load_findings()  # step 1: read file → list

    next_id = max(f["id"] for f in findings) + 1 if findings else 1

    new_finding = {
        "id": next_id,
        "title": body["title"],
        "severity": body["severity"],
    }

    findings.append(new_finding)  # step 2: change list in memory

    save_findings(findings)       # step 3: write whole list back to file

    return jsonify(new_finding), 201
    


if __name__ == "__main__":
    app.run(debug=True)
