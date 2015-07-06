/**
 * ClassName：用户信息控制器
 */
Ext.define("CM.controller.InReviewCtrl", {
	extend:'Ext.app.Controller',
	init:function(app) {
		var addWin;
		Ext.QuickTips.init();
		this.control({
			'inreviewlist actioncolumn[itemId=file]': {
		     	click:this.download
		    },
			'inreviewlist button[itemId=add]': {
				click:function() {
					if(!addWin) {
						addWin = Ext.create("CM.view.form.InReviewAddWin");
					}
					addWin.show();	
				}
			},
			'inreviewaddwin button[itemId=submit]':{
				click:this.submitForm
			},
			'inreviewaddwin button[itemId=cancel]':{
				click:this.closeWin
			}
     	});
     	this.addTab();
	},
	//提交表单
	submitForm: function(btn, e, eOpts) {
		var sForm = btn.up('form');
		if(sForm.getForm().isValid()){
		if(sForm.itemId.toLowerCase().indexOf('add') > -1) {
				var urlAction = 'review_addReview.action';
			} else if(sForm.itemId.toLowerCase().indexOf('modify') > -1) {
				var urlAction = 'review_updateInfo.action';
			}
			sForm.submit({
				url:urlAction,
				method:'POST',
				params:{
					type:'in'
				},
				success:function(form, action){
					var recGrid = Ext.getCmp('inReviewList');
					Ext.Msg.alert("提示", action.result.msg, function(){
						form.owner.up('window').hide();
						recGrid.getStore().load();
					});
				},
				failure:function(form, action){
					Ext.Msg.alert("提示", action.result.errorMessage);
				}
			})
		}
	},
	download: function(grid, rec, index, column, e, eOpts) {
		var recs = grid.getStore().getRange();
		var path = recs[index].get('path');
		window.open('review_download.action?path=' + path);
	},
	//主选项卡添加tab
	addTab:function() {
		var mainTab = Ext.getCmp('mainTab');
		mainTab.add({ 
			itemId: 'inReviewCtrl', //向mainTab的items项中,添加的itemId
    		title: '内部评审',
    		layout:'border',
    		items:[{
    			region:'center',
    			width:'100%',
    			height:'100%',
    			xtype: 'inreviewlist'
    		}],
    		closable : true //允许关闭
		}).show();
	},
	//关闭窗口
	closeWin: function(btn, e, eOpts) {
		var win = btn.up('window');
		win.hide();
	},
	views:[
		'grid.InReviewList'
	],
	stores:[
		'InReviewStore'
	],
	models:[
		'InReviewModel'
	]
});
