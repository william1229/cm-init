Ext.define('CM.view.form.ConsumableAddWin',{
    extend:'Ext.window.Window',
    alias:'widget.consumableaddwin',
    id:'consumableAddWin',
    title:'新增耗材',
    width:560,//要比表单的宽高大一点
    height:430,
    layout : 'fit',
    resizable: false, //禁止改变窗口大小
    modal:true,
    closeAction:'hide',
    items:[{
		xtype:'form',
		itemId:'consumableAddForm',
        layout:'absolute',
        border: false,
        items:[{
			xtype: 'fieldset',
			x:10,
			y:10,
			width:525,
			height: 60,
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
	        title: '耗材信息',
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
		        fieldLabel: '耗材名称'
	        },{
				xtype: 'textfield',
				itemId: 'consumableType',
		        name: 'consumableType',
		        fieldLabel: '耗材型号'
	        },{
				xtype:'numberfield',
				itemId:'quantity',
				name:'quantity',
				hidden:true,
				value:0
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