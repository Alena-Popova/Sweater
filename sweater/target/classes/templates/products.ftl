<#import "parts/common.ftl" as c>
<#import "parts/cards.ftl" as d>
<#import "parts/offers.ftl" as p>

<@c.page>
<a class="btn btn-warning mb-3" data-toggle="collapse" href="#collapseExampleProduct" role="button" aria-expanded="false" aria-controls="collapseExampleProduct">
    Add new Product
</a>
<div class="collapse <#if product??>show</#if>" id="collapseExampleProduct">
    <@p.sell />
</div>
    <@d.productslist />
</@c.page>