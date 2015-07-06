Ext.define('CM.view.form.MetricalAddWin',{
    extend:'Ext.window.Window',
    alias:'widget.metricaladdwin',
    id:'metricalAddWin',
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
		itemId:'metricalAddForm',
        layout:'absolute',
        border: false,
        items:[{
			xtype: 'fieldset',
			x:10,
			y:10,
			width:525,
	        title: '计量信息',
	        defaults: {
	            hideEmptyLabel: false,
	            labelWidth: 80,
	            labelAlign:'right',
	            allowBlank: false
	        },
	       	layout : {
				type : 'table',
	            columns : 2
	        },
	        items:[{
				xtype: 'textfield',
		        itemId: 'instruId',
		        name:'instruId',
		        hidden: true,
				hideLabel:true
	        },{
				xtype: 'textfield',
		        itemId: 'instruName',
		        name:'instruName',
		        fieldLabel: '仪器名称',
		        disabled: true
	        },{
				xtype: 'textfield',
		        itemId: 'instruModel',
		        name:'instruModel',
		        fieldLabel: '仪器型号',
		        disabled: true
	        },{
				xtype:'datefield',
				itemId:'date',
				name:'date',
				format: 'Y-m-d',
				selectOnFocus:true, 
        		editable:false,
        		value:new Date(),
        		maxValue:new Date(),
				fieldLabel:'计量时间'
			},{
				xtype:'datefield',
				itemId:'effectiveDate',
				name:'effectiveDate',
				format: 'Y-m-d',
				selectOnFocus:true, 
        		editable:false, 
        		minValue:new Date(),
				fieldLabel:'有效截止期',
				vtype:'dateScope',     //自定义的验证类型  
      			vtypeText:'有效截止期小于计量时间',  
      			confirmTo:'date'    //要比较的另外一个的组件的id
			},{
				xtype:'textfield',
				name:'serialNumber',
				fieldLabel:'标准编号'
			},{
				xtype:'textfield',
				name:'unit',
				fieldLabel:'单位'
			},{
				xtype:'textarea',
				name:'remark',
				width: 500,
				colspan:2,
				fieldLabel:'备注(可选)'
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