<#macro cardslist>
<div class="card-columns">
    <#list messages as message>
        <div class="card my-3">
        <#if message.filename??>
        <img src="/img/${message.filename}" class="card-img-top">
        </#if>
            <div class="m-2">
                <span>${message.text}</span>
                <div class="mr-1"><b><i>Tag: ${message.tag}</i></b></div>
            </div>
            <div class="card-footer text-muted">
                Author:
                ${message.authorName}
            </div>
            <form method="get" action="/main/${message.getId()}" id="formBag">
                <div class="input-group mb-3">
                    <input type="text" name="answer" id="answerId"
                           class="form-control ml-3 mr-1"
                           aria-label="Recipient's username with two button addons"
                           aria-describedby="button-addon4">
                    <div class="input-group-append" id="button-addon4">
                        <button class="btn btn-primary mr-1" type="submit">Add Comment</button>
                    </div>
                </div>
            </form>
        </div>
    <#else>
          No messages
    </#list>
</div>
</#macro>


<#macro productslist>
<div class="card-columns">
        <#list products as product>
        <div class="card my-3">
        <#if product.filename??>
        <img src="/img/${product.filename}" class="card-img-top">
        </#if>
            <div class="m-2">
                <span>${product.title}</span>
                <div class="mr-1"><b><i>Tag: ${product.tag}</i></b></div>
            </div>
            <div class="card-footer text-muted">
                Seller:
                ${product.authorName}
            </div>
            <form method="get" action="/products/${product.getId()}" id="formBag">
                <div class="input-group mb-3">
                    <input type="number" name="quantity"
                           class="form-control ml-3 mr-1"
                           aria-label="Recipient's username with two button addons"
                           aria-describedby="button-addon4">
                    <div class="input-group-append" id="button-addon4">
                        <button class="btn btn-primary mr-1" type="submit">Buy</button>
                    </div>
                </div>
            </form>
            <div class="card card-body">
                <div>
                    <div class="font-weight-bold">Seller:
                        <div class="font-weight-normal">
                            ${product.authorName}
                        </div>
                    </div>
                </div>
                <div>
                    <div class="font-weight-bold">Price:</div>
                    <span class="input-group-text">$ ${product.getPrice()} </span></div>
                <div>
                    <div class="font-weight-bold">Material:</div>
                    ${product.getMaterial()}</div>
                <div>
                    <div class="font-weight-bold">Manufacturer country:</div>
                    ${product.getCountry()} </div>
                <div>
                    <div class="font-weight-bold">Description:</div>
                    ${product.getDescription()} </div>
            </div>
        </div>
        <#else>
          No products
        </#list>
</div>
</#macro>

<#macro bagSell>
<div class="card-columns">
    <#list products as product, quantity>
        <div class="card my-3">
        <#if product.filename??>
        <img src="/img/${product.filename}" class="card-img-top">
        </#if>
            <div class="m-2">
                <span>${product.title}</span>
                <div class="mr-1"><b><i>Tag: ${product.tag}</i></b></div>
            </div>
            <div class="card-footer text-muted">
                Seller:
                ${product.authorName}
            </div>
            <div class="card card-body">
                <div>
                    <div class="font-weight-bold">Seller:
                        <div class="font-weight-normal">
                            ${product.authorName}
                        </div>
                    </div>
                </div>
                <div>
                    <div class="font-weight-bold">Price:</div>
                    <span class="input-group-text">$ ${product.getPrice()} </span></div>
                <div>
                    <div class="font-weight-bold">Quantity:</div>
                    <div>${quantity} </div>
                </div>
                <div>
                    <div class="font-weight-bold">Material:</div>
                    ${product.getMaterial()}</div>
                <div>
                    <div class="font-weight-bold">Manufacturer country:</div>
                    ${product.getCountry()} </div>
                <div>
                    <div class="font-weight-bold">Description:</div>
                    ${product.getDescription()} </div>
            </div>
        </div>
    <#else>
          No products
    </#list>

</div>
</#macro>


<#macro bagSellbyUser>
<div class="card-columns">
    <#list products as product>
        <div class="card my-3">
        <#if product.filename??>
        <img src="/img/${product.filename}" class="card-img-top">
        </#if>
            <div class="m-2">
                <span>${product.title}</span>
                <div class="mr-1"><b><i>Tag: ${product.tag}</i></b></div>
            </div>
            <div class="card-footer text-muted">
                Seller:
                ${product.authorName}
            </div>
            <div class="card card-body">
                <div>
                    <div class="font-weight-bold">Seller:
                        <div class="font-weight-normal">
                            ${product.authorName}
                        </div>
                    </div>
                </div>
                <div>
                    <div class="font-weight-bold">Price:</div>
                    <span class="input-group-text">$ ${product.getPrice()} </span></div>
                <div>
                    <div class="font-weight-bold">Material:</div>
                    ${product.getMaterial()}</div>
                <div>
                    <div class="font-weight-bold">Manufacturer country:</div>
                    ${product.getCountry()} </div>
                <div>
                    <div class="font-weight-bold">Description:</div>
                    ${product.getDescription()} </div>
            </div>
        </div>
    <#else>
          No products
    </#list>

</div>
</#macro>



