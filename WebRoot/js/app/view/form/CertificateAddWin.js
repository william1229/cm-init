Ext.define('CM.view.form.CertificateAddWin',{
    extend:'Ext.window.Window',
    alias:'widget.certificateaddwin',
    id:'certificateAddWin',
    title:'新增证书',
    width:560,//要比表单的宽高大一点
    height:430,
    fileUpload : true,
    layout : 'fit',
    resizable: false, //禁止改变窗口大小
    modal:true,
    closeAction:'hide',
    items:[{
		xtype:'form',
		itemId:'certificateAddForm',
        layout:'absolute',
        border: false,
        items:[{
			xtype: 'fieldset',
			x:10,
			y:10,
			width:525,
	        title: '证书信息',
	        defaults: {
	            hideEmptyLabel: false,
	            labelWidth: 60,
	            labelAlign:'right',
	            allowBlank: false
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
				xtype:'textfield',
				name:'certificateName',
				fieldLabel:'证书名称'
			},{
				xtype:'textfield',
				name:'type',
				fieldLabel:'证书类型'
			},{
				xtype:'datefield',
				itemId:'date',
				name:'date',
				format: 'Y-m-d',
				selectOnFocus:true, 
        		editable:false,
        		colspan:2,
				fieldLabel:'颁发时间'
			},{
				xtype:'textfield',
				name:'authority',
				width: 500,
				colspan:2,
				fieldLabel:'颁发机构'
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