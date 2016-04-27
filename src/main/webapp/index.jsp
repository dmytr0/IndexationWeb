<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="bean" class="xyz.dimonick.Services.WebController" />

<html  xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>Индексация зп</title>
    <link href="${pageContext.request.contextPath}/css/tooplate_style.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.lightbox-0.5.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.lightbox-0.5.css" media="screen" />

    <script type='text/javascript' src='${pageContext.request.contextPath}/js/jquery.min.js'></script>
    <script type='text/javascript' src='${pageContext.request.contextPath}/js/jquery.scrollTo-min.js'></script>
    <script type='text/javascript' src='${pageContext.request.contextPath}/js/jquery.localscroll-min.js'></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.lightbox-0.5.js"></script>

    <script type="text/javascript">
        $(function() {
            $.localScroll();
            $('#map a').lightBox();
        });
    </script>


</head>
<body>


<c:set var="baseL" value="${bean.base}" />
<c:set var="calcL" value="${bean.calc}" />
<jsp:setProperty name="bean" property="bp" param="bpselect" />
<jsp:setProperty name="bean" property="cp" param="cpselect" />


<div id="tooplate_body_wrapper">
    <div id="tooplate_wrapper">
        <div id="site_title">
            <h1><a href="#">Blue Spark</a></h1>
        </div>

        <div id="tooplate_main">

            <div id="home" class="content_top"></div>
            <div class="content_box">

                <div class="content_title content_ct"><h2>Расчет индексации с 2016</h2></div>

            </div>


<div class="content_box">

                <div class="content">

        <div class="cent" align="center">
        <form  method="get">

            <b>Базовый месяц :</b>

            <select name="bpselect" >
                <c:forEach var="bptmp" items="${baseL}">

                    <c:if test="${param.bpselect ne bptmp }">
                        <option value="${bptmp}">${bptmp}</option>
                    </c:if>
                    <c:if test="${param.bpselect eq bptmp }">
                        <option selected value="${bptmp}">${bptmp}</option>
                    </c:if>

                </c:forEach>
            </select>




            </br></br>

            <b>Месяц расчета : </b>

            <select name="cpselect">
                <c:forEach var="cptmp" items="${calcL}">
                    <c:if test="${param.cpselect ne cptmp }">
                        <option value="${cptmp}">${cptmp}</option>
                    </c:if>
                    <c:if test="${param.cpselect eq cptmp }">
                        <option selected value="${cptmp}">${cptmp}</option>
                    </c:if>

                </c:forEach>
            </select>


            </br></br>

            <input class="edit" type="submit" value="Расчитать"/><br/>


        </form>
        </div>
        <div class="cleaner"></div>
    </div>


</div>

            <div class="content_title content_ct">
                <c:if test="${param.bpselect ne null && param.cpselect ne null}">
                        <h4>
                            <span class="res"> <jsp:getProperty name="bean" property="resNew"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;103% Для всех индексов</span></br>
                            <span class="res"> <jsp:getProperty name="bean" property="resOld"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;103% Для индексов с 01.2016</span>
                        </h4>

                </c:if>
            </div>


        </div>

    </div> <!-- end of warpper -->
</div> <!-- end of body wrapper -->

</body>
</html>
