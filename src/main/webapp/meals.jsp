<%@ page import="ru.javawebinar.topjava.model.MealTo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>


<table border="2" style="border-collapse:collapse;">
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th>&nbsp;</th>
        <th>&nbsp;</th>
    </tr>
    </thead>
    <tbody>


<%
    List<MealTo> mealsTo = (List<MealTo>) request.getAttribute("MealsTo");
%>
<% for (MealTo mealTo : mealsTo) {
%>
<tr>
    <td><font color="<%=mealTo.isExcess() ? "red" : "green"%>"><%=mealTo.getDateTime().toString().replace("T"," ")%></font></td>
    <td><font color="<%=mealTo.isExcess() ? "red" : "green"%>"><%=mealTo.getDescription()%></font></td>
    <td><font color="<%=mealTo.isExcess() ? "red" : "green"%>"><%=mealTo.getCalories()%></font></td>
    <td><font color="<%=mealTo.isExcess() ? "red" : "green"%>"></font></td>
    <td><font color="<%=mealTo.isExcess() ? "red" : "green"%>"></font></td>
</tr>
<%
    }%>

    </tbody>
</table>

</body>
</html>
