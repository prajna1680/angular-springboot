var express = require("express");

var app = express();

app.use("/node_modules",
   express.static("/src/app/angularrestaurant/node_modules"));
app.use("/", express.static("/src/app/angularrestaurant/dist"));

app.listen(3000, function () {
   console.log("HTTP Server running on port 3000");
});
