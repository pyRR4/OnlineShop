
function renderCart() {
    const cart = getCartFromCookies();
    let cartHtml = "";
    if (cart.length === 0) {
        cartHtml = `
            <tr class="text-center">
                <td colspan="6">
                    <h1 class="text-3xl font-extrabold py-4">There are no items in your cart!</h1>
                </td>
            </tr>`
    }
    cart.forEach(item => {
        cartHtml += `
            <tr class="hover">
                <td>${item.name}</td>
                <td>${item.price}</td>
                <td>
                    <button onclick="updateQuantity(${item.id}, -1)" class="btn btn-decrement mx-1">-</button>
                    <span id="quantity-${item.id}">${item.quantity}</span>
                    <button onclick="updateQuantity(${item.id}, 1)" class="btn btn-increment mx-1">+</button>
                </td>
                <td id="price-${item.id}">${(item.price * item.quantity).toFixed(2)}</td>
                <td class="flex items-end justify-end">
                    <a class="btn btn-circle btn-error mx-1" onclick="removeFromCart(${item.id})">
                    <i class="fa-solid fa-xmark"></i></a>
                </td>
            </tr>
        `;
    });
    document.getElementById("cartTableBody").innerHTML = cartHtml;
    renderTotalPrice();
}

function renderTotalPrice() {
    document.getElementById("totalPrice").innerText = getTotalPrice().toFixed(2);
}

function getTotalPrice() {
    const cart = getCartFromCookies();
    return cart.reduce((sum, item) => sum + (item.price * item.quantity), 0);
}

function updateQuantity(productId, change) {
    const cart = getCartFromCookies();
    console.log(cart);
    const id = parseInt(productId);
    const item = cart.find(item => parseInt(item.id) === id);

    if (item) {
        item.quantity += change;

        if (item.quantity < 1) {
            item.quantity = 1;
        }

        saveCartToCookies(cart);
        const quantityElement = document.getElementById(`quantity-${id}`);
        const priceElement = document.getElementById(`price-${id}`);
        const totalPrice = document.getElementById("totalPrice");

        if (quantityElement) {
            quantityElement.textContent = item.quantity;
        } else {
            console.error(`Nie znaleziono elementu z ID quantity-${id}`);
        }

        if (priceElement) {
            priceElement.textContent = (item.price * item.quantity).toFixed(2);
        } else {
            console.error(`Nie znaleziono elementu z ID price-${id}`);
        }

        if (totalPrice) {
            totalPrice.textContent = getTotalPrice().toFixed(2);
        } else {
            console.error(`Nie znaleziono elementu z ID price-${id}`);
        }

    } else {
        console.error(`Produkt z ID ${id} nie został znaleziony w koszyku.`);
    }
}

function removeFromCart(productId) {
    const id = parseInt(productId);
    let cart = getCartFromCookies();
    if (!cart) {
        console.error('Koszyk jest pusty lub nie istnieje.');
        return;
    }
    console.log(id);
    cart = cart.filter(item => parseInt(item.id) !== id);
    console.log(cart);
    saveCartToCookies(cart);
    renderCart();
}

function handleAddToCart(element) {
    const productId = element.getAttribute('data-id');
    const productName = element.getAttribute('data-name');
    const productPrice = parseFloat(element.getAttribute('data-price'));

    addToCart(productId, productName, productPrice);
}

function addToCart(productId, productName, productPrice) {
    const cart = getCartFromCookies();
    let item_quantity;

    const existingItem = cart.find(item => item.id === productId);
    if (existingItem) {
        existingItem.quantity++;
        item_quantity = existingItem.quantity;
    } else {
        cart.push({
            id: productId,
            name: productName,
            price: productPrice,
            quantity: 1
        });
        item_quantity = 1;
    }

    saveCartToCookies(cart);
    showNotification(`${productName} został dodany do koszyka!\nW koszyku znajduje się: ${item_quantity} sztuk tego produktu.`);
}

function showNotification(message) {
    const container = document.getElementById('notification-container');

    const notification = document.createElement('div');
    notification.className = 'bg-green-500 text-white px-4 py-2 rounded shadow-lg animate-fade-out';
    notification.textContent = message;

    container.appendChild(notification);

    setTimeout(() => {
        notification.remove();
    }, 2000);
}

function getCookie(name) {
    let value = "; " + document.cookie;
    let parts = value.split("; " + name + "=");
    if (parts.length === 2) return parts.pop().split(";").shift();
    return null;
}

function setCookie(name, value, days) {
    let expires = "";
    if (days) {
        let date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + value + expires + "; path=/";
}

function deleteCookie(name) {
    setCookie(name, "", -1);
}

function getCartFromCookies() {
    const cartJson = getCookie("cart");
    return cartJson ? JSON.parse(cartJson) : [];
}

function saveCartToCookies(cart) {
    setCookie("cart", JSON.stringify(cart), 7);
}

function logout() {
    deleteCookie("cart");
    deleteCookie("session");
}
