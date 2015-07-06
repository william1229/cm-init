/**
 * ClassName：用户信息控制器
 */
Ext.define("CM.controller.TrainingRecCtrl", {
	extend:'Ext.app.Controller',
	init:function(app) {
		var addWin;
		this.control({
			'trainingrec actioncolumn[itemId=file]': {
		     	click:this.download
		    }
/*			'trainingrec button[itemId=add]':{
				click:function() {
					if(!addWin) {
						addWin = Ext.create("CM.view.form.TrainingRecAddWin");
					}
					addWin.down('form').getForm().reset();
					addWin.show();
				}
		    },
			'trainingrecaddwin button[itemId=submit]':{
				click:this.submitForm
			},
			'trainingrecaddwin button[itemId=cancel]':{
				click:this.closeWin
			},
			'trainingrecaddwin textfield[itemId=name]':{
				focus:function() {
					var userNameWin = Ext.getCmp('userNameWin');
					if(!userNameWin) {
						userNameWin = Ext.create("CM.view.grid.UserNameWin");
					}
					userNameWin.show();
				}
			},
			'usernamewin actioncolumn[itemId=operation]':{
				click: this.selectName
		     }*/
     	});
     	this.addTab();
	},
	//提交表单
	submitForm: function(btn, e, eOpts) {
		var sForm = btn.up('form');
		if(sForm.getForm().isValid()){
			if(sForm.itemId.toLowerCase().indexOf('add') > 0) {
				var urlAction = 'trainingRec_addTrainingRec.action';
			} else if(sForm.itemId.toLowerCase().indexOf('modify') > 0) {
				var urlAction = 'trainingRec_updateInfo.action';
			}
			sForm.submit({
				url:urlAction,
				method:'POST',
				success:function(form, action){
					var recGrid = Ext.getCmp('trainingRec');
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
		window.open('trainingRec_summaryDownload.action?path=' + path);
	},
	//选取姓名
	selectName: function(grid, rec, index, column, e, eOpts) {
		var recs = grid.getStore().getRange();
		var name = recs[index].get('name');
		var userId = recs[index].get('userId');
		var win = Ext.getCmp('trainingRecAddWin');
		grid.up('window').hide();
		win.down('#name').setValue(name);
		win.down('#userId').setValue(userId);
	},
	//主选项卡添加tab
	addTab:function() {
		var mainTab = Ext.getCmp('mainTab');
		//向mainTab的items项中,添加的item
		mainTab.add({ 
			itemId: 'trainingRecCtrl', 
    		title: '培训记录',
    		layout:'border',
    		items:[{
    			region:'center',
    			width:'100%',
    			height:'100%',
    			xtype: 'trainingrec'
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
		'grid.TrainingRec'
	],
	stores:[
		'TrainingRecStore'
//		'NameStore'
	],
	models:[
		'TrainingRecModel'
//		'NameModel'
	]
});
