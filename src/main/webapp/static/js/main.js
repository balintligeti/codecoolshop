init();

function init() {
    getDataForDropDown("/api/suppliers","#dropdown-supplier","dropdown-item supplier")
        .then(() => createDropDownListeners("/api/supplier?id=",".dropdown-item.supplier"));
    getDataForDropDown("/api/categories","#dropdown-category","dropdown-item category")
        .then(() => createDropDownListeners("/api/category?id=",".dropdown-item.category"));
}

async function getDataForDropDown(route,id,className) {
    let url = window.location.origin + route;
    await fetch(url)
        .then((response) =>
            response.json()
        )
        .then( data =>
            createDropDownItem(data, id,className)
        )
}

async function getDataById(route) {
    let url = window.location.origin + route;
    await fetch(url)
        .then((response) =>
            response.json()
        )
        .then( data =>
            filterByCategory(data,route)
        )
}

function createDropDownItem(data, id, className) {
    let container = document.querySelector(id);
    let itemsHtml = "";
    for (let row of data) {
        itemsHtml += `
        <a class="${className}" data-id="${row.id}" href="#">${row.name}</a>`;
    }
    container.insertAdjacentHTML("beforeend",itemsHtml);
}

function createDropDownListeners(route, className) {
    let items = document.querySelectorAll(className);
    for (let item of items) {
        let id = parseInt(item.getAttribute("data-id"))
        item.addEventListener("click",function() {
            getDataById(route+id).then();
        })
    }
}

function filterByCategory(products, route) {
    refreshTitle(products,route)
    refreshProducts(products);
    addToCartButtonListeners().then(r => {});
}

function refreshTitle(products, route) {
    let title = document.querySelector("#category-title");
    title.innerHTML = "";
    let titleHtml = getTitleHtml(products, route);
    title.insertAdjacentHTML("beforeend",titleHtml);
}

function refreshProducts(products) {
    let productsContainer = document.querySelector("#products");
    productsContainer.innerHTML = "";
    let productsHtml = getProductsHtml(products);
    productsContainer.insertAdjacentHTML("beforeend",productsHtml);
}

function getTitleHtml(products,route) {
    let titleHtml;
    if (route.includes("category")) {
        titleHtml = `<strong>${products[0].productCategory.name}</strong>`;
    } else {
        titleHtml = `<strong>${products[0].supplier.name}</strong>`;
    }
    return titleHtml;
}

function getProductsHtml(products) {
    let productsHtml = "";
    for (let product of products) {
        productsHtml += `
         <div class="col col-sm-12 col-md-6 col-lg-4">
                <div class="card"> 
                    <img class="" src="/static/img/product_${product.id}.jpg"  alt="product image"/>
                    <div class="card-header">
                        <h4 class="card-title" >${product.name}</h4>
                        <p class="card-text" >${product.description}</p>
                    </div>
                    <div class="card-body">
                        <div class="card-text">
                            <p class="lead">${product.defaultPrice} ${product.defaultCurrency}</p>
                        </div>
                        <div class="card-text">
                            <a class="btn btn-primary" id="add-to-cart-button" data-product-id="${product.id}" href="#">Add to cart</a>
                        </div>
                    </div>
                </div>
         </div>`;
    }
    return productsHtml;
}


