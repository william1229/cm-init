/**
 *  人员数据列表
 */
Ext.define('CM.view.grid.SInstruList', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.sinstrulist',
	id:'sInstruList',
	layout:'fit',
	initComponent:function() {
		Ext.apply(this,{
				store: 'SInstruStore',
				enableKeyNav:true,
				columnLines:true,
				columns:[
					{header:'序号', xtype: 'rownumberer', width:50, align:'center',menuDisabled:true},
					{header:'仪器Id', dataIndex:'instruId',hidden:true, align:'center', menuDisabled:true},
					{header:'仪器名称', dataIndex:'instruName', flex:8, align:'center',menuDisabled:true},
					{header:'仪器型号', dataIndex:'instruModel', flex:6, align:'center',menuDisabled:true},
					{
						header:'警告', 
						width:60,
						dataIndex:'consumableIsLow',
						align:'center',
						menuDisabled:true,
						renderer:function(value, metaData, record ,rowIndex, colIndex, store, view) { 
							if(value) {
								//鼠标悬停，tip提示
								metaData.tdAttr = 'data-qtip="该仪器有耗材库存量过低，请及时采购！"';
								//单元格显示图片
								return "<img src='images/exclamation.png' />"
							}
						}
					}
				],
				tbar:[
					'->',
					{xtype:'button', itemId:'pConsumable',text:'查看公共耗材', iconCls:'public'}
				],
				dockedItems:[{
					xtype:'pagingtoolbar',
					store:'SInstruStore',
					dock:'bottom',
					displayInfo:true
				}],
				selModel: {
					//selType:'checkboxmodel'
				}
		}),
/*				this.editing = Ext.create("Ext.grid.plugin.CellEditing");
				this.plugins = [this.editing];*/
		this.callParent(arguments);
	}
});