Ext.define('CM.view.form.GasCsumAddWin',{
    extend:'Ext.window.Window',
    alias:'widget.gascsumaddwin',
    id:'gasCsumAddWin',
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
		itemId:'gasCsumAddForm',
        layout:'absolute',
        border: false,
        items:[{
			xtype: 'fieldset',
			x:10,
			y:10,
			width:525,
	        title: '文件信息',
	        defaults: {
	            hideEmptyLabel: false,
	            labelWidth: 60,
	            labelAlign:'right'
	        },
	        items:[{
				xtype:'textfield',
				name:'filename',
				itemId:'filename',
				fieldLabel:'文件名',
				allowBlank: false,
				readOnly:true,
				width: 400,
				cls:'x-item-disabled'
			},{
				xtype:'filefield',
				name:'file',
				itemId:'file',
				fieldLabel:'文件',
				buttonText: '选择文件',
				width: 400,
            	allowBlank: false
			},{
				xtype:'datefield',
				itemId:'date',
				name:'date',
				format: 'Y-m-d',
				selectOnFocus:true, 
        		editable:false,
        		hidden:true,
        		value:new Date()
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