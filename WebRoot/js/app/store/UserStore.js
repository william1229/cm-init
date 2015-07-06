/**
 * 人员数据集
 */
Ext.define('CM.store.UserStore', {
	extend:'Ext.data.Store',
	model:'CM.model.UserModel',
	pageSize:22,
	proxy:{
		type:'ajax',
		url:'user_getUsers.action',
		reader: {
			type:'json',
			root:'users',
			totalProperty: 'total'
		},
		writer: {
			type:'json'
		}
	},
	autoLoad:true
});