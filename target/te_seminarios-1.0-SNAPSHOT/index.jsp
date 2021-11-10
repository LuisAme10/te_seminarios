

<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Eventos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    List<Eventos>lista= (List<Eventos>)request.getAttribute("lista");
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>listado de eventos</h1>
        
        <p>
            <a href="MainController?op=nuevo">Nuevo</a>
        </p>
        
        <table border = "1">
            <tr>
                <th>Id</th>
                <th>Titulo</th>
                <th>Expositor</th>
                <th>fecha</th>
                <th>horas</th>
                <th>cupos</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${lista}">
            <tr>
                <td>${item.id}</td>
                <td>${item.titulo}</td>
                <td>${item.expositor}</td>
                <td>${item.fecha}</td>
                <td>${item.horas}</td>
                <td>${item.cupos}</td>
                <td><a href="MainController?op=editar&id=${item.id}">Editar</a></td>
                <td><a href="MainController?op=eliminar&id=${item.id}"
                       onclick="return(confirm('estas seguro de eliminar ?'))">Eliminar</a></td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
