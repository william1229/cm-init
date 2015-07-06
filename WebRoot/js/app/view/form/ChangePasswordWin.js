Ext.define('CM.view.form.ChangePasswordWin',{
    extend:'Ext.window.Window',
    alias:'widget.changepasswordwin',
    id:'changePasswordWin',
    title:'修改密码',
    width:350,//要比表单的宽高大一点
    height:170,
    layout : 'fit',
    resizable: false, //禁止改变窗口大小
    modal:true,
    closable:false,
    items:[{
		xtype:'form',
		itemId:'changePasswordForm',
        layout:'absolute',
        trackResetOnLoad:true,
    	defaults: {
            hideEmptyLabel: false,
            labelWidth: 60,
            labelAlign:'right',
	        allowBlank:false
        },
        items:[{
        		x:30,
        		y:20,
	       		xtype:'textfield',
				name:'password',
				itemId:'password',
				fieldLabel:'密码',
				inputType:'password',
				itemIdwBlank:false,
                blankText : '密码不能为空',
                regex : /^[\s\S]{6,20}$/,
                regexText : '密码长度不能小于6位'
			},{
				x:30,
        		y:50,
				xtype:'textfield',
				fieldLabel:'确认密码',
				inputType:'password',
				vtype:'password',     //自定义的验证类型  
      			vtypeText:'两次密码不一致',  
      			confirmTo:'pwd1'    //要比较的另外一个的组件的id 
		
		    },{
	            xtype: 'toolbar',
	            y:90,
	            ui: 'footer',
	            padding: '5 80 0 0',
	            defaults: {
	                minWidth: 75
	            },
	            items: ['->',{
	            	itemId:'submit',
	                text: '提交'
	            }, {
	            	itemId:'cancel',
	                text: '取消'
	            }]
		    }
		]
	}]
});