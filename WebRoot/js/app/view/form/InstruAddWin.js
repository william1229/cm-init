Ext.define('CM.view.form.InstruAddWin',{
    extend:'Ext.window.Window',
    alias:'widget.instruaddwin',
    id:'instruAddWin',
    title:'新增仪器',
    width:560,//要比表单的宽高大一点
    height:430,
    layout : 'fit',
    resizable: false, //禁止改变窗口大小
    modal:true,
    closeAction:'hide',
    items:[{
		xtype:'form',
		itemId:'instruAddForm',
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
	            labelAlign:'right',
	            allowBlank:false
	        },
	        items:[{
				xtype: 'textfield',
		        name: 'instruName',
		        fieldLabel: '仪器名称'
	        },{
				xtype:'textfield',
				name:'instruModel',
				fieldLabel:'仪器型号'
			},{
				xtype:'textfield',
				name:'instruNum',
				fieldLabel:'仪器编号'
			},{
				xtype:'textfield',
				name:'manufacturer',
				fieldLabel:'制造商'
			},{
				xtype:'textfield',
				name:'country',
				fieldLabel:'国别'
			},{
				xtype:'textfield',
				name:'distributor',
				fieldLabel:'经销商'
			},{
				xtype:'datefield',
				name:'date',
				format: 'Y-m-d',
				selectOnFocus:true, 
        		editable:false, 
				fieldLabel:'购置时间',
				maxValue:new Date()
			},{
				xtype:'numberfield',
				name:'money',
				fieldLabel:'购置金额',
				// Remove spinner buttons, and arrow key and mouse wheel listeners
        		hideTrigger: true,
       			keyNavEnabled: false,
        		mouseWheelEnabled: false
			},{
				xtype:'textfield',
				name:'location',
				fieldLabel:'放置地点'
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