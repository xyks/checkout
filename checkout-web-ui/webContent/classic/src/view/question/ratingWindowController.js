Ext.define('App.view.question.ratingWindowController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.question-ratingWindow',

    onFormCancel: function() {
    	var me = this;
    	var view = me.getView();
        me.lookupReference('ratingWindowForm').getForm().reset();
        view.hide();
    },

    onFormSubmit: function() {

    	Ext.Msg.alert('function is in progress');
    	/*var me = this;
    	var view = me.getView();
        var formPanel = me.lookupReference('ratingWindowForm'),
            form = formPanel.getForm();

        if (form.isValid()) {
            // In a real application, this would submit the form to the configured url
            // form.submit();
           // form.reset();
            view.hide();
            Ext.MessageBox.alert(
                'Thank you!',
                'Your inquiry has been sent. We will respond as soon as possible.'
            );
        }*/
    }

});
