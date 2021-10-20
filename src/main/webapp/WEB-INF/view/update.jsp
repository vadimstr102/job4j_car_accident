<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<body>
<form action="<c:url value='/save?id=${accident.id}'/>" method='POST'>
    <table>
        <tr>
            <td>Имя нарушителя:</td>
            <td><input type='text' name='name' value="<c:out value="${accident.name}"/>"></td>
        </tr>
        <tr>
            <td>Описание нарушения:</td>
            <td><input type='text' name='text' value="<c:out value="${accident.text}"/>"></td>
        </tr>
        <tr>
            <td>Адрес совершения нарушения:</td>
            <td><input type='text' name='address' value="<c:out value="${accident.address}"/>"></td>
        </tr>
        <tr>
            <td>Тип:</td>
            <td>
                <select name="type.id">
                    <c:forEach items="${types}" var="type">
                        <c:if test="${type.id == accident.type.id}">
                            <option value="<c:out value="${type.id}"/>" selected><c:out value="${type.name}"/></option>
                        </c:if>
                        <c:if test="${type.id != accident.type.id}">
                            <option value="<c:out value="${type.id}"/>"><c:out value="${type.name}"/></option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить"/></td>
        </tr>
    </table>
</form>
</body>
</html>
