// George Totolos 7-2-2015

function getShipmentsTable()
{
    // Get parse Object data 'Shipments' table
    var Shipments = Parse.Object.extend("Shipments");
    var query = new Parse.Query(Shipments);
    query.find({
        success: function (results)  // If query is found
        {
            var table = document.getElementById("output-table"); // Where to put table
            var rowCount = table.rows.length; // Count number of rows to add new ones at bottom
            var row = table.insertRow(rowCount);
            // For every row of data
            for (var i in results)
            {
                rowCount = table.rows.length; // Count number of rows to add new ones at bottom
                row = table.insertRow(rowCount); // Insert data from below

                // Acquire variables from parse
                var shipmentID = results[i].get("ShipmentID");
                var dateReceived = results[i].get("DateReceived");
                var origin = results[i].get("Origin");
                var weight = results[i].get("Weight");
                var totalCost = results[i].get("TotalCost");
                var shippingCost = results[i].get("ShippingCost");
                var productCost = results[i].get("ProductCost");
                var pricePerKg = results[i].get("PricePerKg");

                // Fix/format/modify output
                dateReceived = dateReceived.toLocaleString();
               // shippingCost = shippingCost.toFixed(2);

                // Do insertion of blank cells
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);
                var cell5 = row.insertCell(4);
                var cell6 = row.insertCell(5);
                var cell7 = row.insertCell(6);
                var cell8 = row.insertCell(7);
                var cell9 = row.insertCell(8);
                var cell10 = row.insertCell(9);




                // Create Edit button to be generated for each row
                var editBtn = document.createElement("input");
                //Assign different attributes to the element.
                editBtn.type = "button";
                editBtn.value = "Edit";
                editBtn.setAttribute("id", shipmentID);
                /* elem = document.createElement("input");
                 elem.id ='btnGumb';
                 elem.value = 'x close';
                 elem.type = 'button';
                 elem.onclick = window.close();
                 */
                editBtn.onclick = function() { // Note this is a function
                    alert(this.id);
                };


                // Create delete button to be generated for each row
                var deleteBtn = document.createElement("input");
                //Assign different attributes to the element.
                deleteBtn.type = "button";
                deleteBtn.value = "Delete";
                deleteBtn.setAttribute("id", shipmentID); // Delete based on shipment ID
                deleteBtn.onclick = function() { // Note this is a function
                    alert("Shipment " + this.id + " deleted");
                    // Find row with this shipment ID and delete from parse
                    query.equalTo("ShipmentID",this.id);
                    query.first({
                        success: function(shipment) {
                            //alert("Found product");
                            shipment.destroy({}); // Remove
                            location.reload(); // reload page for updated table
                        },
                        error: function(error) {
                            alert("Error: Could not remove shipment");
                            location.reload();
                        }
                    });
                };




                // Fill cells with variable data
                cell1.innerHTML = shipmentID;
                cell2.innerHTML = dateReceived;
                cell3.innerHTML = origin;
                cell4.innerHTML = weight;
                cell5.innerHTML = totalCost;
                cell6.innerHTML = shippingCost;
                cell7.innerHTML = productCost;
                cell8.innerHTML = pricePerKg;
                cell9.appendChild(editBtn);
                cell10.appendChild(deleteBtn);

                rowCount = rowCount + 1; // increase row count for next row insertion

            }
        }, error: function (error) { // Error loading query from parse
            alert("Could not retrieve Shipment data");
        }
    });
}


/*
function removeProduct(prodID) { // Removes product based on user entered ID
 alert(prodID);

//    event.preventDefault();
    var Shipments = Parse.Object.extend("Shipments");
    var query = new Parse.Query(Shipments);
    // Find product with specified ID
    query.equalTo("ShipmentID",prodID);
    query.first({
        success: function(shipment) {
            //alert("Found product");
            shipment.destroy({}); // Remove
//            location.reload();
        },
        error: function(error) {
            alert("Error: Could not remove product");
 //           location.reload();
        }
    });

}

*/






