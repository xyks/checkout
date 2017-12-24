
Ext.define('App.view.question.details',{
    extend: 'Ext.panel.Panel',

    xtype: 'details',

    requires: [
        'App.view.question.detailsController',
        'App.view.question.detailsModel'
    ],

    controller: 'question-details',
    viewModel: {
        type: 'question-details'
    },
    scrollable: 'y',
    maxHeight: 700,
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    defaults: {
        xtype: 'displayfield',
        labelWidth: 200
    },
    items: [{
        xtype: 'container',
        layout: {
            type: 'hbox',
            pack: 'center'
        },
        items: [{
            xtype: 'button',
            iconCls: 'x-fa fa-heart',
            text: 'Like',
            handler: 'onLikeButtonClick'
        },{
            xtype: 'button',
            margin: '0 0 0 10',
            iconCls: 'x-fa fa-star-half-o',
            text: 'Rating',
            handler: 'onRatingButtonClick'
        },{
            xtype: 'button',
            margin: '0 0 0 10',
            iconCls: 'x-fa fa-pencil-square-o',
            text: 'Edit',
            handler: 'onEditButtonClick',
            bind: {
                hidden: '{!isAdmin}'
            }
        }]
    },{
        fieldLabel: 'ID',
        bind: {
            value: '{question.id}'
        }
    },
    {
        fieldLabel: 'Title',
        bind: {
            value: '{question.title}'
        }
    },{
        xtype: 'textareafield',
        height: 150,
        //flex: 1,
        readOnly: true,
        fieldLabel: 'Answer',
        bind: {
            value: '{question.answer}'
        }
    },{
        fieldLabel: 'Category',
        bind: {
            value: '{question.categoryName}'
        }
    },{
        fieldLabel: 'Create by',
        bind: {
            value: '{question.createByName}'
        }
    },{
        fieldLabel: 'Create date',
        bind: {
            value: '{question.createDate}'
        }
    },{
        fieldLabel: 'Modify by',
        bind: {
            value: '{question.modifyByName}'
        }
    },{
        fieldLabel: 'Modify date',
        bind: {
            value: '{question.modifyDate}'
        }
    },{
        fieldLabel: 'Average rating score',
        bind: {
            value: '{question.avgRatingScore}'
        }
    },{
        fieldLabel: 'My rating score',
        bind: {
            value: '{question.mineRatingScore}'
        }
    },{
        fieldLabel: 'Favorite number',
        bind: {
            value: '{question.followingNumber}'
        }
    },{
        fieldLabel: 'My favorite',
        bind: {
            value: '{question.following}'
        }
    
    }],
    listeners: {
        activate  : 'onLoadData'
    }
});
