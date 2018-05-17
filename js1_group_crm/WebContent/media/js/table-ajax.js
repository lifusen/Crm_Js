var TableAjax = function () {
		var grid;
		var datatableSelector;
		var url;
		
		

    var initPickers = function () {
        //init date pickers
        $('.date-picker').datepicker({
            rtl: Metronic.isRTL(),
            autoclose: true
        });
    }
    
    var newTable = function() {
    	grid = new Datatable();
    }

    var handleRecords = function () {
        grid.init({
            //src: $("#datatable_ajax"),
        		src: $(datatableSelector),
            onSuccess: function (grid) {
                // execute some code after table records loaded
            },
            onError: function (grid) {
                // execute some code on network or other general error  
            },
            dataTable: { // here you can define a typical datatable settings from http://datatables.net/usage/options 
                "lengthMenu": [
                    [5, 10, 20, 50, 100, 2147483647],
                    [5, 10, 20, 50, 100, "所有"] // change per page values here
                ],
                "pageLength": 10, // default record count per page
                "ajax": {
                    //"url": "project/query", // ajax source
                		"url": url, // ajax source
                },
//                "order": [
//                    [1, "asc"]
//                ] // set first column as a default sort by asc
            }
            
        });
        

        // handle group actionsubmit button click
        grid.getTableWrapper().on('click', '.table-group-action-submit', function (e) {
            e.preventDefault();
            var action = $(".table-group-action-input", grid.getTableWrapper());
            if (action.val() != "" && grid.getSelectedRowsCount() > 0) {
                grid.setAjaxParam("customActionType", "group_action");
                grid.setAjaxParam("customActionName", action.val());
                grid.setAjaxParam("id", grid.getSelectedRows());
                grid.getDataTable().ajax.reload();
                grid.clearAjaxParams();
            } else if (action.val() == "") {
                Metronic.alert({
                    type: 'danger',
                    icon: 'warning',
                    message: 'Please select an action',
                    container: grid.getTableWrapper(),
                    place: 'prepend'
                });
            } else if (grid.getSelectedRowsCount() === 0) {
                Metronic.alert({
                    type: 'danger',
                    icon: 'warning',
                    message: 'No record selected',
                    container: grid.getTableWrapper(),
                    place: 'prepend'
                });
            }
        });
    }

    return {

        //main function to initiate the module
        init: function (dtSelector, ajaxUrl) {
        		datatableSelector = dtSelector;
        		url = ajaxUrl;

            initPickers();
            newTable();
            handleRecords();
        },
        
        initWithoutLoading: function (dtSelector, ajaxUrl) {
    		datatableSelector = dtSelector;
    		url = ajaxUrl;
	
	        //initPickers();
    		newTable();
	        //handleRecords();
	    },
	    
	    setAjaxParam: function(key, value) {
	    	grid.setAjaxParam(key, value);
	    },
	    
	    load: function() {
	    	handleRecords();
	    },
        
        getDatatable: function() {
        		return grid;
        }

    };

}();