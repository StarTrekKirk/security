<#import "/lib.ftl" as lib>
<@lib.html location="角色管理" title="角色管理" jses=["/js/datalist.js","/js/roles.js"] csses=["/css/datalist.css"]>
	<@lib.datalist>
		<button id="add">新增</button>
		<button id="remove">删除</button>
	</@lib.datalist>
</@lib.html>