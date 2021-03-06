<%--
  Created by IntelliJ IDEA.
  User: binizmohamed
  Date: 4/7/20
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>

<!-- Static navbar -->
<header>
  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <div id="navbar" class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
          <li class="my-nav ${navArticleActive}"><a href="${pageContext.request.contextPath}/article/">Home</a></li>
          <li class="my-nav ${navTagActive}"><a href="${pageContext.request.contextPath}/tag/">Tag</a></li>
          <c:if test = "${sessionScope.currentRole.name == 'admin'}">
            <li class="my-nav ${navUserActive}"><a href="${pageContext.request.contextPath}/admin/user/">Utilisateurs</a></li>
          </c:if>
          <li class="my-nav"><a href="${pageContext.request.contextPath}/logout">logout</a></li>
        </ul>
      </div><!--/.nav-collapse -->
    </div><!--/.container-fluid -->
  </nav>
</header>