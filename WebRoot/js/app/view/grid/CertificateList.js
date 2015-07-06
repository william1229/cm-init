/**
 *  人员数据列表
 */
Ext.define('CM.view.grid.CertificateList', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.certificatelist',
	id:'certificateList',
	layout:'fit',
	initComponent:function() {
		Ext.apply(this,{
				store: 'CertificateStore',
				enableKeyNav:true,
				columnLines:true,
				columns:[
					{xtype: 'rownumberer',header:'序号', width:50, align:'center',menuDisabled:true},
					{header:'用户ID', dataIndex:'userId',hidden:true, align:'center', menuDisabled:true},
					{header:'用户名', dataIndex:'userName', flex:1, align:'center', menuDisabled:true},
					{header:'姓名', dataIndex:'name',flex:1, align:'center', menuDisabled:true},
					{header:'证书名称', dataIndex:'certificateName',flex:2, align:'center', menuDisabled:true},
					{header:'证书类型', dataIndex:'type',flex:2, align:'center', menuDisabled:true},
					{header:'颁发时间', dataIndex:'date', flex:1, xtype:'datecolumn', format :'Y-m-d', align:'center', menuDisabled:true},
					{header:'颁发机构', dataIndex:'authority',flex:3, align:'center', menuDisabled:true},
					{
						header:'操作',
						menuDisabled: true,
						sortable: false,
						align:'center',
						xtype: 'actioncolumn',
						itemId:'operation',
						width: 80,
						items: [{
						    iconCls:'delete',
						    itemId:'delete',
							tooltip: '删除',
							handler: function(grid, rowIndex, colIndex, item) {
                           		var obj = grid.getStore().getAt(rowIndex);
                           		this.fireEvent('deleteclick', {
                              		record: obj
                           		});
                       		}
						}]
					}
				],
				tbar:[
					'->'
				],
				dockedItems:[{
					xtype:'pagingtoolbar',
					store:'CertificateStore',
					dock:'bottom',
					displayInfo:true
				}],
				selModel: {
					//selType:'checkboxmodel'
				}}),
				this.callParent(arguments);
		}
});