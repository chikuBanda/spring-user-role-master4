<%--
  Created by IntelliJ IDEA.
  User: binizmohamed
  Date: 4/6/20
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Blog</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" />
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <style>
        #space{
            margin: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <jsp:directive.include file="../layout/header.jsp" />
    <header class="col-lg-12">
        <h1>Tous les Utilisateurs du Blog</h1>
        <div class="col-lg-12">
            <a href="${pageContext.request.contextPath}/admin/user/add" id="space" class="btn btn-primary">Ajouter un utilisateur</a>
            <br/>
        </div>
        <table class="table table-bordered">
            <tr>
                <th>Id</th>
                <th>Nom Complet</th>
                <th>email</th>
                <th>Mot de passe</th>
                <th>roles</th>
                <th>Action</th>

            </tr>
            <c:forEach items="${pageable.content}" var="item">
                <tr>
                    <td>${item.id}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/user/view/${item.id}">
                                ${item.name}</a></td>
                    <td>${item.email}</td>
                    <td>${item.password}</td>

                    <td>
                        <ul>
                             <c:forEach items="${item.listRole}" var="role">
                                 <li>
                                    role: ${role.name}
                                 </li>
                             </c:forEach>
                        </ul>
                    </td>

                    <td>
                        <a href="${pageContext.request.contextPath}/admin/user/delete/${pageable.number}/${item.id}" class="btn btn-danger"
                           onclick="if (!(confirm('Vous Voulez supprimer cet element ?'))) return false">Supprimer</a>
                        <a href="${pageContext.request.contextPath}/admin/user/add/${item.id}" class="btn btn-success">Modifier</a>
                    </td>
                </tr>

            </c:forEach>
        </table>




    </header>

</div>

</body>
</html>