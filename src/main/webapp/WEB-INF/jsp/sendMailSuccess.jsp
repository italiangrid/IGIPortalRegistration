<%@ include file="/WEB-INF/jsp/init.jsp"%>

<div class="portlet-msg-success"> Mail successfully sent.<br/> Close this pop-up. </div>

<script>
	var count=3;
	
	var counter=setInterval(timer, 1000); //1000 will  run it every 1 second
	
	function timer(){
		count=count-1;
		if (count <= 0){
			clearInterval(counter);
			window.parent.document.closeFrame();
			return;
		}
		$("#timer").html(count); // watch for spelling
	}
</script>
This Pop-up will be closed in <strong><span id="timer">3</span></strong> secs.
