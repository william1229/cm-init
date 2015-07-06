/**
 * 导航树实体
 */
Ext.define('CM.model.SampleModel', {
	extend:'Ext.data.Model',
	fields:[
		{name:'projectSN', type:'string'},
		{name:'sampleSN', type:'string'},
		{name:'state', type:'string'},
		{name:'quantity', type:'int'},
		{name:'unit', type:'string'},
		{name:'date', type:'date'},
		{name:'name', type:'string'},
		{name:'telephone', type:'string'}
	]
});