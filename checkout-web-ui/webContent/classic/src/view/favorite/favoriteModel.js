Ext.define('App.view.favorite.favoriteModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.favorite-favorite',

    stores: {


    	favoriteQuestionStore: {
    		storeId: 'favoriteQuestionStore',
    		model: 'App.model.Question',
    		autoLoad: true,

    		proxy: {
    			type: 'ajax',
    			url: 'http://localhost:9001/question/following/all',
    			reader: {
    				type: 'json'
    			}
    		}
    	}
    }

});
