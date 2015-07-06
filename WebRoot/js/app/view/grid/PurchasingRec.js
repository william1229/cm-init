/**
 *  人员数据列表
 */
Ext.define('CM.view.grid.PurchasingRec', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.purchasingrec',
	id:'purchasingRec',
	layout:'fit',
	initComponent:function() {
		Ext.QuickTips.init();
		Ext.apply(this,{
				store: 'PurchasingRecStore',
				enableKeyNav:true,
				columnLines:true,
				columns:[
					{header:'序号', width:50, xtype: 'rownumberer', align:'center',menuDisabled:true},
					{header:'记录ID', dataIndex:'recId',hidden:true, align:'center', menuDisabled:true},
					{header:'仪器ID', dataIndex:'instruId',hidden:true, align:'center', menuDisabled:true},
					{header:'用户ID', dataIndex:'userId',hidden:true, align:'center', menuDisabled:true},
					{header:'耗材ID', dataIndex:'consumableId',hidden:true, align:'center', menuDisabled:true},
					{header:'库存量', dataIndex:'currentQuantity',hidden:true, align:'center', menuDisabled:true},
					{header:'用户名', dataIndex:'userName',flex:2, align:'center', menuDisabled:true},
					{header:'申请人', dataIndex:'name',flex:2, align:'center', menuDisabled:true},
					{header:'仪器名称', dataIndex:'instruName',flex:4, align:'center', menuDisabled:true},
					{header:'仪器型号', dataIndex:'instruModel',flex:4, align:'center', menuDisabled:true},
					{header:'耗材名称', dataIndex:'consumableName',flex:4, align:'center', menuDisabled:true},
					{header:'耗材类型', dataIndex:'consumableType',flex:4, align:'center', menuDisabled:true},
					{header:'申请日期', dataIndex:'date', flex:2.7, xtype:'datecolumn', format :'Y-m-d',menuDisabled:true},
					{header:'申请数量', dataIndex:'quantity',flex:2.3, align:'center', menuDisabled:true},
					{header:'说明', dataIndex:'remark', flex:8, align:'center', menuDisabled:true,
						renderer: function (value, metaData, record ,rowIndex, colIndex, store, view) {
		                    var max = 45;
		                    //鼠标悬停，tip提示
		                    metaData.tdAttr = 'data-qtip="' + value + '"';
		                    return value.length < max ? value : value.substring(0, max - 3) + '...';
	                	}
	                },
					{header:'状态', dataIndex:'state',flex:2, align:'center', menuDisabled:true,
						renderer:function(value, metaData, record ,rowIndex, colIndex, store, view) {
							if(value == '已通过') {
								return '<font color="green">' + value + '</font>';
							} else if(value == '未通过') {
								return '<font color="red">' + value + '</font>';
							} else if(value == '待审批') {
								return value;
							} else if(value == '已入库'  || value == '已撤销') {
								return '<font color="gray">' + value + '</font>';
							}
							
						}
					},
					{header:'操作',
						menuDisabled: true,
						sortable: false,
						align:'center',
						xtype: 'actioncolumn',
						itemId:'operation',
						width: 80,
						items: [{
							tooltip: '同意',
							getClass:function(value, metaData, record, rowIndex, colIndex, store) {
								var user = Ext.decode(Ext.util.Cookies.get('user'));
								if(record.get('state') == '待审批' && user.roleNames.indexOf('系统管理员') > -1){
									return 'select'
								} else {
									return 'x-hidden';
								}
							},
							handler: function(grid, rowIndex, colIndex, item) {
                           		var obj = grid.getStore().getAt(rowIndex);
                           		this.fireEvent('agreeclick', {
                              		record: obj
                           		});
                       		}
						},{
							tooltip: '驳回',
							getClass:function(value, metaData, record, rowIndex, colIndex, store) {
								var user = Ext.decode(Ext.util.Cookies.get('user'));
								if(record.get('state') == '待审批' && user.roleNames.indexOf('系统管理员') > -1){
									return 'cancel';
								} else {
									return 'x-hidden';
								}
							},
							handler: function(grid, rowIndex, colIndex, item) {
                           		var obj = grid.getStore().getAt(rowIndex);
                           		this.fireEvent('disagreeclick', {
                              		record: obj
                           		});
                       		}
						},{
							tooltip: '撤销',
							getClass:function(value, metaData, record, rowIndex, colIndex, store) {
								var user = Ext.decode(Ext.util.Cookies.get('user'));
								if(record.get('state') == '待审批' && record.get('userId') == user.userId){
									return 'undo';
								} else {
									return 'x-hidden';
								}
							},
							handler: function(grid, rowIndex, colIndex, item) {
                           		var obj = grid.getStore().getAt(rowIndex);
                           		this.fireEvent('undolclick', {
                              		record: obj
                           		});
                       		}
						}/*,{
							tooltip: '重新申请',
							handler: function(grid, rowIndex, colIndex, item) {
                           		var obj = grid.getStore().getAt(rowIndex);
                           		this.fireEvent('redolclick', {
                              		record: obj
                           		});
                       		}
						}*/,{
							tooltip: '入库',
							getClass:function(value, metaData, record, rowIndex, colIndex, store) {
								var user = Ext.decode(Ext.util.Cookies.get('user'));
								if(record.get('state') == '已通过' && (user.roleNames.indexOf('耗材管理员') > -1)){
									return 'doorIn';
								} else {
									return 'x-hidden';
								}
							},
							handler: function(grid, rowIndex, colIndex, item) {
                           		var obj = grid.getStore().getAt(rowIndex);
                           		this.fireEvent('doorinclick', {
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
					store:'PurchasingRecStore',
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