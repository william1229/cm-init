/**
 * ClassName：用户信息控制器
 */
Ext.define("CM.controller.ProjectInfoCtrl", {
	extend:'Ext.app.Controller',
	init:function(app) {
		var projectAddWin;
		var reportAddWin;
		var editWin;
		var sampleAddWin;
		var str;
		this.control({
			'projectlist button[itemId=add]':{
				click:function() {
					if(!projectAddWin) {
						projectAddWin = Ext.create("CM.view.form.ProjectAddWin");
					} 
					projectAddWin.down('form').getForm().reset();
					projectAddWin.show();
				}
			},
			'projectlist':{
				itemdblclick:function(grid, rec, item, index, e, eOpts) {
			       	if(!editWin) {
						editWin = Ext.create("CM.view.form.ProjectEditWin");
					} 
					this.projectEdit(grid, rec, editWin);
				}
			},
			'projectlist actioncolumn[itemId=operation]':{
				reportgoclick:function(obj, eOpts){
					if(!reportAddWin) {
						reportAddWin = Ext.create("CM.view.form.ReportAddWin");
					} 
					this.openWin(reportAddWin, obj.record);
				},				
				reportdeleteclick:function(obj, eOpts){
					this.deleteReport(obj.record);
				},
				addsampleclick:function(obj, eOpts){
					if(!sampleAddWin) {
						sampleAddWin = Ext.create("CM.view.form.SampleAddWin");
					} 
					this.openWin(sampleAddWin, obj.record);
				}
			},
		    'projectaddwin button[itemId=submit]':{
		    	click:this.submitForm
		    },
		    'projectaddwin button[itemId=cancel]':{
		    	click:this.closeWin
		    },
		   	'sampleaddwin button[itemId=submit]':{
		    	click:this.submitForm
		    },
		    'sampleaddwin button[itemId=cancel]':{
		    	click:this.closeWin
		    },
		    'projecteditwin button[itemId=submit]':{
		    	click:this.submitForm
		    },
		    'projecteditwin button[itemId=cancel]':{
		    	click:this.closeWin
		    },
		    'reportaddwin button[itemId=submit]':{
		    	click:this.submitForm
		    },
		    'reportaddwin button[itemId=cancel]':{
		    	click:this.closeWin
		    },
		    'projectlist actioncolumn[itemId=contract]':{
		     	click:function(grid, rec, index, column, e, eOpts){
		     		str = 'contract'; 
		     		this.download(grid, index, str);
		     	}
		     },
		    'projectlist actioncolumn[itemId=report]':{
		     	click:function(grid, rec, index, column, e, eOpts){
		     		str = 'report'; 
		     		this.download(grid, index, str);
		     	}
		     }
     	});
     	//主选项卡添加tab
		this.addTab();
	},
	//提交表单
	submitForm: function(btn) {
		var sForm = btn.up('form');
		if(sForm.getForm().isValid()){
			if(sForm.itemId.toLowerCase().indexOf('projectadd') > -1) {
				var urlAction = 'project_addProject.action';
			} else if(sForm.itemId.toLowerCase().indexOf('reportadd') > -1) {
				var urlAction = 'project_addReport.action';
			} else if(sForm.itemId.toLowerCase().indexOf('sampleadd') > -1) {
				var urlAction = 'project_addSample.action';
			} else if(sForm.itemId.toLowerCase().indexOf('repairrecadd') > -1) {
				var urlAction = 'project_addRepairRec.action';
			} else if(sForm.itemId.toLowerCase().indexOf('metricaladd') > -1) {
				var urlAction = 'project_addMetrical.action';
			}
			sForm.submit({
				url:urlAction,
				method:'POST',
				success:function(form, action){
					var projectGrid = Ext.getCmp('projectList');
					Ext.Msg.alert("提示", action.result.msg, function(){
						form.owner.up('window').hide();
						projectGrid.getStore().load();
					});
				},
				failure:function(form, action){
					Ext.Msg.alert("提示", action.result.errorMessage);
				}
			})
		}
	},
	//展现修改表单
	projectEdit: function(grid, rec, editWin) {
		editWin.show(null,function() {
			var editForm = editWin.down('form');
			editForm.down('#projectId').setValue(rec.get('projectId'));
			editForm.down('#projectName').setValue(rec.get('projectName'));
			editForm.down('#projectModel').setValue(rec.get('projectModel'));
			editForm.down('#projectNum').setValue(rec.get('projectNum'));
			editForm.down('#location').setValue(rec.get('location'));
			editForm.down('#date').setValue(rec.get('date'));
			editForm.down('#money').setValue(rec.get('money'));
			editForm.down('#manufacturer').setValue(rec.get('manufacturer'));
			editForm.down('#country').setValue(rec.get('country'));
			editForm.down('#distributor').setValue(rec.get('distributor'));
		});
	},
	//删除报告
	deleteReport: function(record){
		Ext.MessageBox.msgButtons[1].setText("是");
		Ext.MessageBox.msgButtons[2].setText("否");
		Ext.MessageBox.show({
			title:'提示',
			msg:'是否删除该合同的报告？',
			buttons: Ext.MessageBox.YESNO,
			icon:Ext.MessageBox.QUESTION,
			fn:function(e){
				if(e == "yes"){
			   		Ext.Ajax.request({
//			      		url: 'project_resetPwd.action?projectId=' + btn.up('form').down('#projectId').value +'&password=123456',
			      		url: 'project_deleteReport.action',
			      		params:{	
			  				reportId: record.get('reportId')
			  			},
			  			success: function(respone){
			  				var projectGrid = Ext.getCmp('projectList');
			      			projectGrid.getStore().load();
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
			itemId: 'projectInfoCtrl', //向mainTab的items项中,添加的itemId
    		title: '项目信息',
    		layout:'border',
    		items:[{
    			region:'center',
    			width:'100%',
    			height:'100%',
    			xtype: 'projectlist'
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
		form.down('#projectId').setValue(record.get('projectId'));
		form.down('#projectName').setValue(record.get('projectName'));
		form.down('#projectSN').setValue(record.get('projectSN'));
		form.down('#date').setValue(record.get('date'));
		win.show();
	},
	download: function(grid, index, str) {
		var recs = grid.getStore().getRange();
		var path = recs[index].get(str);
		window.open('project_download.action?path=' + path);
	},
	//关闭窗口
	closeWin: function(btn) {
		var win = btn.up('window');
		win.hide();
	},
	views:[
		'grid.ProjectList'
	],
	stores:[
		'ProjectStore'
	],
	models:[
		'ProjectModel'
	]
});