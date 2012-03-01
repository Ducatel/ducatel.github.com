$(function(){

	$( "#onglet" ).tabs();

	$('#affImplCaptcha').click(function(){
		$('#divCaptchaImpl').slideToggle('slow');
	});
	
	$('#affScreenCaptcha').click(function(){
		$('#divCaptchaScreen').slideToggle('slow');
	});

	$('#affImplZip').click(function(){
		$('#divBackupZipImpl').slideToggle('slow');
	});
	
	$('#affExempleOpenglSalle').click(function(){
		$('#OpenglSalle').slideToggle('slow');
	});
	
	$('#affExempleSkeletonClassGenerator').click(function(){
		$('#SkeletonClassGenerator').slideToggle('slow');
	});
	
	$('#affExempleFileIntegrityChecker').click(function(){
		$('#FileIntegrityChecker').slideToggle('slow');
	});
	
	$('#affExempleQShutdown').click(function(){
		$('#QShutdown').slideToggle('slow');
	});
	
	$('#affExempleJRSSFeedAggregator').click(function(){
		$('#JRSSFeedAggregator').slideToggle('slow');
	});
	
	$('#affExempleLesCreasDeSab').click(function(){
		$('#LesCreasDeSab').slideToggle('slow');
	});
	
	$('#affExemplePavagePlan').click(function(){
		$('#PavagePlan').slideToggle('slow');
	});

	$('#affExempleCRC').click(function(){
		$('#CRC').slideToggle('slow');
	});

	$('#affExemplecodageReseau').click(function(){
		$('#codageReseau').slideToggle('slow');
	});
	
	$('#affDemocodageReseau').click(function(){
		$('#demoCodageReseau').slideToggle('slow');
	});

	$('#affExempleRoutage').click(function(){
		$('#Routage').slideToggle('slow');
	});
	
	$('#affExempleTeXloud').click(function(){
		$('#TeXloud').slideToggle('slow');
	});
	
});