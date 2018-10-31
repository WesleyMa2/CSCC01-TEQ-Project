var http = require('http');
var fs = require('fs');

var chartColors = {
    blue: "rgb(54, 162, 235)",
    green: "rgb(75, 192, 192)",
    grey: "rgb(201, 203, 207)",
    orange: "rgb(255, 159, 64)",
    purple: "rgb(153, 102, 255)",
    red: "rgb(255, 99, 132)",
    yellow: "rgb(255, 205, 86)"
}

http.createServer(function (req, res) {
    fs.readFile('./index.html', 'utf-8', function (err, data) {
        res.writeHead(200, { 'Content-Type': 'text/html' });

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
                backgroundColor: chartColors.red,
                borderColor: chartColors.red,
                data: chartData,
                fill: false
            },
            {
                label: '# of Children',
                backgroundColor: chartColors.blue,
                borderColor: chartColors.blue,
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
        res.write(result);
        res.end();
    });
}).listen(3000, '127.0.0.1');

console.log('Server running at http://127.0.0.1:3000/');