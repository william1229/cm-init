/**
 * 人员数据集
 */
Ext.define('CM.store.ExReviewStore', {
	extend:'Ext.data.Store',
	model:'CM.model.ExReviewModel',
	pageSize:22,
	proxy:{
		type:'ajax',
		url:'review_getReviews.action',
		extraParams:{	
  			type: 'ex'
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