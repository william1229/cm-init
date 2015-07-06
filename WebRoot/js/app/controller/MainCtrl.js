/**
 * ClassName：中心管理系统控制器
 */
Ext.define('CM.controller.MainCtrl', {
	extend:'Ext.app.Controller',
	init:function() {
		var PIWin;
		var pwdWin;
		var user = Ext.decode(Ext.util.Cookies.get('user'));
		this.control({
			'cmtree': {
				itemclick:this.click
			},
			'pitree': {
				itemclick:this.click
			},
			'mainlayout button[itemId=logout]':{
				click:this.logout
			},
			'mainlayout #personalInfo':{
				beforerender:function(btn, eOpts) {
					btn.setText('<font size="2px" color="white">'+ user.name+'</font>');
				},
				click:function() {
					if(!PIWin) {
						PIWin = Ext.create('CM.view.form.PersonalInfoWin');
					}
					this.openWin(PIWin, user);
				}
			},
			'personalinfowin button[itemId=submit]':{
		    	click:function(btn, e, eOpts) {
			       	this.submitForm(btn, user);
		    	}
		    },
		    'personalinfowin button[itemId=cancel]':{
		    	click:this.closeWin
		    },	    
		    'personalinfowin button[itemId=changePwd]':{
		    	click:function(btn, eOpts) {
		    		if(!pwdWin) {
		    			pwdWin = Ext.create('CM.view.form.ChangePasswordWin');
					}
					pwdWin.down('form').getForm().reset();
		    		pwdWin.show();
		    	}
		    },
		    'changepasswordwin button[itemId=submit]':{
		    	click:function(btn, e, eOpts) {
			       	this.submitForm(btn, user);
		    	}
		    },
		    'changepasswordwin button[itemId=cancel]':{
		    	click:this.closeWin
		    }
		});
	},
	click: function(view, rec, item, index, e, eOpts){
		if(rec.isLeaf()) {
			var ctrlName = rec.raw.ctrlName;
			var ctrlId = rec.raw.id + 'Ctrl';
			var ctrl = this.getController(ctrlName);
			var mainTab = Ext.getCmp('mainTab');
			var tabId = ctrlId.substring(0, ctrlId.indexOf('Ctrl')) +'Tab';
			var tab = mainTab.down('#' + ctrlId);
			if(tab) {
				mainTab.setActiveTab(tab);
			} else {
				ctrl.addTab();
			}
/*			var ctrlName = rec.raw.ctrlName;
			var ctrlId = rec.raw.id + 'Ctrl';
			var ctrls = this.application.controllers.items;
			var flag = false;
			for(var i=0; i<ctrls.length; i++) {
				if(ctrlId.toLowerCase() == ctrls[i].id.toLowerCase()){
					flag = true;
					break;
				}
			}
			if(!flag) {
				Ext.require("CM.controller." + ctrlName,function(){
					var ctrl = this.getController(ctrlName);
					ctrl.init(this);
				},this);
			} else {
				var mainTab = Ext.getCmp('mainTab');
				var tabId = ctrlId.substring(0, ctrlId.indexOf('Ctrl')) +'Tab';
				var tab = mainTab.down('#' + ctrlId);
				if(tab) {
					mainTab.setActiveTab(tab);
				} else {
					this.getController(ctrlName).addTab();
				}
			}*/
		} else {
			if(rec.isExpanded()){
				rec.collapse();
			} else{
				rec.expand();
			}
		}
	},
	logout: function(){
		Ext.MessageBox.msgButtons[1].setText("是");
		Ext.MessageBox.msgButtons[2].setText("否");
		Ext.MessageBox.show({
			title:'提示',
			msg:'是否退出系统？',
			buttons: Ext.MessageBox.YESNO,
			icon:Ext.MessageBox.QUESTION,
			fn:function(e){
				if(e == "yes"){
			   		Ext.Ajax.request({
			      		url: 'login_logout.action',
			  			success: function(respone){
			  				location.reload();
			      		},
			      		failure: function(respone){
			      		}
			   		});
			  	}
			 }
		});
	},
	openWin:function(win,user) {
		var form = win.down('form');
		win.down('form').getForm().reset();
		form.down('#userId').setValue(user.userId);
		form.down('#userName').setValue(user.userName);
		form.down('#name').setValue(user.name);
		form.down('#sex').setValue({'sex':user.sex});
		form.down('#telephone').setValue(user.telephone);
		form.down('#email').setValue(user.email);
		form.down('#school').setValue(user.school);
		form.down('#major').setValue(user.major);
		form.down('#degree').setValue(user.degree);
		form.down('#jobContent').setValue(user.jobContent);
		win.show();
	},
	//提交表单
	submitForm: function(btn, user) {
		var sForm = btn.up('form');
		if(sForm.getForm().isValid()){
			if(sForm.itemId.toLowerCase().indexOf('personalinfo') > -1) {
				var urlAction = 'user_updateInfo.action';
			} else if(sForm.itemId.toLowerCase().indexOf('changepassword') > -1) {
				var urlAction = 'user_resetPwd.action';
			}
			sForm.submit({
				url:urlAction,
				method:'POST',
				params:{
					userId:user.userId
				},
				success:function(form, action){
					Ext.Msg.alert("提示", action.result.msg, function(){
						if(action.result.msg.indexOf('保存') > -1) {
							var values = form.getValues();
							user.name = values.name;
							user.sex = values.sex;
							user.telephone = values.telephone;
							user.email = values.email;
							user.school = values.school;
							user.major = values.major;
							user.degree = values.degree;
							user.jobContent = values.jobContent;
						}
						sForm.up('window').hide();
					});
					Ext.getCmp('changePasswordWin').hide();
				},
				failure:function(form, action){
					Ext.Msg.alert("提示", action.result.errorMessage);
				}
			})
		}
	},
	//关闭窗口
	closeWin: function(btn) {
		var win = btn.up('window');
		win.hide();
	},
	views:[
		'main.MainLayout',
		'main.MainTab',
		'main.CMTree',
		'main.PITree'
	],
	stores:[
		'CMNodeStore',
		'PINodeStore'
	],
	models:[
		'NodeModel'
	]
});