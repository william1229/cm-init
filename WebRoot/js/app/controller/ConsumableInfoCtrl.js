/**
 * ClassName：用户信息控制器
 */
Ext.define("CM.controller.ConsumableInfoCtrl", {
	extend:'Ext.app.Controller',
	init:function(app) {
		var addWin;
		var editWin;
		var inWin;
		var outWin;
		var pWin;
		var instruId;
		var instruName;
		var instruModel;
		var consumableId;
		var type;
		var currentQuantity;
		var flag;     //true为入库，false为出库
		this.control({
			'consumablelist button[itemId=add]':{
				click:function() {
					
					if(!addWin) {
							addWin = Ext.create("CM.view.form.ConsumableAddWin");
						}
					this.openWin(type, addWin, instruName, instruModel);
				}
			},
			'consumablelist actioncolumn[itemId=operation]':{
				deleteclick:function(obj, eOpts) {
					deleteConsumable(obj.record, instruId);
				},
				doorinclick:function(obj, eOpts) {
					if(!inWin) {
						inWin = Ext.create("CM.view.form.DoorInWin");
					}
					flag = true;
					consumableId = obj.record.get('consumableId');
					currentQuantity = obj.record.get('quantity');
					this.openWin(type, inWin, instruName, instruModel, obj.record);
				},
				dooroutclick:function(obj, eOpts) {
					if(!outWin) {
						outWin = Ext.create("CM.view.form.DoorOutWin");
					}
					flag = false;
					consumableId = obj.record.get('consumableId');
					currentQuantity = obj.record.get('quantity');
					this.openWin(type, outWin, instruName, instruModel, obj.record);
				},
				applyclick:function(obj, eOpts) {
					if(!pWin) {
						pWin = Ext.create("CM.view.form.PurchasingWin");
					}
					consumableId = obj.record.get('consumableId');
					this.openWin(type, pWin, instruName, instruModel, obj.record);
				}
			},		
			'consumablelist button[itemId=iConsumable]':{//查看仪器耗材按钮
				click: function(btn, e, eOpts) {
					var sInstruList = Ext.getCmp('sInstruList');
					sInstruList.enable();
					sInstruList.getSelectionModel().select(0);
					btn.up('grid').down('#iConsumable').hide();
				}
			},
		    'consumableaddwin button[itemId=submit]':{
		    	click:function(btn, e, eOpts) {
			       	this.submitForm(btn, flag, instruId);
		    	}
		    },
		    'consumableaddwin button[itemId=cancel]':{
		    	click:this.closeWin
		    },
/*			'consumableeditwin':{
				render: function(win){
					//提交表单
			       	win.down('#submit').on('click',function(btn, e, eOpts){
			       		this.submitForm(btn);
			       	});
			       	//关闭窗口
			       	win.down('#cancel').on('click',function(btn, e, eOpts){
			       		this.closeWin(btn);
			       	});
				}
		     },*/
			'sinstrulist':{
				select:function(grid, rec, item, index, e, eOpts){
					instruId = rec.get('instruId');
					instruName = rec.get('instruName');
					instruModel = rec.get('instruModel');
					type = 'instru';
				   	this.getConsumable(type, grid, instruId, instruName);
				}
			},
			'sinstrulist button[itemId=pConsumable]':{//查看公共耗材按钮
				click:function(btn, e, eOpts) {
					instruId = 0;
					type = 'public';
					var grid = btn.up('grid');
					grid.getSelectionModel().deselect(0);
					grid.disable();
					this.getConsumable(type);
				}
			},
		    'doorinwin button[itemId=submit]':{
		    	click:function(btn, e, eOpts) {
			       	this.submitForm(btn, flag, instruId, consumableId, currentQuantity);
		    	}
		    },
		    'doorinwin button[itemId=cancel]':{
		    	click:this.closeWin
		    },
			'doorinwin textfield[itemId=name]':{
				focus:function() {
					var userNameWin = Ext.getCmp('userNameWin');
					if(!userNameWin) {
						userNameWin = Ext.create("CM.view.grid.UserNameWin");
					}
					userNameWin.show();
				}
			},
			'dooroutwin button[itemId=submit]':{
		    	click:function(btn, e, eOpts) {
			       	this.submitForm(btn, flag, instruId, consumableId, currentQuantity);
		    	}
		    },
		    'dooroutwin button[itemId=cancel]':{
		    	click:this.closeWin
		    },
			'dooroutwin textfield[itemId=name]':{
				focus:function() {
					var userNameWin = Ext.getCmp('userNameWin');
					if(!userNameWin) {
						userNameWin = Ext.create("CM.view.grid.UserNameWin");
					}
					userNameWin.show();
				}
			},
			'purchasingwin button[itemId=submit]': {
				click:function(btn, e, eOpts) {
					var user = Ext.decode(Ext.util.Cookies.get('user'));
			       	this.submitForm(btn, flag, instruId, consumableId, currentQuantity, user.userId);
		    	}
			},
			'purchasingwin button[itemId=cancel]': {
				click:this.closeWin
			},
			'usernamewin actioncolumn[itemId=operation]':{
				click: function(grid, rec, index, column, e, eOpts) {
					this.selectName(grid, index, flag);
				}
		     }
     	});
     	this.addTab();
     	var searchField = Ext.getCmp('consumableList').getView().getFeature('searching').field;
		searchField.disable();
	},
	//提交表单
	submitForm: function(btn, flag, instruId, consumableId, currentQuantity, loginUserId, applyQquantity) {
		var sForm = btn.up('form');
		if(sForm.getForm().isValid()){
			var user = Ext.decode(Ext.util.Cookies.get('user'));
			
			if(sForm.itemId.toLowerCase().indexOf('add') > -1) {
				var urlAction = 'consumable_addConsumable.action';
			} else if(sForm.itemId.toLowerCase().indexOf('edit') > -1 || sForm.itemId.toLowerCase().indexOf('doorin') > -1 || sForm.itemId.toLowerCase().indexOf('doorout') > -1) {
				var urlAction = 'consumable_updateInfo.action';
			} else if(sForm.itemId.toLowerCase().indexOf('purchasing') > -1) {
				var urlAction = 'consumable_applyPurchasing.action';
			}
			sForm.submit({
				url:urlAction,
				method:'POST',
				params:{
	  				instruId: instruId,
	  				consumableId:consumableId,
	  				currentQuantity:currentQuantity,
	  				flag: flag,
	  				userId: user.userId
	  			},
				success:function(form, action){
					var consumableGrid = Ext.getCmp('consumableList');
					sInstruGrid = Ext.getCmp('sInstruList');
					Ext.Msg.alert("提示", action.result.msg, function(){
						form.owner.up('window').hide();
						consumableGrid.getStore().load();
						sInstruGrid.getStore().load();
					});
				},
				failure:function(form, action){
					Ext.Msg.alert("提示", action.result.errorMessage);
				}
			})
		}
	},
/*	//展现修改表单
	consumableEdit: function(grid, rec, editWin) {
		editWin.show(null,function() {
			var editForm = editWin.down('form');
			editForm.down('#consumableId').setValue(rec.data.consumableId);
			editForm.down('#consumableName').setValue(rec.data.consumableName);
			editForm.down('#consumableType').setValue(rec.data.consumableType);
			editForm.down('#consumableModel').setValue(rec.data.consumableModel);
			editForm.down('#consumableNum').setValue(rec.data.consumableNum);
			editForm.down('#location').setValue(rec.data.location);
			editForm.down('#date').setValue(rec.data.date);
			editForm.down('#money').setValue(rec.data.money);
			editForm.down('#manufacturer').setValue(rec.data.manufacturer);
			editForm.down('#country').setValue(rec.data.country);
			editForm.down('#distributor').setValue(rec.data.distributor);
		});
	},*/
	//选取姓名
	selectName: function(grid, index, flag) {
		var recs = grid.getStore().getRange();
		var name = recs[index].get('name');
		var userId = recs[index].get('userId');
		if(flag) {
			var win = Ext.getCmp('doorInWin');
		} else {
			var win = Ext.getCmp('doorOutWin');
		}
		grid.up('window').hide();
		win.down('#name').setValue(name);
		win.down('#userId').setValue(userId);
	},
	//删除耗材
	deleteConsumable: function(record, instruId){
		Ext.MessageBox.msgButtons[1].setText("是");
		Ext.MessageBox.msgButtons[2].setText("否");
		Ext.MessageBox.show({
			title:'提示',
			msg:'是否删除此耗材？',
			buttons: Ext.MessageBox.YESNO,
			icon:Ext.MessageBox.QUESTION,
			fn:function(e){
				if(e == "yes"){
			   		Ext.Ajax.request({
//			      		url: 'consumable_resetPwd.action?consumableId=' + btn.up('form').down('#consumableId').value +'&password=123456',
			      		url: 'consumable_delete.action',
			      		params:{	
			  				consumableId: record.get('consumableId'),
			  				instruId:instruId
			  			},
			  			success: function(respone){
			  				var consumableGrid = Ext.getCmp('consumableList');
			  				sInstruGrid = Ext.getCmp('sInstruList');
			  				Ext.Msg.alert("提示", Ext.JSON.decode(respone.responseText).msg);
			      			consumableGrid.getStore().load();
			      			sInstruGrid.getStore().load();
			      		},
			      		failure: function(respone){
			      			Ext.Msg.alert("提示", Ext.JSON.decode(respone.responseText).errorMessage);
			      		}
			   		});
			  	}
			 }
		});
	},
	getConsumable: function(type, grid, instruId, instruName) {
		var consumableList = Ext.getCmp('consumableList');
		var tbtext = consumableList.down('#iConsumableName');
		var addButton = consumableList.down('#add');
		var operation = consumableList.down('#operation');
		var searchField = consumableList.getView().getFeature('searching').field;
		searchField.enable();
		var user = Ext.decode(Ext.util.Cookies.get('user'));
		if(user.roleNames.indexOf('耗材管理员')) {
			addButton.show();
		}
		if(type == 'instru') {
			tbtext.setText(instruName);
			var proxy = new Ext.data.proxy.Ajax({
	      		url: 'consumable_getConsumables.action',
	      		extraParams:{	
	  				instruId: instruId
	  			},
		  		reader: {
					type:'json',
					root:'consumables',
					totalProperty: 'total'
				},
				writer: {
					type:'json'
				}
	   		});
		} else if(type == 'public') {
			tbtext.setText('公共耗材');
			consumableList.down('#iConsumable').show();
			var proxy = new Ext.data.proxy.Ajax({
	      		url: 'consumable_getConsumables.action',
		  		reader: {
					type:'json',
					root:'consumables',
					totalProperty: 'total'
				},
				writer: {
					type:'json'
				}
	   		});
		}
   		var consumableStore = Ext.getCmp('consumableList').getStore();
   		consumableStore.setProxy(proxy);
   		consumableStore.load();
	},
	//打开耗材操作窗口
	openWin: function(type, win, instruName, instruModel, record) {
		var form = win.down('form');
		form.getForm().reset();
		if(type == 'instru') { 
			form.down('#instruInfo').show();
			form.down('#instruName').setValue(instruName);
			form.down('#instruModel').setValue(instruModel);
			form.down('#consumableInfo').setPosition(10,80);
		} else {
			form.down('#instruInfo').hide();
			form.down('#consumableInfo').setPosition(10,10);
		}
		if(record) {
			var consumableName = record.get('consumableName');
			var consumableType = record.get('consumableType');
			form.down('#consumableName').setValue(consumableName);
			form.down('#consumableType').setValue(consumableType);
		}
		win.show();
	},
	//主选项卡添加tab
	addTab:function() {
		var mainTab = Ext.getCmp('mainTab');
		var tab = mainTab.down('#consumableList'); //查询mainTab中的itemId
		mainTab.add({ 
			itemId: 'consumableInfoCtrl', //向mainTab的items项中,添加的itemId
    		title: '耗材信息',
    		layout:'border',
    		items:[{
    			region:'west',
    			width:'45%',
    			height:'100%',
    			xtype: 'sinstrulist'
    			
    		},{
    			region:'center',
    			width:'55%',
    			height:'100%',
    			xtype: 'consumablelist'
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
		'grid.ConsumableList',
		'grid.SInstruList',
		'grid.PurchasingRec'
	],
	stores:[
		'ConsumableStore',
		'SInstruStore',
		'NameStore'
	],
	models:[
		'ConsumableModel',
		'SInstruModel',
		'NameModel'
	]
});