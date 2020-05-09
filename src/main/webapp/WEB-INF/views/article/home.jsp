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
</head>
<body>
<div class="container">
    <jsp:directive.include file="../layout/header.jsp" />
 <header class="col-lg-12">
    <h1>Tous les articles du Blog</h1>
     <c:if test = "${(sessionScope.currentRole.name == 'admin') || (sessionScope.currentRole.name == 'writer')}">
        <div class="col-lg-12">
            <a href="${pageContext.request.contextPath}/${sessionScope.currentRole.name}/article/add" class="btn btn-primary">A
                jouter Article</a>
            <br/>
        </div>
      </c:if>
 </header>

 <ul class="nav nav-tabs">
     <li class="active"><a data-toggle="tab" href="#tous">Tous</a></li>
     <c:if test = "${(sessionScope.currentRole.name == 'admin') || (sessionScope.currentRole.name == 'writer')}">
        <li><a data-toggle="tab" href="#mes_articles">Mes articles</a></li>
     </c:if>
 </ul>

 <div class="tab-content">
    <div id="tous" class="tab-pane fade in active">
      <table class="table table-bordered">
         <tr>
             <th>Id</th>
             <th>Title</th>
             <th>created</th>

             <c:if test = "${(sessionScope.currentRole.name == 'admin')}">
                 <th>Action</th>
             </c:if>

         </tr>
         <c:forEach items="${pageable.content}" var="item">
             <tr>
                 <td>${item.id}</td>
                 <td>
                     <a href="${pageContext.request.contextPath}/article/view/${item.id}">
                             ${item.title}</a></td>
                 <td><fmt:formatDate type = "date" value = "${article.created}" /> </td>

                 <c:if test = "${(sessionScope.currentRole.name == 'admin')}">
                     <td>
                         <a href="${pageContext.request.contextPath}/${sessionScope.currentRole.name}/article/delete/${pageable.number}/${item.id}"
                            class="btn btn-danger"
                            onclick="if (!(confirm('Vous Voulez supprimer cet elementr?'))) return false">Supprimer</a>
                         <a href="${pageContext.request.contextPath}/${sessionScope.currentRole.name}/article/add/${item.id}"
                            class="btn btn-success">Modifier</a>
                     </td
                 </c:if>
             </tr>

         </c:forEach>
         </table>
      </div>

     <div id="mes_articles" class="tab-pane fade">
       <table class="table table-bordered">
           <tr>
               <th>Id</th>
               <th>Title</th>
               <th>created</th>
               <th>Action</th>

           </tr>
           <c:forEach items="${pageable.content}" var="item">
               <c:if test = "${sessionScope.user.id == item.user.id}">
                   <tr>
                       <td>${item.id}</td>
                       <td>
                           <a href="${pageContext.request.contextPath}/article/view/${item.id}">
                                   ${item.title}</a></td>
                       <td><fmt:formatDate type = "date" value = "${article.created}" /> </td>
                       <td>
                           <a href="${pageContext.request.contextPath}/${sessionScope.currentRole.name}/article/delete/${pageable.number}/${item.id}"
                              class="btn btn-danger"
                              onclick="if (!(confirm('Vous Voulez supprimer cet elementr?'))) return false">Supprimer</a>
                           <a href="${pageContext.request.contextPath}/${sessionScope.currentRole.name}/article/add/${item.id}"
                              class="btn btn-success">Modifier</a>
                       </td>
                   </tr>
               </c:if>
           </c:forEach>
       </table>
     </div>
 </div>

 <nav aria-label="Page navigation example">
    <ul class="pagination">
        <c:choose>
            <c:when test="${pageable.number !=0 }">
                <li class="page-item">
                    <a class="page-link"
                       href="${pageContext.request.contextPath}/article/page/${pageable.number-1 }">
                        Previous
                    </a></li>
            </c:when>
        </c:choose>
        <c:forEach begin="0"   end="${pageable.totalPages -1}" var="i">
            <c:choose>
                <c:when test="${pageable.number ==i }">
                    <li class="page-item disabled"><a class="page-link" href="${pageContext.request.contextPath}/article/page/${i}">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/article/page/${i}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${pageable.number <pageable.totalPages-1 }">
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/article/page/${pageable.number+1 }">Next</a></li>
            </c:when>
        </c:choose>
    </ul>
 </nav>

</div>

</body>
</html>