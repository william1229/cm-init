Ext.define('CM.view.form.InReviewAddWin',{
    extend:'Ext.window.Window',
    alias:'widget.inreviewaddwin',
    id:'inReviewAddWin',
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
		itemId:'inReviewAddForm',
        layout:'absolute',
        border: false,
        items:[{
			xtype: 'fieldset',
			x:10,
			y:10,
			width:525,
	        title: '内部评审',
	        defaults: {
	            hideEmptyLabel: false,
	            labelWidth: 60,
	            labelAlign:'right'
	        },
	        items:[{
				xtype:'datefield',
				itemId:'date',
				name:'date',
				format: 'Y-m-d',
				selectOnFocus:true, 
        		editable:false, 
				fieldLabel:'时间',
				value:new Date()
			},{
				xtype:'filefield',
				name:'file',
				itemId:'file',
				fieldLabel:'报告',
				buttonText: '选择文件',
				width: 400,
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