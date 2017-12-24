
Ext.define('App.view.search.search',{
    extend: 'Ext.tab.Panel',

    requires: [
        'App.view.search.searchController',
        'App.view.search.searchModel'
    ],

    xtype: 'search',

    controller: 'search-search',
    viewModel: {
        type: 'search-search'
    },
    scrollable: 'y',
    bodyPadding: 10,
    items: [{
        xtype: 'panel',
        title: 'Management',
        layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [{
        xtype: 'container',
        layout: {
            type: 'hbox',
            align: 'stretch'
        },
        defaults: {
             //margin: '0 10 0 0',
        },
        items: [{
            xtype: 'combo',
            reference: 'searchCombo',
            fieldLabel: 'Search By',
            queryMode: 'local',
            valueField: 'key',
            displayField: 'value',
            bind:{
                 store: 'searchCategoryStore'
            },
            margin: '0 10 0 0'
           
        },{
            xtype: 'textfield',
            reference: 'searchInputText',
            minWidth: 200

        },{
           xtype: 'button',
          // text: 'Search',
           iconCls: 'x-fa fa-search',
           handler: 'onSearchButtonClick' 
        },
        {
            xtype: 'button',
            margin: '0 0 0 10',
            //text: 'Add question',
             iconCls: 'x-fa fa-plus',
            handler: 'onAddButtonClick',
            bind: {
                hidden: '{!isAdmin}'
            }

        }

        ]
    },{

        xtype: 'grid',
        //title: 'Search result',
        margin: '10 0 0 0',
        bind: {
            store: 'searchQuestionStore'
        },
        minHeight: 300,
        maxHeight: 700,
        scrollable: 'y',
        columns: [ {
            text: "ID",
            dataIndex: 'id',
            width: 100,
            renderer: function(value){
                return '<a href="#">'+value+'</a>';
            }
            
        }, {
            text: "Title",
            dataIndex: 'title',
            miniWidth: 200,
            flex: 1
        },{
            text: 'Category',
            dataIndex: 'categoryName',
            width: 100
        }],
        listeners: {
            cellclick : 'onCellClick' 
        }
        

    }]
    }

    ]
    
});
