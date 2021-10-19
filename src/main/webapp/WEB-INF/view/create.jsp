<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<form action="<c:url value='/save'/>" method='POST'>
    <table>
        <tr>
            <td>Имя нарушителя:</td>
            <td><input type='text' name='name'></td>
        </tr>
        <tr>
            <td>Описание нарушения:</td>
            <td><input type='text' name='text'></td>
        </tr>
        <tr>
            <td>Адрес совершения нарушения:</td>
            <td><input type='text' name='address'></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить"/></td>
        </tr>
    </table>
</form>
</body>
</html>
