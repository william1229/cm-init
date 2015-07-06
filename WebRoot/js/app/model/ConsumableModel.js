/**
 * 仪器耗材实体
 * name：仪器名称
 * isEmpty：该仪器是否有需要购买的耗材
 * nodeType：节点类型
 * isLeaf：是否叶子节点
 */
Ext.define('CM.model.ConsumableModel', {
	extend:'Ext.data.Model',
	fields:[
		{name:'consumableId', type:'int'},
		{name:'consumableName', type:'string'},
		{name:'consumableType', type:'string'},
		{name:'quantity', type:'int'}
	]
});