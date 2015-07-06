/**
 * 人员数据集
 */
Ext.define('CM.store.SInstruStore', {
	extend:'Ext.data.Store',
	model:'CM.model.SInstruModel',
	pageSize:22,
	proxy:{
		type:'ajax',
		url:'instru_getInstrus.action',
		extraParams:{	
			clickNode: 'consumableManagement'
		},
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