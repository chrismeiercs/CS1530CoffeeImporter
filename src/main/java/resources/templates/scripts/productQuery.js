// George Totolos 7-2-2015

function getProductTable( searchShipmentID )
{
    var Products = Parse.Object.extend("Products");
    var query = new Parse.Query(Products);
    query.equalTo("ShipmentID",searchShipmentID);
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
                var cost = results[i].get("ProductCost");;
                var sold = results[i].get("Sold");
                if (sold == true){
                    sold = "Yes";
                }
                if (sold == false){
                    sold = "No";
                }
                var priceSold = results[i].get("PriceSold");
                var weight = results[i].get("Weight");
                var shipmentId = results[i].get("ShipmentID");

                // Create delete button to be generated for each row
                var deleteBtn = document.createElement("input");
                //Assign different attributes to the element.
                deleteBtn.type = "button";
                deleteBtn.value = "Delete";
                deleteBtn.setAttribute("id", id); // Delete based on product ID
                deleteBtn.onclick = function() { // Note this is a function
                    alert("Product " + this.id + " deleted");
                    // Find row with this shipment ID and delete from parse
                    query.equalTo("ProductID",this.id);
                    query.first({
                        success: function(product) {
                            //alert("Found product");
                            product.destroy({}); // Remove
                            location.reload(); // reload page for updated table
                        },
                        error: function(error) {
                            alert("Error: Could not remove shipment");
                            location.reload();
                        }
                    });
                };

                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);
                var cell5 = row.insertCell(4);
                var cell6 = row.insertCell(5);
                var cell7 = row.insertCell(6);
                var cell8 = row.insertCell(7);

                cell1.innerHTML = shipmentId;
                cell2.innerHTML = id;
                cell3.innerHTML = name;
                cell4.innerHTML = weight;
                cell5.innerHTML = cost;
                cell6.innerHTML = sold;
                cell7.innerHTML = priceSold;
                cell8.appendChild(deleteBtn);



                rowCount = rowCount + 1;

            }
        }, error: function (error) {
            alert("Could not retrieve Product data");
        }
    });

}
