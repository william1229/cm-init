/**
 *  人员数据列表
 */
Ext.define('CM.view.grid.MetricalList', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.metricallist',
	id:'metricalList',
	layout:'fit',
	initComponent:function() {
		Ext.QuickTips.init();
		Ext.apply(this,{
				store: 'MetricalStore',
				enableKeyNav:true,
				columnLines:true,
				columns:[
					{header:'序号', width:50, xtype: 'rownumberer', align:'center',menuDisabled:true},
					{header:'仪器名称', dataIndex:'instruName',flex:2, align:'center', menuDisabled:true},
					{header:'仪器型号', dataIndex:'instruModel',flex:2, align:'center', menuDisabled:true},
					{header:'计量时间', dataIndex:'date', flex:2, xtype:'datecolumn', format :'Y-m-d', align:'center', menuDisabled:true},
					{header:'有效截止期', dataIndex:'effectiveDate', flex:2, xtype:'datecolumn', format :'Y-m-d', align:'center', menuDisabled:true},
					{header:'标准编号', dataIndex:'serialNumber', flex:3, align:'center',menuDisabled:true},
					{header:'单位', dataIndex:'unit',flex:4, align:'center',menuDisabled:true},
					{header:'备注', dataIndex:'remark',flex:7, align:'center', menuDisabled:true,
						renderer: function (value, meta, record) {
		                    var max = 45;
		                    //鼠标悬停，tip提示
		                    meta.tdAttr = 'data-qtip="' + value + '"';
		                    return value.length < max ? value : value.substring(0, max - 3) + '...';
	                	}
	                },{	
						header:'操作',
						menuDisabled: true,
						sortable: false,
						align:'center',
						xtype: 'actioncolumn',
						itemId:'operation',
						width: 80,
						items: [{
							tooltip: '删除',
							iconCls: 'delete',
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
					store:'MetricalStore',
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