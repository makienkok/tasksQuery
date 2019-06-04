<script th:inline="javascript">
		                /*<![CDATA[*/
		                	
		               	 $(function() {
		               		 console.log("Action");
		               		 $("#save").click(function(event) {
		                         //stop submit the form, we will post it manually.
		                         event.preventDefault();
		                         
		                         sendJson2Action('/createTask',	 new FormData($('#createTask')) , null);
		                         //Save();
		                     });  
		               	 });
		                
		               	function stringify_censor(key, value) {
		               	    /*
		               		 * if (!isnull(value) && typeof (value) == "number") { return
		               		 * value.toString().replace('.', ','); }
		               		 */
		               	    return value;
		               	}
		               	
		               	function stringify_censor(key, value) {
		               	    /*
		               		 * if (!isnull(value) && typeof (value) == "number") { return
		               		 * value.toString().replace('.', ','); }
		               		 */
		               	    return value;
		               	}
		               	
		               	function sendJson2Action(url, param, code, error) {    
		               	    sendData2Action(url, JSON.stringify(param, stringify_censor)
		               	            // cannot do this in stringify_censor because stringify encode \
		               				// into \\ and does not encode / at all. shit..
		               	            .replace(/\)\//g, ')\\/').replace(/\/Date/g, '\\/Date'), code, error, 'json', 'application/json');    
		               	}

		               	function sendData2Action(p_url, p_data, p_code, p_error, p_dataType, p_contentType) {
		               	    /* actionShowProcessing(true); */
		               	    //showAjaxError(null, '#ajaxException');
		               	    
		               	    $.ajax({
		               	        url: p_url,
		               	        type: 'POST',
		               	        dataType: nvl(p_dataType, false) ,
		               	        contentType: nvl(p_contentType, false),
		               	        processData: false,
		               	        cache: false,
		               	        
		               	        // timeout: 600000,
		               	        data: p_data,
		               	        success: function(rsp) {        	
		               	            //actionShowProcessing();   

		               	            if (!isnull(rsp) && rsp.type == "AjaxException")
		               	            	console.log("rsp Aj " , rsp);
		               	        		/* showAjaxError(rsp.message, '#ajaxException'); */
		               	            
		               	            else if (!isnull(p_code))
		               	                p_code(rsp);
		               	        },
		               	        error: function(msg) {        	
		               	            /* actionShowProcessing(); */
		               	            
		               	            if (!isnull(msg))
		               	                //showAjaxError(msg.responseText);
		               	            	console.log("msg.responseText " , msg.responseText);
		               	            if (!isnull(p_error))
		               	            	//p_error(msg.responseText);
		               	            	console.log("msg.responseText " , msg.responseText);
		               	            
		               	        }
		               	    });
		               	}
		                
		                function Save()
		                {
		                	formData = new FormData($('#task'));
		                	
		                	
		                	$.ajax({
		                        url: '/createTask',
		                        type: 'POST',
		                        /* dataType: 'json', */
		                        contentType: false,
		                        processData: false,
		                        cache: false,
		                        
		                        // timeout: 600000,
		                        data: formData != null? formData: false,
		                        success: function(rsp) {        	
		                            
		                            if (!isnull(rsp) && rsp.type == "AjaxException")
		                            	console.log("aj");
		                        		//showAjaxError(rsp.message, '#ajaxException');
	                        		else
	                        			console.log("resp", resp);
		                            /* else if (!isnull(p_code))
		                                p_code(rsp); */
		                        },
		                        error: function(msg) {        	
		                            
		                        	console.log("err msg ", msg);
		                            /* if (!isnull(msg))
		                                showAjaxError(msg.responseText);
		                            if (!isnull(p_error))
		                            	p_error(msg.responseText); */
		                        }
		                    });
		                }
		                
		                /*]]>*/
		            </script>