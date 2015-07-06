/**
 * 人员数据集
 */
Ext.define('CM.store.CertificateStore', {
	extend:'Ext.data.Store',
	model:'CM.model.CertificateModel',
	pageSize:22,
	proxy:{
		type:'ajax',
		url:'certificate_getCertificates.action',
		reader: {
			type:'json',
			root:'certificates',
			totalProperty: 'total'
		},
		writer: {
			type:'json'
		}
	},
	autoLoad:true
});