/**
 *  人员数据列表
 */
Ext.define('CM.view.grid.StandardList', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.standardlist',
	id:'standardList',
	layout:'fit',
	initComponent:function() {
		Ext.apply(this,{
				store: 'StandardStore',
				enableKeyNav:true,
				columnLines:true,
				columns:[
					{header:'序号', xtype: 'rownumberer', width:50, align:'center',menuDisabled:true},
					{header:'标准ID', dataIndex:'standardId', align:'center',menuDisabled:true,hidden:true},
					{header:'标准编号', dataIndex:'serialNumber', flex:5, align:'center',menuDisabled:true},
					{header:'标准名称', dataIndex:'name', flex:5, align:'center',menuDisabled:true},
					{header:'发布时间', dataIndex:'date', flex:3,  align:'center',xtype:'datecolumn', format :'Y-m-d',menuDisabled:true},
					{header:'有效截止期', dataIndex:'effectiveDate', flex:3,  align:'center',xtype:'datecolumn', format :'Y-m-d',menuDisabled:true},
					{header:'状态', dataIndex:'state', flex:2, align:'center',menuDisabled:true,
						renderer:function(value, metaData, record ,rowIndex, colIndex, store, view) {
							if(value == '作废') {
								return '<font color="red">' + value + '</font>';
							} else {
								return value;
							}
						}
					},{	
						header:'标准文件', 
						dataIndex:'path', 
						itemId: 'file',
						menuDisabled: true,
						sortable: false,
						align:'center',
						xtype: 'actioncolumn',
						width: 80,
						items: [{
						    iconCls:'download',
							tooltip: '下载'
						}]
						
					},
					{
						header:'操作',
						sortable: false,
						align:'center',
						xtype: 'actioncolumn',
						itemId:'operation',
						menuDisabled:true,
						width: 80,
						items: [{
							tooltip: '启用',
							getClass:function(value, metaData, record, rowIndex, colIndex, store) {
								if(record.get('state') == '作废'){
									return 'start'
								} else {
									return 'x-hidden';
								}
							},
							handler: function(grid, rowIndex, colIndex, item) {
                           		var obj = grid.getStore().getAt(rowIndex);
                           		this.fireEvent('stratclick', {
                              		record: obj
                           		});
                       		}
						},{
							tooltip: '停用',
							getClass:function(value, metaData, record, rowIndex, colIndex, store) {
								if(record.get('state') == '在用'){
									return 'stop'
								} else {
									return 'x-hidden';
								}
							},
							handler: function(grid, rowIndex, colIndex, item) {
                           		var obj = grid.getStore().getAt(rowIndex);
                           		this.fireEvent('stopclick', {
                              		record: obj
                           		});
                       		}
						},{
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
					'->',{xtype:'button', itemId:'add',text:'新增', iconCls:'add'}
				],
				dockedItems:[{
					xtype:'pagingtoolbar',
					store:'StandardStore',
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