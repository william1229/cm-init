Ext.define('CM.view.form.SampleAddWin',{
    extend:'Ext.window.Window',
    alias:'widget.sampleaddwin',
    id:'sampleAddWin',
    title:'新增样品',
    width:560,//要比表单的宽高大一点
    height:430,
    layout : 'fit',
    resizable: false, //禁止改变窗口大小
    modal:true,
    closeAction:'hide',
    items:[{
		xtype:'form',
		itemId:'sampleAddForm',
        layout:'absolute',
        border: false,
        items:[{
			xtype: 'fieldset',
			x:10,
			y:10,
			width:525,
	        title: '样品信息',
	        layout : {
				type : 'table',
	            columns : 2
	        }, 
	        defaults: {
	            hideEmptyLabel: false,
	            labelWidth: 80,
	            labelAlign:'right',
	            allowBlank: false
	        },
	        items:[{
				xtype: 'textfield',
				itemId: 'projectId',
		        name: 'projectId',
		        fieldLabel: '项目ID',
		        hidden:true
	        },{
				xtype: 'textfield',
				itemId: 'projectSN',
		        name: 'projectSN',
		        fieldLabel: '项目编号',
		        disabled:true
	        },{
				xtype: 'textfield',
				itemId: 'projectName',
		        name: 'projectName',
		        fieldLabel: '项目名称',
		        disabled:true
	        },{
				xtype: 'textfield',
				itemId: 'sampleSN',
		        name: 'sampleSN',
		        fieldLabel: '样品编号'
			},{
				xtype: 'textfield',
				itemId: 'state',
		        name: 'state',
		        fieldLabel: '性状'
			},{
	            xtype:'numberfield',
	            itemId:'quantity',
	            name:'quantity',
	            fieldLabel:'数量',
	            // Remove spinner buttons, and arrow key and mouse wheel listeners
        		hideTrigger: true,
       			keyNavEnabled: false,
        		mouseWheelEnabled: false
	        },{
				xtype:'datefield',
				itemId:'date',
				name:'date',
				format: 'Y-m-d',
				fieldLabel: '送样时间',
				selectOnFocus:true, 
        		editable:false,
        		colspan:2,
        		value : new Date()
			},{
				xtype: 'textfield',
				itemId: 'name',
		        name: 'name',
		        fieldLabel: '送样人'
			},{
	            xtype:'numberfield',
	            itemId:'telephone',
	            name:'telephone',
	            colspan:2,
	            fieldLabel:'联系电话',
	            blankText : '电话不能为空',
	            // Remove spinner buttons, and arrow key and mouse wheel listeners
        		hideTrigger: true,
       			keyNavEnabled: false,
        		mouseWheelEnabled: false
	        },{
				xtype: 'textfield',
				itemId: 'unit',
		        name: 'unit',
		        colspan:2,
		        width:470,
		        fieldLabel: '送样单位'
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