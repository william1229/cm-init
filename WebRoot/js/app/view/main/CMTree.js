Ext.define('CM.view.main.CMTree', {
	extend: 'Ext.tree.Panel',
	alias: 'widget.cmtree',
	title: '中心管理',
	rootVisible:false,
	store: 'CMNodeStore'
});