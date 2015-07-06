/**
 * 导航树实体
 */
Ext.define('CM.model.SSampleModel', {
	extend:'Ext.data.Model',
	fields:[
		{name:'sSampleId', type:'int'},
		{name:'standardSN', type:'string'},
		{name:'sSampleName', type:'string'},
		{name:'sampleSN', type:'string'},
		{name:'standardValue', type:'string'},
		{name:'relativeUncertainty', type:'string'},
		{name:'specification', type:'string'},
		{name:'effectiveDate', type:'date'},
		{name:'date', type:'date'},
		{name:'source', type:'string'},
		{name:'storageRequirements', type:'string'},
		{name:'quantity', type:'float'}
	]
});