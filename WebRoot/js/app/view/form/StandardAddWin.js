Ext.define('CM.view.form.StandardAddWin',{
    extend:'Ext.window.Window',
    alias:'widget.standardaddwin',
    id:'standardAddWin',
    title:'新增标准',
    width:560,//要比表单的宽高大一点
    height:430,
    fileUpload : true,
    layout : 'fit',
    resizable: false, //禁止改变窗口大小
    modal:true,
    closeAction:'hide',
    items:[{
		xtype:'form',
		itemId:'standardAddForm',
        layout:'absolute',
        border: false,
        items:[{
			xtype: 'fieldset',
			x:10,
			y:10,
			width:525,
	        title: '标准信息',
	        defaults: {
	            hideEmptyLabel: false,
	            labelWidth: 80,
	            labelAlign:'right'
	        },
	        items:[{
				xtype: 'textfield',
		        itemId: 'serialNumber',
		        name:'serialNumber',
		        fieldLabel: '编号',
		        allowBlank: false
	        },{
				xtype:'textfield',
				name:'name',
				fieldLabel:'名称'
			},{
				xtype:'datefield',
				itemId:'date',
				name:'date',
				format: 'Y-m-d',
				selectOnFocus:true, 
        		editable:false, 
				fieldLabel:'发布时间'
			},{
				xtype:'datefield',
				itemId:'effectiveDate',
				name:'effectiveDate',
				format: 'Y-m-d',
				selectOnFocus:true, 
        		editable:false, 
				fieldLabel:'有效截止期',
				vtype:'dateScope',     //自定义的验证类型  
      			vtypeText:'有效截止期小于发布时间',  
      			confirmTo:'date'    //要比较的另外一个的组件的id
			},{
				xtype:'filefield',
				name:'file',
				itemId:'file',
				fieldLabel:'标准文件',
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