$(document).ready(function() {

                        $(".JobOrders").autocomplete({
                            //define callback to format results
                            source: function(req, add){
				//pass request to server
				$.getJSON("../includes/job_orders_db.php", req, function(data) {
							
				//create array for response objects
				var suggestions = [];
							
				//process response
				$.each(data, function(i,val){								
					suggestions.push(val);
                                    });
							
				//pass array to callback
				add(suggestions);
                                });
                            },					
                            //define select handler
                            select: function(e, ui) {
				 $(".JobOrders").val(ui.item.value);		

                            },
  
			});
                        
                        
                    });