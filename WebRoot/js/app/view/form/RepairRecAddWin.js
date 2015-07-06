Ext.define('CM.view.form.RepairRecAddWin',{
    extend:'Ext.window.Window',
    alias:'widget.repairrecaddwin',
    id:'repairRecAddWin',
    title:'新增记录',
    width:560,//要比表单的宽高大一点
    height:430,
    fileUpload : true,
    layout : 'fit',
    resizable: false, //禁止改变窗口大小
    modal:true,
    closeAction:'hide',
    items:[{
		xtype:'form',
		itemId:'repairRecAddForm',
        layout:'absolute',
        border: false,
        items:[{
			xtype: 'fieldset',
			x:10,
			y:10,
			width:525,
	        title: '维修记录',
	        defaults: {
	            hideEmptyLabel: false,
	            labelWidth: 60,
	            labelAlign:'right',
	            allowBlank: false
	        },
	       	layout : {
				type : 'table',
	            columns : 2
	        },
	        items:[{
				xtype: 'textfield',
		        itemId: 'instruId',
		        name:'instruId',
		        hidden: true,
				hideLabel:true
	        },{
				xtype: 'textfield',
		        itemId: 'instruName',
		        name:'instruName',
		        fieldLabel: '仪器名称',
		        disabled: true
	        },{
				xtype: 'textfield',
		        itemId: 'instruModel',
		        name:'instruModel',
		        fieldLabel: '仪器型号',
		        disabled: true
	        },{
				xtype:'textfield',
				name:'name',
				fieldLabel:'维修人员'
			},{
				xtype:'datefield',
				itemId:'date',
				name:'date',
				format: 'Y-m-d',
				selectOnFocus:true, 
        		editable:false, 
        		value: new Date(),
        		maxValue : new Date(),
				fieldLabel:'维修时间'
			},{
				xtype:'numberfield',
				itemId:'cost',
				name:'cost',
				fieldLabel:'维修费用',
				colspan:2,
				// Remove spinner buttons, and arrow key and mouse wheel listeners
        		hideTrigger: true,
       			keyNavEnabled: false,
        		mouseWheelEnabled: false
			},{
				xtype:'textarea',
				name:'content',
				width: 500,
				colspan:2,
				fieldLabel:'维修详情'
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