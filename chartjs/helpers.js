var chartColours = {
    blue: "rgb(54, 162, 235)",
    green: "rgb(75, 192, 192)",
    grey: "rgb(201, 203, 207)",
    orange: "rgb(255, 159, 64)",
    purple: "rgb(153, 102, 255)",
    red: "rgb(255, 99, 132)",
    yellow: "rgb(255, 205, 86)"
};

function getRandomColour(amount) {
    var result = [];
    var keys = Object.keys(chartColours);
    
    if (amount > keys.length) {
        return [];
    }

    for (var i = 0; i < amount; i++) {
        var index = keys.length * Math.random() << 0;
        result.push(chartColours[keys[index]]);
        keys.splice(index, 1);
    }
    return result;
}

function getConfigStub(body) {
    var config = {
        type: body.type,
        responsive: true,
        data: {
            labels: body.xAxisLabels,
            datasets: []
        },
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
                        labelString: body.xAxisTitle
                    }
                }],
                yAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: body.yAxisTitle
                    }
                }]
            }
        },
    };

    return config;
}

module.exports = {
    chartColours,
    getRandomColour,
    getConfigStub
};