Ext.define('App.view.search.searchController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.search-search',

    onSearchButtonClick: function(){
    	var me = this, vm = me.getViewModel();
    	var searchCategory = me.lookupReference('searchCombo').getValue();
    	var searchValue = me.lookupReference('searchInputText').getValue();
    	vm.set('searchCategory',searchCategory);
    	vm.set('searchValue',searchValue);
    	vm.notify();
    	if(searchCategory && searchValue ){
    		vm.getStore('searchQuestionStore').reload();
    	}
    },

    onCellClick ( view, td, cellIndex, record){
       
        var me = this;
        if (cellIndex == 0) {
            me.addTab(record);
        }
    },

    addTab: function(record) {
        var me = this;
        var tabPanel = me.getView();
          
        var questionId = record.get('id');    
        var tab = tabPanel.add({
                xtype: 'details',
                closable: true,
                title: questionId,
                viewModel: {
                    data: {
                        question: record.getData()
                    }
                }
            });
        tabPanel.setActiveTab(tab);
    },

    onAddButtonClick: function(){
        var me  = this;
        var win = me.lookupReference('basicWindow');
        if (!win) {
            win = new App.view.question.basicWindow();
            me.getView().add(win);
        }

        win.getViewModel().set('basic', {});
        win.show();
    }

});
