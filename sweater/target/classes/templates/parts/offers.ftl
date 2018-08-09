<#macro postfromuser>
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
</#macro>


<#macro sell>
    <div class="form-group mt-3">
        <form method="post" action="/products/sell" enctype="multipart/form-data">

            <div class="form-group">
                <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                       value="<#if product??>${product.title}</#if>" name="title" placeholder="Введите название" />
                <#if titleError??>
                <div class="invalid-feedback">
                    ${titleError}
                </div>
                </#if>
            </div>

            <div class="form-group">
                <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                       value="<#if product??>${product.tag}</#if>" name="tag" placeholder="Введите тег" />
                <#if tagError??>
                <div class="invalid-feedback">
                    ${tagError}
                </div>
                </#if>
            </div>

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">$</span>
                </div>
                <input type="text" name="price" class="form-control" aria-label="Amount (to the nearest dollar)"
                placeholder="Цена">
                <div class="input-group-append">
                    <span class="input-group-text">.00</span>
                </div>
            </div>

            <div class="form-group">
                <input type="text" class="form-control"
                       value="<#if product??>${product.material}</#if>" name="material"
                       placeholder="Материал">
            </div>

            <div class="form-group">
                <input type="text" class="form-control"
                       value="<#if product??>${product.country}</#if>" name="country"
                       placeholder="Страна производителя">
            </div>

            <div class="form-group">
                <input type="text" class="form-control"
                       value="<#if product??>${product.description}</#if>" name="description"
                       placeholder="Описание">
            </div>


            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Добавить продукт</button>
            </div>
        </form>
    </div>
</#macro>