/**
 * 人员数据集
 */
Ext.define('CM.store.IAdminNameStore', {
	extend:'Ext.data.Store',
	model:'CM.model.NameModel',
	pageSize:22,
	proxy:{
		type:'ajax',
		url:'user_getNames.action',
		extraParams:{	
  			type: 'admin'
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