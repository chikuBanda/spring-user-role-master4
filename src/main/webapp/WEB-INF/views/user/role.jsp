<%--
  Created by IntelliJ IDEA.
  User: msi
  Date: 2020-04-21
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Authetification</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" />
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <style>
        #global{
            margin-left: 35%;
            margin-right: 35%;
            margin-top: 5%;
        }
    </style>
</head>
<body>
<div id="global">
    <h2>role</h2>
<form:form method="post" action="${pageContext.request.contextPath}/select_roles" modelAttribute="role" >
    <c:forEach items="${roleList}" var="roleItem" varStatus="loop">
           <c:if test = "${loop.index == 0}">
               <div class="form-check">
                    <form:radiobutton
                          path="name"
                          cssClass="form-check-input"
                          id="${roleItem.id}"
                          value="${roleItem.name}" />

                     <label class="form-check-label" for="${roleItem.id}">
                       ${roleItem.name}
                     </label>

                     <form:errors path="name" cssClass="alert-danger" />
               </div>
           </c:if>
           <c:if test = "${loop.index != 0}">
              <div class="form-check">
                   <form:radiobutton
                         path="name"
                         cssClass="form-check-input"
                         id="${roleItem.id}"
                         value="${roleItem.name}"
                         checked="checked"
                          />

                    <label class="form-check-label" for="${roleItem.id}">
                      ${roleItem.name}
                    </label>

                    <form:errors path="name" cssClass="alert-danger" />
              </div>
          </c:if>
     </c:forEach>
    <input class="btn btn-primary" type = "submit" value = "Continue"/>
</form:form>

</div>
</body>
</html>
