<%@ include file="commonFiles/header.jspf"%>
<%@ include file="commonFiles/navbar.jspf"%>
 <body>
 <div class="container">
    <form action="/productspage" method=post modelAttribute="product">
        <label  style="margin-left: 420px;">Enter page number</label>
        <input type="number" name="pageNo" style="margin-left: 420px;width:300px;" required="required"/><br>
        <div class="${visibility}" style="color:red; margin-left: 455px;">${errorMessage}</div><br>
        <label  style="margin-left: 420px;">Enter page size</label>
        <input type="number" name="pageSize" style="margin-left: 420px;width:300px;" required="required"/><br><br>
        <label  style="margin-left: 420px;">Sort By</label>
        <input type="text" name="sortBy" style="margin-left: 420px;width:300px;" value="id" /><br><br>
        <input type="submit" value="Search" style="margin-left: 470px;width:200px;"/>
    </form>
	 <table class="table table-striped">
	 	<thead>
	 		<tr>
	 			<th>Product ID</th>
	 			<th>Product Name</th>
	 			<th>Product Description</th>
	 			<th>Product Price</th>
	 			<th>Product Availability</th>
	 			<th>Update Product</th>
	 			<th>Delete Product</th>
	 		</tr>
	 	</thead>
	 	<tbody>
	 		<c:forEach items="${products}" var="product">
	 			<tr>
	 				<td>${product.id}</td>
	 				<td>${product.productName}</td>
	 				<td>${product.description}</td>
	 				<td>${product.price}</td>
	 				<td>${product.availability}</td>
	 				<td><a type="button" class="btn btn-success"
							href="/updateprod?id=${product.id}">Update</a></td>
					<td><a type="button" class="btn btn-warning"
							href="/deleteprodbyId?id=${product.id}">Delete</a></td>
	 			</tr>
	 		</c:forEach>
	 	</tbody>
	 </table>
</div>
<%@ include file="commonFiles/footer.jspf"%>