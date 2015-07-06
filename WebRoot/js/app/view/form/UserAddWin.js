Ext.define('CM.view.form.UserAddWin',{
    extend:'Ext.window.Window',
    alias:'widget.useraddwin',
    id:'userAddWin',
    title:'新增人员',
    width:560,//要比表单的宽高大一点
    height:430,
    layout : 'fit',
    resizable: false, //禁止改变窗口大小
    modal:true,
    closeAction:'hide',
    items:[{
		xtype:'form',
		itemId:'userAddForm',
        layout:'absolute',
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
	            labelAlign:'right',
	            allowBlank:false
	        },
	        items:[{
				xtype: 'textfield',
		        itemId: 'userName',
		        name:'userName',
		        fieldLabel: '用户名',
		        allowBlank:false,
		        blankText : '用户名不能为空',
		        minLength : 3,
				minLengthText : '用户名长度不能小于{0}',
				maxLength : 12,
				maxLengthText : '用户名长度不能大于{0}'
	        },{
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
	            name:'email',
	            fieldLabel:'E-mail',
	            width:255,
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