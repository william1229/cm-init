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
Ext.define('CM.model.SInstruModel', {
	extend:'Ext.data.Model',
	fields:[
		{name:'instruId', type:'int'},
		{name:'instruName', type:'string'},
		{name:'instruModel', type:'string'},
		{name:'consumableIsLow', type:'boolean'}
	]
});