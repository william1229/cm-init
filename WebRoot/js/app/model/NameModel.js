/**
 * 人员实体
 * name：用户姓名
 * sex：性别
 * age：年龄
 * degree：学历
 * trainingInfo：培训信息
 * nodeType：节点类型
 * isLeaf：是否叶子节点
 */
Ext.define('CM.model.NameModel', {
	extend:'Ext.data.Model',
	fields:[
		{name:'userId', type:'int'},
		{name:'userName', type:'string'},
		{name:'name', type:'string'}
	]
});