/**
 * 培训记录实体
 * name：用户姓名
 * date：时间
 * content：内容概要
 * path：总结保存地址
 */
Ext.define('CM.model.OutInRecModel', {
	extend:'Ext.data.Model',
	fields:[
		{name:'userName', type:'string'},
		{name:'name', type:'string'},
		{name:'date', type:'date'},
		{name:'consumableName', type:'string'},
		{name:'consumableType', type:'string'},
		{name:'instruName', type:'string'},
		{name:'instruModel', type:'string'},
		{name:'quantity', type:'string'},
		{name:'action', type:'string'}
	]
});