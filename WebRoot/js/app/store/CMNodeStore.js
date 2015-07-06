/**
 *Node数据集
 */
Ext.define('CM.store.CMNodeStore', {
	extend:'Ext.data.TreeStore', //必须继承自TreeStore
	model:'CM.model.NodeModel',
	proxy:{
		type:'ajax',
		url:'node_getNodes.action',
		extraParams:{	
  			treeId: 'cm'
  		}
	},
	reader:{    
         type:'json'
    }
});