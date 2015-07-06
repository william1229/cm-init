/**
 * 仪器实体
 * name：用户姓名
 * sex：性别
 * age：年龄
 * degree：学历
 * trainingInfo：培训信息
 * nodeType：节点类型
 * isLeaf：是否叶子节点
 */
Ext.define('CM.model.InstruModel', {
	extend:'Ext.data.Model',
	fields:[
		{name:'instruId', type:'int'},
		{name:'instruName', type:'string'},
		{name:'instruModel', type:'string'},
		{name:'instruNum', type:'string'},
		{name:'manufacturer', type:'string'},
		{name:'country', type:'string'},
		{name:'distributor', type:'string'},
		{name:'date', type:'date'},
		{name:'money', type:'float'},
		{name:'location', type:'string'},
		{name:'name', type:'string'},
		{name:'userId', type:'int'},
		{name:'consumableIsLow', type:'boolean'}
	]
});