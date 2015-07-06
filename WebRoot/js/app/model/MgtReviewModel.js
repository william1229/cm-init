/**
 * 培训记录实体
 * name：用户姓名
 * date：时间
 * content：内容概要
 * path：总结保存地址
 */
Ext.define('CM.model.MgtReviewModel', {
	extend:'Ext.data.Model',
	fields:[
		{name:'date', type:'date'},
		{name:'path', type:'string'}
	]
});