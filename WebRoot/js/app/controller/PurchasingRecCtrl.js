/**
 * ClassName：用户信息控制器
 */
Ext.define("CM.controller.PurchasingRecCtrl", {
	extend:'Ext.app.Controller',
	init:function(app) {
		var state;
		var record;
		var win;
		this.control({
			'purchasingrec actioncolumn[itemId=operation]': {
				agreeclick:function(obj, eOpts) {
					state = '已通过';
					this.operation(obj.record, state);
				},
				disagreeclick:function(obj, eOpts) {
					state = '未通过';
					this.operation(obj.record, state);
				},
				undolclick:function(obj, eOpts) {
					state = '已撤销';
					this.operation(obj.record, state);
				},
				doorinclick:function(obj, eOpts) {
					state = '已入库';
					this.operation(obj.record, state);
				},
				redolclick:function(obj, eOpts) {
					state = '待审批';
					record = obj.record;
					this.openWin(record, win);
				}
			},
			'purchasingwin button[itemId=submit]': {
				click:function(btn, e, eOpts) {
					this.submitForm(btn, record);
				}
			},
			'purchasingwin button[itemId=cancel]': {
				click:this.closeWin
			}
     	});
     	this.addTab();
	},
	//主选项卡添加tab
	addTab:function() {
		var mainTab = Ext.getCmp('mainTab');
		mainTab.add({ 
			itemId: 'purchasingRecCtrl', //向mainTab的items项中,添加的itemId
    		title: '采购记录',
    		layout:'border',
    		items:[{
    			region:'center',
    			width:'100%',
    			height:'100%',
    			xtype: 'purchasingrec'
    		}],
    		closable : true //允许关闭
		}).show();
	},
	openWin: function(record, win) {
		if(!win) {
			win = Ext.create("CM.view.form.PurchasingWin");
		}
		var form = win.down('form');
		form.getForm().reset();
		if(record.get('instruId') != 0) {
			form.down('#instruInfo').show();
			form.down('#instruName').setValue(record.get('instruName'));
			form.down('#instruModel').setValue(record.get('instruModel'));
			form.down('#consumableInfo').setPosition(0,70);
		} else {
			form.down('#instruInfo').hide();
			form.down('#consumableInfo').setPosition(0,0);
		}
		if(record) {
			form.down('#consumableName').setValue(record.get('consumableName'));
			form.down('#consumableType').setValue(record.get('consumableType'));
			form.down('#quantity').setValue(record.get('quantity'));
			form.down('#remark').setValue(record.get('remark'));
			form.down('#date').setValue(record.get('date'));
		}
		win.show();
	},
	//提交表单
	submitForm: function(btn, record) {
		var sForm = btn.up('form');
		if(sForm.getForm().isValid()){
			var urlAction = 'consumable_updatePurchasingInfo'
			sForm.submit({
				url:urlAction,
				method:'POST',
				params:{	
	  				instruId: record.get('instruId'),
	  				userId: record.get('userId'),
	  				consumableId: record.get('consumableId'),
	  				recId:record.get('recId')
	  			},
				success:function(form, action){
					var grid = Ext.getCmp('purchasingRec');
					Ext.Msg.alert("提示", action.result.msg, function(){
						grid.up('window').hide();
						grid.getStore().load();
					});
				},
				failure:function(form, action){
					Ext.Msg.alert("提示", action.result.errorMessage);
				}
			})
		}
	},
	operation: function(record, state, pWin){
		Ext.MessageBox.msgButtons[1].setText("是");
		Ext.MessageBox.msgButtons[2].setText("取消");
		var msg;
		if(state == '已通过') {
			msg = '是否同意该申请？'
		} else if(state == '未通过') {
			msg = '是否驳回该申请？'
		} else if(state == '已撤销') {
			msg = '是否撤销该申请？'
		} else if(state == '已入库') {
			msg = '是否入库该耗材？'
		}
		Ext.MessageBox.show({
			title:'提示',
			msg: msg,
			buttons: Ext.MessageBox.YESNO,
			icon:Ext.MessageBox.QUESTION,
			fn:function(e){
				if(e == "yes"){
			   		Ext.Ajax.request({
			      		url: 'consumable_updatePurchasingState.action',
			      		params:{	
			  				recId: record.get('recId'),
			  				state: state
			  			},
			  			success: function(respone){
			  				var grid = Ext.getCmp('purchasingRec');
			      			grid.getStore().load();
			      		},
			      		failure: function(respone){
			      			Ext.Msg.alert("提示", Ext.JSON.decode(respone.responseText).errorMessage);
			      		}
			   		});
				   	if(state == '已入库') {
				   		Ext.Ajax.request({
				      		url: 'consumable_updateInfo.action',
				      		params:{	
				  				instruId: record.get('instruId'),
				  				userId: record.get('userId'),
				  				consumableId: record.get('consumableId'),
				  				flag: true,
				  				quantity: record.get('quantity'),
				  				currentQuantity: record.get('currentQuantity'),
				  				date: new Date()
				  			},
				  			success: function(respone){
				  				var grid = Ext.getCmp('purchasingRec');
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
		'grid.PurchasingRec'
	],
	stores:[
		'PurchasingRecStore'
	],
	models:[
		'PurchasingRecModel'
	]
});
