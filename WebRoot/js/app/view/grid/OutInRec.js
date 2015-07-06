/**
 *  人员数据列表
 */
Ext.define('CM.view.grid.OutInRec', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.outinrec',
	id:'outInRec',
	layout:'fit',
	initComponent:function() {
		Ext.QuickTips.init();
		Ext.apply(this,{
				store: 'OutInRecStore',
				enableKeyNav:true,
				columnLines:true,
				columns:[
					{header:'序号', width:50, xtype: 'rownumberer', align:'center',menuDisabled:true},
					{header:'用户名', dataIndex:'userName',flex:2, align:'center', menuDisabled:true},
					{header:'申请人', dataIndex:'name',flex:2, align:'center', menuDisabled:true},
					{header:'仪器名称', dataIndex:'instruName',flex:6, align:'center', menuDisabled:true},
					{header:'仪器型号', dataIndex:'instruModel',flex:4, align:'center', menuDisabled:true},
					{header:'耗材名称', dataIndex:'consumableName',flex:4, align:'center', menuDisabled:true},
					{header:'耗材类型', dataIndex:'consumableType',flex:4, align:'center', menuDisabled:true},
					{header:'时间', dataIndex:'date', flex:2, xtype:'datecolumn', format :'Y-m-d', align:'center', menuDisabled:true},
					{header:'数量', dataIndex:'quantity',flex:2, align:'center', menuDisabled:true},
					{header:'行为', dataIndex:'action', flex:1.5, align:'center', menuDisabled:true,
						renderer:function(value, metaData, record ,rowIndex, colIndex, store, view) {
							if(value == '出库') {
								return '<font color="red">' + value + '</font>';
							} else if(value == '入库') {
								return '<font color="green">' + value + '</font>';
							}
						}
					}
				],
				tbar:[
					'->'
				],
				dockedItems:[{
					xtype:'pagingtoolbar',
					store:'OutInRecStore',
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