function toaddTeacher() {
    layer.open({
        type: 2,
        title: "添加教师",
        closeBtn: 1, //不显示关闭按钮
        shade: [0.5],
        area: ['380px', '420px'],
        /*offset: 'rb', //右下角弹出*/
        /* time: 2000, //2秒后自动关闭*/
        anim: 2,
        content: [path+'admin/teacheredit.jsp', 'no'], //iframe的url，no代表不显示滚动条
        end: function(){ //此处用于演示
            location.reload();
        }
    });
}
function addTeacher(){
    var a = layer.load();
    var teacherName = $("#teacherName").val();
    var teacherAccount = $("#teacherAccount").val();
    var teacherPwd = $("#teacherPwd").val();
    var adminPower = $("#adminPower").val();
    if(teacherName==null||teacherName==''){
        layer.close(a);
        layer.msg('教师的名字不能为空');
        return;
    }
    if(teacherAccount==null||teacherAccount==''){
        layer.close(a);
        layer.msg('教师的用户名不能为空');
        return;
    }
    if(teacherPwd==null||teacherPwd==''){
        layer.close(a);
        layer.msg('教师的名字不能为空');
        return;
    }
    $.ajax(
        {
            url:path+"teacher/insertTeacher.action",
            type:"POST",
            dataType:"json",
            data:{"teacherName":teacherName,"teacherAccount":teacherAccount,"teacherPwd":teacherPwd,"adminPower":adminPower},
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

function toupdateTeacher(teacherId){
    layer.open({
        type: 2,
        title: "修改教师",
        closeBtn: 1, //不显示关闭按钮
        shade: [0.5],
        area: ['480px', '491px'],
        scrollbar: true,
        /*offset: 'rb', //右下角弹出*/
        /* time: 2000, //2秒后自动关闭*/
        anim: 2,
        content: [path+'teacher/getTeacherById.action?teacherId='+teacherId, 'no'], //iframe的url，no代表不显示滚动条
        end: function(){ //此处用于演示
            location.reload();
        }
    });
}
function updateTeacher(){
    var a = layer.load();
    var teacherId = $("#teacherId").val();
    var teacherName = $("#teacherName").val();
    var teacherAccount = $("#teacherAccount").val();
    var teacherPwd = $("#teacherPwd").val();
    var adminPower = $("#adminPower").val();
    if(teacherName==null||teacherName==''){
        layer.close(a);
        layer.msg('教师的名字不能为空');
        return;
    }
    if(teacherAccount==null||teacherAccount==''){
        layer.close(a);
        layer.msg('教师的用户名不能为空');
        return;
    }
    if(teacherPwd==null||teacherPwd==''){
        layer.close(a);
        layer.msg('教师的名字不能为空');
        return;
    }
    $.ajax(
        {
            url:path+"teacher/updateTeacher.action",
            type:"POST",
            dataType:"json",
            data:{"teacherId":teacherId,"teacherName":teacherName,"teacherAccount":teacherAccount,
                    "teacherPwd":teacherPwd,"adminPower":adminPower},
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


function deleteTeacher(teacherId) {
    layer.confirm('是否确定要删除？', {
        btn: ['是','否'] //按钮
    }, function(){
        var a = layer.load();
        $.ajax(
            {
                url:path+"teacher/deleteTeacher.action",
                type:"POST",
                dataType:"json",
                data:{"teacherId":teacherId},
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


