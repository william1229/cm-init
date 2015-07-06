/**
 * ClassName：用户信息控制器
 */
Ext.define("CM.controller.AppraisalRecCtrl", {
	extend:'Ext.app.Controller',
	init:function(app) {
/*		var addWin;
		var userNameWin;*/
		Ext.QuickTips.init();
		this.control({
			'appraisalrec actioncolumn[itemId=file]': {
		     	click:this.download
		    }
/*			'appraisalrec button[itemId=add]': {
				click:function() {
					if(!addWin) {
						addWin = Ext.create("CM.view.form.AppraisalRecAddWin");
					}
					addWin.down('form').getForm().reset();
					addWin.show();	
					}
			},
			'appraisalrecaddwin button[itemId=submit]':{
				click:this.submitForm
			},
			'appraisalrecaddwin button[itemId=cancel]':{
				click:this.closeWin
			},
			'appraisalrecaddwin textfield[itemId=name]':{
				focus:function() {
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
		if(sForm.itemId.toLowerCase().indexOf('add') > -1) {
				var urlAction = 'appraisalRec_addAppraisalRec.action';
			} else if(sForm.itemId.toLowerCase().indexOf('modify') > -1) {
				var urlAction = 'appraisalRec_updateInfo.action';
			}
			sForm.submit({
				url:urlAction,
				method:'POST',
				success:function(form, action){
					var recGrid = Ext.getCmp('appraisalRec');
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
	download: function(grid, rec, index, column, e, eOpts) {
		var recs = grid.getStore().getRange();
		var path = recs[index].get('path');
		window.open('appraisalRec_tableDownload.action?path=' + path);
	},
	//选取姓名
	selectName: function(grid, rec, index, column, e, eOpts) {
		var recs = grid.getStore().getRange();
		var name = recs[index].get('name');
		var userId = recs[index].get('userId');
		var win = Ext.getCmp('appraisalRecAddWin');
		grid.up('window').hide();
		win.down('#name').setValue(name);
		win.down('#userId').setValue(userId);
	},
	//主选项卡添加tab
	addTab:function() {
		var mainTab = Ext.getCmp('mainTab');
		mainTab.add({ 
			itemId: 'appraisalRecCtrl', //向mainTab的items项中,添加的itemId
    		title: '考核记录',
    		layout:'border',
    		items:[{
    			region:'center',
    			width:'100%',
    			height:'100%',
    			xtype: 'appraisalrec'
    		}],
    		closable : true //允许关闭
		}).show();
	},
	//关闭窗口
	closeWin: function(btn, e, eOpts) {
		var win = btn.up('window');
		win.hide();
	},
	views:[
		'grid.AppraisalRec'
	],
	stores:[
		'AppraisalRecStore'
//		'NameStore'
	],
	models:[
		'AppraisalRecModel'
//		'NameModel'
	]
});
