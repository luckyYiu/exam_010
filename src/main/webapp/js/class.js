function toaddClass() {
    layer.open({
        type: 2,
        title: "添加班级",
        closeBtn: 1, //不显示关闭按钮
        shade: [0.5],
        area: ['450px', '500px'],
        /*offset: 'rb', //右下角弹出*/
        /* time: 2000, //2秒后自动关闭*/
        anim: 2,
        content: [path+'class/preAddClass.action', 'no'], //iframe的url，no代表不显示滚动条
        end: function(){ //此处用于演示
            location.reload();
        }
    });
}
function addClass(){
    var a = layer.load();
    var className = $("#className").val();
    var gradeId = $("#gradeId").val();
    var teacherId = $("#teacherId").val();
    if(className==null||className==''){
        layer.close(a);
        layer.msg('班级的名字不能为空');
        return;
    }
    $.ajax(
        {
            url:path+"class/insertClass.action",
            type:"POST",
            dataType:"json",
            data:{"className":className,"gradeId":gradeId,"teacherId":teacherId},
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

function toupdateClass(classId){
    layer.open({
        type: 2,
        title: "修改班级",
        closeBtn: 1, //不显示关闭按钮
        shade: [0.5],
        area: ['340px', '410px'],
        /*offset: 'rb', //右下角弹出*/
        /* time: 2000, //2秒后自动关闭*/
        anim: 2,
        content: [path+'class/getClassById.action?classId='+classId, 'no'], //iframe的url，no代表不显示滚动条
        end: function(){ //此处用于演示
            location.reload();
        }
    });
}
function updateClass(){
    var a = layer.load();
    var className = $("#className").val();
    var gradeId = $("#gradeId").val();
    var teacherId = $("#teacherId").val();
    var classId = $("#classId").val();
    if(className==null||className==''){
        layer.close(a);
        layer.msg('班级的名字不能为空');
        return;
    }
    $.ajax(
        {
            url:path+"class/updateClass.action",
            type:"POST",
            dataType:"json",
            data:{"className":className,"gradeId":gradeId,"teacherId":teacherId,"classId":classId},
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

function deleteClass(classId) {
    layer.confirm('是否确定要删除？', {
        btn: ['是','否'] //按钮
    }, function(){
        var a = layer.load();
        $.ajax(
            {
                url:path+"class/deleteClass.action",
                type:"POST",
                dataType:"json",
                data:{"classId":classId},
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