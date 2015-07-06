Ext.define('CM.view.form.TrainingRecAddWin',{
    extend:'Ext.window.Window',
    alias:'widget.trainingrecaddwin',
    id:'trainingRecAddWin',
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
		itemId:'trainingRecAddForm',
        layout:'absolute',
        border: false,
        items:[{
			xtype: 'fieldset',
			x:10,
			y:10,
			width:525,
	        title: '培训记录',
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
				itemId:'startDate',
				name:'startDate',
				format: 'Y-m-d',
				selectOnFocus:true, 
        		editable:false, 
				fieldLabel:'开始时间'
			},{
				xtype:'datefield',
				itemId:'endDate',
				name:'endDate',
				format: 'Y-m-d',
				selectOnFocus:true, 
        		editable:false, 
				fieldLabel:'结束时间',
				vtype:'dateScope',     //自定义的验证类型  
      			vtypeText:'结束时间小于开始时间',  
      			confirmTo:'startDate'    //要比较的另外一个的组件的id
			},{
				xtype:'textfield',
				name:'unit',
				width: 500,
				colspan:2,
				fieldLabel:'组织单位'
			},{
				xtype:'textfield',
				name:'location',
				width: 500,
				colspan:2,
				fieldLabel:'地点'
			},{
				xtype:'textarea',
				name:'content',
				width: 500,
				colspan:2,
				fieldLabel:'概要内容'
			},{
				xtype:'filefield',
				name:'file',
				itemId:'file',
				colspan:2,
				fieldLabel:'总结',
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