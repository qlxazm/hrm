$(function () {
    var url = 'http://' + window.location.host + '/marcopolo';
    var sock = new SockJS(url);
    var stomp = Stomp.over(sock);
    var payload = JSON.stringify({message: '这是消息内容'});

    stomp.connect('guest', 'guest', function (frame) {

        stomp.subscribe("/topic/marco", function (message) {
            var content = message.body;
            // var obj = JSON.parse(content);
            console.log("订阅的服务端直接返回的消息：" + content);
        }, {});

        stomp.subscribe("/user/queue/notification", function (message) {
            var content = message.body;
            console.log('这是发送给指定用户的消息：' + content)
        })

        // 发送信息
        // stomp.send("/app/marco", {}, payload);
    });
});