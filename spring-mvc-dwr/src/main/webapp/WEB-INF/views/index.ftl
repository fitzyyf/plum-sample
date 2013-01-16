<#import '/spring.ftl' as s/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <script type="text/javascript" src="<@s.url '/resources/scripts/jquery/jquery-min.js'/>"></script>
    <script type='text/javascript' src="<@s.url '/dwr/engine.js'/>"></script>
    <script type='text/javascript' src="<@s.url '/dwr/util.js'/>"></script>
    <script type="text/javascript" src="<@s.url '/dwr/interface/logReadDwr.js'/>"></script>
    <script type="text/javascript" src="<@s.url '/dwr/interface/readlog.js'/>"></script>

    <title>Spring MVC - DWR 示例</title>
</head>
<body>
<h3>Spring MVC - DWR 示例</h3>
<h4>Ajax version</h4>
示例1:

<div style="border: 1px solid #ccc; width: 100%;">


    <button id="begin">开始</button><button id="end">取消</button>
	读取日志文件${log_name}.txt
    <div id="showContent" style="width:100%">

    </div>
</div>
<script type="text/javascript">

    var log_name = '${log_name}';

    $(document).ready(function () {
        dwr.engine.setActiveReverseAjax(true);
        $('#begin').click(function () {
            readlog.toggle(log_name)
        });
        $('#end').click(function () {
            readlog.stop(log_name)
        });
    });


    //反向Ajax回调函数
    function showContent(data) {
        $('#showContent').append(data);
    }

    //错误提示消除
    dwr.engine._errorHandler = function (message, ex) {
        dwr.engine._debug("Error: " + ex.name + ", " + ex.message, true);
    };
</script>
</body>
</html>