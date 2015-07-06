/**
 * 人员数据集
 */
Ext.define('CM.store.PurchasingRecStore', {
	extend:'Ext.data.Store',
	model:'CM.model.PurchasingRecModel',
	pageSize:22,
	proxy:{
		type:'ajax',
		url:'consumable_getPurchasingRecs.action',
		reader: {
			type:'json',
			root:'purchasingRecs',
			totalProperty: 'total'
		},
		writer: {
			type:'json'
		}
	},
	autoLoad:true
});