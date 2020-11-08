function reRenderDisplay(d) {
    var ordersTable = $('#orders');
    var driversTable = $('#drivers');
    var trucksTable = $('#trucks');

    ordersTable.empty();
    driversTable.empty();
    trucksTable.empty();

    for (var i = 0; i < d.orders.length; i++) {
        var order = d.orders[i];

        var drivers = '';
        for (var j = 0; j < order.drivers.length; j++) {
            var driver = order.drivers[j];
            drivers += '<p>' + driver + '</p>';
        }

        ordersTable.append(
            '<tr>' +
            '<td>' + (i + 1) + '</td>' +
            '<td>' + order.id + '</td>' +
            '<td>' + order.status + '</td>' +
            '<td>' + order.truck + '</td>' +
            '<td>' + drivers +'</td>' +
            '<td>' + order.currentWaypoint + '</td>'
            + '</tr>'
        )
    }

    driversTable.append(
        '<tr>' +
        '<td>' + d.drivers.all + '</td>' +
        '<td>' + d.drivers.free + '</td>' +
        '<td>' + (d.drivers.all - d.drivers.free) + '</td>' +
        + '</tr>'
    )

    trucksTable.append(
        '<tr>' +
        '<td>' + d.trucks.all + '</td>' +
        '<td>' + d.trucks.free + '</td>' +
        '<td>' + (d.trucks.all - d.trucks.free - d.trucks.broken) + '</td>' +
        '<td>' + d.trucks.broken + '</td>' +
        + '</tr>'
    )
}
