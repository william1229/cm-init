/**
 * 人员数据集
 */
Ext.define('CM.store.SSampleStore', {
	extend:'Ext.data.Store',
	model:'CM.model.SSampleModel',
	pageSize:22,
	proxy:{
		type:'ajax',
		url:'sSample_getSSamples.action',
		reader: {
			type:'json',
			root:'sSamples',
			totalProperty: 'total'
		},
		writer: {
			type:'json'
		}
	},
	autoLoad:true
});