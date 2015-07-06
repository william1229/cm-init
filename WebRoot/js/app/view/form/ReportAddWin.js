Ext.define('CM.view.form.ReportAddWin',{
    extend:'Ext.window.Window',
    alias:'widget.reportaddwin',
    id:'reportAddWin',
    title:'发放报告',
    width:560,//要比表单的宽高大一点
    height:430,
    layout : 'fit',
    resizable: false, //禁止改变窗口大小
    modal:true,
    closeAction:'hide',
    items:[{
		xtype:'form',
		itemId:'reportAddForm',
        layout:'absolute',
        border: false,
        items:[{
			xtype: 'fieldset',
			x:10,
			y:10,
			width:525,
	        title: '报告信息',
	        layout : {
				type : 'table',
	            columns : 2
	        }, 
	        defaults: {
	            hideEmptyLabel: false,
	            labelWidth: 90,
	            labelAlign:'right',
	            allowBlank: false
	        },
	        items:[{
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
				xtype:'datefield',
				itemId:'date',
				name:'date',
				format: 'Y-m-d',
				colspan:2,
				fieldLabel: '签订时间',
				selectOnFocus:true, 
        		editable:false,
        		disabled:true
			},{
				xtype:'datefield',
				itemId:'reportDate',
				name:'reportDate',
				format: 'Y-m-d',
				fieldLabel: '发放时间',
				selectOnFocus:true, 
				colspan:2,
        		editable:false,
        		value : new Date(),
        		vtype:'dateScope',     //自定义的验证类型  
      			vtypeText:'发放时间小于签订时间',  
      			confirmTo:'date'    //要比较的另外一个的组件的id
			},{
				xtype:'filefield',
				name:'report',
				itemId:'report',
				fieldLabel:'报告文件',
				buttonText: '选择文件',
				colspan:2,
				width: 490,
            	allowBlank: false
			},{
				xtype: 'textfield',
				itemId: 'projectId',
		        name: 'projectId',
		        hidden:true
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