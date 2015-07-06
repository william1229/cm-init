/**
 * 导航树实体
 */
Ext.define('CM.model.NodeModel', {
	extend:'Ext.data.Model',
	fields:[
		{name:'id', type:'string'},
		{name:'text', type:'string'},
		{name:'leaf', type:'boolean'}
	]
});