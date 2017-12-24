Ext.define('App.view.record.recordController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.record-record',

    onReloadData: function(){
    	var me = this;
    	me.loadStatistics();
    	me.onRefreshButtonClick();
    },


    loadStatistics: function(){
    	var me = this;
    	Ext.Ajax.request({
    		url: 'question/training/statistics',
    		method: 'GET',
    		success: function(response, opts){
    			var result = Ext.decode(response.responseText);
    			me.getViewModel().set('statistics',result);
    			me.getViewModel().notify();
    		},
    		failure: function(response, opts){
				//Ext.Msg.alert('Status','Failed to submit, please try again.');
    		},


    	});
    },

    onRefreshButtonClick: function(){
    	var me = this;
    	me.getViewModel().getStore('recordQuestionStore').reload();
    },

    onClearAllButtonClick: function(){
    	var me = this;
    	Ext.Ajax.request({
    		url: 'question/training/all',
    		method: 'DELETE',
    		success: function(response, opts){
    			//var result = Ext.decode(response.responseText);
    			Ext.Msg.alert('Status','Clear all training records successfully.');

    			me.onReloadData();

    		},
    		failure: function(response, opts){
				Ext.Msg.alert('Status','Failed to clear all training records, please try again.');
    		},


    	});
    }
});
