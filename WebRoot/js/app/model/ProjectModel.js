/**
 * 导航树实体
 */
Ext.define('CM.model.ProjectModel', {
	extend:'Ext.data.Model',
	fields:[
		{name:'projectId', type:'int'},
		{name:'reportId', type:'int'},
		{name:'projectSN', type:'string'},
		{name:'projectName', type:'string'},
		{name:'date', type:'date'},
		{name:'unit', type:'string'},
		{name:'telephone', type:'string'},
		{name:'money', type:'float'},
		{name:'reportDate', type:'date'},
		{name:'contract', type:'string'},
		{name:'report', type:'string'}
	]
});