<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<jsp:include page="includes/head.jsp"/>

<body>

<jsp:include page="includes/header.jsp"/>

<!-- NAVIGATION -->
<div id="navigation">
    <!-- container -->
    <div class="container">
        <div id="responsive-nav ">
            <!-- category nav -->
            <div class="category-nav">
                <span class="category-header">KATEGORİLER <i class="fa fa-list"></i></span>
                <ul class="category-list">

                    <c:forEach var="category" items="${leftCategories}">

                        <li class="dropdown side-dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                    ${category.name}

                                <c:if test="${subCategories.get(category.id).size() > 0}">
                                    <i class="fa fa-angle-right"></i>
                                </c:if>
                            </a>

                            <c:if test="${subCategories.get(category.id).size() > 0}">
                                <div class="custom-menu">

                                    <div class="row">


                                        <div class="col-md-8">

                                            <div class="row">

                                                <c:forEach var="subCategory" items="${subCategories.get(category.id)}">

                                                    <div class="col-md-6">
                                                        <ul class="list-links">
                                                            <li>
                                                                <h3 class="list-links-title"></h3></li>
                                                            <li><a href="#">${subCategory.name}</a></li>
                                                        </ul>
                                                        <hr class="hidden-md hidden-lg">
                                                    </div>

                                                </c:forEach>

                                            </div>

                                        </div>

                                        <div class="col-md-4 hidden-sm hidden-xs">
                                            <a class="banner banner-2" href="#">
                                                <img src="../../categoryImages/${category.id}/${mainCategoryPictures.get(category.id).name}"
                                                     alt="">
                                                <div class="banner-caption">

                                                </div>
                                            </a>
                                        </div>

                                    </div>
                                </div>
                            </c:if>

                        </li>

                    </c:forEach>

                </ul>
            </div>
            <!-- /category nav -->

            <!-- menu nav -->
            <div class="menu-nav">
                <span class="menu-header">Menu <i class="fa fa-bars"></i></span>
                <ul class="menu-list">
                    <li><a href="/products">Ürünler</a></li>
                    <li class="dropdown default-dropdown"><a class="dropdown-toggle" data-toggle="dropdown"
                                                             aria-expanded="true">KALSIN <i
                            class="fa fa-caret-down"></i></a>
                        <ul class="custom-menu">
                            <li><a href="index2.html">Home</a></li>
                            <li><a href="products.html">Products</a></li>
                            <li><a href="product-page.html">Product Details</a></li>
                            <li><a href="checkout.html">ÇIKIŞ YAPMAK</a></li>
                        </ul>
                    </li>

                </ul>
            </div>
            <!-- menu nav -->

        </div>
    </div>
    <!-- /container -->
</div>
<!-- /NAVIGATION -->

<!-- HOME -->
<div id="home">
    <!-- container -->
    <div class="container">
        <!-- home wrap -->
        <div class="home-wrap">
            <!-- home slick -->
            <div id="home-slick">
                <!-- banner -->
                <div class="banner banner-1">
                    <img src="/img/kamış-makina.jpg" alt="">
                    <div class="banner-caption text-center">
                        <h1>Bags sale</h1>
                        <h3 class="white-color font-weak">Up to 50% Discount</h3>
                        <button class="primary-btn">Shop Now</button>
                    </div>
                </div>
                <!-- /banner -->

                <!-- banner -->
                <div class="banner banner-1">
                    <img src="/img/sazan.jpg" alt="">
                    <div class="banner-caption">
                        <h1 class="primary-color">HOT DEAL<br><span class="white-color font-weak">Up to 50% OFF</span>
                        </h1>
                        <button class="primary-btn">Shop Now</button>
                    </div>
                </div>
                <!-- /banner -->

                <!-- banner -->
                <div class="banner banner-1">
                    <img src="/img/pörç.jpg" alt="">
                    <div class="banner-caption">
                        <h1 class="white-color">New Product <span>Collection</span></h1>
                        <button class="primary-btn">Shop Now</button>
                    </div>
                </div>
                <!-- /banner -->
            </div>
            <!-- /home slick -->
        </div>
        <!-- /home wrap -->
    </div>
    <!-- /container -->
</div>
<!-- /HOME -->

<!-- section -->
<div class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <!-- banner -->
            <div class="col-md-4 col-sm-6">
                <a class="banner banner-1" href="#">
                    <img src="/img/kamışlar.jpg" alt="">
                    <div class="banner-caption text-center">
                        <h2 class="white-color">KAMIŞLAR</h2>
                    </div>
                </a>
            </div>
            <!-- /banner -->

            <!-- banner -->
            <div class="col-md-4 col-sm-6">
                <a class="banner banner-1" href="#">
                    <img src="/img/makiina.jpg" alt="">
                    <div class="banner-caption text-center">
                        <h2 class="white-color">MAKİNALAR</h2>
                    </div>
                </a>
            </div>
            <!-- /banner -->

            <!-- banner -->
            <div class="col-md-4 col-md-offset-0 col-sm-6 col-sm-offset-3">
                <a class="banner banner-1" href="#">
                    <img src="/img/suniyemler.jpg" alt="">
                    <div class="banner-caption text-center">
                        <h2 class="white-color">SUNİ YEMLER</h2>
                    </div>
                </a>
            </div>
            <!-- /banner -->

        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- /section -->

<jsp:include page="includes/footer.jsp"/>

</body>

</html>
