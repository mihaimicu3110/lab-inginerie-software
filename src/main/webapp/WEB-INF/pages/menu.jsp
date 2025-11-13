<%--
  Created by IntelliJ IDEA.
  User: Mihai
  Date: 13/11/2025
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header data-bs-theme="dark">
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <div class="container-fluid">

            <!-- Brand -->
            <a class="navbar-brand" href="${pageContext.request.contextPath}">Parking Lot</a>

            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse"
                    aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarCollapse">

                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    <!-- ABOUT BUTTON -->
                    <li class="nav-item">
                        <a class="nav-link
                            ${pageContext.request.requestURI.substring(pageContext.request.requestURI.lastIndexOf("/")) eq '/about.jsp' ? 'active' : ''}"
                           aria-current="page"
                           href="${pageContext.request.contextPath}/about.jsp">
                            About
                        </a>
                    </li>

                    <!-- Example link left in menu -->
                    <li class="nav-item">
                        <a class="nav-link" href="#">Link</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link disabled" aria-disabled="true">Disabled</a>
                    </li>
                </ul>

                <!-- LOGIN BUTTON (replacing search bar) -->
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Login">Login</a>
                    </li>
                </ul>

            </div>
        </div>
    </nav>
</header>
