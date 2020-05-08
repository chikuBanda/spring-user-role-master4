<%--
  Created by IntelliJ IDEA.
  User: binizmohamed
  Date: 4/6/20
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Modifier utilisateur</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" />
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</head>
<body>

<div class="container">
    <jsp:directive.include file="../layout/header.jsp" />
    <header class="col-lg-12">
        <form:errors path="user" cssClass="text-danger" />
        <form:form method="post" action="${pageContext.request.contextPath}/user/edit/save" modelAttribute="user" >
            <form:input path="id" type="hidden" />
            <div class="form-group">
                <label for="name">Nom Complet</label>
                <form:input path="name" cssClass="form-control" value="${currentUser.name}" placeholder="Nom complet" />
                <form:errors path="name" cssClass="alert-danger" />
            </div>
            <div class="form-group">
                <label for="email">email</label>
                <form:input path="email" cssClass="form-control" value="${currentUser.email}" placeholder="email" />
                <form:errors path="email" cssClass="alert-danger" />
            </div>
            <div class="form-group">
                <label for="password">Mot de passe </label>
                <form:input path="password" cssClass="form-control" type="password" value="${currentUser.password}" placeholder="mot de passe " />
                <form:errors path="password" cssClass="alert-danger" />
            </div>
            <div class="form-group">
                <label for="confirmedPassword">Confirmer le mot de passe</label>
                <form:input path="confirmedPassword" cssClass="form-control" type="password" value="${currentUser.confirmedPassword}" placeholder=" confirmer le mot de passe" />
                <form:errors path="confirmedPassword" cssClass="alert-danger" />
            </div>

            <div class="form-check">
                <label class="form-check-label">
                    Roles
                </label>
                <br/>

                <c:forEach items="${roles}" var="role">
                    <c:choose>
                        <c:when test="${role.used}">
                            <form:checkbox path="listRole" value="${role.id}" label="${ role.name }" checked="checked" />
                            <br/>
                        </c:when>
                        <c:otherwise>
                            <form:checkbox path="listRole" value="${role.id}" label="${ role.name }" />
                            <br/>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <form:errors path="listRole" cssClass="alert-danger" />
            </div>

            <input class="btn btn-primary" type = "submit" value = "Submit"/>
        </form:form>
    </header>
</div>
</body>
</html>
