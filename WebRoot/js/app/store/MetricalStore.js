/**
 * 人员数据集
 */
Ext.define('CM.store.MetricalStore', {
	extend:'Ext.data.Store',
	model:'CM.model.MetricalModel',
	pageSize:22,
	proxy:{
		type:'ajax',
		url:'instru_getMetricals.action',
		reader: {
			type:'json',
			root:'metricals',
			totalProperty: 'total'
		},
		writer: {
			type:'json'
		}
	},
	autoLoad:true
});