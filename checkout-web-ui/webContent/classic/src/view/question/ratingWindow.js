
Ext.define('App.view.question.ratingWindow',{
    extend: 'Ext.window.Window',

    xtype: 'rating-window',

    requires: [
        'App.view.question.ratingWindowController',
        'App.view.question.ratingWindowModel'
    ],

    controller: 'question-ratingWindow',
    viewModel: {
        type: 'question-ratingWindow'
    },

    reference: 'ratingWindow',

    title: 'Rating',
    width: 300,
    height: 250,
    minWidth: 250,
    minHeight: 250,
    layout: 'fit',
    modal: true,
    closeAction: 'hide',
    items: [{
        xtype: 'form',
        reference: 'ratingWindowForm',
        border: false,
        bodyPadding: 10,

        fieldDefaults: {
            msgTarget: 'side',
            labelAlign: 'top',
            labelWidth: 100,
            labelStyle: 'font-weight:bold'
        },
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        items: [{
            xtype: 'rating'
        },{
            xtype: 'textareafield',
            fieldLabel: 'Comment',
            labelAlign: 'top',
            flex: 1,
            margin: '0',
            afterLabelTextTpl: [
                '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
            ],
            allowBlank: false
        }]
    }],
    buttonAlign: 'center',
    buttons: [{
        text: 'submit',
        handler: 'onFormSubmit'
    },{
        text: 'Cancel',
        handler: 'onFormCancel'
    }]

});
