function toaddSubject() {
    layer.open({
        type: 2,
        title: "添加试题",
        closeBtn: 1, //不显示关闭按钮
        shade: [0.5],
        area: ['100%', '100%'],
        /*offset: 'rb', //右下角弹出*/
        /* time: 2000, //2秒后自动关闭*/
        anim: 2,
        content: [path+'subject/preAddSubject.action', 'yes'], //iframe的url，no代表不显示滚动条
        end: function(){ //此处用于演示
            location.reload();
        }
    });
}

function addSubject() {
    var a = layer.load();
    var subjectName = $("#radioSubject-subject-subjectName").val();
    var subjectType = $("#subjectType-hidden-value").val();
    var optionA = $("#radioSubject-subject-optionA").val();
    var optionB = $("#radioSubject-subject-optionB").val();
    var optionC = $("#radioSubject-subject-optionC").val();
    var optionD = $("#radioSubject-subject-optionD").val();
    var rightResult = $("input[type='radio']:checked").val();
    var subjectEasy = $("#subjectEasy").val();
    var division = $("#division").val();
    var courseId = $("#courseId").val();
    var gradeId = $("#gradeId").val();
    var subjectScore = $("#subjectScore").val();
    if(subjectName==null||subjectName==''){
        layer.close(a);
        layer.msg('题目不能为空');
        return;
    }
    $.ajax(
        {
            url:path+"subject/insertSubject.action",
            type:"POST",
            dataType:"json",
            data:{"subjectName":subjectName,"optionA":optionA,"optionB":optionB,"optionC":optionC,
                "optionD":optionD,"rightResult":rightResult,"subjectScore":subjectScore,"subjectType":subjectType,
                "courseId":courseId,"gradeId":gradeId,"subjectEasy":subjectEasy,"division":division},
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

function toupdateSubject(subjectId) {
    layer.open({
        type: 2,
        title: "修改试卷",
        closeBtn: 1, //不显示关闭按钮
        shade: [0.5],
        area: ['100%', '100%'],
        /*offset: 'rb', //右下角弹出*/
        /* time: 2000, //2秒后自动关闭*/
        anim: 2,
        content: [path+'subject/getSubjectById.action?subjectId='+subjectId, 'yes'], //iframe的url，no代表不显示滚动条
        end: function(){ //此处用于演示
            location.reload();
        }
    });
}
function updateSubject() {
    var subjectType = $("#subjectType-hidden-value").val();
    var a = layer.load();
    if(subjectType == '0') {
        var subjectId = $("#subjectId").val();
        var subjectName = $("#radioSubject-subject-subjectName").val();
        var subjectType = $("#subjectType-hidden-value").val();
        var optionA = $("#radioSubject-subject-optionA").val();
        var optionB = $("#radioSubject-subject-optionB").val();
        var optionC = $("#radioSubject-subject-optionC").val();
        var optionD = $("#radioSubject-subject-optionD").val();
        var rightResult = $("input[type='radio']:checked").val();
        var subjectEasy = $("#subjectEasy").val();
        var division = $("#division").val();
        var courseId = $("#courseId").val();
        var gradeId = $("#gradeId").val();
        var subjectScore = $("#subjectScore").val();
        if(subjectName==null||subjectName==""){
            layer.close(a);
            alert("subjectName=null");
            return ;
        }
    }else if(subjectType == '1'){
        obj = document.getElementsByName("rightResult");
        check_val = [];
        for(k in obj){
            if(obj[k].checked)
                check_val.push(obj[k].value);
        }
        if(check_val.length == 2){
            var rightResult= check_val[0]+check_val[1];
        }else if(check_val.length == 3){
            var rightResult= check_val[0]+check_val[1]+check_val[2];
        }else if(check_val.length == 4){
            var rightResult= check_val[0]+check_val[1]+check_val[2]+check_val[3];
        }
        var subjectId =$("#subjectId").val();
        var subjectName = $("#checkSubject-subject-subjectName").val();
        var optionA = $("#checkSubject-subject-optionA").val();
        var optionB = $("#checkSubject-subject-optionB").val();
        var optionC = $("#checkSubject-subject-optionC").val();
        var optionD = $("#checkSubject-subject-optionD").val();
        var subjectScore = $("#subjectScore").val();
        var subjectType = $("#subjectType-hidden-value").val();
        var courseId = $("#courseId").val();
        var gradeId = $("#gradeId").val();
        var subjectEasy = $("#subjectEasy").val();
        var division = $("#division").val();
        if(subjectName==null||subjectName==""){
            layer.close(a);
            alert("subjectName=null");
            return ;
        }
    }else if(subjectType == '2') {
        var subjectId = $("#subjectId").val();
        var subjectName = $("#answerSubject-subject-subjectName").val();
        var rightResult = $("#answerSubject-subject-answer").val();
        var subjectType = $("#subjectType-hidden-value").val();
        var subjectScore = $("#subjectScore").val();
        var courseId = $("#courseId").val();
        var gradeId = $("#gradeId").val();
        var subjectEasy = $("#subjectEasy").val();
        var division = $("#division").val();
        if (subjectName == null || subjectName == "") {
            layer.close(a);
            alert("subjectName=null");
            return;
        }
    }
    $.ajax(
        {
            url:path+"subject/updateSubject.action",
            type:"POST",
            data:{"subjectName":subjectName,"subjectType":subjectType,"optionA":optionA,"optionB":optionB,
                "optionC":optionC,"optionD":optionD,"rightResult":rightResult,"subjectEasy":subjectEasy,
                "division":division,"courseId":courseId,"gradeId":gradeId,"subjectScore":subjectScore,"subjectId":subjectId},
            success:function(data){
                layer.close(a);
                if(data.state==0){
                    layer.msg(data.msg,function () {
                        layer.closeAll();
                        parent.window.layer.closeAll();
                        /*$(".layui-layer-close1").click();*/
                    });
                }else{
                    layer.msg(data.msg,function () {

                    });
                }
            }
        }
    );
}


function deleteSubject(subjectId) {
    layer.confirm('是否确定要删除？', {
        btn: ['是','否'] //按钮
    }, function(){
        var a = layer.load();
        $.ajax(
            {
                url:path+"subject/deleteSubject.action",
                type:"POST",
                dataType:"json",
                data:{"subjectId":subjectId},
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