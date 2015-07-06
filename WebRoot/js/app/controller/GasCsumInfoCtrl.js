/**
 * ClassName：用户信息控制器
 */
Ext.define("CM.controller.GasCsumInfoCtrl", {
	extend:'Ext.app.Controller',
	init:function(app) {
		var addWin;
		this.control({
			'gascsumlist button[itemId=upload]':{
				click:function() {
					if(!addWin) {
						addWin = Ext.create("CM.view.form.GasCsumAddWin");
					}
					addWin.down('form').getForm().reset();
					addWin.show();
				}
		    },
		    'gascsumlist actioncolumn[itemId=file]': {
		     	click:this.download
		    },
			'gascsumaddwin button[itemId=submit]':{
				click:this.submitForm
			},
			'gascsumaddwin button[itemId=cancel]':{
				click:this.closeWin
			},
			'gascsumaddwin filefield[itemId=file]':{
				change:function(field, value, eOpts) {
					var filename = value.substring(value.lastIndexOf('\\') + 1, value.lastIndexOf('.'))
					addWin.down('form').down('#filename').setValue(filename);
				}
			}
     	});
     	this.addTab();
	},
	//提交表单
	submitForm: function(btn, e, eOpts) {
		var sForm = btn.up('form');
		var user = Ext.decode(Ext.util.Cookies.get('user'));
		if(sForm.getForm().isValid()){
			if(sForm.itemId.toLowerCase().indexOf('add') > -1) {
				var urlAction = 'consumable_addGasCsum.action';
			} else if(sForm.itemId.toLowerCase().indexOf('modify') > -1) {
				var urlAction = 'consumable_updateInfo.action';
			}
			sForm.submit({
				url:urlAction,
				method:'POST',
				params:{
	  				userId:user.userId
	  			},
				success:function(form, action){
					var recGrid = Ext.getCmp('gasCsumList');
					Ext.Msg.alert("提示", action.result.msg, function(){
						form.owner.up('window').hide();
						recGrid.getStore().load();
					});
				},
				failure:function(form, action){
					Ext.Msg.alert("提示", action.result.errorMessage);
				}
			})
		}
	},
	//下载
	download: function(grid, rec, index, column, e, eOpts) {
		var recs = grid.getStore().getRange();
		var path = recs[index].get('path');
		window.open('consumable_gasFileDownload.action?path=' + path);
	},
	//主选项卡添加tab
	addTab:function() {
		var mainTab = Ext.getCmp('mainTab');
		//向mainTab的items项中,添加的item
		mainTab.add({ 
			itemId: 'gasCsumInfoCtrl', 
    		title: '气体耗材',
    		layout:'border',
    		items:[{
    			region:'center',
    			width:'100%',
    			height:'100%',
    			xtype: 'gascsumlist'
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
		'grid.GasCsumList'
	],
	stores:[
		'GasCsumStore'
	],
	models:[
		'GasCsumModel'
	]
});
