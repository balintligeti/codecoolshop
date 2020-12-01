const ORIGIN = window.location.origin
const API_LINK_CART_ITEMS = ORIGIN + '/api/order'
const API_LINK_ADD_TO_CART = origin + '/api/cart?product_id='
const API_LINK_INCREMENT_ORDER = ORIGIN + "/api/order/increment?id=";
const API_LINK_DECREMENT_ORDER = ORIGIN + "/api/order/decrement?id=";
const API_LINK_DELETE_ORDER = ORIGIN + "/api/order/delete/item?id=";

async function addToCartButtonListeners() {
    const addToCartButtons = document.querySelectorAll('#add-to-cart-button');
    for (const button of addToCartButtons) {
        let productID = parseInt(button.getAttribute("data-product-id"))
        button.addEventListener('click',
            () => addItemToOrder(productID))
    }
    $(document).on('click', 'ul .dropdown-menu', function (e) {
        e.stopPropagation();
    });
}


async function postToServerWithGet(API_LINK, productID) {
    try {
        let response = await fetch(API_LINK + productID)
        return await response
    } catch (error) {
        console.log(error)
    }
}

async function postOrderIncrement(id) {
    await fetch(API_LINK_INCREMENT_ORDER+id, {
        method: 'POST'
    });
}

async function postOrderDecrement(id) {
    await fetch(API_LINK_DECREMENT_ORDER+id, {
        method: 'POST'
    });
}

async function postOrderDelete(id) {
    await fetch(API_LINK_DELETE_ORDER+id, {
        method: 'POST'
    });
}


async function getCartData(API_LINK) {
    try {
        let response = await fetch(API_LINK)
        return await response.json()
    } catch (error) {
        console.log(error)
    }
}


async function addItemToOrder(productID) {
    await postToServerWithGet(API_LINK_ADD_TO_CART, productID)
    await renderShoppingCart(API_LINK_CART_ITEMS);
    console.log("Added to Cart")
}


async function renderShoppingCart(api_link){

    const orderData = await getCartData(api_link)
    const cartItems = orderData['cartItems'];
    const cartCount = orderData['itemCount'];
    const prices = orderData['cartPrices'];
    const ids = orderData['itemIds'];
    const fullPrice = orderData['totalPriceWithCurrency']
    const keys = Object.keys(cartItems)

    let cartInnerHtml = "";
    for (const product of keys) {
        let lineItemFullPrice = prices[product] * cartItems[product]
        cartInnerHtml += `
         <li>
              <span class="item">
                <span class="item-left">
                    <span class="item-info">
                        <span class="shopping-cart-item-title">${product}</span>
                        <span class="price-span">$${prices[product]}/unit</span>
                        <span>${cartItems[product]} unit</span>
                        <span>$${lineItemFullPrice}</span>
                    </span>
                </span>
                <span class="item-right">
                   <a class = "delete" href="javascript:void(0)" data-id="${ids[product]}"><i class="fa fa-times-circle fa-3" aria-hidden="true"></i></a>
                    <div class="change-amount-of-product">
                        <a href="javascript:void(0)"><i class="fas fa-minus button-decrement" data-id="${ids[product]}"></i></a>
                        <a href="javascript:void(0)"><i class="fas fa-plus button-increment" data-id="${ids[product]}"></a></i>
                    </div>              
                </span>
              </span>
        </li>
        <li class="divider"></li>
        `;
    }
    let totalPriceContainer = "";
    if(fullPrice) {
        totalPriceContainer +=
            `<li class="total-cart-price">Total price: ${fullPrice} </li>
             <li><a class="cart-bottom-text-center" href="/checkout">Checkout</a></li>`;
    } else {
        totalPriceContainer += `<div class="empty-basket-container">
                                <div class="empty-basket">
                                <span>Basket is empty &#128577;</span>
                                </div>
                                </div>`
    }

    await renderCartCount(cartCount)
    const itemContainer = document.querySelector(".dropdown-cart")
    itemContainer.innerHTML = "";
    itemContainer.insertAdjacentHTML("afterbegin", cartInnerHtml)
    itemContainer.insertAdjacentHTML("beforeend", totalPriceContainer)
    setButtonListeners();
}

function setButtonListeners() {
    let incrementContainers = document.querySelectorAll(".button-increment");
    for (let container of incrementContainers) {
        container.addEventListener("click", function () {
            let id = container.getAttribute("data-id");
            postOrderIncrement(id);
            renderShoppingCart(API_LINK_CART_ITEMS);
        });
    }

    let decrementContainers = document.querySelectorAll(".button-decrement");
    for (let container of decrementContainers) {
        container.addEventListener("click", function () {
            let id = container.getAttribute("data-id");
            postOrderDecrement(id);
            renderShoppingCart(API_LINK_CART_ITEMS);
        });
    }

    let deleteContainers = document.querySelectorAll(".delete");
    for (let container of deleteContainers) {
        container.addEventListener("click", function () {
            let id = container.getAttribute("data-id");
            postOrderDelete(id)
            renderShoppingCart(API_LINK_CART_ITEMS);
        });
    }

}

async function renderCartCount(cartCount) {
    const cartCountContainer = document.querySelector("#cart-count")
    cartCount.innerHTML = "";
    cartCountContainer.innerHTML = cartCount
}

async function main() {
    await addToCartButtonListeners();
    await renderShoppingCart(API_LINK_CART_ITEMS);
}


main().then(r => {
})