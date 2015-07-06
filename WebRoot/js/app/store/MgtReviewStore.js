/**
 * 人员数据集
 */
Ext.define('CM.store.MgtReviewStore', {
	extend:'Ext.data.Store',
	model:'CM.model.MgtReviewModel',
	pageSize:22,
	proxy:{
		type:'ajax',
		url:'review_getReviews.action',
		extraParams:{	
  			type: 'mgt'
  		},
		reader: {
			type:'json',
			root:'reviews',
			totalProperty: 'total'
		},
		writer: {
			type:'json'
		}
	},
	autoLoad:true
});