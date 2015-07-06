/**
 * 人员数据集
 */
Ext.define('CM.store.GasCsumStore', {
	extend:'Ext.data.Store',
	model:'CM.model.GasCsumModel',
	pageSize:22,
	proxy:{
		type:'ajax',
		url:'consumable_getGasCsums.action',
		reader: {
			type:'json',
			root:'gasCsums',
			totalProperty: 'total'
		},
		writer: {
			type:'json'
		}
	},
	autoLoad:true
});