<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<optgroup label="银行">
<c:forEach var="lendingInstitution" items="${sessionScope.lendingInstitutionMap.bank }">
	<option value="${lendingInstitution.id }">${lendingInstitution.name }</option>
</c:forEach>
</optgroup>
<optgroup label="小贷">
	<c:forEach var="lendingInstitution" items="${sessionScope.lendingInstitutionMap.smallLand }">
		<option value="${lendingInstitution.id }">${lendingInstitution.name }</option>
	</c:forEach>
</optgroup>