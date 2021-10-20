<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

    <title>Accident</title>
</head>
<body>
<!--jQuery-->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>

<div class="container pt-3">
    <div class="row mb-3">
        <div class="col text-end">
            <a href="<c:url value='/create'/>" class="btn btn-success btn-sm" role="button">Добавить инцидент</a>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <table class="table table-hover table-striped">
                <thead>
                <tr class="table-dark">
                    <th scope="col">№</th>
                    <th scope="col">Имя нарушителя</th>
                    <th scope="col">Описание нарушения</th>
                    <th scope="col">Адрес совершения нарушения</th>
                    <th scope="col">Тип нарушения</th>
                    <th scope="col">Статьи нарушения</th>
                    <th scope="col">Редактирование</th>
                </tr>
                </thead>
                <tbody>
                <c:set var="count" value="1"/>
                <c:forEach items="${accidents}" var="accident">
                    <tr>
                        <th scope="row">
                            <c:out value="${count}"/>
                        </th>
                        <td>
                            <c:out value="${accident.name}"/>
                        </td>
                        <td>
                            <c:out value="${accident.text}"/>
                        </td>
                        <td>
                            <c:out value="${accident.address}"/>
                        </td>
                        <td>
                            <c:out value="${accident.type.name}"/>
                        </td>
                        <td>
                            <ul>
                                <c:forEach items="${accident.rules}" var="rule">
                                    <li><c:out value="${rule.name}"/></li>
                                </c:forEach>
                            </ul>
                        </td>
                        <td class="text-center align-middle">
                            <a href="<c:url value='/update?id=${accident.id}'/>" class="btn btn-success btn-sm" role="button">Редактировать</a>
                        </td>
                    </tr>
                    <c:set var="count" value="${count + 1}"/>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
