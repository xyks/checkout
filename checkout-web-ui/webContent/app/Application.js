/**
 * The main application class. An instance of this class is created by app.js when it
 * calls Ext.application(). This is the ideal place to handle application launch and
 * initialization details.
 */
Ext.define('App.Application', {
    extend: 'Ext.app.Application',

    name: 'App',

    quickTips: false,
    platformConfig: {
        desktop: {
            quickTips: true
        }
    },

    stores: [
        // TODO: add global / shared stores here
    ],

    launch: function () {

        Ext.Ajax.on('beforerequest',function(conn , options){
            options.headers = options.headers || {};
            //options.headers['just_for_test'] = 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwicm9sZSI6IkFkbWluIn0.SMXQfNi7qtgbY5aEkpk6qJIQxGRgwDGxE-itLw86fpXmwe9N3rwfxf-sJLtnjGExPEZi_yqXaKMPk8XjKJ95UQ';
        });
    },

    onAppUpdate: function () {
        Ext.Msg.confirm('Application Update', 'This application has an update, reload?',
            function (choice) {
                if (choice === 'yes') {
                    window.location.reload();
                }
            }
        );
    }
});
