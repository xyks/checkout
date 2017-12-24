Ext.define('App.view.favorite.favoriteController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.favorite-favorite',



    onRefreshButtonClick: function(){
    	var me = this;
    	me.getViewModel().getStore('favoriteQuestionStore').reload();
    },

    onRemoveButtonClick: function(){
    	var me = this;
    	var grid = me.lookupReference('favoriteQuestionGrid');
    	var selected = grid.getSelection();
    	var data = [];
    	Ext.each(selected,function(item, index){
    		data.push(item.id);
    	});
    	
    	Ext.Ajax.request({
    		url: 'http://localhost:9001/question/following',
    		jsonData: data,
    		method: 'DELETE',
    		success: function(response, opts){
    			//var result = Ext.decode(response.responseText);
    			Ext.Msg.alert('Status','Remove successfully.');
    			me.onRefreshButtonClick();

    		},
    		failure: function(response, opts){
				Ext.Msg.alert('Status','Failed to remove from favorite list, please try again.');
    		},


    	});
    }
});
