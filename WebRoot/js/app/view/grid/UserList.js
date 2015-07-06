/**
 *  人员数据列表
 */
Ext.define('CM.view.grid.UserList', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.userlist',
	id:'userList',
	layout:'fit',
	initComponent:function() {
		Ext.apply(this,{
				store: 'UserStore',
				enableKeyNav:true,
				columnLines:true,
				columns:[
					{header:'序号', xtype: 'rownumberer', width:50, align:'center',menuDisabled:true},
					{header:'用户ID', dataIndex:'userId',hidden:true, align:'center',menuDisabled:true},
					{header:'用户名', dataIndex:'userName', flex:3, align:'center',menuDisabled:true},
					{header:'姓名', dataIndex:'name', flex:2, align:'center',menuDisabled:true},
					{header:'性别', dataIndex:'sex', flex:1.5, align:'center',menuDisabled:true},
					{header:'电话', dataIndex:'telephone',  align:'center',flex:4,menuDisabled:true},
					{header:'E-mail', dataIndex:'email', flex:5, align:'center',menuDisabled:true},
					{header:'用户类型', dataIndex:'roleNames', flex:15, align:'center',menuDisabled:true,
						renderer: function (value, meta, record) {
		                    var max = 45;
		                    //鼠标悬停，tip提示
		                    meta.tdAttr = 'data-qtip="' + value + '"';
		                    return value.length < max ? value : value.substring(0, max - 3) + '...';
	                	}
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
						    itemId:'delete',
							tooltip: '删除',
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
					store:'UserStore',
					dock:'bottom',
					displayInfo:true
				}],
/*				features: [{  
			        ftype: 'searching',
			        minChars: 2,  
			        width: 150,
			        mode: 'remote',//远程还是本地store
			        position: 'top',//状态栏还是工具栏  
			        iconCls: 'search',//图标  
			        menuStyle: 'radio',//单选还是多选  
			        showSelectAll: false,   //是否显示全选按钮  
			        checkIndexes: ['name'], //默认选择列  
			        disableIndexes: ['userId','sex','telephone','email','roleNames','userName']  //禁止那些列参与查询  
				}], */
				selModel: {
					//selType:'checkboxmodel'
				}}),
/*				this.editing = Ext.create("Ext.grid.plugin.CellEditing");
				this.plugins = [this.editing];*/
				this.callParent(arguments);
		}
});