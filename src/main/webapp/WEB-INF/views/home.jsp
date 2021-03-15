<%@ include file="commonFiles/header.jspf"%>
<%@ include file="commonFiles/navbar.jspf"%>
<body style="background-color:powderblue;">
    <h1>Add new product to your cart</h1>
    <form action="/products" class="form" method="post">
    	<label>Product ID: </label><br>
    	<input type="number" name="id" required="required"/><br><br>
    	<label>Product Name: </label><br>
    	<input type="text" name="productName" required="required" autocomplete="off"/><br><br>
    	<label>Product Description: </label><br>
    	<input type="text" name ="description" required="required" autocomplete="off"/><br><br>
    	<label>Product Price: </label><br>
        <input type="number" name="price" required="required" autocomplete="off"/><br><br>
    	<label>Product Availability: </label><br>
    	<input type="text" name="availability" required="required" autocomplete="off"/><br><br>
    	<input class="button" style="width:100%" type="submit" value="Add"/>
    </form>
<%@ include file="commonFiles/footer.jspf"%>