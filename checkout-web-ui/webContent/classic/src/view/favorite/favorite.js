
Ext.define('App.view.favorite.favorite',{
    extend: 'Ext.panel.Panel',

    xtype: 'favorite',

    requires: [
        'App.view.favorite.favoriteController',
        'App.view.favorite.favoriteModel'
    ],

    controller: 'favorite-favorite',
    viewModel: {
        type: 'favorite-favorite'
    },

    items: [{
    //question list
        xtype: 'grid',
        reference: 'favoriteQuestionGrid',
        title: 'Favorite list',
        minHeight: 300,
        maxHeight: 700,
        scrollable: 'y',
        bind: {
            store: 'favoriteQuestionStore',
        },
        
        columnLines: true,
        selModel: {
            type: 'checkboxmodel',
            checkOnly: true
        },
        tools: [{
            type: 'minus',
            handler: 'onRemoveButtonClick',
            tooltip: 'Remove from favorite list'
        },{
                type: 'refresh',
                handler: 'onRefreshButtonClick',
                tooltip: 'Refresh'
        }],
        columns: [{
            text: "ID",
            dataIndex: 'id',

            width: 100
        }, {
            text: 'Category',
            dataIndex: 'categoryName',
            width: 100
        },{
            text: "Question",
            dataIndex: 'title',
            miniWidth: 200,
            flex: 1
        }]

    }]
});
