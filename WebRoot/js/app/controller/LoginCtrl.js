/**
 * ClassName：登陆控制器
 */
Ext.define("CM.controller.LoginCtrl", {
	extend:'Ext.app.Controller',
	
	init:function() {
		this.control({
			'loginform': {
				afterrender:this.focus
			},
			'loginform button[itemId=login]': {
				click:this.login 
			},
			'loginform textfield[itemId=userName]': {
				keypress: function(field, e, eOpts) {
					if(e.getKey() == Ext.EventObject.ENTER) {
						this.login(field, e, eOpts); //textfield响应回车事件
					}
				}
			},
			'loginform textfield[itemId=password]': {
				keypress: function(field, e, eOpts) {
					if(e.getKey() == Ext.EventObject.ENTER) {
						this.login(field, e, eOpts); //textfield响应回车事件
					}
				}
			}
		});
	},
	login: function(cmp, e, eObjs){
		var loginForm = cmp.up('form');
		if(loginForm.getForm().isValid()){  //表单验证通过
			loginForm.submit({
				url : 'login_login.action',
				method:'POST',
				waitMsg:'请稍等，正在登录...',
				success : function(form, action) {
					Ext.util.Cookies.set('user', Ext.encode(action.result.user));
					window.location.href = 'html/main.jsp';
				},
				failure : function(form, action) {
					Ext.MessageBox.alert("登录失败", action.result.errorMessage, function() {
						if(action.result.errorMessage.indexOf("用户名") > -1){
							//用户名错误,textfield获取焦点并全选
							form.owner.getComponent('userName').focus(true,true);;
						} else if(action.result.errorMessage.indexOf("密码") > -1){
							//密码错误,textfield获取焦点并全选
							form.owner.getComponent('password').focus(true,true); 
						}
					});
				}
			})
		} else {
			var fields = loginForm.getForm().getFields().getRange();
			for(var i=0; i<fields.length; i++) {
				var field = fields[i];
				if(!field.isValid()) {
					field.focus(true,true);
					break;
				}
			}
		}
	},
	focus:function(form, eObjs) { //加载页面后，用户名框获得焦点
		form.down('#userName').focus(true);
	},
	views:[
		'form.LoginForm'
	],
	stores:[
		
	],
	models:[
		
	]
});