<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Item Manager</title>
    <style>
        table { border-collapse: collapse; width: 100%; margin-top: 20px; }
        th, td { border: 1px solid #aaa; padding: 8px; text-align: left; }
        input { margin: 5px; }
        button { margin: 5px; }
    </style>
</head>
<body>
<h2>Item Manager</h2>

<form method="post" action="/items/add">
    <input type="text" name="name" placeholder="Name" required>
    <input type="text" name="quality" placeholder="Quality" required>
    <button type="submit">Add Item</button>
</form>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Quality</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${items}">
        <tr>
            <form method="post" action="/items/update">
                <td><input type="hidden" name="id" value="${item.id}"/>${item.id}</td>
                <td><input type="text" name="name" value="${item.name}"/></td>
                <td><input type="text" name="quality" value="${item.quality}"/></td>
                <td>
                    <button type="submit">Update</button>
                    <a href="/items/delete/${item.id}" onclick="return confirm('Are you sure?')">Delete</a>
                </td>
            </form>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
