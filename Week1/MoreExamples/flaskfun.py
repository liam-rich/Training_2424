from flask import Flask, request
app: Flask = Flask(__name__) #this tells flask where to look for resources (name is reference to the module it resides in)

count = 0

@app.route("/",methods = ["GET"])
def hello_world():
    return "Hello World"

#get request with path parameter
@app.route("/greeting/<name>",methods=["GET"])
def greeting(name:str) -> str:
    return f"Hello {name}"

app.run()

