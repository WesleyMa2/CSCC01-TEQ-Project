const http = require('http');
const fs = require('fs');
const express = require('express');
const bodyParser = require('body-parser');
const uuid = require('short-uuid')();
const helpers = require('./helpers.js');

const app = express();
app.use(bodyParser.json({
    limit: "5mb"
}));

const shortLinks = {};

//curl -X POST -H "Content-Type: application/json" -d '{ "type": "bar", "xAxisTitle": "# Of Children", "xAxisLabels": ["1", "2", "3", "4", "5+"], "yAxisTitle": "Value", "dataSet": [{ "header": "2018", "data": [3, 6, 1, 7, 8] }, { "header": "2017", "data": [2, 8, 3, 12, 6] }] }' http://localhost:3000/
app.post('/', function (req, res, next) {
    fs.readFile('./index.html', 'utf-8', function (err, data) {
        if (err) {
            return res.json({ error: err });
        }

        var limit = req.body.dataSet.length;
        var config = helpers.getConfigStub(req.body);
        var colours = helpers.getRandomColour(limit);

        for (var i = 0; i < limit && colours.length <= limit; i++) {
            config.data.datasets[i] = { 
                label: req.body.dataSet[i].header,
                backgroundColor: colours[i].replace(')', ', 0.5)'),
                borderColor: colours[i],
                data: req.body.dataSet[i].data,
                fill: false
            }
        }

        result = data.replace('{{config}}', JSON.stringify(config));

        console.log();

        var link = uuid.new();
        shortLinks[link] = result;
        return res.send('http://localhost:3000/' + link);
    });
});

app.get('/:id/', function (req, res, next) {
    if (req.params.id in shortLinks) {
        res.writeHead(200, { 'Content-Type': 'text/html' });
        res.write(shortLinks[req.params.id]);
        return res.end();
    }
    return res.json({error: "invalid id: " + req.params.id });
});

app.use(function (req, res, next) {
    res.status(501).end("Invalid API endpoint: " + req.url);
    console.log("HTTP Response", res.statusCode);
});

const PORT = 3000;
http.createServer(app).listen(PORT, function (err) {
    if (err) console.log(err);
    else console.log("HTTP server on http://localhost:%s", PORT);
});