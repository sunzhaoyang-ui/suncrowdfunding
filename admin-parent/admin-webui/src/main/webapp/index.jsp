<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Title</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <script type="text/javascript">
        $(function () {

            $("#btn1").click(function () {
                $.ajax({
                    "url": "send/array1",
                    "type": "post",
                    "data": {
                        "array": [5, 8, 12]
                    },
                    "dataType": "text",
                    "success": function (response) {
                        alert(response);
                    },
                    "error": function (response) {
                        alert(response);
                    }
                });
            });

            $("#btn2").click(function () {
                $.ajax({
                    "url": "send/array2",
                    "type": "post",
                    "data": {
                        "array[0]": 5,
                        "array[1]": 8,
                        "array[2]": 12
                    },
                    "dataType": "text",
                    "success": function (response) {
                        alert(response);
                    },
                    "error": function (response) {
                        alert(response);
                    }
                });
            });

            $("#btn3").click(function () {

                var array = [5,8,12];

                var requestBody = JSON.stringify(array);

                $.ajax({
                    "url": "send/array3",
                    "type": "post",
                    "data": requestBody,
                    "contentType":"application/json;charset=utf-8",
                    "dataType": "text",
                    "success": function (response) {
                        alert(response);
                    },
                    "error": function (response) {
                        alert(response);
                    }
                });
            });

            $("#btn4").click(function () {

                var student = {
                    stuId: 5,
                    stuName: "tom",
                    address: {
                        province: "江苏",
                        city: "南京",
                        street: "秣陵街道"
                    },
                    subjectList: [
                        {
                            subName: "java",
                            subScore: 100
                        },
                        {
                            subName: "c++",
                            subScore: 98
                        }
                    ],
                    map:{
                        key1: "value1",
                        key2: "value2"
                    }
                };

                var requestBody = JSON.stringify(student);

                $.ajax({
                    "url": "send/compose",
                    "type": "post",
                    "data": requestBody,
                    "contentType":"application/json;charset=utf-8",
                    "dataType": "json",
                    "success": function (response) {
                        console.log(response);
                    },
                    "error": function (response) {
                        console.log(response);
                    }
                });
            });

            $("#btn5").click(function (){
                layer.msg("layer的弹框");
            });
        });
    </script>
</head>

<body>
    <a href="test/ssm">测试SSM整合环境</a>
    <br>
    <button id="btn1">Send [5,8,12] One</button>
    <br>
    <button id="btn2">Send [5,8,12] Two</button>
    <br>
    <button id="btn3">Send [5,8,12] Three</button>
    <br>
    <button id="btn4">Send Compose</button>
    <br>
    <button id="btn5">点我弹框</button>
</body>
</html>
