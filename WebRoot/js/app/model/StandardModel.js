/**
 * 导航树实体
 */
Ext.define('CM.model.StandardModel', {
	extend:'Ext.data.Model',
	fields:[
		{name:'standardId', type:'int'},
		{name:'serialNumber', type:'string'},
		{name:'name', type:'string'},
		{name:'date', type:'date'},
		{name:'effectiveDate', type:'date'},
		{name:'state', type:'string'},
		{name:'path', type:'string'}
	]
});