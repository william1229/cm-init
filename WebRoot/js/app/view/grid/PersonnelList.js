/**
 *  人员数据列表
 */
Ext.define('CM.view.grid.PersonnelList', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.personnellist',
	id:'personnelList',
	layout:'fit',
	initComponent:function() {
		Ext.apply(this,{
				store: 'UserStore',
				enableKeyNav:true,
				columnLines:true,
				columns:[
					{xtype: 'rownumberer',header:'序号', width:50, align:'center',menuDisabled:true},
					{header:'用户ID', dataIndex:'userId',hidden:true, align:'center', menuDisabled:true},
					{header:'用户名', dataIndex:'userName', flex:2, align:'center', menuDisabled:true},
					{header:'姓名', dataIndex:'name',flex:2, align:'center', menuDisabled:true},
					{header:'性别', dataIndex:'sex', flex:1.5, align:'center',menuDisabled:true},
					{header:'电话', dataIndex:'telephone', flex:3, align:'center', menuDisabled:true},
					{header:'E-mail', dataIndex:'email', flex:4, align:'center', menuDisabled:true},
					{header:'毕业院校', dataIndex:'school', flex:4, align:'center', menuDisabled:true},
					{header:'专业', dataIndex:'major', flex:3, align:'center', menuDisabled:true},
					{header:'学位', dataIndex:'degree', flex:2, align:'center', menuDisabled:true},
					{text:'工作内容', dataIndex:'jobContent', flex:10, align:'center', menuDisabled:true},
					{	
						header:'操作',
						menuDisabled: true,
						sortable: false,
						align:'center',
						xtype: 'actioncolumn',
						itemId:'operation',
						width: 80,
						items: [{
							tooltip: '新增证书',
							iconCls: 'book',
							handler: function(grid, rowIndex, colIndex, item) {
                           		var obj = grid.getStore().getAt(rowIndex);
                           		this.fireEvent('bookclick', {
                              		record: obj
                           		});
                       		}
                       	},{
							tooltip: '新增考核记录',
							iconCls: 'page',
							handler: function(grid, rowIndex, colIndex, item) {
                           		var obj = grid.getStore().getAt(rowIndex);
                           		this.fireEvent('pageclick', {
                              		record: obj
                           		});
                       		}
                       	},{
							tooltip: '新增培训记录',
							iconCls: 'report',
							handler: function(grid, rowIndex, colIndex, item) {
                           		var obj = grid.getStore().getAt(rowIndex);
                           		this.fireEvent('reportclick', {
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
					store:'UserStore',
					dock:'bottom',
					displayInfo:true
				}],
				selModel: {
					//selType:'checkboxmodel'
				}}),
				this.callParent(arguments);
		}
});