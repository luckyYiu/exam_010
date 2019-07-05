function getFile() {
    var inputFile=$("#inputFile");
    var importOption=$("input[name='importOption']:checked").val();
    var division=$("select[name='division']").val();
    var courseId=$("select[name='courseId']").val();
    var gradeId=$("select[name='gradeId']").val();
    $.ajax(
        {
            url:path+"/subject/uploadSubjectInfo.action",
            type:"POST",
            dataType:"json",
            data:{"inputFile":inputFile,"importOption":importOption,"division":division,
                "courseId":courseId,"gradeId":gradeId},
            success:function(data){
                layer.close(a);
                var state = data.state;
                if(state==0){
                    layer.msg(data.msg,{time:400},function(){
                        layer.closeAll();
                        location.href=path+"admin/index.jsp";
                    });
                }else{
                    layer.msg(data.msg);
                    $("input").val("");
                }
            }
        }
    );
}