Ext.define('App.view.profile.profileController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.profile-profile',


    onLoginButtonClick: function(){
    	var me = this;
    	var formPanel = me.lookupReference('loginForm'),
             form = formPanel.getForm();

    	Ext.Ajax.request({
    		url: 'login',
    		method: 'POST',
    		jsonData: form.getValues(),
    		success: function(response, opts){
    			var vm = me.getViewModel();
    			vm.set('isOnline', true);
    			form.reset();

    			var result = response.responseText;
    			if (result == 'admin') {
    				var mainVm = me.getView().up('app-main').getViewModel();
    				mainVm.set('isAdmin', true);
    			}

    		},
    		failure: function(response, opts){
				Ext.Msg.alert('Status','Failed to login, please try again.');
    		},


    	});
    },

    onLogoutButtonClick: function(){
    	var me = this;
    	var mainVm = me.getView().up('app-main').getViewModel();
    	var vm = me.getViewModel();
    	vm.set('isOnline', false);
    	mainVm.set('isAdmin', false);
        document.cookie = "helper=";
    }

});
