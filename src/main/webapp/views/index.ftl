<#import "/spring.ftl" as spring />
<#include "macros/personFormInput.ftl"/>
<#include "macros/changePersonModal.ftl"/>
<!DOCTYPE HTML>
<html>
<head>
    <title>Spring CRUD example</title>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="resources/css/style.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2>Hello from Spring Freemaker CRUD example</h2>
    <form id="mainForm" class=".form-inline" action="saveProduct" method="POST">
        <label>Please add a Product :-)</label>
        <@productFormInput bean="product" withSubmit=true/>
    </form>
    <h3>Products history</h3>
    <ul class="unstyled prettyprint">
        <#if (products?? && products?size != 0)>
            <#list products as product>
                <li class="product-row">
                    <label class="force-middle">${product.description} / ${product.code} / ${product.price}</label>
                    <div class="force-middle product-data">
                        <input type="hidden" class="personHash" value="${product.hash}">
                        <input type="hidden" class="personName" value="${product.description}">
                        <!--<input type="hidden" class="personBirthDate" value="${product.formattedBirthDate}">-->
                        <input type="hidden" class="personEmail" value="${product.code}">
                        <i class="icon-edit" title="Edit product"></i>
                        <i class="icon-remove" title="Remove product"></i>
                    </div>
                </li>
            </#list>
        <#else>
            No product around.
        </#if>
    </ul>
<@changeProductModal/>
</div>
<script src="resources/js/jquery-2.0.2.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/code.js"></script>
</body>
</html>