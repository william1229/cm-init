Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget='side';
	Ext.Loader.setConfig({
		enabled:true
	});
	Ext.application({
		name:'CM',
		appFolder:'js/app',
		launch:function() {
			Ext.create('Ext.container.Viewport', {
				layout:'fit',
				items: [{
					width:'100%',
					height:'100%',
					xtype:'mainlayout'
				}]
			});
		},
		controllers:[
			'MainCtrl'
		]
	});
    Array.prototype.remove = function(val) { //数组扩展功能
            var index = this.indexOf(val);
            if (index > -1) {
                this.splice(index, 1);
            }
    };
    Ext.apply(Ext.form.VTypes,{
		//val：文本框值，field：文本框组件
	    password:function(val, field){ 
    		var pwd = field.previousNode().getValue(); //获取上一个兄弟组件的值
    		return (val == pwd);
	    },
	    dateScope:function(val, field){
	    	var endDate = field.getValue();
    		var startDate = field.previousNode().getValue(); //获取上一个兄弟组件的值
    		return (startDate <= endDate);
	    }
   	});
});