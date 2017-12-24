
Ext.define('App.view.training.training',{
    extend: 'Ext.panel.Panel',

    xtype: 'training',

    requires: [
        'App.view.training.trainingController',
        'App.view.training.trainingModel'
        
    ],

    controller: 'training-training',
    viewModel: {
        type: 'training-training'
    },

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
     scrollable: 'y',
    defaults: {
        margin: '10 0 0 0'
    },
    items:[{
      xtype: 'container',
      layout: {
        type: 'hbox',
        aligin: 'stretch'
      }, 
      items: [{
        xtype: 'combo',
        reference: 'categoryCombo',
        bind: {
            store: 'trainingCategoryStore'
        },
        fieldLabel: 'Select Category',
        //labelAlign: 'top',
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
      },{
         xtype: 'button',
         //margin: '0 0 0 10',
         iconCls: 'x-fa fa-random',
         //text: 'Load',
        // minWidth: 80,
         handler: 'onReloadButtonClick' 
      }]
        
     
    },{
    //question list
        xtype: 'grid',
        reference: 'questionGrid',
        title: 'Question',
        bind: {
            store: 'trainingQuestionStore',
        },
        
        columnLines: true,
        selModel: {
            type: 'checkboxmodel',
            checkOnly: true
        },
        minHeight: 300,
        maxHeight: 700,
        scrollable: 'y',
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
            text: "Category",
            dataIndex: 'categoryName',
            width: 100
        },{
            xtype: 'actioncolumn',
            text: 'Favorite',
            items:[{
                
                getClass: function(v, metadata, r){
                    if(r.get('following') == true){
                        return 'x-fa fa-heart';
                    }
                }
            }]
        }/*,{
            text: 'Rating',
            columns: [{
                xtype: 'widgetcolumn',
                text: 'AVG',
                width: 100,
                dataIndex: 'avgRating',
                widget: {
                    xtype: 'rating',
                    overStyle: 'color: orange'
                }
            },{
                xtype: 'widgetcolumn',
                text: 'Mine',
                width: 100,
                dataIndex: 'myRating',
                widget: {
                    xtype: 'rating',
                    selectedStyle: 'color: gray',
                    overStyle: 'color: green'
                }
            }]
        }*/],
        plugins: [{
            ptype: 'rowwidget',
            widget: {
               xtype: 'textareafield',
                labelAlign: 'top',
              // minHeight: 300,
                grow: true,
                growMin: 300, 
                growMax: 500,
                readOnly: true,
                fieldLabel: 'Answer',
                bind: {
                    value: '{record.answer}'
                }
            }
        }/*{
            ptype: 'rowexpander',
            rowBodyTpl : new Ext.XTemplate(
                '<p>{answer}</p>'
           )
        }*/],
        buttonAlign: 'center',
        fbar: [{
            minWidth: 80,
            text: 'Submit',
            handler: 'onSubmitButtonClick'

        }]


    }]
});
