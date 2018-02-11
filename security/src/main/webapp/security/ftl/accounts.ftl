<#import "/lib.ftl" as lib>
<@lib.html location="用户管理" title="用户管理" jses=["/js/datalist.js","/js/accounts.js"] csses=["/css/datalist.css"]>
	<@lib.datalist>
		<button id="add">新增</button>
		<button id="enable">启用</button>
		<button id="disable">禁用</button>
		<button id="remove">删除</button>
	</@lib.datalist>
</@lib.html>