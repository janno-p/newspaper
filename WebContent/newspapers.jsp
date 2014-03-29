<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:genericpage>
    <jsp:attribute name="title">Ajalehtede nimekiri</jsp:attribute>
    <jsp:body>
        <c:choose>
        <c:when test="${not empty newspapers}">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Nimi</th>
                    <th>Esimene väljaanne</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="newspaper" items="${newspapers}">
                <tr>
                    <td><c:out value="${newspaper.id}" /></td>
                    <td><c:out value="${newspaper.name}" /></td>
                    <td><fmt:formatDate pattern="dd.MM.yyyy" value="${newspaper.foundedAt}" /></td>
                    <td><a href="#">Kirjeldus</a></td>
                    <td><a href="s?id=<c:out value="${newspaper.id}" />">Muuda</a></td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        </c:when>
        <c:otherwise>
        <div class="alert alert-info">
            <p><strong>Ajalehed puuduvad.</strong> Rakenduses ei ole hetkel registreeritud mitte
            ühegi ajalehe andmeid.</p>
        </div>
        </c:otherwise>
        </c:choose>
    </jsp:body>
</t:genericpage>
