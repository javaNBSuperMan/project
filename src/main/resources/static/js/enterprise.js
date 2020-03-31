var uploadFiles = [];
var index = 0;
var uploadFilesLenth = 0;
var bool = true;

function setImagePreview(id) {
    var docObj = document.getElementById(id);
    var AllowImgFileSize = 10500000; //上传图片最大值(单位字节)（ 10 M = 10485760 B ）超过10M上传失败
    var imgUrlBase64;
    for (var i = 0; i < docObj.files.length; i++) {
        var file = docObj.files[i];
        if (file) {
            //将文件以Data URL形式读入页面
            var reader = new FileReader();
            imgUrlBase64 = reader.readAsDataURL(file);
            var b = 0;

            reader.onload = function (e) {
                //var ImgFileSize = reader.result.substring(reader.result.indexOf(",") + 1).length;//截取base64码部分
                if (AllowImgFileSize != 0 && AllowImgFileSize < e.target.result.length) {
                    layer.msg('上传失败，' + file.name + '大于10M！,请上传少于10M的图片！');
                } else {
                    //执行上传操作
                    var imgUrlId = "imgUrl" + index;
                    uploadFiles[index] = {
                        img: e.target.result,
                        imgName: docObj.files[b].name,
                        imgSize: docObj.files[b].size
                    };
                    var fileIndex = "imgIndex" + index;
                    var img = "<img onclick=\"deleteDiv('" + index + "')\" title='点击删除' class='divImage' id = '" + fileIndex + "' src='" + e.target.result + "'/>";
                    if(bool) {
                        $("#uploadDiv").append('<button id="delAll" style="width: 116px" class="btn btn-info" type="button" onclick="deleteAll()">删除全部</button>');
                        bool = false;
                    }
                    $("#uploadDiv").append(img);
                    b++;
                    index++;
                    uploadFilesLenth++;
                }
            }
        }
    }
    return true;
}