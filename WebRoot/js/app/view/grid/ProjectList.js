/**
 *  人员数据列表
 */
Ext.define('CM.view.grid.ProjectList', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.projectlist',
	id:'projectList',
	layout:'fit',
	initComponent:function() {
		Ext.apply(this,{
				store: 'ProjectStore',
				enableKeyNav:true,
				columnLines:true,
				columns:[
					{header:'序号', width:50, xtype: 'rownumberer', align:'center',menuDisabled:true},
					{header:'项目编号', dataIndex:'projectSN', flex:2, align:'center',menuDisabled:true},
					{header:'项目名称', dataIndex:'projectName', flex:3, align:'center', menuDisabled:true},
					{header:'签订时间', dataIndex:'date', flex:2, xtype:'datecolumn', format :'Y-m-d', align:'center', menuDisabled:true},
					{header:'委托单位', dataIndex:'unit',flex:3, align:'center', menuDisabled:true},
					{header:'联系电话', dataIndex:'telephone',flex:2, align:'center', menuDisabled:true},
					{header:'金额(元)', dataIndex:'money', flex:2, align:'center', menuDisabled:true},
					{header:'报告发放时间', dataIndex:'reportDate', flex:2, xtype:'datecolumn', format :'Y-m-d', align:'center', menuDisabled:true},
					{	
						header:'合同', 
						dataIndex:'contract', 
						itemId: 'contract',
						menuDisabled: true,
						sortable: false,
						align:'center',
						xtype: 'actioncolumn',
						width: 60,
						items: [{
						    iconCls:'download',
							tooltip: '下载'
						}]
					},{	
						header:'报告', 
						dataIndex:'report', 
						itemId: 'report',
						menuDisabled: true,
						sortable: false,
						align:'center',
						xtype: 'actioncolumn',
						width: 60,
						items: [{
						    getClass:function(value, metaData, record, rowIndex, colIndex, store) {
								if(value != '') {
									return 'download';
								} else {
									return 'x-hidden';
								}
							},
							tooltip: '下载'
						}]
					},{	
						header:'操作',
						menuDisabled: true,
						sortable: false,
						align:'center',
						xtype: 'actioncolumn',
						itemId:'operation',
						width: 80,
						items: [{
							tooltip: '添加样品',
							iconCls:'bottle',
							handler: function(grid, rowIndex, colIndex, item) {
                           		var obj = grid.getStore().getAt(rowIndex);
                           		this.fireEvent('addsampleclick', {
                              		record: obj
                           		});
                       		}
                       	},{
							tooltip: '发放报告',
							getClass:function(value, metaData, record, rowIndex, colIndex, store) {
								if(record.get('report') == '') {
									return 'report_go';
								} else {
									return 'x-hidden';
								}
							},
							handler: function(grid, rowIndex, colIndex, item) {
                           		var obj = grid.getStore().getAt(rowIndex);
                           		this.fireEvent('reportgoclick', {
                              		record: obj
                           		});
                       		}
                       	},{
							tooltip: '删除报告',
							getClass:function(value, metaData, record, rowIndex, colIndex, store) {
								if(record.get('report') != '') {
									return 'report_delete';
								} else {
									return 'x-hidden';
								}
							},
							handler: function(grid, rowIndex, colIndex, item) {
                           		var obj = grid.getStore().getAt(rowIndex);
                           		this.fireEvent('reportdeleteclick', {
                              		record: obj
                           		});
                       		}
                       	},{
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
					store:'ProjectStore',
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