/**
 * ClassName：用户信息控制器
 */
Ext.define("CM.controller.CertificateInfoCtrl", {
	extend:'Ext.app.Controller',
	init:function(app) {
//		var addWin;
		this.control({
/*			'certificatelist button[itemId=add]':{
				click:function() {
					if(!addWin) {
						addWin = Ext.create("CM.view.form.CertificateAddWin");
					}
					addWin.down('form').getForm().reset();
					addWin.show();
				}
		    },
			'certificateaddwin button[itemId=submit]':{
				click:this.submitForm
			},
			'certificateaddwin button[itemId=cancel]':{
				click:this.closeWin
			},
			'certificateaddwin textfield[itemId=name]':{
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
				var urlAction = 'certificate_addCertificate.action';
			} else if(sForm.itemId.toLowerCase().indexOf('modify') > 0) {
				var urlAction = 'certificate_updateInfo.action';
			}
			sForm.submit({
				url:urlAction,
				method:'POST',
				success:function(form, action){
					var recGrid = Ext.getCmp('certificateList');
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
		Ext.Ajax.request({
			url: 'certificate_summaryDownload.action',
	  		params:{	
				downloadPath: path
			},
			success: function(respone){
				var obj = Ext.decode(respone.responseText);
				//弹出下载对话框
        		window.location.href = obj.path;
	  		},
	  		failure: function(respone){
	  			Ext.Msg.alert("提示", Ext.JSON.decode(respone.responseText).errorMessage);
	  		}
		})
	},
	//选取姓名
	selectName: function(grid, rec, index, column, e, eOpts) {
		var recs = grid.getStore().getRange();
		var name = recs[index].get('name');
		var userId = recs[index].get('userId');
		var win = Ext.getCmp('certificateAddWin');
		grid.up('window').hide();
		win.down('#name').setValue(name);
		win.down('#userId').setValue(userId);
	},
	//主选项卡添加tab
	addTab:function() {
		var mainTab = Ext.getCmp('mainTab');
		//向mainTab的items项中,添加的item
		mainTab.add({ 
			itemId: 'certificateInfoCtrl', 
    		title: '证书信息',
    		layout:'border',
    		items:[{
    			region:'center',
    			width:'100%',
    			height:'100%',
    			xtype: 'certificatelist'
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
		'grid.CertificateList'
	],
	stores:[
		'CertificateStore'
//		'NameStore'
	],
	models:[
		'CertificateModel'
//		'NameModel'
	]
});
