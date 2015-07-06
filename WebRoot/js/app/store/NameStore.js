/**
 * 人员数据集
 */
Ext.define('CM.store.NameStore', {
	extend:'Ext.data.Store',
	model:'CM.model.NameModel',
	pageSize:22,
	proxy:{
		type:'ajax',
		url:'user_getNames.action',
		extraParams:{	
  			type: 'user'
  		},
		reader: {
			type:'json',
			root:'names',
			totalProperty: 'total'
		},
		writer: {
			type:'json'
		}
	},
	autoLoad:true
});