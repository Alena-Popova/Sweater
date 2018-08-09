<#include "security.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-lg  navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Sweater</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main">Messages </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/products">Products </a>
            </li>
            <#if user??>
            <li class="nav-item">
                <a class="nav-link" href="/user/profile">Profile</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/user-messages/${currentUserId}">My messages</a>
            </li>
            </#if>
        </ul>

        <div class="navbar-text mr-3">${name}</div>
<#if user??>
        <div class="btn-group mr-3 ml-3" role="group">
            <button id="btnGroupDrop1" type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                Basket
            </button>
            <div class="dropdown-menu mr-3" aria-labelledby="btnGroupDrop1">
                <a class="dropdown-item" href="/products/mysale/${currentUserId}">For sale</a>
                <a class="dropdown-item" href="/products/mybuy/${currentUserId}">For buy</a>
            </div>
        </div>
</#if>

        <form class="form-inline mr-2" method="get" action="/main">
            <input class="form-control mr-sm-2" type="search"
                   name="filter" value="${filter?ifExists}"
                   placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>

        <@l.logout />
    </div>
</nav>