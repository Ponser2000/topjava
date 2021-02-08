<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>

<table style=" border: 1px solid #dddddd; border-collapse:collapse;">
    <thead>
    <tr>
        <th style=" border: 1px solid #dddddd;">Date</th>
        <th style=" border: 1px solid #dddddd;">Description</th>
        <th style=" border: 1px solid #dddddd;">Calories</th>
        <th style=" border: 1px solid #dddddd;">&nbsp;</th>
        <th style=" border: 1px solid #dddddd;">&nbsp;</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${MealsTo}" var="meals">
        <c:if test="${meals.excess == true}">
            <tr style="color: red">
        </c:if>
        <c:if test="${meals.excess == false}">
            <tr style="color: green">
        </c:if>
        <td style=" border: 1px solid #dddddd;">
            <fmt:parseDate value="${ meals.dateTime }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
            <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${ parsedDateTime }"/>
        </td>
        <td style=" border: 1px solid #dddddd;"><c:out value="${meals.description}"/></td>
        <td style=" border: 1px solid #dddddd;"><c:out value="${meals.calories}"/></td>
        <td style=" border: 1px solid #dddddd;"></td>
        <td style=" border: 1px solid #dddddd;"></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
