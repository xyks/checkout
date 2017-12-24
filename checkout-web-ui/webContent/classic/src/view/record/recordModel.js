Ext.define('App.view.record.recordModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.record-record',
    data: {
        statistics: {
        	
        }
    },

    stores: {


    	recordQuestionStore: {
    		storeId: 'recordQuestionStore',
    		model: 'App.model.Question',
    		autoLoad: true,

    		proxy: {
    			type: 'ajax',
    			url: 'question/training/all',
    			reader: {
    				type: 'json'
    			}
    		}
    	}
    }

});
