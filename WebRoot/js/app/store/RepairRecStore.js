/**
 * 人员数据集
 */
Ext.define('CM.store.RepairRecStore', {
	extend:'Ext.data.Store',
	model:'CM.model.RepairRecModel',
	pageSize:22,
	proxy:{
		type:'ajax',
		url:'instru_getRepairRecs.action',
		reader: {
			type:'json',
			root:'repairRecs',
			totalProperty: 'total'
		},
		writer: {
			type:'json'
		}
	},
	autoLoad:true
});