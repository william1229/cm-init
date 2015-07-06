Ext.define('CM.view.form.SSampleOutWin',{
    extend:'Ext.window.Window',
    alias:'widget.ssampleoutwin',
    id:'sSampleOutWin',
    title:'标样出库',
    width:560,//要比表单的宽高大一点
    height:430,
    layout : 'fit',
    resizable: false, //禁止改变窗口大小
    modal:true,
    closeAction:'hide',
    items:[{
		xtype:'form',
		itemId:'sSampleOutForm',
        layout:'absolute',
        border: false,
        items:[{
			xtype: 'fieldset',
			x:10,
			y:10,
			width:525,
	        title: '标样信息',
	        layout : {
				type : 'table',
	            columns : 2
	        }, 
	        defaults: {
	            hideEmptyLabel: false,
	            labelWidth: 90,
	            labelAlign:'right'
	        },
	        items:[{
				xtype: 'textfield',
				itemId: 'sSampleName',
		        name: 'sSampleName',
		        fieldLabel: '标准物质名称',
		        disabled:true
			},{
				xtype: 'textfield',
				itemId: 'standardSN',
		        name: 'standardSN',
		        fieldLabel: '标准编号',
		        disabled:true
	        },{
				xtype: 'textfield',
				itemId: 'sampleSN',
		        name: 'sampleSN',
		        fieldLabel: '样品编号',
		        disabled:true
			},{
				xtype: 'textfield',
				itemId: 'specification',
		        name: 'specification',
		        fieldLabel: '规格',
		        disabled:true
			},{
				xtype: 'textfield',
				itemId: 'sSampleId',
		        name: 'sSampleId',
		        hidden:true
			}]
	    },{
			xtype: 'fieldset',
			width:525,
			y:100,
			x:10,
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
				xtype:'numberfield',
				itemId:'quantity',
				name:'quantity',
				fieldLabel:'入库数量',
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
				hidden:true
			},{
				xtype: 'textfield',
		        itemId: 'userId',
		        name:'userId',
		        hidden: true
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