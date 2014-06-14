<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Isart MarketPlace</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
	<div class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="/dashboard"> Isart MarketPlace </a>
			</div>
		</div>
	</div>
	<div class="container theme-showcase">

		<div class="page-header">
			<h1>Actions récentes :</h1>
		</div>
		
		<c:forEach items="${logs}" var="log">
			<c:if test="${log.action == 'ACCOUNT_CREATION'}">
				<div class="alert alert-danger">
					<strong>Nouveau compte créé : </strong> ${log.playerLogin}
				</div>
			</c:if>
			<c:if test="${log.action == 'PLAYER_LOG_IN'}">
				<div class="alert alert-success">
					L'utilisateur <strong>${log.playerLogin}</strong> vient de se connecter.
				</div>
			</c:if>
			<c:if test="${log.action == 'PLAYER_BUY_ITEM'}">
				<div class="alert alert-info">
					<div class="pull-left" style="padding-right: 20px;">
						<img src="${log.item.imgUrl}" height="64" class="img-rounded"/>
					</div>
					L'utilisateur <strong>${log.playerLogin}</strong> a acheté <strong>${log.item.name}</strong>*${log.itemQuantity}.
					<br/>
					<strong>Prix total</strong> : ${log.item.price * log.itemQuantity}€
					<br/>
					<strong>Nouveau solde : </strong> ${log.playerAccountBalance}€
					<div class="clearfix"></div>
				</div>
			</c:if>
			<c:if test="${log.action == 'PLAYER_SELL_ITEM'}">
				<div class="alert alert-warning">
					<div class="pull-left" style="padding-right: 20px;">
						<img src="${log.item.imgUrl}" height="64" class="img-rounded"/>
					</div>
					L'utilisateur <strong>${log.playerLogin}</strong> a vendu <strong>${log.item.name}</strong>*${log.itemQuantity}.
					<br/>
					<strong>Prix total</strong> : ${log.item.price * log.itemQuantity}€
					<br/>
					<strong>Nouveau solde : </strong> ${log.playerAccountBalance}€
					<div class="clearfix"></div>
				</div>
			</c:if>
		</c:forEach>
		
	</div>
</body>
</html>