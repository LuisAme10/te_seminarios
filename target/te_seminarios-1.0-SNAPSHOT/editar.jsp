
<%@page import="com.emergentes.modelo.Eventos"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    Eventos even = (Eventos) request.getAttribute("even");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
            <c:if test="${even.id==0}">
                nuevo evento
            </c:if>
            <c:if test="${even.id!=0}">
                editar evento
            </c:if>
        </h1>
                
           <form action="MainController" method="post">
            <input type="hidden" name="id" value="${even.id}">
            <table>
                <tr>    
                    <td>titulo</td>
                    <td><input type="text" name="titulo" value="${even.titulo}"></td>
                </tr>
                <tr>
                    <td>expositor</td>
                    <td><input type="text" name="expositor" value="${even.expositor}" /></td>
                </tr>
                <tr>
                    <td>fecha</td>
                    <td><input type="text" name="fecha" value="${even.fecha}" /></td>
                </tr>
                <tr>
                    <td>horas</td>
                    <td><input type="text" name="horas" value="${even.horas}" /></td>
                </tr>
                <tr>
                    <td>cupos</td>
                    <td><input type="text" name="cupos" value="${even.cupos}" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="enviar"></td>
                </tr>
            </table>
        </form>     
    </body>
</html>
