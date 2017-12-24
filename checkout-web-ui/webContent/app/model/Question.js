

Ext.define('App.model.Question', {
    extend: 'Ext.data.Model',

    fields: [
        {name: 'id', type: 'int'},
        {name: 'title', type: 'string' },
        {name: 'answer', type: 'string'},
        {name: 'answer', type: 'string'},
        {name: 'avgRating', type: 'int'},
        {name: 'myRating', type: 'int'},
        {name: 'categoryName', type: 'string'}

    ]
        
});