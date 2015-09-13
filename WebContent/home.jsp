<%@ include file="allIncludes.jspf" %>
<div class="container-fluid">
  <br>
  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
      <li data-target="#myCarousel" data-slide-to="3"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">

      <div class="item active">
        <img src="images/carousel_00.jpg" alt="Chania" width="790" height="270">
        <div class="carousel-caption">
          <h3></h3>
          <p></p>
        </div>
      </div>

      <div class="item">
        <img src="images/carousel_0.jpg" alt="Chania" width="790" height="270">
        <div class="carousel-caption">
          <h3></h3>
          <p></p>
        </div>
      </div>
    
      <div class="item">
        <img src="images/carousel_1.jpg" alt="Flower" width="790" height="270">
        <div class="carousel-caption">
          <h3></h3>
          <p></p>
        </div>
      </div>

      <div class="item">
        <img src="images/carousel_2.jpg" alt="Flower" width="460" height="245">
        <div class="carousel-caption">
          <h3></h3>
          <p></p>
        </div>
      </div>
	  
      <div class="item">
        <img src="images/carousel_3.jpg" alt="Flower" width="460" height="245">
        <div class="carousel-caption">
          <h3></h3>
          <p> .</p>
        </div>
      </div>
      <div class="item">
        <img src="images/carousel_4.jpg" alt="Flower" width="460" height="245">
        <div class="carousel-caption">
          <h3></h3>
          <p></p>
        </div>
      </div>
  
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
	</div>
	<div class="row" style="padding-top:20px;">
		<div class="col-md-12" align="center">
		  <p></p>
		  <p></p>
		</div>
	</div>
	<div class="row" style="padding-left:100px;padding-right:100px;padding-top:50px;">
		<div class="col-md-4">
			<img class="img-responsive" src="images/cool_picture.jpg" alt="cool_picture" width="236" height="236"> 
		</div>
		<div class="col-md-4">
			<div class="panel panel-default">
				<div class="panel-heading" align="center">New Member?</div>
				<div class="panel-body">
					<%@ include file="sign_up.jspf"%>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="panel panel-default">
				<div class="panel-heading" align="center">Existing Member?</div>
				<div class="panel-body">
					<%@ include file="sign_in.jspf"%>
				</div>
			</div>
		</div>
	</div>
	<div class="jumbotron">
		<h2>BEST SOLD PRODUCTS</h2>
		<div class="row" style="padding-left:100px;padding-right:100px;padding-top:50px;">
			<div class="col-md-4" style="padding-right:10px;" align="center">
				<a href="#" class="thumbnail" onmouseover="show_pic_details(1)" onmouseout="hid_pic_details(1)">
					<img src="images/best_1.jpg" alt="Best_prod" style="width:150px;height:250px">
				</a>
				<div id="best_1" style="display:none;margin-top:-100px">
					<p>30 $</p>
					<button type="button" class="btn btn-default">Add to cart</button>
				</div>
			</div>
			<div class="col-md-4" style="padding-left:10px;padding-right:10px;" align="center">
				<a href="#" class="thumbnail" onmouseover="show_pic_details(2)" onmouseout="hid_pic_details(2)">
					<img src="images/best_2.jpg" alt="Best_prod" style="width:150px;height:250px">
				</a>
				<div id="best_2" style="display:none;margin-top:-100px">
					<p>30 $</p>
					<button type="button" class="btn btn-default">Add to cart</button>
				</div>
			</div>
			<div class="col-md-4" style="padding-left:10px;" align="center">
				<a href="#" class="thumbnail" onmouseover="show_pic_details(3)" onmouseout="hid_pic_details(3)">
					<img src="images/best_3.jpg" alt="Best_prod" style="width:150px;height:250px;">
				</a>
				<div id="best_3" style="display:none;margin-top:-100px">
					<p>30 $</p>
					<button type="button" class="btn btn-default">Add to cart</button>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="footer_home.jspf"%>