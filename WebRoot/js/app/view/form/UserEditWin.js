Ext.define('CM.view.form.UserEditWin',{
    extend:'Ext.window.Window',
    alias:'widget.usereditwin',
    id:'userEditWin',
    title:'编辑信息',
    width:560,//要比表单的宽高大一点
    height:430,
    layout : 'fit',
    resizable: false, //禁止改变窗口大小
    modal:true,
    closeAction:'hide',
    items:[{
		xtype:'form',
		itemId:'userEditForm',
        layout:'absolute',
        trackResetOnLoad:true,
        items:[{
			xtype: 'fieldset',
			width:230,
			height:112,
	        title: '账号信息',
	        x:10,
	        y:10,	
	        defaults: {
	            hideEmptyLabel: false,
	            labelWidth: 60,
	            labelAlign:'right'
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
		        fieldLabel: '用户名',
		        disabled:true
	        },{
	        	xtype: 'toolbar',
	            ui: 'footer',
	            padding:'5 0 0 20',
	            items: ['初始密码：123456','-',{
	            	itemId:'resetPwd',
	            	name:'resetPwd',
	                text: '重置密码'
	            }]
	        }]
	    },{
			xtype: 'fieldset',
			x:255,
			y:10,
			width:280,
			title: '基本资料',
			layout : {
				type : 'table',
	            columns : 2
	        }, 
			defaults: {
			    hideEmptyLabel: false,
			    labelWidth: 41,
			    labelAlign:'right'
			},
			items:[{
				xtype:'textfield',
				itemId:'name',
				name:'name',
				fieldLabel:'姓名',
				allowBlank:false,
		        minLength : 2,
		        width:140,
				minLengthText : '姓名长度不能小于{0}'
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
	            xtype:'numberfield',
	            itemId:'telephone',
	            name:'telephone',
	            colspan:2,
	            fieldLabel:'电话',
	            allowBlank:false,
	            blankText : '电话不能为空',
	            width:255,
	            // Remove spinner buttons, and arrow key and mouse wheel listeners
        		hideTrigger: true,
       			keyNavEnabled: false,
        		mouseWheelEnabled: false
	            },{
	            xtype:'textfield',
	            itemId:'email',
	            colspan:2,
	            width:255,
	            name:'email',
	            fieldLabel:'E-mail',
	            vtype:"email",
	            vtypeText:"不是有效的邮箱地址"
	            }]	

	    },{
			xtype: 'fieldset',
			x:10,
			y:140,
			width:525,
        	title: '用户类型',
	        defaults: {
	            hideEmptyLabel: false
	        },
	        items: [{
	            xtype: 'checkboxgroup',
	            cls: 'x-check-group-alt',
	            hideLabel:true,
	            height: 85,
	            // Distribute controls across 3 even columns, filling each row
	            // from left to right before starting the next row
	            columns: 5,
	            itemId:'checkboxGroup',
	            items: [
					//inputValue:数据库role表中对应的roleId	            
	            	{boxLabel: '档案管理员', name: 'roleIds', inputValue: '1'}, 
	                {boxLabel: '标准管理员', name: 'roleIds', inputValue: '2'},
	                {boxLabel: '质量管理员', name: 'roleIds', inputValue: '3'}, 
	                {boxLabel: '项目管理员', name: 'roleIds', inputValue: '4'},
	                {boxLabel: '样品管理员', name: 'roleIds', inputValue: '5'},
	                {boxLabel: '标样管理员', name: 'roleIds', inputValue: '6'},
	                {boxLabel: '仪器管理员', name: 'roleIds', inputValue: '7'},
	                {boxLabel: '采购管理员', name: 'roleIds', inputValue: '8'},
	                {boxLabel: '耗材管理员', name: 'roleIds', inputValue: '9'},
	           		{boxLabel: '气体管理员', name: 'roleIds', inputValue: '10'},
	           		{boxLabel: '其他', name: 'roleIds', inputValue: '11', hidden:true, checked:true}
	            ]
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