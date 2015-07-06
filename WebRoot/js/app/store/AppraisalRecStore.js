/**
 * 人员数据集
 */
Ext.define('CM.store.AppraisalRecStore', {
	extend:'Ext.data.Store',
	model:'CM.model.AppraisalRecModel',
	pageSize:22,
	proxy:{
		type:'ajax',
		url:'appraisalRec_getAppraisalRecs.action',
		reader: {
			type:'json',
			root:'appraisalRecs',
			totalProperty: 'total'
		},
		writer: {
			type:'json'
		}
	},
	autoLoad:true
});