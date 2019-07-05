function toaddExamPaper() {
    layer.open({
        type: 2,
        title: "添加试卷",
        closeBtn: 1, //不显示关闭按钮
        shade: [0.5],
        area: ['90%', '90%'],
        /*offset: 'rb', //右下角弹出*/
        /* time: 2000, //2秒后自动关闭*/
        anim: 2,
        content: [path + 'examPaper/preAddExamPaper.action', 'no'], //iframe的url，no代表不显示滚动条
        end: function () { //此处用于演示
            location.reload();
        }
    });
}
function addExamPaper() {
    var a = layer.load();
    var division = $("#division").val();
    var examPaperEasy = $("#examPaperEasy").val();
    var gradeId = $("#gradeId").val();
    var examPaperName = $("#examPaperName").val();
    var subjectNum = $("#subjectNum").val();
    var examPaperScore = $("#examPaperScore").val();
    var examPaperTime = $("#examPaperTime").val();
    if(examPaperName==null||examPaperName==''){
        layer.close(a);
        layer.msg('试卷的名字不能为空');
        return;
    }
    $.ajax(
        {
            url:path+"examPaper/insertExamPaper.action",
            type:"POST",
            dataType:"json",
            data:{"division":division,"examPaperEasy":examPaperEasy,"gradeId":gradeId,"examPaperName":examPaperName,
                "subjectNum":subjectNum,"examPaperScore":examPaperScore,"examPaperTime":examPaperTime},
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

function toupdateExamPaper(examPaperId) {
    layer.open({
        type: 2,
        title: "修改试卷",
        closeBtn: 1, //不显示关闭按钮
        shade: [0.5],
        area: ['90%', '90%'],
        /*offset: 'rb', //右下角弹出*/
        /* time: 2000, //2秒后自动关闭*/
        anim: 2,
        content: [path+'examPaper/getExamPaperById.action?examPaperId='+examPaperId, 'no'], //iframe的url，no代表不显示滚动条
        end: function(){ //此处用于演示
            location.reload();
        }
    });
}
function updateExamPaper() {
    var a = layer.load();
    var division = $("#division").val();
    var examPaperEasy = $("#examPaperEasy").val();
    var gradeId = $("#gradeId").val();
    var examPaperName = $("#examPaperName").val();
    var subjectNum = $("#subjectNum").val();
    var examPaperScore = $("#examPaperScore").val();
    var examPaperTime = $("#examPaperTime").val();
    var examPaperId = $("#examPaperId").val();
    if(examPaperName==null||examPaperName==''){
        layer.close(a);
        layer.msg('试卷的名字不能为空');
        return;
    }
    $.ajax(
        {
            url:path+"examPaper/updateExamPaper.action",
            type:"POST",
            dataType:"json",
            data:{"division":division,"examPaperEasy":examPaperEasy,"gradeId":gradeId,"examPaperName":examPaperName,
                "subjectNum":subjectNum,"examPaperScore":examPaperScore,"examPaperTime":examPaperTime,
                "examPaperId":examPaperId},
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

function deleteExamPaper(examPaperId) {
    layer.confirm('是否确定要删除？', {
        btn: ['是','否'] //按钮
    }, function(){
        var a = layer.load();
        $.ajax(
            {
                url:path+"examPaper/deleteExamPaper.action",
                type:"POST",
                dataType:"json",
                data:{"examPaperId":examPaperId},
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

//手动添加试题到试卷中
function examPaperAddSubjects(examPaperId) {
    zeroModal.show({
        title : '添加试题-手动',
        iframe : true,
        url : path+'examSubjectMiddle/selectSubjectAll.action?handAdd=1&examPaperId='+examPaperId,
        width : '90%',
        height : '90%',
        cancel : true,
        top : '0px',
        left : '0px',
        esc : true,
        overlay : true,
        overlayClose : true,
        // 关闭后回调刷新数据
        onClosed : function() {
            $("form input").attr("disabled", "disabled");
            $("form").attr("method", "get");
            $("form").attr("action", path+"examPaper/examPaperList.action").submit();
        }
    });
}

/*试题添加处理，手动组卷*/
$(function() {
    $("input:checkbox").click(function() {
        //试题编号
        var checked = $(this).attr("id");
        //试题对应的分数
        var score = $(this).parent().siblings("#subjectScore").text();

        //维持已选试题数量
        var choosed = parseInt($("#choosed").text());
        if(this.checked) {
            $("#choosed").text(choosed+1);
        } else {
            $("#choosed").text(choosed-1);
        }

        //获取试卷编号
        var examPaperId = $("#examPaperId").text();
        if(examPaperId == null || examPaperId.trim() == "") {
            alert("无法获取试卷信息，暂时无法添加！");
            return false;
        }

        $.ajax({
            type: "POST",
            data: "subjectId="+checked+"&score="+score+"&examPaperId="+examPaperId,
            url: path+"examSubjectMiddle/getChooseSubId.action",
            success: function(data) {
                if(data.trim().indexOf("f-exists-") != -1) {
                    zeroModal.show({
                        title : "错误的提交",
                        content : "此试题已经存在该试卷中了!",
                        width : '200px',
                        height : '130px',
                        overlay : false,
                        ok : true,
                        onClosed : function() {
                            //刷新当前页面
                            //location.reload();
                        }
                    });
                    var choosed = parseInt($("#choosed").text());
                    $("#choosed").text(choosed-1);
                    //截取题号
                    var subjectId = data.substring(data.lastIndexOf("-")+1);
                    //移除选择
                    $("#"+subjectId).replaceWith("<span style='color:red;font-size:12px;'>exists</span>");
                    return false;
                }
            },
            error: function(data) {
                alert("提交失败");
            }
        });
    });


    //分页跳转到指定页码
    $("#scannerPageForm").submit(function() {
        //获取页码
        var page = $("#scannerPage").val();
        if(isNaN(page) || page.trim() == "" || page.trim() == null || page<1) {
            $("#scannerPage").val("");
            return false;
        }
        return true;
    });
});


//添加试题集合到指定试卷中
$("#isAddHandle").click(function() {
    //获取试卷编号
    var examPaperId = $("#examPaperId").text();
    if(examPaperId == null || examPaperId.trim() == "") {
        alert("无法获取试卷信息，暂时无法添加！");
        return false;
    }
    $.ajax({
        type: "POST",
        data: "examPaperId="+examPaperId,
        url: path+"examSubjectMiddle/handAdd.action",
        success: function() {
            alert("添加成功 !");
        }
    });
});


//跳转自动添加试题
function autoAddSubjects(examPaperId) {
    zeroModal.show({
        title : '自动生成试题',
        iframe : true,
        url : path+'admin/autoSubjectSelect.jsp?examPaperId='+examPaperId,
        width : '30%',
        height : '80%',
        top : '30px',
        left : '430px',
        overlay : true,
        ok: true,
        onClosed : function() {
            /* location.href = "examPapers";*/
        }
    });
}

//生成试题
$(function() {
    /*   $('.selectpicker').selectpicker({
           style: 'btn-default',
           size: 8
       });*/

    $("#handlesubmit").click(function() {
        var subjectNum=$('#subjectNum').val();
        var courseId=$('#courseId').val();
        var subjectEasy=$('#subjectEasy').val();
        var gradeId=$('#gradeId').val();
        var examPaperId=$('#examPaperId').val();
        $.ajax({
            type : 'POST',
            data : {"num":subjectNum,
                "courseId":courseId,
                "subjectEasy":subjectEasy,
                "gradeId":gradeId,
                "examPaperId":examPaperId
            },
            url : path+"examSubjectMiddle/autoAddSubject.action",
            success : function(t) {
                zeroModal.show({
                    title : "操作成功",
                    content : "自动添加试题成功!",
                    width : '200px',
                    height : '130px',
                    overlay : false,
                    ok : true
                });
                /* if (result.get("state")=="1"){
                     layer.msg(result.get("msg"));
                     parent.reload();
                     result ;
                 }else{
                     layer.msg(result.get("msg"));
                     parent.reload();
                     result ;
                 }*/
            },
            error : function() {
                alert("也许没有满足条件的试题, 操作失败!");
            }
        });
    });
});
