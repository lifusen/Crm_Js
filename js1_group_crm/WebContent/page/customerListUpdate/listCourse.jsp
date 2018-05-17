<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style type="text/css" >
#listCourse {margin-bottom: 10px;}
#listCourse a.gray{color: #ddd; background-color: #eee;}
</style>

<div id="listCourse">
	<c:choose>
		<c:when test="${customer.state==0||customer.state==1 }">
			<a class="btn green">分配中 <i class="icon-chevron-right"></i></a>
		</c:when>
		<c:otherwise>
			<a class="btn blue">分配中 <i class="icon-chevron-right"></i></a>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${customer.state==0||customer.state==1 }">
			<a class="btn gray">跟进中 <i class="icon-chevron-right"></i></a>
		</c:when>
		<c:when test="${customer.state==2||customer.state==3 }">
			<a class="btn green">跟进中 <i class="icon-chevron-right"></i></a>
		</c:when>
		<c:otherwise>
			<a class="btn blue">跟进中 <i class="icon-chevron-right"></i></a>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${customer.state==0||customer.state==1||customer.state==2||customer.state==3 }">
			<a class="btn gray">已签单 <i class="icon-chevron-right"></i></a>
			<a id="warrantCourse1" class="btn gray">权证跟进 <i class="icon-chevron-right"></i></a>
		</c:when>
		<c:when test="${customer.state==4 && empty customer.listCourse}">
			<a class="btn blue">已签单 <i class="icon-chevron-right"></i></a>
			<a id="warrantCourse1" class="btn green">权证跟进 <i class="icon-chevron-right"></i></a>
		</c:when>
		<c:otherwise>
			<a class="btn blue">已签单 <i class="icon-chevron-right"></i></a>
			<a id="warrantCourse1" class="btn blue">权证跟进 <i class="icon-chevron-right"></i></a>
		</c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test="${customer.state==5}">
			<a class="btn yellow">退单</a>
		</c:when>
		<c:when test="${customer.state==6}">
			<a class="btn yellow">退件</a>
		</c:when>
		<c:otherwise>
		
			<c:choose>
				<c:when test="${customer.state==0||customer.state==1||customer.state==2||customer.state==3||customer.state==4 && empty customer.listCourse}">
					<a id="warrantCourse3" class="btn gray">合同签订 <i class="icon-chevron-right"></i></a>
					<a id="warrantCourse4" class="btn gray">合同审批 <i class="icon-chevron-right"></i></a>
				</c:when>
				<c:when test="${customer.state==4 && customer.listCourse==100}">
					<a id="warrantCourse3" class="btn blue">合同签订 <i class="icon-chevron-right"></i></a>
					<a id="warrantCourse4" class="btn green">合同审批 <i class="icon-chevron-right"></i></a>
				</c:when>
				<c:otherwise>
					<a id="warrantCourse3" class="btn blue">合同签订 <i class="icon-chevron-right"></i></a>
					<a id="warrantCourse4" class="btn blue">合同审批 <i class="icon-chevron-right"></i></a>
				</c:otherwise>
			</c:choose>
			
			<c:if test="${customer.loanType=='房贷' }">
				<c:choose>
					<c:when test="${customer.state==0||customer.state==1||customer.state==2||customer.state==3||customer.state==4 && (customer.listCourse==100 || empty customer.listCourse)}">
						<a id="warrantCourse5" class="btn gray">办理抵押 <i class="icon-chevron-right"></i></a>
					</c:when>
					<c:when test="${customer.state==4 && customer.listCourse==101}">
						<a id="warrantCourse5" class="btn green">办理抵押 <i class="icon-chevron-right"></i></a>
					</c:when>
					<c:otherwise>
						<a id="warrantCourse5" class="btn blue">办理抵押 <i class="icon-chevron-right"></i></a>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${customer.state==0||customer.state==1||customer.state==2||customer.state==3||customer.state==4 && (customer.listCourse==100 || customer.listCourse==101 || empty customer.listCourse)}">
						<a id="warrantCourse6" class="btn gray">放款</a>
					</c:when>
					<c:when test="${customer.state==4 && customer.listCourse==102}">
						<a id="warrantCourse6" class="btn green">放款</a>
					</c:when>
					<c:otherwise>
						<a id="warrantCourse6" class="btn blue">放款</a>
					</c:otherwise>
				</c:choose>
			</c:if>
			<c:if test="${customer.loanType!='房贷' }">
				<c:choose>
					<c:when test="${customer.state==0||customer.state==1||customer.state==2||customer.state==3||customer.state==4 && (customer.listCourse==100 || empty customer.listCourse)}">
						<a id="warrantCourse6" class="btn gray">放款</a>
					</c:when>
					<c:when test="${customer.state==4 && customer.listCourse==101}">
						<a id="warrantCourse6" class="btn green">放款</a>
					</c:when>
					<c:otherwise>
						<a id="warrantCourse6" class="btn blue">放款</a>
					</c:otherwise>
				</c:choose>
			</c:if>
		</c:otherwise>
	</c:choose>
	
	

</div>
