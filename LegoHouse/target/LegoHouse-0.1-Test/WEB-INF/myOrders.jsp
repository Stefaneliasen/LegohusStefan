<%-- 
    Document   : myOrders
    Created on : 16-10-2017, 13:40:51
    Author     : Arne
--%>

<%@page import="entity.LegoWall"%>
<%@page import="entity.LegoHouse"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% LegoHouse legohouse = new LegoHouse(); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <title>Cupcake Shop</title>
</head>
<body>
    <form id="addProduct" action="FrontController" method="POST">
        <input type="hidden" name="command" value="myOrders">
        <table class="table table-striped">
            <tr>
                <th> Order Id  |</th>
                <th> User Id  | </th>
                <th> Quantity | </th>
                <th> Legoblock Name |</th>
            </tr>
            <% LegoHouse orderline = (LegoHouse) session.getAttribute("legohouse");
                if (legohouse.getLegoWalls() != null) {
                    for (int i = 0; i < orderline.getLegoWalls().size(); i++) {
                        out.print("<tr><td>" + orderline.getOrderId() + "</td>" + "<td>" + orderline.getUserId() + "</td>" + "<td>" + orderline.getLegoWalls().get(i).getLegoblockAmount() + "</td>" + "<td>" + orderline.getLegoWalls().get(i).getLegoblock().getName() + "</td>" + "</tr>");
                    }
                }
            %>
            </tbody>
        </table>
    </form>
<body>
</body>
</html>
