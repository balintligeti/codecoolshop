init();

function init() {
    getDataForPrice()
        .then(() => setConfirmListener());
}

async function getDataForPrice() {
    let url = window.location.origin + "/api/order";
    await fetch(url)
        .then((response) =>
            response.json()
        )
        .then( data =>
            setPrice(data)
        )
}

async function deleteOrder() {
    let url = window.location.origin + "/api/order/delete";
    await fetch(url)
}

function setPrice(data) {
    let priceContainer = document.querySelector("#price");
    priceContainer.innerHTML = data.totalPriceWithCurrency;
}

function setConfirmListener() {
    let confirmContainer = document.querySelector("#confirm");
    confirmContainer.addEventListener("click",deleteOrder);
}

