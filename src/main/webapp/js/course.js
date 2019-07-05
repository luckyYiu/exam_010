function toaddCourse() {
    layer.open({
        type: 2,
        title: "添加科目",
        closeBtn: 1, //不显示关闭按钮
        shade: [0.5],
        area: ['340px', '350px'],
        /*offset: 'rb', //右下角弹出*/
        /* time: 2000, //2秒后自动关闭*/
        anim: 2,
        content: [path+'course/preAddCourse.action', 'no'], //iframe的url，no代表不显示滚动条
        end: function(){ //此处用于演示
            location.reload();
        }
    });
}

function addCourse(){
    var a = layer.load();
    var courseName = $("#courseName").val();
    var division = $("#division").val();
    var gradeId = $("#gradeId").val();
    if(courseName==null||courseName==''){
        layer.close(a);
        layer.msg('科目的名字不能为空');
        return;
    }
    $.ajax(
        {
            url:path+"course/insertCourse.action",
            type:"POST",
            dataType:"json",
            data:{"courseName":courseName,"division":division,"gradeId":gradeId},
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

function toupdateCourse(courseId){
    layer.open({
        type: 2,
        title: "修改科目",
        closeBtn: 1, //不显示关闭按钮
        shade: [0.5],
        area: ['340px', '410px'],
        /*offset: 'rb', //右下角弹出*/
        /* time: 2000, //2秒后自动关闭*/
        anim: 2,
        content: [path+'course/getCourseById.action?courseId='+courseId, 'no'], //iframe的url，no代表不显示滚动条
        end: function(){ //此处用于演示
            location.reload();
            /*layer.open({
                type: 2,
                title: '很多时候，我们想最大化看，比如像这个页面。',
                shadeClose: true,
                shade: false,
                maxmin: true, //开启最大化最小化按钮
                area: ['893px', '600px'],
                content: '//fly.layui.com/'
            });*/
        }
    });
}
function updateCourse(){
    var a = layer.load();
    var courseName = $("#courseName").val();
    var division = $("#division").val();
    var gradeId = $("#gradeId").val();
    var courseId = $("#courseId").val();
    if(courseName==null||courseName==''){
        layer.close(a);
        layer.msg('科目的名字不能为空');
        return;
    }
    $.ajax(
        {
            url:path+"course/updateCourse.action",
            type:"POST",
            dataType:"json",
            data:{"courseName":courseName,"gradeId":gradeId,"division":division,"courseId":courseId},
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

function deleteCourse(courseId) {
    layer.confirm('是否确定要删除？', {
        btn: ['是','否'] //按钮
    }, function(){
        var a = layer.load();
        $.ajax(
            {
                url:path+"course/deleteCourse.action",
                type:"POST",
                dataType:"json",
                data:{"courseId":courseId},
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