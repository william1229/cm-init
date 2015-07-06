Ext.define('CM.view.form.PurchasingWin',{
    extend:'Ext.window.Window',
    alias:'widget.purchasingwin',
    id:'purchasingWin',
    title:'申请采购',
    width:560,//要比表单的宽高大一点
    height:430,
    layout : 'fit',
    resizable: false, //禁止改变窗口大小
    modal:true,
    closeAction:'hide',
    items:[{
		xtype:'form',
		itemId:'purchasingForm',
        layout:'absolute',
        trackResetOnLoad:true,
        items:[{
			xtype: 'fieldset',
			itemId:'instruInfo',
			x:10,
			y:10,
			width:525,
			height: 60,
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
			width:525,
			xtype: 'fieldset',
			itemId:'consumableInfo',
	        title: '采购信息',
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
				fieldLabel:'采购数量',
				colspan:2,
				// Remove spinner buttons, and arrow key and mouse wheel listeners
        		hideTrigger: true,
       			keyNavEnabled: false,
        		mouseWheelEnabled: false,
        		minValue:1
			},{
				xtype:'textarea',
				name:'remark',
				itemId:'remark',
				width: 470,
				colspan:2,
				allowBlank:true,
				fieldLabel:'说明(可选)'
			},{
				xtype:'datefield',
				itemId:'date',
				name:'date',
				format: 'Y-m-d',
				value : new Date(),
        		hidden:true
			},{
				xtype:'textfield',
				itemId:'state',
				name:'state',
				value : '待审批',
        		hidden:true
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