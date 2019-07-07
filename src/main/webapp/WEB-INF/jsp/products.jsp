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
        <div id="responsive-nav">
            <!-- category nav -->
            <div class="category-nav show-on-click">
                <span class="category-header">Categories <i class="fa fa-list"></i></span>
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
                </ul>
            </div>
            <!-- menu nav -->
        </div>
    </div>
    <!-- /container -->
</div>
<!-- /NAVIGATION -->

<!-- BREADCRUMB -->
<div id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="#">Home</a></li>
            <li class="active">Products</li>
        </ul>
    </div>
</div>
<!-- /BREADCRUMB -->

<!-- section -->
<div class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <!-- ASIDE -->
            <div id="aside" class="col-md-3">

            </div>
            <!-- /ASIDE -->

            <!-- MAIN -->
            <div id="main" class="col-md-9">

                <!-- store top filter -->
                <div class="store-filter clearfix">
                    <div class="pull-left">
                        <div class="row-filter">
                            <a href="#"><i class="fa fa-th-large"></i></a>
                            <a href="#" class="active"><i class="fa fa-bars"></i></a>
                        </div>
                        <div class="sort-filter">
                            <span class="text-uppercase">Sort By:</span>
                            <select class="input">
                                <option value="0">Position</option>
                                <option value="0">Price</option>
                                <option value="0">Rating</option>
                            </select>
                            <a href="#" class="main-btn icon-btn"><i class="fa fa-arrow-down"></i></a>
                        </div>
                    </div>
                    <div class="pull-right">
                        <div class="page-filter">
                            <span class="text-uppercase">Show:</span>
                            <select class="input">
                                <option value="0">10</option>
                                <option value="1">20</option>
                                <option value="2">30</option>
                            </select>
                        </div>
                        <ul class="store-pages">
                            <li><span class="text-uppercase">Page:</span></li>
                            <li class="active">1</li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#"><i class="fa fa-caret-right"></i></a></li>
                        </ul>
                    </div>
                </div>
                <!-- /store top filter -->

                <!-- STORE -->
                <div id="store">
                    <!-- row -->
                    <div class="row">

                        <c:forEach var="product" items="${products}">

                            <div class="col-md-4 col-sm-6 col-xs-6">
                                <div class="product product-single">
                                    <div class="product-thumb">

                                        <c:if test="${product.picture != null}">
                                            <img src="../../productImages/${product.id}/${product.picture.name}" alt="">
                                        </c:if>

                                    </div>
                                    <div class="product-body">
                                        <h3 class="product-price">${product.price} ₺</h3>
                                        <h2 class="product-name"><a href="/${product.name}-${product.id}">${product.name}</a></h2>
                                        <h2 class="product-desc"><a href="/${product.name}-${product.id}">${product.description}</a></h2>
                                    </div>
                                </div>
                            </div>

                        </c:forEach>

                    </div>
                    <!-- /row -->
                </div>
                <!-- /STORE -->

                <!-- store bottom filter -->
                <div class="store-filter clearfix">
                    <div class="pull-left">
                        <div class="row-filter">
                            <a href="#"><i class="fa fa-th-large"></i></a>
                            <a href="#" class="active"><i class="fa fa-bars"></i></a>
                        </div>
                        <div class="sort-filter">
                            <span class="text-uppercase">Sort By:</span>
                            <select class="input">
                                <option value="0">Position</option>
                                <option value="0">Price</option>
                                <option value="0">Rating</option>
                            </select>
                            <a href="#" class="main-btn icon-btn"><i class="fa fa-arrow-down"></i></a>
                        </div>
                    </div>
                    <div class="pull-right">
                        <div class="page-filter">
                            <span class="text-uppercase">Show:</span>
                            <select class="input">
                                <option value="0">10</option>
                                <option value="1">20</option>
                                <option value="2">30</option>
                            </select>
                        </div>
                        <ul class="store-pages">
                            <li><span class="text-uppercase">Page:</span></li>
                            <li class="active">1</li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#"><i class="fa fa-caret-right"></i></a></li>
                        </ul>
                    </div>
                </div>
                <!-- /store bottom filter -->
            </div>
            <!-- /MAIN -->
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- /section -->

<jsp:include page="includes/footer.jsp"/>

</body>

</html>
