Ext.define('CM.util.GridDoActionUtil', {
	//**
	
	doInsert:function(grid, modelObj, treeObj) {
		if(!grid && modelObj) {
			alert('参数不正确');
			return;
		}
		//得到表格数据集
		var store = grid.getStore();
		//得到目前表格的数据集合长度
		var storeCount = store.getCount();
		//得到编辑插件
		var edit = grid.editing;
		//得到数据模型
		var model = store.model;
		
		if(storeCount == 0) { //根节点
			//初始化一个模型类
			var mObj = new model(modelObj);
			edit.cancelEdit(); //取消其他插件编辑活动
			store.insert(0, mObj);
		}
	}
})