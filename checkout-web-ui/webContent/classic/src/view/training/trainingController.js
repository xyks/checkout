Ext.define('App.view.training.trainingController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.training-training',

    onReloadButtonClick: function(){
    	var me = this, vm = me.getViewModel();
    	var categoryId = me.lookupReference('categoryCombo').getValue();
    	vm.set('categoryId',categoryId);
    	vm.notify();
    	if(categoryId){
    		vm.getStore('trainingQuestionStore').reload();
    	}
    },

    onSubmitButtonClick: function(){
    	var me = this, vm = me.getViewModel();
    	var grid = me.lookupReference('questionGrid');
    	var selected = grid.getSelection();
    	var all = grid.getStore().getRange();
    	var unSelected = Ext.Array.difference(all,selected);
    	var data = [];
    	Ext.each(selected,function(item, index){
    		var vo = { id: item.id, pass: true};
    		data.push(vo);
    	});
    	Ext.each(unSelected,function(item, index){
    		var vo = { id: item.id, pass: false};
    		data.push(vo);
    	});
    	
    	Ext.Ajax.request({
    		url: 'question/training',
    		jsonData: data,
    		method: 'PUT',
    		success: function(response, opts){
    			//var result = Ext.decode(response.responseText);
    			Ext.Msg.alert('Status','Submit successfully.');

    		},
    		failure: function(response, opts){
				Ext.Msg.alert('Status','Failed to submit, please try again.');
    		},


    	});

    },




});
