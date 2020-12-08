// Load google charts
$(document).ready(function(){
    google.charts.load('current', {'packages':['corechart']});
    //google.charts.setOnLoadCallback(drawChart);
})

// Draw the chart and set the chart values
function drawChart(data) {
    data = data.replace("{",'').replace("}",'')

    var elements = data.split(",")
    console.log(elements,typeof(elements))
    for(x=0;x<elements.length;x++){
        console.log(x)
        console.log(elements[x])
        split = elements[x].split("=")
        console.log(split)
        elements[x] = [split[0],parseInt(split[1])]
    }
    console.log(elements);
    var data = google.visualization.arrayToDataTable(elements);



    // Optional; add a title and set the width and height of the chart
    var options = {'title':'My Average Day', 'width':550, 'height':400};

    // Display the chart inside the <div> element with id="piechart"
    var chart = new google.visualization.BarChart(document.getElementById('chart'));
    chart.draw(data, options);
}