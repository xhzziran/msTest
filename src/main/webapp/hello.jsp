<%--
  Created by IntelliJ IDEA.
  User: boss-bian
  Date: 2018/8/16
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>主页</title>
    <script type="text/javascript" src="/statics/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="/statics/js/jquery.pagination.js"></script>
    <link rel="stylesheet" href="/statics/js/bootstrap.min.css">
</head>
<body>

欢迎，${loginUser.userName}&nbsp;<a href="/user/exit"  onclick="return confirm('关闭窗口')">退出系统</a>
<a href="javascript:void(0);" onclick="show(1);">添加销售</a>
<a href="javascript:void(0);" onclick="detail()">查询销售记录</a>
<a href="javascript:void(0);">库存</a>

<div>

    <table id="table1" style="display: none">
        <tr><td><h2>添加商品</h2></td></tr>
        <tr><td>商品名称:</td><td>
            <select id="goods" name="goods">0

            <option value="">请选择商品信息</option>
            <option value="1">奶粉</option>
            <option value="2">纸尿布</option>
            <option value="3">奶瓶</option>
            <option value="4">衣服</option>

        </select></td></tr>

        <tr><td>销售单价:</td><td><input id="price" type="text" name="price"></td></tr>
        <tr><td>销售数量:</td><td><input id="quantity" type="text" name="quantity"></td></tr>
        <tr><td><button onclick="sale();">保存</button></td></tr>

    </table>

    <div id="detail" style="display: none">

        <tr><td>查询销售记录:</td><td colspan="2"><select id="saleDetail" name="saleDetail">

            <option value="saleDate" selected>日期</option>
            <option value="totalPrice">单笔总价</option>

        </select></td><td><button onclick="saleDetail(0)">提交</button></td></tr>

        <table id="table2" border="1">


        </table>
        <div id="pagination" class="pagination"></div>
    </div>

</div>



<script type="text/javascript">

    function show(index) {

        $("#table"+index).siblings().hide();
        $("#table"+index).show();
    }
    function sale() {

        var bool = true;
        if($("#goods").val()==""){
            alert("名称必填")
            bool=false;
        }
        if($("#price").val()==""){
            alert("价格必填")
            bool=false;
        }else if (isNaN($("#price").val())){
            alert("价格必须是数字");
            bool=false;

        }
        if($("#quantity").val()==""){
            alert("商品数量必填")
            bool=false;
        }else if (isNaN($("#quantity").val())){
            alert("商品数量必须是数字");
            bool=false;
        }

        var productId=$("#goods").val();
        var price = $("#price").val();
        var quantity = $("#quantity").val();

        if(bool){
            var productId = $("#goods").val();

            $.ajax({
                type:"POST",
                url:"/user/addSale",
                data:{"productId":productId,"price":price,"quantity":quantity},
                dataType:"json",
                success:function(data){
                    var str = data.indicate;
                    alert(str);

                }
            });
        }

    }


    function detail() {

        $("#detail").siblings().hide();
        $("#detail").show();
        saleDetail(0);

    }

    function saleDetail(pageNum) {
        pageNum=pageNum+1;

        var submit = $("#saleDetail").val();
        $.ajax({
            type:"get",
            url:"/user/saleDetail",
            data:{"pageNum":pageNum,"pageSize":3,"obj":submit},
            dataType:"JSON",
            success:function (data) {

                var htmls = [];
                $.each(data.list,function(i,item){

                    htmls[i]="<tr><td>"+item.id+"</td><td>"+item.product+"</td><td>"+item.price+
                        "</td><td>"+item.quantity+"</td><td>"+item.totalPrice+
                        "</td><td>"+item.saleDate+"</td><td>"+item.salesman+"</td></tr>";

                });

                $("#table2").html(htmls);
                var num = data.pageNum-1;
                $("#pagination").pagination(data.total,{
                    current_page:num,
                    items_per_page:data.pageSize,
                    prev_text:"上一页",
                    next_text:"下一页",
                    callback:saleDetail

                });

            }
        });
    }


</script>

</body>
</html>
