/**
 * ClassName：用户信息控制器
 */
Ext.define("CM.controller.SSampleInfoCtrl", {
	extend:'Ext.app.Controller',
	init:function(app) {
		var addWin;
		var inWin;
		var outWin;
		var sSampleId;
		var currentQuantity;
		var userNameWin;
		var flag;
		Ext.QuickTips.init();
		this.control({
			'ssamplelist button[itemId=add]':{
				click:function() {
					if(!addWin) {
						addWin = Ext.create("CM.view.form.SSampleAddWin");
					}
					addWin.down('form').getForm().reset();
					addWin.show();
				}
		     },
		    'ssamplelist actioncolumn[itemId=operation]':{
				doorinclick:function(obj, eOpts) {
					if(!inWin) {
						inWin = Ext.create("CM.view.form.SSampleInWin");
					}
					flag = true;
					sSampleId = obj.record.get('sSampleId');
					currentQuantity = obj.record.get('quantity');
					this.openWin(inWin, obj.record, flag);
				},
				dooroutclick:function(obj, eOpts) {
					if(!outWin) {
						outWin = Ext.create("CM.view.form.SSampleOutWin");
					}
					flag = false;
					sSampleId = obj.record.get('sSampleId');
					currentQuantity = obj.record.get('quantity');
					this.openWin(outWin, obj.record, flag);
				}
		    },
			'ssampleaddwin button[itemId=submit]':{
				click:this.submitForm
			},
			'ssampleaddwin button[itemId=cancel]':{
				click:this.closeWin
			},
			'ssampleinwin button[itemId=submit]':{
				click:function(btn, e, eOpts) {
			       	this.submitForm(btn, flag, currentQuantity);
		    	}
			},
			'ssampleinwin button[itemId=cancel]':{
				click:this.closeWin
			},
			'ssampleoutwin button[itemId=submit]':{
				click:function(btn, e, eOpts) {
			       	this.submitForm(btn, flag, currentQuantity);
		    	}
			},
			'ssampleoutwin button[itemId=cancel]':{
				click:this.closeWin
			},
			'ssampleoutwin textfield[itemId=name]':{
				focus:function() {
					var userNameWin = Ext.getCmp('userNameWin');
					if(!userNameWin) {
						userNameWin = Ext.create("CM.view.grid.UserNameWin");
					}
					userNameWin.show();
				}
			},
			'usernamewin actioncolumn[itemId=operation]':{
				click: function(grid, rec, index, column, e, eOpts) {
					this.selectName(grid, index, flag);
				}
		    }
     	});
     	this.addTab();
     	var user = Ext.decode(Ext.util.Cookies.get('user'));
     	if(user.roleNames.indexOf('系统管理员') || user.roleNames.indexOf('标样管理员')) {
			var grid = Ext.getCmp('sSampleList');
			grid.down('#operation').show();
		}
	},
	//提交表单
	submitForm: function(btn, flag, currentQuantity) {
		var sForm = btn.up('form');
		if(sForm.getForm().isValid()){
			if(sForm.itemId.toLowerCase().indexOf('add') > -1) {
				var urlAction = 'sSample_addSSample.action';
			} else if(sForm.itemId.toLowerCase().indexOf('out') > -1 || sForm.itemId.toLowerCase().indexOf('in') > -1) {
				var urlAction = 'sSample_updateQuantity.action';
			}
			sForm.submit({
				url:urlAction,
				method:'POST',				
				params:{
	  				flag: flag,
					currentQuantity:currentQuantity
	  			},
				success:function(form, action){
					var recGrid = Ext.getCmp('sSampleList');
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
	//打开出入库操作窗口
	openWin: function(win, record, flag) {
		var form = win.down('form');
		form.getForm().reset();
		form.down('#standardSN').setValue(record.get('standardSN'));
		form.down('#sSampleName').setValue(record.get('sSampleName'));
		form.down('#sampleSN').setValue(record.get('sampleSN'));
		form.down('#specification').setValue(record.get('specification'));
		form.down('#sSampleId').setValue(record.get('sSampleId'));
		if(flag == true) {
			form.down('#userId').setValue(userId);
		}
		win.show();
	},
	//选取姓名
	selectName: function(grid, index, flag) {
		var recs = grid.getStore().getRange();
		var name = recs[index].get('name');
		var userId = recs[index].get('userId');
		if(flag) {
			var win = Ext.getCmp('sSampleInWin');
		} else {
			var win = Ext.getCmp('sSampleOutWin');
		}
		grid.up('window').hide();
		win.down('#name').setValue(name);
		win.down('#userId').setValue(userId);
	},
	//主选项卡添加tab
	addTab:function() {
		var mainTab = Ext.getCmp('mainTab');
		mainTab.add({
			itemId: 'sSampleInfoCtrl', //向mainTab的items项中,添加的itemId
    		title: '标样信息',
    		layout:'border',
    		items:[{
    			region:'center',
    			width:'100%',
    			height:'100%',
    			xtype: 'ssamplelist'
    		}],
    		closable : true //允许关闭
		}).show();
	},
	//关闭窗口
	closeWin: function(btn) {
		var win = btn.up('window');
		win.hide();
	},
	views:[
		'grid.SSampleList'
	],
	stores:[
		'SSampleStore',
		'NameStore'
	],
	models:[
		'SSampleModel',
		'NameModel'
	]
});
