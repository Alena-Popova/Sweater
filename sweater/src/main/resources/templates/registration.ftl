<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
Add new user
<div class="mb-1"></div>
${message?ifExists}
    <@l.login "/registration" true/>
</@c.page>