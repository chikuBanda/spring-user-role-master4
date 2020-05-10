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
    <title>Ajouter tag</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" />
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" />
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>

    <div class="container">
        <jsp:directive.include file="../layout/header.jsp" />
        <header class="col-lg-12">
            <h1 class="page-heading">Ajouter un tag</h1>
            <form:form method="post" action="${pageContext.request.contextPath}/tag/save" modelAttribute="tag" >
                <form:input path="id" type="hidden" />
                <c:choose>
                    <c:when test="${currentRole.name == 'writer'}">
                        <div class="form-group">
                            <label for="user">Writer ID</label>
                            <form:input path="user"
                                cssClass="form-control"
                                value="${currentUser.id}"
                                placeholder="user"
                                readonly="true"
                                />
                            <form:errors path="user" cssClass="alert-danger" />
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="form-group">
                            <label class="form-check-label">
                                User
                            </label>

                            <form:select path="user"  class="form-control" id="select">
                                <c:forEach items="${users}" var="u">
                                    <c:choose>
                                       <c:when test="${tag.user.id == u.id}">
                                            <option value="${u.id}" selected="true"> ${u.name}  </option>
                                       </c:when>
                                       <c:otherwise>
                                            <option value="${u.id}"> ${u.name}  </option>
                                       </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </form:select>
                            <form:errors path="user" cssClass="alert-danger" />
                        </div>
                    </c:otherwise>
                </c:choose>

                <div class="form-group">
                    <label for="title">Titre</label>
                    <form:input path="title" cssClass="form-control"  placeholder="titre" />
                    <form:errors path="title" cssClass="alert-danger" />
                </div>
                <input type = "submit" value = "Submit"/>
            </form:form>
        </header>
    </div>
</body>
</html>
