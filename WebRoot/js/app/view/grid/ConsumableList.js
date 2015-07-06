/**
 *  人员数据列表
 */
Ext.define('CM.view.grid.ConsumableList', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.consumablelist',
	id:'consumableList',
	layout:'fit',
	initComponent:function() {
		Ext.apply(this,{
				store: 'ConsumableStore',
				enableKeyNav:true,
				columnLines:true,
				columns:[
					{header:'序号', xtype: 'rownumberer', width:50, align:'center',menuDisabled:true},
					{header:'耗材Id', dataIndex:'consumableId',hidden:true, align:'center', menuDisabled:true},
					{header:'耗材名称', dataIndex:'consumableName', flex:6, align:'center', menuDisabled:true},
					{header:'耗材型号', dataIndex:'consumableType', flex:5, align:'center', menuDisabled:true},
					{header:'库存数量', dataIndex:'quantity', flex:2, align:'center', menuDisabled:true},
					{
						header:'操作',
						menuDisabled: true,
						sortable: false,
						align:'center',
						xtype: 'actioncolumn',
						itemId:'operation',
						width:80,
						items: [{
							tooltip: '申请采购',
							iconCls: 'cart',
							handler: function(grid, rowIndex, colIndex, item) {
                           		var obj = grid.getStore().getAt(rowIndex);
                           		this.fireEvent('applyclick', {
                              		record: obj
                           		});
                       		}
						}/*,{
							tooltip: '入库',
							handler: function(grid, rowIndex, colIndex, item) {
                           		var obj = grid.getStore().getAt(rowIndex);
                           		this.fireEvent('doorinclick', {
                              		record: obj
                           		});
                       		}
						}*/,{
							tooltip: '出库',
							getClass:function(value, metaData, record, rowIndex, colIndex, store) {
								var user = Ext.decode(Ext.util.Cookies.get('user'));
								if(user.roleNames.indexOf('耗材管理员') > -1) {
									return 'doorOut';
								} else {
									return 'x-hidden';
								}
							},
							handler: function(grid, rowIndex, colIndex, item) {
                           		var obj = grid.getStore().getAt(rowIndex);
                           		this.fireEvent('dooroutclick', {
                              		record: obj
                           		});
                       		}
						},{
						    itemId:'delete',
							tooltip: '删除',
							getClass:function(value, metaData, record, rowIndex, colIndex, store) {
								var user = Ext.decode(Ext.util.Cookies.get('user'));
								if(user.roleNames.indexOf('耗材管理员') > -1) {
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
						}/*{
						    iconCls:'edit',
						    itemId:'edit',
							tooltip: '修改',
							handler: function(grid, rowIndex, colIndex, item) {
                           		var rec = grid.getStore().getAt(rowIndex);
                           		this.fireEvent('editclick', {
                              		record: rec
                           		});
                       		}
						},*/]
					}
				],
				tbar:[{xtype:'tbtext', itemId:'iConsumableName'},
					'->',{xtype:'button', itemId:'iConsumable',text:'查看仪器耗材', iconCls:'instru',hidden:true},
					{xtype:'button', itemId:'add',text:'新增', iconCls:'add',hidden:true}
				],
				dockedItems:[{
					xtype:'pagingtoolbar',
					store:'ConsumableStore',
					dock:'bottom',
					displayInfo:true
				}],
				features: [{
			        ftype: 'searching',
			        id:'searching',
			        minChars: 2,  
			        width: 150,
			        mode: 'remote',//远程还是本地store
			        position: 'top',//状态栏还是工具栏  
			        iconCls: 'search',//图标  
			        menuStyle: 'radio',//单选还是多选  
			        showSelectAll: false,   //是否显示全选按钮  
			        checkIndexes: ['consumableName'], //默认选择列  
			        disableIndexes: ['rownumberer','consumableId','consumableType','quantity']  //禁止那些列参与查询  
				}],
				selModel: {
					//selType:'checkboxmodel'
				}}),
/*				this.editing = Ext.create("Ext.grid.plugin.CellEditing");
				this.plugins = [this.editing];*/
				this.callParent(arguments);
		}
});