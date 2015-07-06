/**
 * 人员数据集
 */
Ext.define('CM.store.InstruStore', {
	extend:'Ext.data.Store',
	model:'CM.model.InstruModel',
	pageSize:22,
	proxy:{
		type:'ajax',
		url:'instru_getInstrus.action',
		reader: {
			type:'json',
			root:'instrus',
			totalProperty: 'total'
		},
		writer: {
			type:'json'
		}
	},
	autoLoad:true
});