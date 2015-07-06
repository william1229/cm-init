Ext.define('CM.view.form.ConsumableEditWin',{
    extend:'Ext.window.Window',
    alias:'widget.consumableeditwin',
    id:'consumableEditWin',
    title:'编辑信息',
    width:560,//要比表单的宽高大一点
    height:430,
    layout : 'fit',
    resizable: false, //禁止改变窗口大小
    modal:true,
    closeAction:'hide',
    items:[{
		xtype:'form',
		itemId:'consumableEditForm',
        layout:'absolute',
        border: false,
        items:[{
			xtype: 'fieldset',
			height: 350,
	        title: '耗材信息',
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
		        itemId: 'consumableName',
		        name: 'consumableName',
		        fieldLabel: '仪器名称'
	        },{
				xtype:'textfield',
				itemId:'consumableModel',
				name:'consumableModel',
				fieldLabel:'仪器型号'
			},{
				xtype:'numberfield',
				itemId:'quantity',
				name:'quantity',
				fieldLabel:'库存数量',
				// Remove spinner buttons, and arrow key and mouse wheel listeners
        		hideTrigger: true,
       			keyNavEnabled: false,
        		mouseWheelEnabled: false
			},{
				xtype: 'textfield',
		        itemId: 'consumableId',
		        name: 'consumableId',
		       	hidden: true,
				hideLabel:true
	        }]
	    }, {
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