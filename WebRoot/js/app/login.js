Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget='side';
	Ext.Loader.setConfig({
		enabled:true
	});
	Ext.application({
		name:'CM',
		appFolder:'js/app',
		launch:function() {
			var width = 545;
			var height = 330;
			var x = window.screen.availWidth / 2 - width / 2;
			var y = window.screen.availHeight / 2 - height / 2;
			Ext.create('Ext.container.Viewport', {
				layout:'absolute',
				cls:'login_bg',
				items: [{
					x:x,
					y:y,
					width : width,
					height : height,
					xtype:'loginform'
				}]
			});
		},
		controllers:[
			'LoginCtrl'
		]
	});
});