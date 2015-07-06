Ext.define('CM.view.main.MainTab', {
	extend: 'Ext.tab.Panel',
	alias: 'widget.maintab',
	id:'mainTab',
	activeTab : 0,//默认激活第一个tab页
	animScroll : true,//使用动画滚动效果
	enableTabScroll : true,//tab标签超宽时自动出现滚动按钮
	autoDistory:false,
	layout:'fit',	
	plugins: Ext.create('Ext.ux.TabCloseMenu', {
	    closeTabText: '关闭当前',
        closeOthersTabsText: '关闭其他',
        closeAllTabsText: '关闭所有',
        extraItemsTail: [
            '-',
            {
                text: '可关闭',
                checked: true,
                hideOnClick: true,
                handler: function (item) {
                    currentItem.tab.setClosable(item.checked);
                }
            }
        ],
        listeners: {
            beforemenu: function (menu, item) {
                menu.child('[text="可关闭"]').setChecked(item.closable);
                currentItem = item;
            }
        }
    }),
	items: [
		{
			title: '欢迎页面',
			closable : false, //不允许关闭
			html:'<img src="images/login_bg.png" height="100%" width="100%"/>'
		}
	]
});