<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:genericpage>
    <jsp:attribute name="title">Ajaleht</jsp:attribute>
    <jsp:body>
        <form class="form-horizontal" role="form" action="s?action=save" method="post">
            <div class="form-group">
                <label class="col-sm-2 control-label">Id:</label>
                <div class="col-sm-4">
                    <p class="form-control-static"><c:out value="${newspaper.id}" /></p>
                </div>
            </div>
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">Nimi:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="name" name="name" placeholder="Ajalehe nimi" value="<c:out value="${newspaper.name}" />" />
                </div>
            </div>
            <div class="form-group">
                <label for="foundedAt" class="col-sm-2 control-label">Esimene väljaanne:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="foundedAt" name="foundedAt" placeholder="PP.KK.AAAA" value="<fmt:formatDate pattern="dd.MM.yyyy" value="${newspaper.foundedAt}" />" />
                </div>
            </div>
            <div class="form-group">
                <label for="description" class="col-sm-2 control-label">Kirjeldus:</label>
                <div class="col-sm-4">
                    <textarea class="form-control" rows="3" id="description" name="description"><c:out value="${newspaper.description}" /></textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-4">
                    <button type="submit" class="btn btn-default">Salvesta</button>
                </div>
            </div>
        </form>
    </jsp:body>
</t:genericpage>
