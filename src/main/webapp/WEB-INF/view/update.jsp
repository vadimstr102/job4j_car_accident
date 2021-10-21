<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<!-- JavaScript scripts -->
<script src="<c:url value="/resources/script/validate.js"/>"></script>

<div class="container pt-3">
    <div class="row justify-content-center">
        <div class="col-5">
            <div class="card">
                <div class="card-header">
                    Редактирование нарушения
                </div>
                <div class="card-body">
                    <form action="<c:url value='/save?id=${accident.id}'/>" method='POST'>
                        <div class="form-group mb-3">
                            <label for="name" class="form-label">Имя нарушителя</label>
                            <input type="text" class="form-control" id="name" name="name" value="<c:out value="${accident.name}"/>">
                        </div>
                        <div class="form-group mb-3">
                            <label for="text" class="form-label">Описание нарушения</label>
                            <input type="text" class="form-control" id="text" name="text" value="<c:out value="${accident.text}"/>">
                        </div>
                        <div class="form-group mb-3">
                            <label for="address" class="form-label">Адрес совершения нарушения</label>
                            <input type="text" class="form-control" id="address" name="address" value="<c:out value="${accident.address}"/>">
                        </div>
                        <div class="form-group mb-3">
                            <label for="type" class="form-label">Тип нарушения</label>
                            <select class="form-select" id="type" name="type.id" title="Выберите тип нарушения">
                                <c:forEach items="${types}" var="type">
                                    <c:if test="${type.id == accident.type.id}">
                                        <option value="<c:out value="${type.id}"/>" selected><c:out value="${type.name}"/></option>
                                    </c:if>
                                    <c:if test="${type.id != accident.type.id}">
                                        <option value="<c:out value="${type.id}"/>"><c:out value="${type.name}"/></option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group mb-3">
                            <label for="rules" class="form-label">Статьи нарушения</label>
                            <select class="form-select" id="rules" name="rulesIds" title="Выберите статьи нарушения" multiple>
                                <c:forEach items="${rules}" var="rule">
                                    <option value="<c:out value="${rule.id}"/>"><c:out value="${rule.name}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <div class="d-grid gap-2">
                                <button type="submit" class="btn btn-primary" onclick="return validate()">Сохранить</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- JavaScript scripts -->
<script>
    function validate() {
        let name = $('#name').val();
        let text = $('#text').val();
        let address = $('#address').val();
        let type = $('#type').val();
        let rules = $('#rules').val().length;

        if (name === "") {
            alert($('#name').attr('placeholder'));
            return false;
        }
        if (text === "") {
            alert($('#text').attr('placeholder'));
            return false;
        }
        if (address === "") {
            alert($('#address').attr('placeholder'));
            return false;
        }
        if (type === "") {
            alert($('#type').attr('title'));
            return false;
        }
        if (rules === 0) {
            alert($('#rules').attr('title'));
            return false;
        }
        return true;
    }
</script>
</body>
</html>
