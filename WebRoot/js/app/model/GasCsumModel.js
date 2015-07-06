/**
 * 仪器耗材实体
 * name：仪器名称
 * isEmpty：该仪器是否有需要购买的耗材
 * nodeType：节点类型
 * isLeaf：是否叶子节点
 */
Ext.define('CM.model.GasCsumModel', {
	extend:'Ext.data.Model',
	fields:[
		{name:'filename', type:'string'},
		{name:'date', type:'date'},
		{name:'name', type:'string'},
		{name:'path', type:'string'}
	]
});