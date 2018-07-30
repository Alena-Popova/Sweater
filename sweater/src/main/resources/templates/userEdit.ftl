<#import "parts/common.ftl" as c>
<@c.page>
User editor:

<form action="/user" method="post">
    <input type="text" value="${user.username}" value ="${user.username}">

    <#list roles as role>
        <div>
            <label><input type="checkbox" name="${role}">${role}</label>
        </div>
    </#list>
    <input type="text" value="${user.id}" name="userId">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button type="submit">Save</button>
</form>
</@c.page>
