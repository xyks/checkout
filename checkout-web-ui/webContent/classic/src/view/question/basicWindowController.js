Ext.define('App.view.question.basicWindowController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.question-basicwindow',

    onFormCancel: function() {
    	var me = this;
    	var view = me.getView();
        me.lookupReference('basicWindowForm').getForm().reset();
        view.hide();
    },

    onFormSubmit: function() {
    	var me = this;
    	var view = me.getView();
        var formPanel = me.lookupReference('basicWindowForm'),
            form = formPanel.getForm();

        if (form.isValid()) {
	    	var data = me.getViewModel().get('basic');

	    	var isAdd = !data.id;

	    	Ext.Ajax.request({
	    		url: 'question',
	    		jsonData: data,
	    		method: isAdd? 'POST': 'PUT',
	    		success: function(response, opts){
	    			//var result = Ext.decode(response.responseText);
	    			Ext.Msg.alert('Status','Submit successfully.');

	    			if (!isAdd) {
						view.up('details').fireEvent('activate');
	    			}
	    			

	    		},
	    		failure: function(response, opts){
					Ext.Msg.alert('Status','Failed to submit, please try again.');
	    		},


	    	});
            view.hide();
        }
    }

});
