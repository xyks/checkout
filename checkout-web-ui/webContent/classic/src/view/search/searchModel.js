Ext.define('App.view.search.searchModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.search-search',
    data: {
        searchCategory: '',
        searchValue: ''
    },

    stores: {

    	searchCategoryStore: {
    		storeId: 'searchCategoryStore',
    		fields: ['key','value'],
    		autoLoad: true,

    		data: [
    			
    			{ key: 'title', value: 'Title'},
                { key: 'id', value: 'Question ID'}
    			]
    		
    	},

    	searchQuestionStore: {
    		storeId: 'searchQuestionStore',
    		model: 'App.model.Question',
    		autoLoad: false,

    		proxy: {
    			type: 'ajax',
    			url: 'question/search/{searchCategory}/{searchValue}',
    			reader: {
    				type: 'json'
    			}
    		}
    	},
    }

});
