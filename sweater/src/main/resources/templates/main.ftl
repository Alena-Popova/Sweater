<#import "parts/common.ftl" as c>

<@c.page>
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/main" class="form-inline">
            <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by tag">
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form>
    </div>
</div>

<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Add new Message
</a>
<div class="collapse <#if message??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                       value="<#if message??>${message.text}</#if>" name="text" placeholder="Введите сообщение" />
                <#if textError??>
                <div class="invalid-feedback">
                    ${textError}
                </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control"
                       value="<#if message??>${message.tag}</#if>" name="tag" placeholder="Тэг">
                <#if tagError??>
                    <div class="invalid-feedback">
                        ${tagError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Добавить</button>
            </div>
        </form>
    </div>
</div>

<div class="card-columns">
    <#list messages as message>
    <div class="card my-3">
        <#if message.filename??>
        <img src="/img/${message.filename}" class="card-img-top">
        </#if>
        <div class="m-2">
            <span>${message.text}</span>
            <div class="mr-1"> <b><i>Tag: ${message.tag}</i></b></div>
        </div>
        <div class="card-footer text-muted">
            Seller:
            ${message.authorName}
        </div>

        <form method="get" action="/main/${message.getId()}" id="formBag">
            <div class="input-group mb-3">
                <input type="number" name="numbers" id="numberId"
                       class="form-control ml-3 mr-1" placeholder="1"
                       value="1"
                       aria-label="Recipient's username with two button addons"
                       aria-describedby="button-addon4">
                <div class="input-group-append" id="button-addon4">
                    <button class="btn btn-primary mr-1" type="submit">Add to bag</button>
                </div>
            </div>
        </form>
        <div class="mb-3 ml-3">
            <button class="btn btn-warning ml-1 mr-3" type="button" data-toggle="collapse"
                    data-target="#collapseExampleInfo" aria-expanded="false" aria-controls="collapseExampleInfo">
                Info
            </button>

            <div class="collapse" id="collapseExampleInfo">
                <div class="card card-body">
                    <div>Seller: ${message.authorName}</div>
                    <div>Price: -- </div>
                    <div>Material: -- </div>
                    <div>Manufacturer country: -- </div>
                </div>
            </div>
        </div>

    </div>
    <#else>
    No message
    </#list>
</div>
</@c.page>