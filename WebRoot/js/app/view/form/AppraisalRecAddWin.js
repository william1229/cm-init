Ext.define('CM.view.form.AppraisalRecAddWin',{
    extend:'Ext.window.Window',
    alias:'widget.appraisalrecaddwin',
    id:'appraisalRecAddWin',
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
		itemId:'appraisalRecAddForm',
        layout:'absolute',
        border: false,
        items:[{
			xtype: 'fieldset',
			x:10,
			y:10,
			width:525,
	        title: '考核记录',
	        defaults: {
	            hideEmptyLabel: false,
	            labelWidth: 60,
	            labelAlign:'right'
	        },
	        layout : {
				type : 'table',
	            columns : 2
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
		        name:'userName',
		        fieldLabel: '用户名',
		        disabled: true
	        },{
				xtype: 'textfield',
		        itemId: 'name',
		        name:'name',
		        fieldLabel: '姓名',
		        disabled: true
	        },{
				xtype:'datefield',
				itemId:'date',
				name:'date',
				format: 'Y-m-d',
				selectOnFocus:true, 
        		editable:false, 
				fieldLabel:'时间',
				colspan:2,
				value:new Date()
			},{
				xtype:'filefield',
				name:'file',
				itemId:'file',
				fieldLabel:'考核表',
				buttonText: '选择文件',
				colspan:2,
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