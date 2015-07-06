/**
 * ClassName：用户信息控制器
 */
Ext.define("CM.controller.SampleInfoCtrl", {
	extend:'Ext.app.Controller',
	init:function(app) {
		var sampleAddWin;
		var accreditAddWin;
		var repairAddWin;
		var metricalAddWin;
		var editWin;
		this.control({
			'samplelist button[itemId=add]':{
				click:function() {
					if(!sampleAddWin) {
						sampleAddWin = Ext.create("CM.view.form.SampleAddWin");
					} 
					sampleAddWin.down('form').getForm().reset();
					sampleAddWin.show();
				}
			},
			'samplelist':{
				itemdblclick:function(grid, rec, item, index, e, eOpts) {
			       	if(!editWin) {
						editWin = Ext.create("CM.view.form.SampleEditWin");
					} 
					this.sampleEdit(grid, rec, editWin);
				}
			},
			'samplelist actioncolumn[itemId=operation]':{
				deleteclick:function(obj, eOpts){
					this.deleteSample(obj.record);
				}
			},
		    'sampleaddwin button[itemId=submit]':{
		    	click:this.submitForm
		    },
		    'sampleaddwin button[itemId=cancel]':{
		    	click:this.closeWin
		    },
		    'sampleeditwin button[itemId=submit]':{
		    	click:this.submitForm
		    },
		    'sampleeditwin button[itemId=cancel]':{
		    	click:this.closeWin
		    },
		    'samplelist actioncolumn[itemId=operation]':{
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
	},
	//提交表单
	submitForm: function(btn) {
		var sForm = btn.up('form');
		if(sForm.getForm().isValid()){
			if(sForm.itemId.toLowerCase().indexOf('sampleadd') > -1) {
				var urlAction = 'sample_addSample.action';
			} else if(sForm.itemId.toLowerCase().indexOf('sampleedit') > -1) {
				var urlAction = 'sample_updateInfo.action';
			} else if(sForm.itemId.toLowerCase().indexOf('accreditadd') > -1) {
				var urlAction = 'sample_accreditOperation.action';
			} else if(sForm.itemId.toLowerCase().indexOf('repairrecadd') > -1) {
				var urlAction = 'sample_addRepairRec.action';
			} else if(sForm.itemId.toLowerCase().indexOf('metricaladd') > -1) {
				var urlAction = 'sample_addMetrical.action';
			}
			sForm.submit({
				url:urlAction,
				method:'POST',
				success:function(form, action){
					var sampleGrid = Ext.getCmp('sampleList');
					Ext.Msg.alert("提示", action.result.msg, function(){
						form.owner.up('window').hide();
						sampleGrid.getStore().load();
					});
				},
				failure:function(form, action){
					Ext.Msg.alert("提示", action.result.errorMessage);
				}
			})
		}
	},
	//展现修改表单
	sampleEdit: function(grid, rec, editWin) {
		editWin.show(null,function() {
			var editForm = editWin.down('form');
			editForm.down('#sampleId').setValue(rec.get('sampleId'));
			editForm.down('#sampleName').setValue(rec.get('sampleName'));
			editForm.down('#sampleModel').setValue(rec.get('sampleModel'));
			editForm.down('#sampleNum').setValue(rec.get('sampleNum'));
			editForm.down('#location').setValue(rec.get('location'));
			editForm.down('#date').setValue(rec.get('date'));
			editForm.down('#money').setValue(rec.get('money'));
			editForm.down('#manufacturer').setValue(rec.get('manufacturer'));
			editForm.down('#country').setValue(rec.get('country'));
			editForm.down('#distributor').setValue(rec.get('distributor'));
		});
	},
	//删除仪器
	deleteSample: function(record){
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
//			      		url: 'sample_resetPwd.action?sampleId=' + btn.up('form').down('#sampleId').value +'&password=123456',
			      		url: 'sample_delete.action',
			      		params:{	
			  				sampleId: record.get('sampleId')
			  			},
			  			success: function(respone){
			  				var sampleGrid = Ext.getCmp('sampleList');
			  				Ext.Msg.alert("提示", Ext.JSON.decode(respone.responseText).msg);
			      			sampleGrid.getStore().load();
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
			itemId: 'sampleInfoCtrl', //向mainTab的items项中,添加的itemId
    		title: '样品信息',
    		layout:'border',
    		items:[{
    			region:'center',
    			width:'100%',
    			height:'100%',
    			xtype: 'samplelist'
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
		form.down('#sampleId').setValue(record.get('sampleId'));
		form.down('#sampleName').setValue(record.get('sampleName'));
		form.down('#sampleModel').setValue(record.get('sampleModel'));
		if(form.itemId.indexOf('accredit') > -1) {
			form.down('#name').setValue(record.get('name'));
			form.down('#userId').setValue(record.get('userId'));
		}
		win.show();
	},
	//关闭窗口
	closeWin: function(btn) {
		var win = btn.up('window');
		win.hide();
	},
	views:[
		'grid.SampleList'
	],
	stores:[
		'SampleStore'
	],
	models:[
		'SampleModel'
	]
});