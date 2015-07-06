/**
 * 人员数据集
 */
Ext.define('CM.store.TrainingRecStore', {
	extend:'Ext.data.Store',
	model:'CM.model.TrainingRecModel',
	pageSize:22,
	proxy:{
		type:'ajax',
		url:'trainingRec_getTrainingRecs.action',
		reader: {
			type:'json',
			root:'trainingRecs',
			totalProperty: 'total'
		},
		writer: {
			type:'json'
		}
	},
	autoLoad:true
});