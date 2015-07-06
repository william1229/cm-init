/**
 * 人员数据集
 */
Ext.define('CM.store.SSOutInRecStore', {
	extend:'Ext.data.Store',
	model:'CM.model.SSOutInRecModel',
	pageSize:22,
	proxy:{
		type:'ajax',
		url:'sSample_getOutInRecs.action',
		reader: {
			type:'json',
			root:'sSOutInRecs',
			totalProperty: 'total'
		},
		writer: {
			type:'json'
		}
	},
	autoLoad:true
});