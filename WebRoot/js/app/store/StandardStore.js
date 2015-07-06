/**
 * 人员数据集
 */
Ext.define('CM.store.StandardStore', {
	extend:'Ext.data.Store',
	model:'CM.model.StandardModel',
	pageSize:22,
	proxy:{
		type:'ajax',
		url:'standard_getStandards.action',
		reader: {
			type:'json',
			root:'standards',
			totalProperty: 'total'
		},
		writer: {
			type:'json'
		}
	},
	autoLoad:true
});