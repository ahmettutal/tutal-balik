<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<jsp:include page="includes/head.jsp" />

<body class="nav-md">
<div class="container body">
    <div class="main_container">

        <jsp:include page="includes/header.jsp" />

        <!-- page content -->
        <div class="right_col" role="main">
            <h1>Bir şeyler ters gitti!</h1>
            <h2>Mühendislerimiz çalışıyor :b</h2>
            <p>${message}</p>
        </div>
        <!-- /page content -->

        <jsp:include page="includes/footer.jsp"/>

    </div>
</div>

</body>
</html>
