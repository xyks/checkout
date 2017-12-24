Ext.define('App.view.question.basicWindowModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.question-basicwindow',
    data: {
        name: 'App'
    },

    stores: {
    	categoryStore: {
    		storeId: 'categoryStore',
    		model: 'App.model.Category',
    		autoLoad: true,

    		proxy: {
    			type: 'ajax',
    			url: 'category',
    			reader: {
    				type: 'json'
    			}
    		}
    	},


    }

});
