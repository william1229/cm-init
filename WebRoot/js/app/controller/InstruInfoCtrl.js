/**
 * ClassName：用户信息控制器
 */
Ext.define("CM.controller.InstruInfoCtrl", {
	extend:'Ext.app.Controller',
	init:function(app) {
		var instruAddWin;
		var accreditAddWin;
		var repairAddWin;
		var metricalAddWin;
		var editWin;
		this.control({
			'instrulist button[itemId=add]':{
				click:function() {
					if(!instruAddWin) {
						instruAddWin = Ext.create("CM.view.form.InstruAddWin");
					} 
					instruAddWin.down('form').getForm().reset();
					instruAddWin.show();
				}
			},
			'instrulist':{
				itemdblclick:function(grid, rec, item, index, e, eOpts) {
			       	if(!editWin) {
						editWin = Ext.create("CM.view.form.InstruEditWin");
					} 
					this.instruEdit(grid, rec, editWin);
				}
			},
			'instrulist actioncolumn[itemId=operation]':{
				deleteclick:function(obj, eOpts){
					this.deleteInstru(obj.record);
				}
			},
		    'instruaddwin button[itemId=submit]':{
		    	click:this.submitForm
		    },
		    'instruaddwin button[itemId=cancel]':{
		    	click:this.closeWin
		    },
		    'instrueditwin button[itemId=submit]':{
		    	click:this.submitForm
		    },
		    'instrueditwin button[itemId=cancel]':{
		    	click:this.closeWin
		    },
		    'instrulist actioncolumn[itemId=operation]':{
				accreditclick:function(obj, eOpts) {
					if(!accreditAddWin) {
						accreditAddWin = Ext.create("CM.view.form.AccreditAddWin");
					} 
					this.openWin(accreditAddWin, obj.record);
				},
				accreditdeleteclick:function(obj, eOpts) {
					this.accreditDelete(obj.record);
				},
				repairclick:function(obj, eOpts) {
					if(!repairAddWin) {
						repairAddWin = Ext.create("CM.view.form.RepairRecAddWin");
					} 
					this.openWin(repairAddWin, obj.record);
				},
				metricalclick:function(obj, eOpts) {
					if(!metricalAddWin) {
						metricalAddWin = Ext.create("CM.view.form.MetricalAddWin");
					} 
					this.openWin(metricalAddWin, obj.record);
				}
		     },
		    'accreditaddwin textfield[itemId=name]':{
		    	focus:function(field, e, eOpts){
		    			var iAdminNameWin = Ext.getCmp('iAdminNameWin');
			       		if(!iAdminNameWin) {
							iAdminNameWin = Ext.create("CM.view.grid.IAdminNameWin");
						}
						iAdminNameWin.show();
			    }
		    },
		    'accreditaddwin button[itemId=submit]':{
		    	click:this.submitForm
		    },
		    'accreditaddwin button[itemId=cancel]':{
		    	click:this.closeWin
		    },
		    'repairrecaddwin button[itemId=submit]':{
		    	click:this.submitForm
		    },
		    'repairrecaddwin button[itemId=cancel]':{
		    	click:this.closeWin
		    },
		    'metricaladdwin button[itemId=submit]':{
		    	click:this.submitForm
		    },
		    'metricaladdwin button[itemId=cancel]':{
		    	click:this.closeWin
		    },
		    'iadminnamewin actioncolumn[itemId=operation]':{
				click:this.selectName
		    }
     	});
     	//主选项卡添加tab
		this.addTab();
		var user = Ext.decode(Ext.util.Cookies.get('user'));
		if(user.roleNames.indexOf('系统管理员') > -1) {
			var grid = Ext.getCmp('instruList');
			grid.down('#name').show();
		}
	},
	//提交表单
	submitForm: function(btn) {
		var sForm = btn.up('form');
		if(sForm.getForm().isValid()){
			if(sForm.itemId.toLowerCase().indexOf('instruadd') > -1) {
				var urlAction = 'instru_addInstru.action';
			} else if(sForm.itemId.toLowerCase().indexOf('instruedit') > -1) {
				var urlAction = 'instru_updateInfo.action';
			} else if(sForm.itemId.toLowerCase().indexOf('accreditadd') > -1) {
				var urlAction = 'instru_accreditOperation.action';
			} else if(sForm.itemId.toLowerCase().indexOf('repairrecadd') > -1) {
				var urlAction = 'instru_addRepairRec.action';
			} else if(sForm.itemId.toLowerCase().indexOf('metricaladd') > -1) {
				var urlAction = 'instru_addMetrical.action';
			}
			sForm.submit({
				url:urlAction,
				method:'POST',
				success:function(form, action){
					var instruGrid = Ext.getCmp('instruList');
					Ext.Msg.alert("提示", action.result.msg, function(){
						form.owner.up('window').hide();
						instruGrid.getStore().load();
					});
				},
				failure:function(form, action){
					Ext.Msg.alert("提示", action.result.errorMessage);
				}
			})
		}
	},
	//展现修改表单
	instruEdit: function(grid, rec, editWin) {
		editWin.show(null,function() {
			var editForm = editWin.down('form');
			editForm.down('#instruId').setValue(rec.get('instruId'));
			editForm.down('#instruName').setValue(rec.get('instruName'));
			editForm.down('#instruModel').setValue(rec.get('instruModel'));
			editForm.down('#instruNum').setValue(rec.get('instruNum'));
			editForm.down('#location').setValue(rec.get('location'));
			editForm.down('#date').setValue(rec.get('date'));
			editForm.down('#money').setValue(rec.get('money'));
			editForm.down('#manufacturer').setValue(rec.get('manufacturer'));
			editForm.down('#country').setValue(rec.get('country'));
			editForm.down('#distributor').setValue(rec.get('distributor'));
		});
	},
	//删除仪器
	deleteInstru: function(record){
		Ext.MessageBox.msgButtons[1].setText("是");
		Ext.MessageBox.msgButtons[2].setText("否");
		Ext.MessageBox.show({
			title:'提示',
			msg:'是否删除该仪器？',
			buttons: Ext.MessageBox.YESNO,
			icon:Ext.MessageBox.QUESTION,
			fn:function(e){
				if(e == "yes"){
			   		Ext.Ajax.request({
//			      		url: 'instru_resetPwd.action?instruId=' + btn.up('form').down('#instruId').value +'&password=123456',
			      		url: 'instru_delete.action',
			      		params:{	
			  				instruId: record.get('instruId')
			  			},
			  			success: function(respone){
			  				var instruGrid = Ext.getCmp('instruList');
			  				Ext.Msg.alert("提示", Ext.JSON.decode(respone.responseText).msg);
			      			instruGrid.getStore().load();
			      		},
			      		failure: function(respone){
			      			Ext.Msg.alert("提示", Ext.JSON.decode(respone.responseText).errorMessage);
			      		}
			   		});
			  	}
			 }
		});
	},
	addTab:function() {
		var mainTab = Ext.getCmp('mainTab');
		mainTab.add({ 
			itemId: 'instruInfoCtrl', //向mainTab的items项中,添加的itemId
    		title: '仪器信息',
    		layout:'border',
    		items:[{
    			region:'center',
    			width:'100%',
    			height:'100%',
    			xtype: 'instrulist'
    		}],
    		closable : true //允许关闭
		}).show();
	},	
	//选取姓名
	selectName: function(grid, rec, index, column, e, eOpts) {
		var recs = grid.getStore().getRange();
		var name = recs[index].get('name');
		var userId = recs[index].get('userId');
		var win = Ext.getCmp('accreditAddWin');
		grid.up('window').hide();
		win.down('#name').setValue(name);
		win.down('#userId').setValue(userId);
	},
	//打开操作窗口
	openWin: function(win, record) {
		var form = win.down('form');
		form.getForm().reset();
		form.down('#instruId').setValue(record.get('instruId'));
		form.down('#instruName').setValue(record.get('instruName'));
		form.down('#instruModel').setValue(record.get('instruModel'));
		if(form.itemId.indexOf('accredit') > -1) {
			form.down('#name').setValue(record.get('name'));
			form.down('#userId').setValue(record.get('userId'));
		}
		win.show();
	},
	accreditDelete:function(record) {
		var name = record.get('name');
		Ext.MessageBox.msgButtons[1].setText("是");
		Ext.MessageBox.msgButtons[2].setText("否");
		Ext.MessageBox.show({
			title:'提示',
			msg:'是否撤销对该管理员的仪器授权？',
			buttons: Ext.MessageBox.YESNO,
			icon:Ext.MessageBox.QUESTION,
			fn:function(e){
				if(e == "yes"){
					if(name == null || name == '') {
						Ext.Msg.alert("提示", '该仪器没有管理员！');
					} else {
						Ext.Ajax.request({
				      		url: 'instru_accreditOperation.action',
				      		params:{	
				  				instruId: record.get('instruId'),
				  				userId: null
				  			},
				  			success: function(respone){
				  				var grid = Ext.getCmp('instruList');
//				  				Ext.Msg.alert("提示", Ext.JSON.decode(respone.responseText).msg);
				      			grid.getStore().load();
				      		},
				      		failure: function(respone){
				      			Ext.Msg.alert("提示", Ext.JSON.decode(respone.responseText).errorMessage);
				      		}
				   		});
					}
			  	}
			 }
		});
	},
	//关闭窗口
	closeWin: function(btn) {
		var win = btn.up('window');
		win.hide();
	},
	views:[
		'grid.InstruList'
	],
	stores:[
		'InstruStore',
		'IAdminNameStore'
	],
	models:[
		'InstruModel',
		'NameModel'
	]
});