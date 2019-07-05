function toaddGrade(){
    layer.open({
        type: 2,
        title: "添加年级",
        closeBtn: 1, //不显示关闭按钮
        shade: [0.5],
        area: ['340px', '215px'],
        /*offset: 'rb', //右下角弹出*/
        /* time: 2000, //2秒后自动关闭*/
        anim: 2,
        content: [path+'admin/gradeedit.jsp', 'no'], //iframe的url，no代表不显示滚动条
        end: function(){ //此处用于演示
            location.reload();
        }
    });
}

$(function(){
    $(".lookclass").click(function(){
        var a = layer.load();
        var gradeId = $(this).attr("id");
        gradeId = gradeId.substring(1,gradeId.length);
        var gradeName =$("#g"+gradeId).html();
        $.ajax(
            {
                url:path+"class/getClassInfoByGradeId.action",
                type:"POST",
                dataType:"json",
                data:{"gradeId":gradeId},
                success:function(data){
                    layer.close(a);
                    var html = '';
                    for(var i =0;i<data.classes.length;i++){
                        html+=data.classes[i].className+"<br>"
                    }
                    layer.open({
                        type: 1,
                        title:"["+gradeName+"]班级信息",
                        skin: 'layui-layer-rim', //加上边框
                        area: ['420px', '240px'], //宽高
                        content: html
                    });
                }
            }
        );
    });
});

function addGrade(){
    var a = layer.load();
    var gradeName = $("#gradeName").val();
    if(gradeName==null||gradeName==''){
        layer.close(a);
        layer.msg('年级的名字不能为空');
        return;
    }
    $.ajax(
        {
            url:path+"grade/insertGrade.action",
            type:"POST",
            dataType:"json",
            data:{"gradeName":gradeName},
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

function toupdateGrade(gradeId){
    layer.open({
        type: 2,
        title: "修改年级",
        closeBtn: 1, //不显示关闭按钮
        shade: [0.5],
        area: ['340px', '215px'],
        /*offset: 'rb', //右下角弹出*/
        /* time: 2000, //2秒后自动关闭*/
        anim: 2,
        content: [path+'grade/getGradeById.action?gradeId='+gradeId, 'no'], //iframe的url，no代表不显示滚动条
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
function updateGrade(){
    var a = layer.load();
    var gradeName = $("#gradeName").val();
    var gradeId = $("#gradeId").val();
    if(gradeName==null||gradeName==''){
        layer.close(a);
        layer.msg('年级的名字不能为空');
        return;
    }
    $.ajax(
        {
            url:path+"grade/updateGrade.action",
            type:"POST",
            dataType:"json",
            data:{"gradeName":gradeName,"gradeId":gradeId},
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

function deleteGrade(gradeId) {
    layer.confirm('是否确定要删除？', {
        btn: ['是','否'] //按钮
    }, function(){
        var a = layer.load();
        $.ajax(
            {
                url:path+"grade/deleteGrade.action",
                type:"POST",
                dataType:"json",
                data:{"gradeId":gradeId},
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