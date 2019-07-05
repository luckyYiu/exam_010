function toupdateStudent(studentId) {
    layer.open({
        type: 2,
        title: "修改学生",
        closeBtn: 1, //不显示关闭按钮
        shade: [0.5],
        area: ['480px', '491px'],
        scrollbar: true,
        /*offset: 'rb', //右下角弹出*/
        /* time: 2000, //2秒后自动关闭*/
        anim: 2,
        content: [path+'student/getStudentById.action?studentId='+studentId, 'no'], //iframe的url，no代表不显示滚动条
        end: function(){ //此处用于演示
            location.reload();
        }
    });
}
function updateStudent(){
    var a = layer.load();
    var studentId = $("#studentId").val();
    var studentName = $("#studentName").val();
    var studentAccount = $("#studentAccount").val();
    var studentPwd = $("#studentPwd").val();
    var classId = $("#classId").val();
    if(studentName==null||studentName==''){
        layer.close(a);
        layer.msg('学生的名字不能为空');
        return;
    }
    if(studentAccount==null||studentAccount==''){
        layer.close(a);
        layer.msg('学生的用户名不能为空');
        return;
    }
    if(studentPwd==null||studentPwd==''){
        layer.close(a);
        layer.msg('学生的名字不能为空');
        return;
    }
    $.ajax(
        {
            url:path+"student/updateStudent.action",
            type:"POST",
            dataType:"json",
            data:{"studentName":studentName,"studentAccount":studentAccount,
                "studentPwd":studentPwd,"classId":classId,"studentId":studentId},
            success:function(data){
                layer.close(a);
                if(data.state==0){
                    layer.msg(data.msg,function () {
                        layer.closeAll();
                        parent.window.layer.closeAll();
                    });
                }else{
                    layer.msg(data.msg,function () {

                    });
                }
            }
        }
    );
}

function deleteStudent(studentId) {
    layer.confirm('是否确定要删除？', {
        btn: ['是','否'] //按钮
    }, function(){
        var a = layer.load();
        $.ajax(
            {
                url:path+"student/deleteStudent.action",
                type:"POST",
                dataType:"json",
                data:{"studentId":studentId},
                success:function(data){
                    layer.close(a);
                    if(data.state==0){
                        layer.msg(data.msg,function () {
                            layer.closeAll();
                            location.reload();
                        });
                    }else{
                        layer.msg(data.msg,function () {

                        });
                    }
                }
            }
        );
    }, function(){
        layer.closeAll();
    });
}

