
Ext.define('App.view.profile.profile',{
    extend: 'Ext.panel.Panel',

    requires: [
        'App.view.profile.profileController',
        'App.view.profile.profileModel'
    ],

    xtype: 'profile',

    controller: 'profile-profile',
    viewModel: {
        type: 'profile-profile'
    },

    layout: {
        type: 'vbox',
        align: 'center'
    },
    defaults: {
        margin: '10 0 0 0',
        width: 320
    },
    items: [{

        xtype: 'form',
        reference: 'loginForm',
        bind: {
            hidden: '{isOnline}'
        },
        title: 'Login',
        frame:true,
        //width: 320,
        bodyPadding: 10,

        defaultType: 'textfield',

        items: [{
            allowBlank: false,
            fieldLabel: 'User name',
            name: 'name',
            emptyText: 'user name'
        }, {
            allowBlank: false,
            fieldLabel: 'Password',
            name: 'password',
            emptyText: 'password',
            inputType: 'password'
        }],
        buttonAlign: 'center',
        buttons: [
            /*{ text:'Register' },*/
            { text:'Login' ,
              handler: 'onLoginButtonClick'  
            }
        ],

        defaults: {
            anchor: '100%',
            labelWidth: 120
        }
    },{
        xtype: 'button',
        text: 'Logout',
        handler: 'onLogoutButtonClick',
        bind: {
            hidden: '{!isOnline}'
        },
    }]
});
