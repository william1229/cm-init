/**
 *  人员数据列表
 */
Ext.define('CM.view.grid.TrainingRec', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.trainingrec',
	id:'trainingRec',
	layout:'fit',
	initComponent:function() {
		Ext.QuickTips.init();
		Ext.apply(this,{
				store: 'TrainingRecStore',
				enableKeyNav:true,
				columnLines:true,
				columns:[
					{header:'序号', width:50, xtype: 'rownumberer', align:'center',menuDisabled:true},
					{header:'用户名', dataIndex:'userName', flex:3, align:'center', menuDisabled:true},
					{header:'培训人员', dataIndex:'name',flex:3, align:'center',menuDisabled:true},
					{header:'开始时间', dataIndex:'startDate', flex:3.5,  align:'center',xtype:'datecolumn', format :'Y-m-d',menuDisabled:true},
					{header:'结束时间', dataIndex:'endDate', flex:3.5,  align:'center',xtype:'datecolumn', format :'Y-m-d',menuDisabled:true},
					{header:'地点', dataIndex:'location',flex:9, align:'center',menuDisabled:true},
					{header:'组织单位', dataIndex:'unit',flex:6, align:'center',menuDisabled:true},
					{header:'概要内容', dataIndex:'content', flex:15, align:'center',menuDisabled:true,
						renderer: function (value, meta, record) {
		                    var max = 45;
		                    //鼠标悬停，tip提示
		                    meta.tdAttr = 'data-qtip="' + value + '"';
		                    return value.length < max ? value : value.substring(0, max - 3) + '...';
	                	}
				    },
					{	
						header:'总结', 
						dataIndex:'path', 
						itemId: 'file',
						menuDisabled: true,
						sortable: false,
						align:'center',
						xtype: 'actioncolumn',
						width: 50,
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
					'->'/*{xtype:'button', itemId:'add',text:'新增', iconCls:'add'},'-',*/
				],
				dockedItems:[{
					xtype:'pagingtoolbar',
					store:'TrainingRecStore',
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