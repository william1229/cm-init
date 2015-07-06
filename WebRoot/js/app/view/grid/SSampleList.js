/**
 *  人员数据列表
 */
Ext.define('CM.view.grid.SSampleList', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.ssamplelist',
	id:'sSampleList',
	layout:'fit',
	initComponent:function() {
		Ext.apply(this,{
				store: 'SSampleStore',
				enableKeyNav:true,
				columnLines:true,
				columns:[
					{header:'序号', xtype: 'rownumberer', width:50, align:'center',menuDisabled:true},
					{header:'标样ID', dataIndex:'sSampleId',hidden:true, align:'center',menuDisabled:true},
					{header:'标准物质名称', dataIndex:'sSampleName', flex:4, align:'center',menuDisabled:true},
					{header:'标准编号', dataIndex:'standardSN', flex:3, align:'center',menuDisabled:true},
					{header:'样品编号', dataIndex:'sampleSN', flex:3, align:'center',menuDisabled:true},
					{header:'标准值', dataIndex:'standardValue', flex:2, align:'center',menuDisabled:true},
					{header:'相对不确定度(k=2)', dataIndex:'relativeUncertainty', flex:4, align:'center',menuDisabled:true},
					{header:'规格', dataIndex:'specification', flex:2, align:'center',menuDisabled:true},
					{header:'购买日期', dataIndex:'date', flex:3,  align:'center',xtype:'datecolumn', format :'Y-m-d',menuDisabled:true},
					{header:'有效截止期', dataIndex:'effectiveDate', flex:3,  align:'center',xtype:'datecolumn', format :'Y-m-d',menuDisabled:true},
					{header:'来源', dataIndex:'source', flex:4, align:'center',menuDisabled:true},
					{header:'存放要求', dataIndex:'storageRequirements', flex:2.5, align:'center',menuDisabled:true},
					{header:'库存数量', dataIndex:'quantity', flex:2.5, align:'center',menuDisabled:true},
					{
						header:'操作',
						sortable: false,
						align:'center',
						xtype: 'actioncolumn',
						itemId:'operation',
						menuDisabled:true,
						hidden:true,
						width: 80,
						items: [{
							tooltip: '出库',
							iconCls: 'doorOut',
							handler: function(grid, rowIndex, colIndex, item) {
                           		var obj = grid.getStore().getAt(rowIndex);
                           		this.fireEvent('dooroutclick', {
                              		record: obj
                           		});
                       		}
						},{
							tooltip: '入库',
							iconCls: 'doorIn',
							handler: function(grid, rowIndex, colIndex, item) {
                           		var obj = grid.getStore().getAt(rowIndex);
                           		this.fireEvent('doorinclick', {
                              		record: obj
                           		});
                       		}
						},{
						    iconCls:'delete',
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
					store:'SSampleStore',
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