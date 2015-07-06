/**
 *  人员数据列表
 */
Ext.define('CM.view.grid.GasCsumList', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.gascsumlist',
	id:'gasCsumList',
	layout:'fit',
	initComponent:function() {
		Ext.apply(this,{
				store: 'GasCsumStore',
				enableKeyNav:true,
				columnLines:true,
				columns:[
					{header:'序号', xtype: 'rownumberer', width:50, align:'center',menuDisabled:true},
					{header:'文件名', dataIndex:'filename', flex:15, align:'center', menuDisabled:true},
					{header:'上传时间', dataIndex:'date', flex:2, xtype:'datecolumn', format :'Y-m-d', align:'center', menuDisabled:true},
					{header:'上传人', dataIndex:'name', flex:2, align:'center', menuDisabled:true},
					{header:'下载', dataIndex:'path', flex:1, align:'center', menuDisabled:true,
						itemId: 'file',
						sortable: false,
						xtype: 'actioncolumn',
						width: 60,
						items: [{
						    iconCls:'download',
							tooltip: '下载'
						}]
					},{
						header:'操作',
						menuDisabled: true,
						sortable: false,
						align:'center',
						xtype: 'actioncolumn',
						itemId:'operation',
						width:80,
						items: [{
						    itemId:'delete',
							tooltip: '删除',
							getClass:function(value, metaData, record, rowIndex, colIndex, store) {
								var user = Ext.decode(Ext.util.Cookies.get('user'));
								if(user.roleNames.indexOf('耗材管理员') > -1) {
									return 'delete';
								} else {
									return 'x-hidden';
								}
							},
							handler: function(grid, rowIndex, colIndex, item) {
                           		var obj = grid.getStore().getAt(rowIndex);
                           		this.fireEvent('deleteclick', {
                              		record: obj
                           		});
                       		}
						}]
					}
				],
				tbar:['->',
					{xtype:'button', itemId:'upload',text:'上传', iconCls:'upload'}
				],
				dockedItems:[{
					xtype:'pagingtoolbar',
					store:'GasCsumStore',
					dock:'bottom',
					displayInfo:true
				}],
				selModel: {
					//selType:'checkboxmodel'
				}}),
/*				this.editing = Ext.create("Ext.grid.plugin.CellEditing");
				this.plugins = [this.editing];*/
				this.callParent(arguments);
		}
});