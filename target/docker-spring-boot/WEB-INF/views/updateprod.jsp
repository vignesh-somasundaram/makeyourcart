<%@ include file="commonFiles/header.jspf"%>
<%@ include file="commonFiles/navbar.jspf"%>
<body style="background-color:powderblue;">
    <div class="container">
    		<form:form method="post" modelAttribute="product" commandName="product">
    			<form:hidden path="id"/>
    			<fieldset class="form-group">
    				<form:label path="productName">Product Name</form:label>
    				<form:input path="productName" type="text"
    					class="form-control" required="required"/>
    				<form:errors path="productName" cssClass="text-warning"/>
    			</fieldset>
    			<fieldset class="form-group">
                    <form:label path="description">Description</form:label>
                    <form:input path="description" type="text"
                        class="form-control" required="required"/>
                    <form:errors path="description" cssClass="text-warning"/>
                </fieldset>
                <fieldset class="form-group">
                    <form:label path="price">Price</form:label>
                    <form:input path="price" type="number"
                        class="form-control" required="required"/>
                    <form:errors path="price" cssClass="text-warning"/>
                </fieldset>
                <fieldset class="form-group">
                <form:label path="availability">Availability</form:label>
                <form:input path="availability" type="text"
                    class="form-control" required="required"/>
                <form:errors path="availability" cssClass="text-warning"/>
            </fieldset>
    			<button type="submit" class="btn btn-success">Submit</button>
    		</form:form>
    	</div>
<%@ include file="commonFiles/footer.jspf"%>