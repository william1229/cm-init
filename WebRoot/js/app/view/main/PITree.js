Ext.define('CM.view.main.PITree', {
	extend: 'Ext.tree.Panel',
	alias: 'widget.pitree',
	title: '个人信息',
	rootVisible:false,
	store:'PINodeStore'
});