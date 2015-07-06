/**
 * ClassName：用户信息控制器
 */
Ext.define("CM.controller.SSOutInRecCtrl", {
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
			itemId: 'sSOutInRecCtrl', //向mainTab的items项中,添加的itemId
    		title: '标样出入库记录',
    		layout:'border',
    		items:[{
    			region:'center',
    			width:'100%',
    			height:'100%',
    			xtype: 'ssoutinrec'
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
		'grid.SSOutInRec'
	],
	stores:[
		'SSOutInRecStore'
	],
	models:[
		'SSOutInRecModel'
	]
});
