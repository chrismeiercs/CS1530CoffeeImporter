// George Totolos 7-2-2015


function getProductTable()
{
    var Products = Parse.Object.extend("Products");
    var query = new Parse.Query(Products);
    query.find({
        success: function (results) {
            var table = document.getElementById("output-table");
            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);
            for (var i in results){
                rowCount = table.rows.length;
                row = table.insertRow(rowCount);
                var id = results[i].get("ProductID");
                var name = results[i].get("ProductName");
                var cost = results[i].get("ProductCost");
                cost = cost.toFixed(2);
                var sold = results[i].get("Sold");
                if (sold == true){
                    sold = "Yes";
                }
                if (sold == false){
                    sold = "No";
                }
                var priceSold = results[i].get("PriceSold");
                priceSold = priceSold.toFixed(2);
                var weight = results[i].get("Weight");
                weight = weight.toFixed(2);
                var shipmentId = results[i].get("ShipmentID");

                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);
                var cell5 = row.insertCell(4);
                var cell6 = row.insertCell(5);
                var cell7 = row.insertCell(6);
                cell1.innerHTML = id;
                cell2.innerHTML = name;
                cell3.innerHTML = weight;
                cell4.innerHTML = cost;
                cell5.innerHTML = sold;
                cell6.innerHTML = priceSold;
                cell7.innerHTML = shipmentId;


                rowCount = rowCount + 1;

            }
        }, error: function (error) {
            alert("Could not retrieve Product data");
        }
    });
}
function getShipmentsTable()
{
    var Shipments = Parse.Object.extend("Shipments");
    var query = new Parse.Query(Shipments);
    query.find({
        success: function (results) {
            var table = document.getElementById("output-table");
            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);
            for (var i in results){
                rowCount = table.rows.length;
                row = table.insertRow(rowCount);
                var shipmentID = results[i].get("ShipmentID");
                var shippingCost = results[i].get("ShippingCost");
                shippingCost = shippingCost.toFixed(2);
                var totalCost = results[i].get("TotalCost");
                totalCost = totalCost.toFixed(2);
                var weight = results[i].get("Weight");
                weight = weight.toFixed(2);
                var dateReceived = results[i].get("DateReceived");
                var origin = results[i].get("Origin");
                var productCost = results[i].get("ProductCost");
                productCost = productCost.toFixed(2);
                var pricePerKg = results[i].get("PricePerKg");
                pricePerKg = pricePerKg.toFixed(2);

                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);
                var cell5 = row.insertCell(4);
                var cell6 = row.insertCell(5);
                var cell7 = row.insertCell(6);
                var cell8 = row.insertCell(7);

                cell1.innerHTML = shipmentID;
                cell2.innerHTML = dateReceived;
                cell3.innerHTML = origin;
                cell4.innerHTML = shippingCost;
                cell5.innerHTML = totalCost;
                cell6.innerHTML = productCost;
                cell7.innerHTML = weight;
                cell8.innerHTML = pricePerKg;

                rowCount = rowCount + 1;

            }
        }, error: function (error) {
            alert("Could not retrieve Shipment data");
        }
    });
}






