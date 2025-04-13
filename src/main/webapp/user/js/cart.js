
 async function fetchCart() {
    try {
        const response = await fetch("http://localhost:8080/FinalLapTrinhWeb_war/api/cart");
        const cart = await response.json();
        renderCart(cart);
        console.log("cart" , cart)
    } catch (error) {
        console.error("Error fetching cart:", error);
        document.getElementById("cart-container").innerHTML = "<h3 style='text-align: center;'>Không thể tải giỏ hàng.</h3>";
    }
}

function renderCart(cart) {
    const cartContainer = document.getElementById("cart-container");

    if (!cart.cartItems || cart.cartItems.length === 0) {
        cartContainer.innerHTML = "<h3 style='text-align: center;'>Giỏ hàng trống!</h3>";
        return;
    }

    cartItemsHTML = cart.cartItems.map(item => {
        if (!item.product || typeof item.quantity === "undefined") return "";

        const {product} = item;
        const totalPrice = product.price * item.quantity;

        return `
                    <tr>
                        <td class="shoping__cart__item">
                            <img src="http://localhost:8080/FinalLapTrinhWeb_war/${product.imageUrl}" alt="${product.productName}">
                            <h5>${product.productName}</h5>
                        </td>
                        <td class="shoping__cart__price">${product.price.toLocaleString()} VND</td>
                        <td class="shoping__cart__quantity">
                            <div class="quantity" style=" display: flex;gap: 14px;">
                                <button style="padding: 5px 10px;outline: none;border: none;" class="update-cart-btn" data-action="decrement" data-id="${product.id}">-</button>
                                <p>${item.quantity}</p>
                                <button style="padding: 5px 10px;outline: none;border: none;" class="update-cart-btn" data-action="increment" data-id="${product.id}">+</button>
                            </div>
                        </td>
                        <td class="shoping__cart__total">${totalPrice.toLocaleString()} VND</td>
                        <td class="shoping__cart__item__close">
                            <button style="padding: 5px 10px;outline: none;border: none;" class="delete-cart-btn" data-id="${product.id}">X</button>
                        </td>
                    </tr>`;
    }).join("");

    cartContainer.innerHTML = `
                <div class="shoping__cart__table">
                    <table>
                        <thead>
                            <tr>
                                <th>Sản phẩm</th>
                                <th>Giá bán</th>
                                <th>Số lượng</th>
                                <th>Tổng</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>${cartItemsHTML}</tbody>
                    </table>
                </div>
                <div class="shoping__checkout">
                    <h5>TỔNG TIỀN GIỎ HÀNG</h5>
                    <p style="text-align: right;font-size: 18px;color: red;">Tổng: <span style="font-size: 18px;font-weight: bold;">${cart.totalAmount.toLocaleString()} VND</span></p>
                    <a href="check_out.jsp" class="primary-btn">TIẾN HÀNH THANH TOÁN</a>
                </div>`;

    attachCartEvents();
}

function attachCartEvents() {
    document.querySelectorAll(".update-cart-btn").forEach(btn => {
        btn.addEventListener("click", async () => {
            const productId = btn.getAttribute("data-id");
            console.log("productId" , productId)
            const action = btn.getAttribute("data-action");
            await updateCart(productId, action);
        });
    });

    document.querySelectorAll(".delete-cart-btn").forEach(btn => {
        btn.addEventListener("click", async () => {
            const productId = btn.getAttribute("data-id");
            await deleteFromCart(productId);
        });
    });
}

async function updateCart(productId, action) {
    if(action === "decrement"){
        console.log('decrement')
        try {
            await fetch("http://localhost:8080/FinalLapTrinhWeb_war/api/cart/decrement", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ productId})
            });
            fetchCart();
        } catch (error) {
            console.error("Error updating cart:", error);
        }
    }
    if(action === "increment"){
        try {
            await fetch("http://localhost:8080/FinalLapTrinhWeb_war/api/cart/increment", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ productId})
            });
            fetchCart();
        } catch (error) {
            console.error("Error updating cart:", error);
        }
    }
}

async function deleteFromCart(productId) {
    try {
        await fetch("http://localhost:8080/FinalLapTrinhWeb_war/api/cart/delete", {
            method: "DELETE",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ productId })
        });
        fetchCart();
    } catch (error) {
        console.error("Error deleting item from cart:", error);
    }
}

document.addEventListener("DOMContentLoaded", fetchCart);