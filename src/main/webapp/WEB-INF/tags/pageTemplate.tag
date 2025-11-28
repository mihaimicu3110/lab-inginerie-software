<%@tag description="base page template" pageEncoding="UTF-8"%>
<%@attribute name="pageTitle"%>
<%@attribute name="activePage" required="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>${pageTitle}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</head>

<body>

<jsp:include page="/WEB-INF/pages/menu.jsp" />

<main class="container-fluid mt-5">
    <jsp:doBody/>
</main>

<jsp:include page="/WEB-INF/pages/footer.jsp" />

</body>
</html>
