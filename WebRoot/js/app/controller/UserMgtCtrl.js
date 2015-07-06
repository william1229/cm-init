/**
 * ClassName：用户信息控制器
 */
Ext.define("CM.controller.UserMgtCtrl", {
	extend:'Ext.app.Controller',
	init:function(app) {
		var addWin;
		var editWin;
		this.control({
			'userlist button[itemId=add]':{
				click:function() {
					if(!addWin) {
						addWin = Ext.create("CM.view.form.UserAddWin");
					} 
					addWin.down('form').getForm().reset();
					addWin.show();
				}
			},
			'userlist':{
				itemdblclick:function(grid, rec, item, index, e, eOpts) {
			       	if(!editWin) {
						editWin = Ext.create("CM.view.form.UserEditWin");
					} 
					this.userEdit(grid, rec, editWin);
				}
			},
			'userlist actioncolumn[itemId=operation]':{
				deleteclick:function(obj, eOpts) {
					this.deleteUser(obj.record);
				}
			},
			'useraddwin button[itemId=submit]':{
				click:this.submitForm
			},
			'useraddwin button[itemId=cancel]':{
				click:this.closeWin
			},
			'usereditwin button[itemId=submit]':{
				click:this.submitForm
			},
			'usereditwin button[itemId=cancel]':{
				click:this.closeWin
			},
			'usereditwin button[itemId=resetPwd]':{
				click:this.resetPwd
			}
     	});
     	this.addTab();
	},
	//提交表单
	submitForm: function(btn, e, eOpts) {
		var sForm = btn.up('form');
		var flag;
		if(sForm.getForm().isValid()){
			if(sForm.itemId.toLowerCase().indexOf('add') > -1) {
				var urlAction = 'user_addUser.action';
			} else if(sForm.itemId.toLowerCase().indexOf('edit') > -1) {
				var urlAction = 'user_updateInfo.action';
				flag = true;
			}
			sForm.submit({
				url:urlAction,
				method:'POST',
				params:{	
	  				flag: flag
	  			},
				success:function(form, action){
					var userGrid = Ext.getCmp('userList');
					Ext.Msg.alert("提示", action.result.msg, function(){
						form.owner.up('window').hide();
						userGrid.getStore().load();
					});
				},
				failure:function(form, action){
					Ext.Msg.alert("提示", action.result.errorMessage);
				}
			})
		}
	},
	//展现修改表单
	userEdit: function(grid, rec, editWin) {
		var editForm = editWin.down('form');
		editForm.getForm().reset();
		editWin.show(null,function() {
			editForm.down('#userId').setValue(rec.get('userId'));
			editForm.down('#userName').setValue(rec.get('userName'));
			editForm.down('#name').setValue(rec.get('name'));
			editForm.down('#telephone').setValue(rec.get('telephone'));
			editForm.down('#sex').setValue({'sex':rec.get('sex')});
			editForm.down('#email').setValue(rec.get('email'));
			var checkBoxs = editForm.down('#checkboxGroup').items.items; //checkboxGroup里的选项名称
			var roleNames = rec.get('roleNames').split(','); //界面上的roleName值
			if(roleNames.indexOf("") < 0){
				for(var i=0; i<roleNames.length; i++){
					var name = roleNames[i];
					for(var j=0; j<checkBoxs.length; j++) {
						if(checkBoxs[j].boxLabel.indexOf(name) > -1) {
							checkBoxs[j].setValue(true);
							break;
						} 
					}
				}
			}
		});
	},
	//删除用户
	deleteUser: function(record){
		Ext.MessageBox.msgButtons[1].setText("是");
		Ext.MessageBox.msgButtons[2].setText("否");
		Ext.MessageBox.show({
			title:'提示',
			msg:'是否删除该用户？',
			buttons: Ext.MessageBox.YESNO,
			icon:Ext.MessageBox.QUESTION,
			fn:function(e){
				if(e == "yes"){
			   		Ext.Ajax.request({
			      		url: 'user_delete.action',
			      		params:{	
			  				userId: record.get('userId')
			  			},
			  			success: function(respone){
			  				var userGrid = Ext.getCmp('userList');
			  				Ext.Msg.alert("提示", Ext.JSON.decode(respone.responseText).msg);
			      			userGrid.getStore().load();
			      		},
			      		failure: function(respone){
			      			Ext.Msg.alert("提示", Ext.JSON.decode(respone.responseText).errorMessage);
			      		}
			   		});
			  	}
			 }
		});
	},
	//重置用户密码
	resetPwd: function(btn, e, eOpts) {
		Ext.MessageBox.msgButtons[1].setText("是");
		Ext.MessageBox.msgButtons[2].setText("否");
		Ext.MessageBox.show({
			title:'提示',
			msg:'是否要重置密码？',
			buttons: Ext.MessageBox.YESNO,
			icon:Ext.MessageBox.QUESTION,
			fn:function(e){
				if(e == "yes"){
			   		Ext.Ajax.request({
			      		url: 'user_resetPwd.action',
			      		params:{	
			  				userId:btn.up('form').down('#userId').value,
			  				password:'123456'
			  			},
			  			success: function(respone){
			  				Ext.Msg.alert("提示", Ext.JSON.decode(respone.responseText).msg);
			      		},
			      		failure: function(respone){
			      			Ext.Msg.alert("提示", Ext.JSON.decode(respone.responseText).errorMessage);
			      		}
			   		});
			  	}
			 }
		});
	},
	//主选项卡添加tab
	addTab:function() {
		var mainTab = Ext.getCmp('mainTab');
		mainTab.add({
			itemId: 'userMgtCtrl', //向mainTab的items项中,添加的itemId
    		title: '用户管理',
    		layout:'border',
    		items:[{
    			region:'center',
    			width:'100%',
    			height:'70%',
    			xtype: 'userlist'	
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
		'grid.UserList'
	],
	stores:[
		'UserStore'
	],
	models:[
		'UserModel'
	]
});