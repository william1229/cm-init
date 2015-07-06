Ext.define('CM.view.form.SSampleAddWin',{
    extend:'Ext.window.Window',
    alias:'widget.ssampleaddwin',
    id:'sSampleAddWin',
    title:'新增标样',
    width:560,//要比表单的宽高大一点
    height:430,
    fileUpload : true,
    layout : 'fit',
    resizable: false, //禁止改变窗口大小
    modal:true,
    closeAction:'hide',
    items:[{
		xtype:'form',
		itemId:'sSampleAddForm',
        layout:'absolute',
        border: false,
        items:[{
			xtype: 'fieldset',
			x:10,
			y:10,
			width:525,
	        title: '标样信息',
	        defaults: {
	            hideEmptyLabel: false,
	            labelWidth: 85,
	            labelAlign:'right'
	        },
	        layout : {
				type : 'table',
	            columns : 2
	        }, 
	        items:[{
				xtype:'textfield',
				name:'sSampleName',
				fieldLabel:'标准物质名称'
			},{
				xtype: 'textfield',
		        itemId: 'standardSN',
		        name:'standardSN',
		        fieldLabel: '标准编号',
		        allowBlank: false
	        },{
				xtype:'textfield',
				name:'sampleSN',
				fieldLabel:'样品编号'
			},{
				xtype:'textfield',
				name:'standardValue',
				fieldLabel:'标准值'
			},{
				xtype:'textfield',
				name:'relativeUncertainty',
				fieldLabel:'相对不确定度(k=2)'
			},{
				xtype:'textfield',
				name:'specification',
				fieldLabel:'规格'
			},{
				xtype:'datefield',
				itemId:'date',
				name:'date',
				format: 'Y-m-d',
				selectOnFocus:true, 
        		editable:false, 
				fieldLabel:'购买日期'
			},{
				xtype:'datefield',
				itemId:'effectiveDate',
				name:'effectiveDate',
				format: 'Y-m-d',
				selectOnFocus:true, 
        		editable:false, 
				fieldLabel:'有效截止期',
				vtype:'dateScope',     //自定义的验证类型  
      			vtypeText:'有效截止期小于购买时间',  
      			confirmTo:'date'    //要比较的另外一个的组件的id
			},{
				xtype:'textfield',
				name:'source',
				fieldLabel:'来源'
			},{
				xtype:'textfield',
				name:'storageRequirements',
				fieldLabel:'存放要求'
			},{
				xtype:'numberfield',
				name:'quantity',
				itemId:'quantity',
				fieldLabel:'库存数量',
            	allowBlank: false,
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