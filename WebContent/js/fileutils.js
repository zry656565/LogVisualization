function uploadFile() {
	var file = dwr.util.getValue("file1");
	var name = dwr.util.getValue("sourcename");
	var fileArr = new Array();
	fileArr[0] = file;
	FileUtils.uploadFile(fileArr, name, {
		callback : function(data) {
			alert(data);
		}
	});
}