/**
 *  人员数据列表
 */
Ext.define('CM.view.grid.InReviewList', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.inreviewlist',
	id:'inReviewList',
	layout:'fit',
	initComponent:function() {
		Ext.apply(this,{
				store: 'InReviewStore',
				enableKeyNav:true,
				columnLines:true,
				columns:[
					{header:'序号', width:50, xtype: 'rownumberer', align:'center',menuDisabled:true},
					{header:'时间', dataIndex:'date', flex:3, xtype:'datecolumn', format :'Y-m-d', align:'center', menuDisabled:true},
					{	
						header:'报告', 
						dataIndex:'path', 
						itemId: 'file',
						menuDisabled: true,
						sortable: false,
						align:'center',
						xtype: 'actioncolumn',
						width: 60,
						items: [{
						    iconCls:'download',
							tooltip: '下载'
						}]
					},
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
					'->',{xtype:'button', itemId:'add',text:'新增', iconCls:'add'}
				],
				dockedItems:[{
					xtype:'pagingtoolbar',
					store:'InReviewStore',
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