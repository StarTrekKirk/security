<#import "lib.ftl" as lib>
<@lib.html title="主页" location="主页" csses=["/resources/css/home.css"]>
	<div id="content">
		<span>
			<#if permissions.queryuser>
				<span class="module"><a id="usersettings" href="${ctx}/accounts" title="用户管理"></a></span>
			</#if>
			<#if permissions.queryrole>
				<span class="module"><a id="rolesettings" href="${ctx}/roles" title="角色管理"></a></span>
			</#if>
			<#if permissions.querypermission>
				<span class="module"><a id="permissionsettings" href="${ctx}/premissions" title="权限管理"></a></span>
			</#if>
		</span>
	</div>
</@lib.html>