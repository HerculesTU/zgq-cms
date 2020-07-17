$(function () {

    /*提交评论*/
    $("#submitCommentBtn").click(function () {
    	layer.msg("请输入昵称")
        if($("#nickname").val()==""){
            layer.msg("请输入昵称")
            return;
        }else if($("#comment-textarea").val()==""){
            layer.msg("说点什么吧")
            return;
        }
        $.ajax({
            url: ctx+"portal/comments/save",
            type: "post",
            dataType: "json",
            data: $("#comment-form").serialize(),
            success: function(json) {
              alert(json.msg);
                if (json.code != 0 && json.code != '0') {
                    layer.msg(json.msg);
                    return;
                }
                data = json.data;

                layer.msg(json.msg, {
                    offset: '30%',
                    time: 800
                }, function () {
                    location.reload();
                });

            }});
    })
    
})