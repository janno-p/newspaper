<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:genericpage>
    <jsp:attribute name="title">Ajaleht</jsp:attribute>
    <jsp:body>
        <c:if test="${not empty success && success == true}">
        <div class="alert alert-success alert-dismissable">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <p><strong>Muudatused on edukalt salvestatud!</strong></p>
        </div>
        </c:if>
        <c:if test="${not empty errors && errors.size() > 0}">
        <div class="alert alert-danger">
            <p><strong>Muudatuste salvestamine ebaõnnestus!</strong> Palun paranda allpool välja toodud vead.</p>
        </div>
        </c:if>
        <form class="form-horizontal" role="form" action="s?action=save" method="post">
            <div class="form-group">
                <label class="col-sm-2 control-label">Id:</label>
                <div class="col-sm-4">
                    <p class="form-control-static"><c:out value="${newspaper.id}" /></p>
                    <input type="hidden" name="id" value="<c:out value="${newspaper.id}" />" />
                </div>
            </div>
            <div class="form-group<c:if test="${not empty errors && errors.containsKey(\"name\")}"> has-error</c:if>">
                <label for="name" class="col-sm-2 control-label">Nimi:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="name" name="name" placeholder="Ajalehe nimi" value="<c:out value="${newspaper.name}" />" />
                </div>
                <c:if test="${not empty errors && errors.containsKey(\"name\")}">
                <div class="col-sm-6">
                    <p class="help-block"><c:out value="${errors.get(\"name\")}" /></p>
                </div>
                </c:if>
            </div>
            <div class="form-group<c:if test="${not empty errors && errors.containsKey(\"foundedAt\")}"> has-error</c:if>">
                <label for="foundedAt" class="col-sm-2 control-label">Esimene väljaanne:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="foundedAt" name="foundedAt" placeholder="PP.KK.AAAA" value="<c:out value="${newspaper.foundedAt}" />" />
                </div>
                <c:if test="${not empty errors && errors.containsKey(\"foundedAt\")}">
                <div class="col-sm-6">
                    <p class="help-block"><c:out value="${errors.get(\"foundedAt\")}" /></p>
                </div>
                </c:if>
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
