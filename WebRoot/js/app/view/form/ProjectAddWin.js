Ext.define('CM.view.form.ProjectAddWin',{
    extend:'Ext.window.Window',
    alias:'widget.projectaddwin',
    id:'projectAddWin',
    title:'新增项目',
    width:560,//要比表单的宽高大一点
    height:430,
    layout : 'fit',
    resizable: false, //禁止改变窗口大小
    modal:true,
    closeAction:'hide',
    items:[{
		xtype:'form',
		itemId:'projectAddForm',
        layout:'absolute',
        border: false,
        items:[{
			xtype: 'fieldset',
			x:10,
			y:10,
			width:525,
	        title: '项目信息',
	        layout : {
				type : 'table',
	            columns : 2
	        }, 
	        defaults: {
	            hideEmptyLabel: false,
	            labelWidth: 80,
	            labelAlign:'right',
	            allowBlank: false
	        },
	        items:[{
				xtype: 'textfield',
				itemId: 'projectSN',
		        name: 'projectSN',
		        fieldLabel: '项目编号'
	        },{
				xtype: 'textfield',
				itemId: 'projectName',
		        name: 'projectName',
		        fieldLabel: '项目名称'
			},{
				xtype:'datefield',
				itemId:'date',
				name:'date',
				format: 'Y-m-d',
				fieldLabel: '签订时间',
				selectOnFocus:true, 
        		editable:false,
        		colspan:2,
        		value : new Date()
			},{
				xtype: 'textfield',
				itemId: 'unit',
		        name: 'unit',
		        colspan:2,
		        width:470,
		        fieldLabel: '委托单位'
			},{
	            xtype:'numberfield',
	            itemId:'telephone',
	            name:'telephone',
	            fieldLabel:'联系电话',
	            blankText : '电话不能为空',
	            // Remove spinner buttons, and arrow key and mouse wheel listeners
        		hideTrigger: true,
       			keyNavEnabled: false,
        		mouseWheelEnabled: false
	        },{
				xtype:'numberfield',
				name:'money',
				fieldLabel:'金额(元)',
				// Remove spinner buttons, and arrow key and mouse wheel listeners
        		hideTrigger: true,
       			keyNavEnabled: false,
        		mouseWheelEnabled: false
			},{
				xtype:'filefield',
				name:'contract',
				itemId:'contract',
				fieldLabel:'合同文件',
				buttonText: '选择文件',
				colspan:2,
				width: 470,
            	allowBlank: false
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