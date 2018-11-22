<#import "/lib.ftl" as lib>
<@lib.html>
	<div id="header">
		<span id='user'>${user}</span>
			<a href="/logout" target="_self">注销</a>
	</div>
	<div id="content">
		<h1>hello freemarker!</h1>
	</div>
</@lib.html>