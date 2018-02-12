<#--html指令-->
<#macro html location user title jses=[] csses=[] ctx="/app">
	<!DOCTYPE HTML>
	<html>
		<head>
			<title>${title}</title>
			<meta charset="utf-8">
			<meta ctx="${ctx}">
			<meta name="renderer" content="webkit|ie-comp|ie-stand">
			<meta http-equiv="content-Type" content="text/html charset=UTF-8">
			<meta name="author" content="feihu">
			<link type="text/css" rel="stylesheet" href="${ctx}/css/html.css"></script>
			<#list csses as css>
				<link type="text/css" rel="stylesheet" href="${ctx}${css}"></script>
			</#list>
			<script type="text/javascript" src="/app/js/jquery-3.2.1.js"></script>
			<script type="text/javascript" src="${ctx}/js/html.js"></script>
		</head>
		<body>
			<div class="header">
				<span class="header-before">
					<a class="header-home" href="${ctx}/home">home</a>
					<span class="header-flag">当前位置:</span>
					<span class="header-location">${location}</span>
				</span>
				<span class="header-after">
					<span class="header-user">${user}</span>
					<span class="header-logout"><a href="/app/logout" target="_self">注销</a></span>
				</span>
			</div>
			<#nested>
			<#list jses as js>
				<script type="text/javascript" src="${ctx}${js}"></script>
			</#list>
		</body>
	</html>
</#macro>
<#--datalist指令-->
<#macro datalist columns data>
	<div class="datalist">
		<div class="datalist-header">
			<#if permissions.add>
				<button id="add">新增</button>
			</#if>
			<#if permissions.remove>
				<button id="remove">删除</button>
			</#if>
			<#nested>
		</div>
		<div class="datalist-table">
			<table>
				<thead>
					<tr>
						<#if permissions.remove>
							<th>
								<input class="datalist-selector-all" type="checkbox">
							</th>
						</#if>
						<#list columns as column>
							<th>${column}</th>
						</#list>
						<#if permissions.remove || permissions.update>
							<th colspan=2>操作选项</th>
						</#if>
					</tr>
				</thead>
				<tbody>
					<#list data as row>
						<tr>
							
							<#if permissions.remove>
								<td>
									<input class="datalist-selector" type="checkbox">
								</td>
							</#if>
							<#list row as item>
								<td>${item}</td>
							</#list>
							<#if permissions.update>
								<td>
									<a class="datalist-edit" href="javascript:void(0)">编辑</a>
								</td>
							</#if>
							<#if permissions.remove>
								<td>
									<a class="datalist-remove"  href="javascript:void(0)">删除</a>
								</td>
							</#if>
						</tr>
					</#list>
				</tbody>
			</table>
		</div>
	</div>
</#macro>
<#macro form fields>
	<div class="form">
		<form enctype="application/x-www-form-urlencoded">
			<table>
				<tbody>
					<#list fields as field>
						<tr class="form-item <#if field.type='hidden'>form-item-hidden</#if>">
							<td>${field.caption}:</td>
							<td>
								<#switch field.type>
									<#case "radio">
										<#list field.options as option>
											<#local kv=option?split(":")>
											<#local k=kv[0]>
											<#local v=kv[1]>
											<input name="${field.name}" type="${field.type}" value="${v}" <#if v=(field.value?string('true','false'))>checked</#if>>${k}
										</#list>
									<#break>
									<#case "checkbox">
										<#list field.options as option>
											<#local kv=option?split(":")>
											<#local k=kv[0]>
											<#local v=kv[1]>
											<input name="${field.name}" type="${field.type}" value="${v}" <#if field.value?seq_contains(v?number)>checked</#if>>${k}
										</#list>
									<#break>
									<#default>
										<input name="${field.name}" type="${field.type}" value="${field.value}">
								</#switch>
							</td>
						<tr>
					</#list>
					<tr class="form-item">
						<td colspan=2>
							<button id="form-submit" type="button">保存</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>	
</#macro>