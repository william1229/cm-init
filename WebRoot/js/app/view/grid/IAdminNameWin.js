/**
 *  人员数据列表
 */
Ext.define('CM.view.grid.IAdminNameWin',{
    extend:'Ext.window.Window',
    alias:'widget.iadminnamewin',
    title:'用戶列表',
    id:'iAdminNameWin',
    width:480,//要比表单的宽高大一点
    height:430,
    layout : 'fit',
    modal : true, // 开启遮罩
    resizable: false,//禁止改变窗口大小
    closeAction:'hide',
    items:[{
		xtype:'grid',
		itemId:'iAdminNameList',
		store: 'IAdminNameStore',
		enableKeyNav:true,
		columnLines:true,
		columns:[
			{header:'序号', xtype: 'rownumberer', width:50, align:'center', menuDisabled:true},
			{header:'用户ID', dataIndex:'userId',hidden:true, align:'center', menuDisabled:true},
			{header:'用户名', dataIndex:'userName', flex:3, align:'center', menuDisabled:true},
			{header:'姓名', dataIndex:'name', flex:3, align:'center', menuDisabled:true},
			{header:'操作',
				menuDisabled: true,
				sortable: false,
				align:'center',
				xtype: 'actioncolumn',
				itemId:'operation',
				width: 80,
				items: [{
				    iconCls:'select',
					tooltip: '选择'
				}]
			}
		],
/*		tbar:[
			'->',{xtype:'button', itemId:"userSearch",text:'查询', iconCls:'table_remove'}
		],*/
		dockedItems:[{
			xtype:'pagingtoolbar',
			store:'IAdminNameStore',
			dock:'bottom',
			displayInfo:true
		}]
	}]
});
