/**
 * 人员数据集
 */
Ext.define('CM.store.SampleStore', {
	extend:'Ext.data.Store',
	model:'CM.model.SampleModel',
	pageSize:22,
	proxy:{
		type:'ajax',
		url:'project_getSamples.action',
		reader: {
			type:'json',
			root:'samples',
			totalProperty: 'total'
		},
		writer: {
			type:'json'
		}
	},
	autoLoad:true
});