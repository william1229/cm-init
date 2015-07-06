/**
 * ClassName：用户信息控制器
 */
Ext.define("CM.controller.MetricalInfoCtrl", {
	extend:'Ext.app.Controller',
	init:function(app) {
		this.control({
     	});
     	this.addTab();
	},
	//主选项卡添加tab
	addTab:function() {
		var mainTab = Ext.getCmp('mainTab');
		mainTab.add({ 
			itemId: 'metricalInfoCtrl', //向mainTab的items项中,添加的itemId
    		title: '计量信息',
    		layout:'border',
    		items:[{
    			region:'center',
    			width:'100%',
    			height:'100%',
    			xtype: 'metricallist'
    		}],
    		closable : true //允许关闭
		}).show();
	},
	//关闭窗口
	closeWin: function(btn) {
		var win = btn.up('window');
		win.hide();
	},
	views:[
		'grid.MetricalList'
	],
	stores:[
		'MetricalStore'
	],
	models:[
		'MetricalModel'
	]
});
