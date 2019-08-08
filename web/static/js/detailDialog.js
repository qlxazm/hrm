$(function(){
    var detailBtn = $('.detail');
    var detailDialog = $('.dialog_detail');
    var detailDialogCloser = $('.dialog_close');
    var contentDialog = $('.dialog_content');

    detailBtn.click(function(event){
        var id = $(this).attr('data-id');
        $.ajax('/employee/detailEmployee',{
            dataType: 'json',
            type: 'post',
            contentType: 'application/json',
            data: JSON.stringify({id: id}),
            aysnc: true,
            success: function(data) {
                var fragment = parseData(data);
                contentDialog.empty().append(fragment);
                detailDialog.fadeIn();
            },
            error: function(err) {
                console.log('请求员工信息失败！');
            }
        });
    });


    function parseData(data) {
        var mapper = {
                name: '姓名：',
                //dept: '部门：',
                //job: '职位：',
                sex: '性别：',
                phone: '手机：',
                email: '邮箱：',
                //birthday: '生日：',
                education: '教育背景：',
                card_id: '身份证:',
                address: '地址:',
                post_code: '邮编:',
                qq_num: 'QQ：',
                tel: '电话：',
                party: '党派：',
                race: '民族：',
                speciality: '特长：',
                hobby: '爱好：',
                remark: '描述：'
            };

        var keys = Object.keys(mapper);
        var i = 0;
        var len = keys.length;
        var key;
        var fragment = document.createDocumentFragment();
        for(; i < len; i++) {
            key = keys[i];
            var outer = $('<div/>');
            outer.addClass('field');
            var label = $('<span/>');
            label.addClass('label');
            label.text(mapper[key]);
            var info = $('<div/>');
            info.addClass('input');
            info.text(data[key]);
            outer.append(label);
            outer.append(info);
            $(fragment).append(outer);
        }
        return fragment;

  /*  <div class="field">
            <span class="label"><i style="color: red">*</i>&nbsp;&nbsp;&nbsp;姓名：</span>
        <div class="input"><form:input path="name"/></div>
            <form:errors path="name" cssClass="message"/>
            </div>*/

    }

    detailDialogCloser.click(function(event){detailDialog.fadeOut()});
});