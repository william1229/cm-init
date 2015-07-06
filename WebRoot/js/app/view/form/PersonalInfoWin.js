Ext.define('CM.view.form.PersonalInfoWin',{
    extend:'Ext.window.Window',
    alias:'widget.personalinfowin',
    id:'personalInfoWin',
    title:'修改信息',
    width:560,//要比表单的宽高大一点
    height:430,
    fileUpload : true,
    layout : 'fit',
    resizable: false, //禁止改变窗口大小
    modal:true,
    closeAction:'hide',
    items:[{
		xtype:'form',
		itemId:'personalInfoForm',
        layout:'absolute',
        border: false,
        items:[{
			xtype: 'fieldset',
			x:10,
			y:10,
			width:525,
	        title: '个人信息',
	        defaults: {
	            hideEmptyLabel: false,
	            labelWidth: 60,
	            labelAlign:'right',
	            allowBlank: false
	        },
	       	layout : {
				type : 'table',
	            columns : 3
	        },
	        items:[{
				xtype: 'textfield',
		        itemId: 'userId',
		        name:'userId',
		        hidden: true,
				hideLabel:true
	        },{
				xtype: 'textfield',
		        itemId: 'userName',
		        name:'userName',
		        fieldLabel: '用户名',
		        colspan:2,
		        readOnly:true,
				cls:'x-item-disabled'
	        },{
				xtype: 'textfield',
		        itemId: 'name',
		        name:'name',
		        fieldLabel: '姓名'
	        },{
				xtype: 'radiogroup',
	            layout: 'table',
	            fieldLabel:'性别',
	            itemId:'sex',
	            items: [{
	            	name:'sex',
	            	boxLabel:'男',
	            	inputValue: '男',
	            	checked:true
	            	},{
	            	name:'sex',
	            	boxLabel:'女',
	            	inputValue: '女'
	            }]
	        },{
	        	xtype:'button',
	        	itemId:'changePwd',
	        	text:'修改密码'
	        },{
	            xtype:'numberfield',
	            itemId:'telephone',
	            name:'telephone',
	            fieldLabel:'电话',
	            allowBlank:false,
	            blankText : '电话不能为空',
	            // Remove spinner buttons, and arrow key and mouse wheel listeners
        		hideTrigger: true,
       			keyNavEnabled: false,
        		mouseWheelEnabled: false
	        },{
	            xtype:'textfield',
	            itemId:'email',
	            name:'email',
	            fieldLabel:'E-mail',
	            vtype:"email",
	            vtypeText:"不是有效的邮箱地址",
		        colspan:2
	        },{
				xtype:'textfield',
				itemId:'school',
				name:'school',
				fieldLabel:'毕业院校'
			},{
				xtype:'textfield',
				itemId:'major',
				name:'major',
				fieldLabel:'专业',
		        colspan:2
			},{
				xtype:'textfield',
				itemId:'degree',
				name:'degree',
				fieldLabel:'学位'
			},{
				xtype:'textarea',
				itemId:'jobContent',
				name:'jobContent',
				width: 500,
				colspan:3,
				fieldLabel:'工作内容'
			}]
	    },{
	            xtype: 'toolbar',
	            y:350,
	            ui: 'footer',
	            padding: '5 180 0 0',
	            defaults: {
	                minWidth: 75
	            },
	            items: ['->',{
	            	itemId:'submit',
	                text: '保存'
	            }, {
	            	itemId:'cancel',
	                text: '取消'
	            }]
        }]
	}]
});









Ext.define('CM.view.form.ChangePwdWin',{
    extend:'Ext.window.Window',
    alias:'widget.changepwdwin',
    id:'changePwdWin',
    title:'修改密码',
    width:560,//要比表单的宽高大一点
    height:430,
    layout : 'fit',
    resizable: false, //禁止改变窗口大小
    modal:true,
    closeAction:'hide',
    items:[{
		xtype:'form',
		itemId:'changePwdForm',
        layout:'absolute',
        trackResetOnLoad:true,
        items:[{
			xtype: 'fieldset',
			x:10,
			y:10,
			width:525,
			height: 60,
	        defaults: {
	            hideEmptyLabel: false,
	            labelWidth: 80,
	            labelAlign:'right'
	        },
	        items:[{
				xtype:'textfield',
				name:'password',
				fieldLabel:'密码',
				inputType:'password',
				itemIdwBlank:false,
                blankText : '密码不能为空',
                regex : /^[\s\S]{6,20}$/,
                regexText : '密码长度不能小于6位'
				},{
				xtype:'textfield',
				fieldLabel:'确认密码',
				inputType:'password',
				vtype:'password',     //自定义的验证类型  
      			vtypeText:'两次密码不一致',  
      			confirmTo:'pwd1'    //要比较的另外一个的组件的id 
			}]
	    },{
            xtype: 'toolbar',
            y:350,
            ui: 'footer',
            padding: '5 180 0 0',
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
	    }]
	}]
});