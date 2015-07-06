/**
 *  人员数据列表
 */
Ext.define('CM.view.grid.InstruList', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.instrulist',
	id:'instruList',
	layout:'fit',
	initComponent:function() {
		Ext.apply(this,{
				store: 'InstruStore',
				enableKeyNav:true,
				columnLines:true,
				columns:[
					{header:'序号', xtype: 'rownumberer', width:50, align:'center',menuDisabled:true},
					{header:'仪器Id', dataIndex:'instruId',hidden:true, align:'center', menuDisabled:true},
					{header:'仪器名称', dataIndex:'instruName', flex:5, align:'center', menuDisabled:true},
					{header:'仪器型号', dataIndex:'instruModel', flex:3, align:'center', menuDisabled:true},
					{header:'仪器编号', dataIndex:'instruNum', flex:2, align:'center', menuDisabled:true},
					{header:'放置地点', dataIndex:'location', flex:1.6, align:'center', menuDisabled:true},
					{header:'购置时间', dataIndex:'date', flex:2, xtype:'datecolumn', format :'Y-m-d', align:'center', menuDisabled:true},
					{header:'购置金额(元)', dataIndex:'money', flex:2, align:'center', menuDisabled:true},
					{header:'制造商', dataIndex:'manufacturer', align:'center', menuDisabled:true},
					{header:'国别', dataIndex:'country', flex:1.3, align:'center',menuDisabled:true},
					{header:'经销商', dataIndex:'distributor', flex:3, align:'center', menuDisabled:true},
					{header:'管理员', dataIndex:'name', itemId:'name', flex:1.3, align:'center', menuDisabled:true,hidden:true},
					{header:'仪器管理员用户ID', dataIndex:'userId', itemId:'userId', align:'center', menuDisabled:true,hidden:true},
					{
						header:'操作',
						sortable: false,
						align:'center',
						xtype: 'actioncolumn',
						itemId:'operation',
						menuDisabled:true,
						width: 90,
						items: [{
							tooltip: '维修',
							iconCls:'repair',
							handler: function(grid, rowIndex, colIndex, item) {
                           		var obj = grid.getStore().getAt(rowIndex);
                           		this.fireEvent('repairclick', {
                              		record: obj
                           		});
                       		}
						},{
							tooltip: '计量',
							iconCls:'metrical',
							handler: function(grid, rowIndex, colIndex, item) {
                           		var obj = grid.getStore().getAt(rowIndex);
                           		this.fireEvent('metricalclick', {
                              		record: obj
                           		});
                       		}
						},{
							tooltip: '授权',
							getClass:function(value, metaData, record, rowIndex, colIndex, store) {
								var user = Ext.decode(Ext.util.Cookies.get('user'));
								if(user.roleNames.indexOf('系统管理员') > -1) {
									return 'keyAdd';
								} else {
									return 'x-hidden';
								}
							},
							handler: function(grid, rowIndex, colIndex, item) {
                           		var obj = grid.getStore().getAt(rowIndex);
                           		this.fireEvent('accreditclick', {
                              		record: obj
                           		});
                       		}
						},{
							tooltip: '撤销授权',
							getClass:function(value, metaData, record, rowIndex, colIndex, store) {
								var user = Ext.decode(Ext.util.Cookies.get('user'));
								if(user.roleNames.indexOf('系统管理员') > -1) {
									return 'keyDelete';
								} else {
									return 'x-hidden';
								}
							},
							handler: function(grid, rowIndex, colIndex, item) {
                           		var obj = grid.getStore().getAt(rowIndex);
                           		this.fireEvent('accreditdeleteclick', {
                              		record: obj
                           		});
                       		}
						},{
							tooltip: '删除',
							getClass:function(value, metaData, record, rowIndex, colIndex, store) {
								var user = Ext.decode(Ext.util.Cookies.get('user'));
								if(user.roleNames.indexOf('系统管理员') > -1) {
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
				tbar:[
					'->',{xtype:'button', itemId:'add',text:'新增', iconCls:'add'}
				],
				dockedItems:[{
					xtype:'pagingtoolbar',
					store:'InstruStore',
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