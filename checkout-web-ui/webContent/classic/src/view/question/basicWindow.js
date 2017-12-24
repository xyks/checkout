
Ext.define('App.view.question.basicWindow',{
    extend: 'Ext.window.Window',

    xtype: 'basicWindow',

    

    requires: [
        'App.view.question.basicWindowController',
        'App.view.question.basicWindowModel'
    ],

    controller: 'question-basicwindow',
    viewModel: {
        type: 'question-basicwindow'
    },

    reference: 'basicWindow',

    title: 'Edit',
    width: 600,
    height: 500,
    minWidth: 250,
    minHeight: 400,
    layout: 'fit',
    modal: true,
    closeAction: 'hide',
    items: [{
        xtype: 'form',
        reference: 'basicWindowForm',
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
            xtype: 'textfield',
            fieldLabel: 'Title',
            afterLabelTextTpl: [
                '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
            ],
            bind: {
                value: '{basic.title}'
            },
            allowBlank: false
        },{
            xtype: 'textareafield',
            fieldLabel: 'Answer',
            flex: 1,
            margin: '0',
            afterLabelTextTpl: [
                '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
            ],
            bind: {
                value: '{basic.answer}'
            },
            allowBlank: false
        },{
            xtype: 'combo',
            fieldLabel: 'Category',
            bind: {
                store: 'categoryStore',
                 value: '{basic.categoryId}'
            },
            valueField: 'id',
            tpl: Ext.create('Ext.XTemplate', '<ul class="x-list-plain"><tpl for=".">',
                    '<li role="option" class="x-boundlist-item"> <tpl for="levelArray">&nbsp;&nbsp;&nbsp;&nbsp;</tpl> {name} </li>',
                    '</tpl></ul>'
            ),
            displayTpl: Ext.create('Ext.XTemplate',
                    '<tpl for=".">',
                        '{name}',
                    '</tpl>'    
            )
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
