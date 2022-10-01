<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<title>Customer Relationship Management</title>
</head>

<body>

	<div class="container">

		<h1 class="bg-success text-center">Customer Relationship Management </h1>
		<hr>

		<!-- Add a search form -->

		<form action="/CustomerRelationshipManagement/customer/addCustomer" class="form-inline" method="post">

			<button type="submit" name="Add Customer"  class="btn btn-primary btn-sm mb-1">Add Customer</button>

		</form>
	

		<table class="table table-bordered table-striped ">
			<thead class="thead">
				<tr class="bg-active">
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${customers}" var="cust">
					<tr>
						<td><c:out value="${cust.firstName}" /></td>
						<td><c:out value="${cust.lastName}" /></td>
						<td><c:out value="${cust.email}" /></td>
						
						<td>
							<!-- Add "update" button/link --> <a
							href="/CustomerRelationshipManagement/customer/updateCustomer?id=${cust.id}" 
							class="btn btn-info btn-sm"> Update </a> <!-- Add "delete" button/link -->
							<a href="/CustomerRelationshipManagement/customer/deleteCustomer?id=${cust.id}"
							class="btn btn-danger btn-sm"
							onclick="if (!(confirm('Are you sure you want to delete?'))) return false">
								Delete </a>

						</td>

					</tr>
				</c:forEach>

			</tbody>
		</table>

	</div>

</body>
</html>



