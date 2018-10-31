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

app.post('/', function (req, res, next) {
    fs.readFile('./index.html', 'utf-8', function (err, data) {
        var chartData = [];
        for (var i = 0; i < 12; i++)
            chartData.push(Math.random() * 50);

        var chartData2 = [];
        for (var i = 0; i < 12; i++)
            chartData2.push(Math.random() * 50);

        var valueData = {
            labels: ["January", "February", "March", "April", "May", "June", "July", "September", "October", "November", "December"],
            datasets: [{
                label: '# of Wastemans',
                backgroundColor: helpers.chartColors.red,
                borderColor: helpers.chartColors.red,
                data: chartData,
                fill: false
            },
            {
                label: '# of Children',
                backgroundColor: helpers.chartColors.blue,
                borderColor: helpers.chartColors.blue,
                data: chartData2,
                fill: false
            }]
        };

        var config = {
            type: 'line',
            responsive: true,
            data: valueData,
            options: {
                responsive: true,
                title: {
                    display: true,
                    text: 'TEQ Report'
                },
                tooltips: {
                    mode: 'index',
                    intersect: false,
                },
                hover: {
                    mode: 'nearest',
                    intersect: true
                },
                scales: {
                    xAxes: [{
                        display: true,
                        scaleLabel: {
                            display: true,
                            labelString: 'Month'
                        }
                    }],
                    yAxes: [{
                        display: true,
                        scaleLabel: {
                            display: true,
                            labelString: 'Value'
                        }
                    }]
                }
            },
        };

        result = data.replace('{{config}}', JSON.stringify(config, 0, 2));

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