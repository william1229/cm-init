Ext.define('CM.view.form.DoorOutWin',{
    extend:'Ext.window.Window',
    alias:'widget.dooroutwin',
    id:'doorOutWin',
    title:'耗材出库',
    width:560,//要比表单的宽高大一点
    height:430,
    layout : 'fit',
    resizable: false, //禁止改变窗口大小
    modal:true,
    closeAction:'hide',
    items:[{
		xtype:'form',
		itemId:'doorOutForm',
        layout:'absolute',
        items:[{
			xtype: 'fieldset',
			width:525,
			height: 60,
			x:10,
			y:10,
			itemId:'instruInfo',
	        title: '仪器信息',
	        layout : {
				type : 'table',
	            columns : 2
	        }, 
	        defaults: {
	            hideEmptyLabel: false,
	            labelWidth: 80,
	            labelAlign:'right'
	        },
	        items:[{
				xtype: 'textfield',
				itemId: 'instruName',
		        name: 'instruName',
		        fieldLabel: '仪器名称',
		        disabled:true
	        },{
				xtype: 'textfield',
				itemId: 'instruModel',
		        name: 'instruModel',
		        fieldLabel: '仪器型号',
		        disabled:true
			}]
	    },{
			xtype: 'fieldset',
			width:525,
			itemId:'consumableInfo',
	        title: '出库信息',
	        layout : {
				type : 'table',
	            columns : 2
	        }, 
	        defaults: {
	            hideEmptyLabel: false,
	            labelWidth: 80,
	            labelAlign:'right',
	            allowBlank:false
	        },
	        items:[{
				xtype: 'textfield',
				itemId: 'consumableName',
		        name: 'consumableName',
		        fieldLabel: '耗材名称',
		        disabled:true
	        },{
				xtype: 'textfield',
				itemId: 'consumableType',
		        name: 'consumableType',
		        fieldLabel: '耗材型号',
		        disabled:true
	        },{
				xtype:'numberfield',
				itemId:'quantity',
				name:'quantity',
				fieldLabel:'出库数量',
				// Remove spinner buttons, and arrow key and mouse wheel listeners
        		hideTrigger: true,
       			keyNavEnabled: false,
        		mouseWheelEnabled: false
			},{
				xtype: 'textfield',
		        itemId: 'name',
		        name:'name',
		        fieldLabel: '申请人',
		        allowBlank: false,
		        blankText : '申请人不能为空'
	        },{
				xtype:'datefield',
				itemId:'date',
				name:'date',
				format: 'Y-m-d',
				selectOnFocus:true, 
        		editable:false,
        		value : new Date(),
        		maxValue : new Date(),
				fieldLabel:'时间'
			},{
				xtype: 'textfield',
		        itemId: 'userId',
		        name:'userId',
		        hidden: true,
				hideLabel:true
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