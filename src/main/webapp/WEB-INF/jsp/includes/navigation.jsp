<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                                                            <li>
                                                                <a href="/urunler${category.link}${subCategory.link}">${subCategory.name}</a>
                                                            </li>
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
                    <li><a href="/urunler">Ürünler</a></li>
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
