<#import "parts/common.ftl" as c>
<#import "parts/cards.ftl" as d>
<@c.page>
<div class="alert alert-primary" role="alert">
    Ваша продуктовая корзина:
</div>
<div class="alert alert-success">Общая цена: <span> ${price} $</span> </div>
    <@d.bagSell />
</@c.page>