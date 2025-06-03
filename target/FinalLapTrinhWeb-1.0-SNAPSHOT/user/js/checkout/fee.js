const totalAmount = document.getElementById("totalAmount");
const city = document.getElementById('city');
const district = document.getElementById('district');
const fee = document.getElementById('fee');
const totalPayment = document.getElementById("totalPayment");
const feeInput = document.getElementById("fee-input");
const totalAmountInput = document.getElementById("totalAmount-input");
const quantity = document.getElementById("quantity");

const discount = document.getElementById("discount");
const discountFee = document.getElementById("discount-fee");
const discountFeeInput = document.getElementById("discount-fee-input");

let valueOfGoods = 0;
let feeCost = 0;
let discountPercent = 1; // 1 means 100%, no discount

// Lấy giỏ hàng
async function getCart() {
    try {
        const response = await fetch("http://localhost:8080/FinalLapTrinhWeb_war/api/cart");
        const cart = await response.json();
        valueOfGoods = cart.totalAmount;
        quantity.value = cart.cartItems.reduce((acc, item) => acc + item.quantity, 0);
        totalAmount.innerHTML = `Tổng: ${cart.totalAmount} VND`;
        updateTotalPayment();
    } catch (error) {
        console.error("Error fetching cart:", error);
    }
}

// Tính tổng tiền thanh toán
function updateTotalPayment() {
    const totalBeforeDiscount = valueOfGoods + feeCost;
    const finalTotal = totalBeforeDiscount * discountPercent;

    totalAmountInput.value = finalTotal;
    totalPayment.setAttribute("data-id" ,finalTotal.toFixed(0) )
    totalPayment.innerHTML = `Tổng tiền thanh toán: ${finalTotal.toFixed(0)} VND`;
}

// Lấy phí vận chuyển
async function getFee() {
    const cityValue = city.value.trim();
    const districtValue = district.value.trim();

    if (cityValue && districtValue) {
        try {
            const url = `http://localhost:8080/FinalLapTrinhWeb_war/user/checkout?city=${encodeURIComponent(cityValue)}&district=${encodeURIComponent(districtValue)}&value=${valueOfGoods}`;
            const response = await fetch(url);
            const data = await response.json();

            feeCost = parseInt(data.fee.fee) || 0;
            fee.innerHTML = `Phí vận chuyển: ${feeCost} VND`;
            feeInput.value = feeCost;

            updateTotalPayment();
        } catch (error) {
            console.error("Lỗi khi lấy phí:", error);
        }
    }
}

// Lấy mã giảm giá
async function getDiscount() {
    const discountCode = discount.value.trim();

    if (!discountCode) {
        discountPercent = 1;
        discountFee.innerHTML = "";
        updateTotalPayment();
        return;
    }

    try {
        const url = `http://localhost:8080/FinalLapTrinhWeb_war/user/discount?discount=${encodeURIComponent(discountCode)}`;
        const response = await fetch(url);
        const data = await response.json();

        if (data.id) {
            discountPercent = (100 - data.discountValue) / 100;
            discountFee.innerHTML = `Giảm giá: ${data.discountValue}%`;
            discountFeeInput.value = data.id
        } else {
            discountPercent = 1;
            discountFee.innerHTML = "Mã không hợp lệ";
        }

        updateTotalPayment();
    } catch (error) {
        console.error("Lỗi khi lấy giảm giá:", error);
    }
}


//  call province
const citySelect = document.getElementById("city");
const districtSelect = document.getElementById("district");
const wardSelect = document.getElementById("addressLine2");


fetch("https://open.oapi.vn/location/provinces?page=0&size=100")
    .then(res => res.json())
    .then(data => {
        data.data.forEach(province => {
            const option = document.createElement("option");
            option.value = province.name;
            option.text = province.name;
            option.setAttribute("data-province-id", province.id); // Lưu id thay vì provinceId
            citySelect.appendChild(option);
        });
    });

// Khi chọn tỉnh → load huyện
citySelect.addEventListener("change", () => {
    const selectedOption = citySelect.options[citySelect.selectedIndex];
    const provinceId = selectedOption.getAttribute("data-province-id");
    // console.log("pro", provinceId)
    districtSelect.innerHTML = "<option value=''>Chọn Huyện / Quận</option>";
    wardSelect.innerHTML = "<option value=''>Chọn Xã / Phường</option>";

    fetch(`https://open.oapi.vn/location/districts/${provinceId}?page=0&size=100`)
        .then(res => res.json())
        .then(data => {
            data.data.forEach(district => {
                const option = document.createElement("option");
                option.value = district.name;
                option.text = district.name;
                option.setAttribute("data-district-id", district.id); // Lưu id thay vì districtId
                districtSelect.appendChild(option);
            });
        });
});

// Khi chọn huyện → load xã
districtSelect.addEventListener("change", () => {
    const selectedOption = districtSelect.options[districtSelect.selectedIndex];
    const districtId = selectedOption.getAttribute("data-district-id"); // Lấy id từ data attribute

    wardSelect.innerHTML = "<option value=''>Chọn Xã / Phường</option>";

    fetch(`https://open.oapi.vn/location/wards/${districtId}?page=0&size=100`)
        .then(res => res.json())
        .then(data => {
            data.data.forEach(ward => {
                const option = document.createElement("option");
                option.value = ward.name;
                option.text = ward.name;
                option.setAttribute("data-ward-id", ward.id); // Lưu id thay vì wardId
                wardSelect.appendChild(option);
            });
        });
});

// Gọi khi load lần đầu
getCart();

// Sự kiện cập nhật dữ liệu
city.addEventListener("input", () => {
    getFee();
    getDiscount();
});

district.addEventListener("input", () => {
    getFee();
    getDiscount();
});

discount.addEventListener("input", () => {
    getDiscount();
});
