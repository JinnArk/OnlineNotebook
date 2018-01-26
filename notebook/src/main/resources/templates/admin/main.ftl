<!DOCTYPE HTML>
<html>
<head>
<title>NOTEBOOK</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="NOTEBOOK" />

<script type="application/x-javascript"> 
	addEventListener("load", function() { 
		setTimeout(hideURLbar, 0); 
		}, false
	); 
	function hideURLbar(){
	window.scrollTo(0,1); 
	} 
</script>

<!--自定义css-->
<link href="../static/css/own/admin.css" rel='stylesheet' type='text/css' />
<!-- Bootstrap Core CSS -->
<link href="../static/css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="../static/css/style.css" rel='stylesheet' type='text/css' />
<!-- font CSS -->
<link rel="icon" href="../static/images/ico/favicon-16.ico" type="image/x-icon" >
<!-- font-awesome icons -->
<link href="../static/css/font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome icons -->
<!-- chart -->
<script src="../static/js/Chart.js"></script>
<!-- //chart -->
 <!-- js-->
<script src="../static/js/jquery-1.11.1.min.js"></script>
<script src="../static/js/modernizr.custom.js"></script>
<!--webfonts-->
<link href='../static/css/own/google_font.css' rel='stylesheet' type='text/css'>
<!--//webfonts--> 
<!--animate-->
<link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
<script src="../static/js/wow.min.js"></script>
	<script>
		 new WOW().init();
	</script>
<!--//end-animate-->
<!-- Metis Menu -->

<!--//Metis Menu -->
<!-- Metis Menu -->
<script src="../static/js/metisMenu.min.js"></script>
<script src="../static/js/custom.js"></script>
<link href="../static/css/custom.css" rel="stylesheet">
<!--//Metis Menu -->

</head> 

<body class="cbp-spmenu-push">
	<div class="main-content">
		<#include "rightNav.ftl">
		<#include "head.ftl">
		<#include "${content}.ftl">
	</div>
	
	<!-- Classie -->
		<script src="../static/js/classie.js"></script>
		<script>
			var menuLeft = document.getElementById( 'cbp-spmenu-s1' ),
				showLeftPush = document.getElementById( 'showLeftPush' ),
				body = document.body;
				
			showLeftPush.onclick = function() {
				classie.toggle( this, 'active' );
				classie.toggle( body, 'cbp-spmenu-push-toright' );
				classie.toggle( menuLeft, 'cbp-spmenu-open' );
				disableOther( 'showLeftPush' );
			};
			

			function disableOther( button ) {
				if( button !== 'showLeftPush' ) {
					classie.toggle( showLeftPush, 'disabled' );
				}
			}
		</script>
	<!-- Bootstrap Core JavaScript --> 
		
        <script type="text/javascript" src="../static/js/bootstrap.min.js"></script>

        <script type="text/javascript" src="../static/js/dev-loaders.js"></script>
        <script type="text/javascript" src="../static/js/dev-layout-default.js"></script>
		<script type="text/javascript" src="../static/js/jquery.marquee.js"></script>
		<link href="../static/css/bootstrap.min.css" rel="stylesheet">

		<script type="text/javascript" src="../static/js/jquery.jqcandlestick.min.js"></script>
		<link rel="stylesheet" type="text/css" href="../static/css/jqcandlestick.css" />
		
		<!--max-plugin-->
		<script type="text/javascript" src="../static/js/plugins.js"></script>
		<!--//max-plugin-->
		
		<!--scrolling js-->
		<script src="../static/js/jquery.nicescroll.js"></script>
		<script src="../static/js/scripts.js"></script>
		<!--//scrolling js-->
</body>

<script src="../static/js/own/admin.js"></script>
<script src="../static/js/own/ajaxUtil.js"></script>
</html>