<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a
		href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/MetaConnect/OAuth2&response_type=code&client_id=60843522167-mmj77k1qjg2iiqe6c3hqrnfged0ds5af.apps.googleusercontent.com&approval_prompt=force&hd=metacube.com"
		class="btn btn-default btn-lg btn-block btn-social btn-google">
		clickme</a>
</body>
</html> -->



<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>MetaConnect</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/grayscale.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
</head>
<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">
 <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="js/jquery.easing.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="js/grayscale.js"></script>
    <!-- Navigation -->
    <nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
                    <i class="fa fa-bars"></i>
                </button>
             <!--   <a class="navbar-brand page-scroll" href="#page-top">
                    <i class="fa fa-play-circle"></i>  <span class="light">Start</span> Bootstrap
                </a> -->
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse navbar-right navbar-main-collapse">
                <ul class="nav navbar-nav">
                    <!-- Hidden li included to remove active class from about link when scrolled up past about section -->
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#about">About Us</a>
                    </li>
                
                    <li>
                        <a class="page-scroll" href="#contact">Contact Us</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Intro Header -->
    <header class="intro">
        <div class="intro-body">
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <h1 class="brand-heading">Meta-Connect</h1>
                      <!--  <p class="intro-text">A free, responsive, one page Bootstrap theme.<br>Created by Start Bootstrap.</p> -->
					  
					   
					 <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/MetaConnect/OAuth2&response_type=code&client_id=60843522167-mmj77k1qjg2iiqe6c3hqrnfged0ds5af.apps.googleusercontent.com&approval_prompt=force&hd=metacube.com"
					 class="btn btn-default btn-lg btn-block btn-social btn-google"><i class="fa fa-google-plus fa-fw"></i> <span class="network-name">SignIn With Google</span></a>
                        <a href="#about" class="btn btn-circle page-scroll">
                            <i class="fa fa-angle-double-down animated"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- About Section -->
    <section id="about" class="container content-section text-center">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
                <h2>About MetaConnect</h2>
                <p> Meta-Connect that provides a social community for people interested in a particular subject or interest together. Members create their own online profile with data, pictures, and any other information . The theme is open source, and you can use it for any purpose, personal or commercial.</p>
                <p>We always want to have all the information about others and this Social Network is a easy way to know about how others are performing and can share their experience and can communicate through the post they will update and by comments on their post.</p>
            </div>
        </div>
    </section>

    

    <!-- Contact Section -->
    <section id="contact" class="container content-section text-center">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
                <h2>Contact MetaConnect</h2>
                <p>Feel free to email us to provide some feedback on our templates, give us suggestions for new templates and themes, or to just say hello!</p>
                <p><a href="mailto:feedback@metaconnect.com">feedback@metaconnect.com</a>
                </p>
                       </div>
        </div>
    </section>
<!-- Footer -->
    <footer>
        <div class="container text-center">
            <p>Copyright &copy; MetaConnect 2015</p>
        </div>
    </footer>

   

</body>

</html>
