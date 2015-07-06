Ext.define('CM.view.form.AccreditAddWin',{
    extend:'Ext.window.Window',
    alias:'widget.accreditaddwin',
    id:'accreditAddWin',
    title:'仪器授权',
    width:560,//要比表单的宽高大一点
    height:430,
    layout : 'fit',
    resizable: false, //禁止改变窗口大小
    modal:true,
    closeAction:'hide',
    items:[{
		xtype:'form',
		itemId:'accreditAddForm',
        layout:'absolute',
        border: false,
        items:[{
			xtype: 'fieldset',
			x:10,
			y:10,
			width:525,
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
			},{
				xtype: 'textfield',
				itemId: 'name',
		        name: 'name',
		        fieldLabel: '仪器管理员',
		        allowBlank: false,
		        blankText : '管理员不能为空'
			},{
	        	xtype: 'textfield',
	        	itemId: 'userId',
	        	name:'userId',
	        	hidden: true,
				hideLabel:true
	        },{
	        	xtype: 'textfield',
	        	itemId: 'instruId',
	        	name:'instruId',
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
                text: '授权'
            }, {
            	itemId:'cancel',
                text: '取消'
            }]
	    }]
	}]
});