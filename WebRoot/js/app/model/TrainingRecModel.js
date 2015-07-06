/**
 * 培训记录实体
 * name：用户姓名
 * date：时间
 * content：内容概要
 * path：总结保存地址
 */
Ext.define('CM.model.TrainingRecModel', {
	extend:'Ext.data.Model',
	fields:[
		{name:'userName', type:'string'},
		{name:'name', type:'string'},
		{name:'startDate', type:'date'},
		{name:'endDate', type:'date'},
		{name:'unit', type:'string'},
		{name:'location', type:'string'},
		{name:'content', type:'string'},
		{name:'path', type:'string'}
	]
});