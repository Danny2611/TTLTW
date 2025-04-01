const  totalAmount = document.getElementById("totalAmount")
const city = document.getElementById('city')
const district= document.getElementById('district')
const fee = document.getElementById('fee')
const totalPayment = document.getElementById("totalPayment")
let valueOfGoods=0;
 async function getCart() {
    try {
        const response = await fetch("http://localhost:8080/FinalLapTrinhWeb_war/api/cart");
        const cart = await response.json();
        console.log("cart" , cart)
        valueOfGoods = cart.totalAmount
        totalAmount.innerHTML = `Tổng: ${cart.totalAmount} VND`
        totalPayment.innerHTML= `Tổng tiền thanh toán: ${totalAmount} VND`
    } catch (error) {
        console.error("Error fetching cart:", error);
        }
}
getCart()


// GET FEE
const getFee = async () => {
    const cityValue = city.value.trim();
    const districtValue = district.value.trim();

    if (cityValue && districtValue) {
        console.log(valueOfGoods)
        try {
            const url = `http://localhost:8080/FinalLapTrinhWeb_war/user/checkout?city=${encodeURIComponent(cityValue)}&district=${encodeURIComponent(districtValue)}&value=${valueOfGoods}`;
            const response = await fetch(url);
            const data = await response.json();
            fee.innerHTML = `Phí vận chuyển: ${data.fee.fee} VND`
            totalPayment.innerHTML= `Tổng tiền thanh toán: ${valueOfGoods + data.fee.fee} VND`
        } catch (error) {
            console.error("Lỗi khi lấy phí:", error);
        }
    }

};

city.addEventListener("input", getFee);
district.addEventListener("input", getFee);