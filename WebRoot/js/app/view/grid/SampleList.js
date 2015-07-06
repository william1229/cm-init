/**
 *  人员数据列表
 */
Ext.define('CM.view.grid.SampleList', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.samplelist',
	id:'sampleList',
	layout:'fit',
	initComponent:function() {
		Ext.apply(this,{
				store: 'SampleStore',
				enableKeyNav:true,
				columnLines:true,
				columns:[
					{header:'序号', width:50, xtype: 'rownumberer', align:'center',menuDisabled:true},
					{header:'项目编号', dataIndex:'projectSN', flex:3, align:'center',menuDisabled:true},
					{header:'样品编号', dataIndex:'sampleSN', flex:3, align:'center',menuDisabled:true},
					{header:'性状', dataIndex:'state', flex:2, align:'center', menuDisabled:true},
					{header:'数量', dataIndex:'quantity', flex:1, align:'center', menuDisabled:true},
					{header:'送样单位', dataIndex:'unit',flex:3, align:'center', menuDisabled:true},
					{header:'送样时间', dataIndex:'date', flex:1.7, xtype:'datecolumn', format :'Y-m-d', align:'center', menuDisabled:true},
					{header:'送样人', dataIndex:'name',flex:1, align:'center', menuDisabled:true},
					{header:'联系电话', dataIndex:'telephone',flex:3, align:'center', menuDisabled:true},
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
							tooltip: '删除'
						}/*,{
						    iconCls:'edit',
							tooltip: '删除'
						}*/]
					}
				],
				tbar:[
					'->'
				],
				dockedItems:[{
					xtype:'pagingtoolbar',
					store:'SampleStore',
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