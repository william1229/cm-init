/**
 * ClassName：登陆界面布局
 */
Ext.define("CM.view.form.LoginForm", {
	extend:'Ext.form.Panel',
	alias:'widget.loginform',
	layout:'absolute',
	defaultType:'textfield',
	bodyCls:'login_form_bg',
	defaults : {
		allowBlank : false,
		labelAlign : 'left',
		labelWidth: 50,
		width:300,
		height:40,
		enableKeyEvents: true
	},
	items:[{
		x:60,
		y:150,
		itemId:'userName',
		name:'userName',
		fieldLabel : '用户名',
		fieldCls:'user',
		labelCls:'login_form_label',
		blankText : '用户名必须输入'
	}, {
		x:60,
		y:210,
		itemId:'password',
		name:'password',
		fieldLabel : '密&nbsp;&nbsp;&nbsp;码',
		fieldCls:'lock',
		labelCls:'login_form_label',
		inputType : 'password',
		blankText : '密码必须输入'
	},{
	    xtype: 'toolbar',
	    ui: 'footer',
	    x:390,
		y:150,
		width:100,
		height:100,
		layout:'absolute',
	    items: [{
	    	x:0,
	    	y:0,
			width:100,
			height:100,
	    	itemId:'login',
	    	text : '<span style="font-size:25px;">登&nbsp;&nbsp;录</span>'
	    }]
	}]
});