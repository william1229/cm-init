/**
 * 人员数据集
 */
Ext.define('CM.store.ProjectStore', {
	extend:'Ext.data.Store',
	model:'CM.model.ProjectModel',
	pageSize:22,
	proxy:{
		type:'ajax',
		url:'project_getProjects.action',
		reader: {
			type:'json',
			root:'projects',
			totalProperty: 'total'
		},
		writer: {
			type:'json'
		}
	},
	autoLoad:true
});