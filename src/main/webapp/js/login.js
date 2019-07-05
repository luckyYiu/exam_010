function teacherLogin() {
	var a = layer.load();
	var username = $("#username").val();
    var pwd = $("#pwd").val();
    if(username==null||username==''){
    	layer.close(a);
    	$(".error-msg-name").css("display","block");
        $(".error-msg-name").html("请输入用户名！");
        return;
	}
    if(pwd==null||pwd==''){
        layer.close(a);
        $(".error-msg-pwd").css("display","block");
        $(".error-msg-pwd").html("请输入密码！");
        return;
    }
    $.ajax(
        {
            url:path+"teacher/login.action",
            type:"POST",
            dataType:"json",
            data:{"teacherAccount":username,"teacherPwd":pwd},
            success:function(data){
                layer.close(a);
                var state = data.state;
                if(state==0){
                    layer.msg(data.msg,{time:400},function(){
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

function goLogin() {
    layer.open({
        type: 2,
        title: "学生登录",
        closeBtn: 1, //不显示关闭按钮
        shade: [0.5],
        area: ['340px', '300px'],
        /*offset: 'rb', //右下角弹出*/
        /* time: 2000, //2秒后自动关闭*/
        anim: 2,
        content: [path+'reception/login.jsp', 'no'], //iframe的url，no代表不显示滚动条
        end: function(){ //此处用于演示
            location.reload();
        }
    });
}
function studentLogin() {
    var a = layer.load();
    var username = $("#username").val();
    var pwd = $("#pwd").val();
    $.ajax(
        {
            url:path+"student/login.action",
            type:"POST",
            dataType:"json",
            data:{"studentAccount":username,"studentPwd":pwd},
            success:function(data){
                layer.close(a);
                var state = data.state;
                if(state==0){
                    layer.msg(data.msg,{time:400},function(){
                        parent.window.layer.closeAll();
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

function goRegister() {
    layer.open({
        type: 2,
        title: "学生注册",
        closeBtn: 1, //不显示关闭按钮
        shade: [0.5],
        area: ['400px', '420px'],
        /*offset: 'rb', //右下角弹出*/
        /* time: 2000, //2秒后自动关闭*/
        anim: 2,
        content: [path+'student/preAddStudent.action', 'no'], //iframe的url，no代表不显示滚动条
        end: function(){ //此处用于演示
            location.reload();
        }
    });
}
function register() {
    var a = layer.load();
    var studentName = $("#validateName").val();
    var studentAccount = $("#account").val();
    var studentPwd = $("#pwd").val();
    var classId = $("#classId").val();
    if(studentName==null||studentName==''){
        layer.close(a);
        layer.msg('学生的名字不能为空');
        return;
    }
    if(studentAccount==null||studentAccount==''){
        layer.close(a);
        layer.msg('学生的账号不能为空');
        return;
    }
    if(studentPwd==null||studentPwd==''){
        layer.close(a);
        layer.msg('账号密码不能为空');
        return;
    }
    $.ajax(
        {
            url:path+"student/insertStudent.action",
            type:"POST",
            dataType:"json",
            data:{"studentName":studentName,"studentAccount":studentAccount,
                    "studentPwd":studentPwd,"classId":classId},
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

/*
$(function () {
    $("#adminLogin").Validform({
        tiptype:function (msg,o,data) {
            if(o.type==3){
                layer.msg(msg,function () {

                })
                return false;
            }
        }


    })
});*/
