$(function(){
    var selectAllCheckbox = $('.selectAll');       //全选
    var allCheckbox = $("input[name='category']"); //每条记录的选择框
    var selectCheckboxItems = Object.create(null); //选中的选择框
    var batchDelete = $('.batchDelete');           //批量删除
    var deleteIdsInput = $('.delete_ids');
    var deleteUserBtn = $('.delete_btn');     //隐藏的删除按钮
    var deleteUser = $('.delete');                 //删除单行用户


    selectAllCheckbox.change(function(event){
        var ischecked = this.checked;
        if (!selectCheckboxItems) {
            selectCheckboxItems = Object.create(null);
        }
        for (var i = 0, len = allCheckbox.length; i < len; i++){
            if (allCheckbox[i].disabled){
                continue;
            }
            var id = $(allCheckbox[i]).attr('data-id');
            allCheckbox[i].checked = ischecked;
            selectCheckboxItems[id] = ischecked;
        }
    });

    allCheckbox.on('change', function (event) {
        var id = $(this).attr('data-id');
        if (!selectCheckboxItems) {
            selectCheckboxItems = Object.create(null)
        }
        selectCheckboxItems[id] = this.checked;
    });

    batchDelete.click(function (){
        var deleteIds = [];
        var keys = Object.keys(selectCheckboxItems);
        var i = 0, len = keys.length, key = '';
        for (;i < len; i++){
            key = keys[i];
            if (selectCheckboxItems[key]) {
                deleteIds.push(key);
            }
        }
        if (!deleteIds.length){
            return;
        }
        deleteIds = deleteIds.join(',');
        deleteIdsInput.val(deleteIds);
        /** 提交要删除的id **/
        deleteUserBtn.click();
    });

});