Ext.define('App.view.question.detailsController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.question-details',

    onLikeButtonClick: function(){
    	var me  = this;
    	var questionId = me.getViewModel().get('question').id;
    	Ext.Ajax.request({
    		url: 'question/following/'+questionId,
    		method: 'POST',
    		success: function(response, opts){
    			//var result = Ext.decode(response.responseText);
    			Ext.Msg.alert('Status','Add to favorite list successfully.');
    			me.getView().fireEvent('activate');
    		},
    		failure: function(response, opts){
				Ext.Msg.alert('Status','Failed to add to favorite list, please try again.');
    		},


    	});
    },

    onRatingButtonClick: function(){
    	var me  = this;
    	var win = me.lookupReference('ratingWindow');
    	if (!win) {
    		win = new App.view.question.ratingWindow();
    		me.getView().add(win);
    	}
    	win.show();
    },

    onEditButtonClick: function(){
    
    	var me  = this;
    	var win = me.lookupReference('basicWindow');
    	if (!win) {
    		win = new App.view.question.basicWindow();
    		me.getView().add(win);
    	}

    	win.getViewModel().set('basic', Ext.copy({},me.getViewModel().get('question'),'id,title,answer,categoryId'));
    	win.show();
    },

    onLoadData: function(){
    	var me  = this, vm = me.getViewModel();
    	var questionId = vm.get('question').id;
    	Ext.Ajax.request({
    		url: 'question/details/'+questionId,
    		method: 'GET',
    		success: function(response, opts){
    			var result = Ext.decode(response.responseText);
    			//Ext.Msg.alert('Status','Add to favorite list successfully.');
    			vm.set('question', result);
    		},
    		failure: function(response, opts){
				Ext.Msg.alert('Status','Failed to get data, please try again.');
    		},


    	});
    }


});
