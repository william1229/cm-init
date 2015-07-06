/**
 * ClassName：中心管理系统main布局
 */
Ext.define('CM.view.main.MainLayout', {
	extend:'Ext.panel.Panel',
	alias:'widget.mainlayout',
	id:'mainLayout',
	layout:'border',
	defaults: {
		collapsible:false,
		split:true,
		layout:'fit'
	},
	items:[
		{
			region:'north',
			xtype: 'toolbar',
		    ui: 'footer',
			cls:'logo',
			width:'100%',
			height:70,
			layout : {
				type : 'table',
	            columns : 5
	        }, 
		    items: [{
			    	xtype:'tbitem',
			    	rowspan:2,
			    	tdAttrs:{
			    		style: {
		                	width: '90%'
	            		}
			    	}
		    	},{
					xtype:'tbitem',
			    	rowspan:2,
			    	html:'<img src="images/iue.png" />'
			    },{
					xtype:'tbtext',
					colspan:2,
			    	text:'<font size="3px" color="white">中国科学院城市环境研究所</font>'
			    },{
			    	xtype:'button',
			    	itemId:'logout',
			    	tooltip:'安全退出',
			    	rowspan:2,
			    	cls:'main_logout'
			    },{
			    	xtype:'tbtext',
			    	text:'<font style="font-weight:bold;color:white;">欢迎您，</font>',
			    	tdAttrs:{
			    		style: {
		                	width: 5
	            		}
			    	}
			    },{
			    	xtype:'button',
			    	itemId:'personalInfo'
			    }
			]
		},
		{
			title:'导航栏',
			region:'west',
			flex:1.5,
			collapsible:true,
			xtype:'panel',
			layout:'accordion',
			items:[{
					xtype:'cmtree'
				}/*,{
					xtype:'pitree'
				}*/]
		},
		{
			region:'center',
			xtype:'panel',
			flex:10,
			items:[{
				xtype:'maintab'
			}]
		}
	]
});