/**
 * ClassName：用户信息控制器
 */
Ext.define("CM.controller.StandardInfoCtrl", {
	extend:'Ext.app.Controller',
	init:function(app) {
		var addWin;
		var userNameWin;
		Ext.QuickTips.init();
		this.control({
			'standardlist button[itemId=add]':{
				click:function() {
					if(!addWin) {
						addWin = Ext.create("CM.view.form.StandardAddWin");
					}
					addWin.down('form').getForm().reset();
					addWin.show();
				}
		    },
		    'standardlist actioncolumn[itemId=operation]':{
		    	stratclick:function(obj, eOpts) {
					click:this.updateState(obj.record);
				},
				stopclick:function(obj, eOpts) {
					click:this.updateState(obj.record);
				}
		    },
			'standardlist actioncolumn[itemId=file]': {
		     	click:this.download
		    },
			'standardaddwin button[itemId=submit]':{
				click:this.submitForm
			},
			'standardaddwin button[itemId=cancel]':{
				click:this.closeWin
			}
     	});
     	this.addTab();
	},
	//提交表单
	submitForm: function(btn) {
		var sForm = btn.up('form');
		if(sForm.getForm().isValid()){
			if(sForm.itemId.toLowerCase().indexOf('add') > 0) {
				var urlAction = 'standard_addStandard.action';
			} else if(sForm.itemId.toLowerCase().indexOf('modify') > 0) {
				var urlAction = 'standard_updateInfo.action';
			}
			sForm.submit({
				url:urlAction,
				method:'POST',
				success:function(form, action){
					var recGrid = Ext.getCmp('standardList');
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
		window.open('standard_fileDownload.action?path=' + path);
	},
	updateState:function(record) {
		var state;
		var msg;
		if(record.get('state') == '在用') {
			state = '作废';
			msg = '是否停用该标准？';
		} else if(record.get('state') == '作废') {
			state = '在用';
			msg = '是否启用该标准？';
		}
		Ext.MessageBox.msgButtons[1].setText("是");
		Ext.MessageBox.msgButtons[2].setText("否");
		Ext.MessageBox.show({
			title:'提示',
			msg: msg,
			buttons: Ext.MessageBox.YESNO,
			icon:Ext.MessageBox.QUESTION,
			fn:function(e){
				if(e == "yes"){
					Ext.Ajax.request({
						url: 'standard_updateState.action',
				  		params:{	
							state: state,
							standardId: record.get('standardId')
						},
						success: function(respone){
							var grid = Ext.getCmp('standardList');
			      			grid.getStore().load();
				  		},
				  		failure: function(respone){
				  			Ext.Msg.alert("提示", Ext.JSON.decode(respone.responseText).errorMessage);
				  		}
					})
				}
			}
		});
	},
	//主选项卡添加tab
	addTab:function() {
		var mainTab = Ext.getCmp('mainTab');
		mainTab.add({
			itemId: 'standardInfoCtrl', //向mainTab的items项中,添加的itemId
    		title: '标准信息',
    		layout:'border',
    		items:[{
    			region:'center',
    			width:'100%',
    			height:'100%',
    			xtype: 'standardlist'
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
		'grid.StandardList'
	],
	stores:[
		'StandardStore'
	],
	models:[
		'StandardModel'
	]
});
