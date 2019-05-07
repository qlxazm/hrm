$(function(){
   var sideBarLevelOne = $(".menu > li:first-child");   //侧边栏1级别菜单
    var sideBarLevelTwo = $(".sub_menu a"); //侧边栏2级别菜单

    sideBarLevelOne.click(function(event){
        var sideBarLevelTwo = $(this).siblings(); //紧跟在点击菜单之后的子菜单
        var height = sideBarLevelTwo.css("height");
        height = parseInt(height) === 0 ? "auto" : "0px";
        sideBarLevelTwo.css("height", height);
        $(this).children("a").toggleClass("menu_active");
    });

    sideBarLevelTwo.click(function(event){
        sideBarLevelTwo.removeClass("sub_menu_active");
        $(this).addClass("sub_menu_active");
    });
});