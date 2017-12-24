Ext.define('App.view.training.trainingModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.training-training',
    data: {
        categoryId: null
    },

    stores: {

    	trainingQuestionStore: {
    		storeId: 'trainingQuestionStore',
    		model: 'App.model.Question',
    		autoLoad: false,

    		proxy: {
    			type: 'ajax',
    			url: 'question/category/{categoryId}/training',
    			reader: {
    				type: 'json'
    			}
    		}
    	},

    	trainingCategoryStore: {
    		storeId: 'trainingCategoryStore',
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
