/**
 * ClassName：用户信息控制器
 */
Ext.define("CM.controller.PersonnelInfoCtrl", {
	extend : 'Ext.app.Controller',
	init : function(app) {
		var appraisalWin;
		var certificateWin;
		var trainingRecWin;
		this.control({
			'personnellist actioncolumn[itemId=operation]' : {
				pageclick:function(obj, eOpts) {
					if(!appraisalWin) {
						appraisalWin = Ext.create("CM.view.form.AppraisalRecAddWin");
					}
					this.openWin(appraisalWin, obj.record);
				},
				reportclick:function(obj, eOpts) {
					if(!trainingRecWin) {
						trainingRecWin = Ext.create("CM.view.form.TrainingRecAddWin");
					}
					this.openWin(trainingRecWin, obj.record);
				},
				bookclick:function(obj, eOpts) {
					if(!certificateWin) {
						certificateWin = Ext.create("CM.view.form.CertificateAddWin");
					}
					this.openWin(certificateWin, obj.record);
				}
			},
			'appraisalrecaddwin button[itemId=submit]':{
				click:this.submitForm
			},
			'appraisalrecaddwin button[itemId=cancel]':{
				click:this.closeWin
			},
			'certificateaddwin button[itemId=submit]':{
				click:this.submitForm
			},
			'certificateaddwin button[itemId=cancel]':{
				click:this.closeWin
			},
			'trainingrecaddwin button[itemId=submit]':{
				click:this.submitForm
			},
			'trainingrecaddwin button[itemId=cancel]':{
				click:this.closeWin
			}
		});
		this.addTab();
	},
	// 提交表单
	submitForm : function(btn, e, eOpts) {
		var sForm = btn.up('form');
		if (sForm.getForm().isValid()) {
			if (sForm.itemId.toLowerCase().indexOf('appraisalrec') > -1) {
				var urlAction = 'appraisalRec_addAppraisalRec.action';
			} else if (sForm.itemId.toLowerCase().indexOf('certificate') > -1) {
				var urlAction = 'certificate_addCertificate.action';
			} else if (sForm.itemId.toLowerCase().indexOf('trainingrec') > -1) {
				var urlAction = 'trainingRec_addTrainingRec.action';
			} 
			sForm.submit({
				url : urlAction,
				method : 'POST',
				success : function(form, action) {
					Ext.Msg.alert("提示", action.result.msg, function() {
						form.owner.up('window').hide();
					});
				},
				failure : function(form, action) {
					Ext.Msg.alert("提示", action.result.errorMessage);
				}
			})
		}
	},
	openWin:function(win,record) {
		var form = win.down('form');
		form.getForm().reset();
		form.down('#userId').setValue(record.get('userId'));
		form.down('#userName').setValue(record.get('userName'));
		form.down('#name').setValue(record.get('name'));
		win.show();	
	},
	// 主选项卡添加tab
	addTab:function() {
		var mainTab = Ext.getCmp('mainTab');
		mainTab.add({
			itemId : 'personnelInfoCtrl', // 向mainTab的items项中,添加的itemId
			title : '人员信息',
			layout:'border',
			items : [{
				region:'center',
				width : '100%',
				height : '100%',
				xtype : 'personnellist'
			}],
			closable : true // 允许关闭
		}).show();
	},
	// 关闭窗口
	closeWin : function(btn) {
		var win = btn.up('window');
		win.hide();
	},
	views : [
		'grid.PersonnelList'
	],
	stores : [
		'UserStore'
	],
	models : [
		'UserModel'
	]
});