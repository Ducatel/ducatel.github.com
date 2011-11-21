$(function(){

	$('#affImplCaptcha').click(function(){
		$('#divCaptchaImpl').slideToggle('slow');
	});

	$('#affImplZip').click(function(){
		$('#divBackupZipImpl').slideToggle('slow');
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
});