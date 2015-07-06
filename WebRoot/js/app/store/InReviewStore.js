/**
 * 人员数据集
 */
Ext.define('CM.store.InReviewStore', {
	extend:'Ext.data.Store',
	model:'CM.model.InReviewModel',
	pageSize:22,
	proxy:{
		type:'ajax',
		url:'review_getReviews.action',
		extraParams:{	
  			type: 'in'
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