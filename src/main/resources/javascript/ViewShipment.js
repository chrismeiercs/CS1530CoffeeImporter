/**
 * Created by Chris on 7/1/2015.
 */

function initParse(){

    Parse.initialize("lXpYJrImjyl3YSKDvxX9R6H3GGqIKQrB6WbI6Eu1", "tmfM1mnCsFEE9onzPSAqwKubvgKakqPppNx8dnsq");
	
}



function getUniqueProducts(){

    var Products = Parse.Object.extend("Products");
    var query = new Parse.Query(Products);
    
    

    query.find({
        success: function(results){
            
			var resultsArray = [];
			for(i=0; i < results.length; i++){
				object = results[i];
				
				resultsArray.push(object.get("ProductName"));
			}
			
			var uniqueResults = $.unique(resultsArray);
			
			$("#products").append("<option>Select Product</option>");
			uniqueResults.forEach(function(productName){
				//alert(productName);
				$("#products").append("<option value='"+productName+"'>"+productName+"</option>");
			});
			
       
        },
        error: function(error){
            alert("Error: " + error.code + " " + error.message);
        }
    });
}

function queryShipments(productName){
    var Product = Parse.Object.extend("Products");
    var Shipment = Parse.Object.extend("Shipments");
	alert(productName);
    var findProducts = new Parse.Query(Product);
    var productsQuery = findProducts.equalTo("ProductName", productName);

    var shipmentArray = [];

    productsQuery.find({
        success: function(results) {
			$('#shipmentTable tr').has('td').remove();
            for(var i=0; i < results.length; i++){
                var object = results[i];
				
				
				var cost = object.get("ProductCost");
				var name = object.get("ProductName");
				var shipmentID = object.get("ShipmentID");
				$('#shipmentTable').append('<tr><td>'+name+'</td><td>'+shipmentID+'</td><td>'+cost+'</td></tr>');
            }
			
			

			
			
        },
        error: function(error){
            alert("Error: " + error.code + " " + error.message);
        }
    });
    
}



