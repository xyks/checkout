
Ext.define('App.view.record.record',{
    extend: 'Ext.panel.Panel',

    requires: [
        'App.view.record.recordController',
        'App.view.record.recordModel'
    ],

    xtype: 'record',

    controller: 'record-record',
    viewModel: {
        type: 'record-record'
    },

    scrollable: 'y',
    layout: {
        type: 'vbox',
        align: 'stretch'
    },

    items: [{
        xtype: 'panel',
        height: 250,
        layout: {
            type: 'hbox',
            align: 'stretch'
        },
        tbar: [{
            xtype: 'displayfield',
            fieldLabel: 'All',
             bind: {
                value: '{statistics.all}'
            }
        },{
            xtype: 'displayfield',
            fieldLabel: 'Checked',
            bind: {
                value: '{statistics.checked}'
            }
        },{

            xtype: 'displayfield',
            fieldLabel: 'Unchceked',
             bind: {
                value: '{statistics.unchecked}'
            }
        },{
            xtype: 'displayfield',
            fieldLabel: 'Clear',
             bind: {
                value: '{statistics.clear}'
            }           
        },{
            xtype: 'displayfield',
            fieldLabel: 'Unclear',
            bind: {
                value: '{statistics.unclear}'
            }            
        }],
        defaults: {
            margin: 10
        },
        items:[{
        xtype: 'gauge',
        flex: 1,
        value: 50,
        trackStart: 0,
        trackLength: 360,
        bind: {
            value: '{statistics.checkedPercentage}',
        },
        textTpl: Ext.create('Ext.XTemplate', 
            '<tpl>Progress: {value:number("0.00")}% </tpl>'
        )
        
    },{
        xtype: 'gauge',
        flex: 1,

        trackStart: 0,
        trackLength: 360,
        bind: {
            value: '{statistics.clearPercentage}',
        },
        textTpl: Ext.create('Ext.XTemplate', 
            '<tpl>Clear: {value:number("0.00")}% </tpl>'
        )
    }]
    },{

        xtype: 'grid',
        title: 'Training record',
        minHeight: 300,
        maxHeight: 500,
        scrollable: 'y',
        bind: {
            store: 'recordQuestionStore'
        },

        tools: [{
                type: 'refresh',
                handler: 'onRefreshButtonClick',
                tooltip: 'Refresh'
            }],
        columns: [{
            text: "ID",
            dataIndex: 'id',
            width: 100
        }, {
            text: "Question",
            dataIndex: 'title',
            miniWidth: 200,
            flex: 1
        },{
            text: 'Clear',
            dataIndex: 'pass',
            width: 100
        },{
            text: 'Category',
            dataIndex: 'categoryName',
            width: 100
        }],
         buttonAlign: 'center',
        fbar: [{
            minWidth: 80,
            text: 'Clear all records',
            handler: 'onClearAllButtonClick'

        }]

    }],

    listeners: {
        reloadData : 'onReloadData'
    }
});
