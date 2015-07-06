/**
 * 人员数据集
 */
Ext.define('CM.store.OutInRecStore', {
	extend:'Ext.data.Store',
	model:'CM.model.OutInRecModel',
	pageSize:22,
	proxy:{
		type:'ajax',
		url:'consumable_getOutInRecs.action',
		reader: {
			type:'json',
			root:'outInRecs',
			totalProperty: 'total'
		},
		writer: {
			type:'json'
		}
	},
	autoLoad:true
});