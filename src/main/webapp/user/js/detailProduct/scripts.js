window.addEventListener("scroll", () => {
    var header = document.querySelector(".container");
    header.classList.toggle("sticky", window.scrollY > 90);

    var headding = document.querySelector(".summary-inner");
    headding.classList.toggle("headding-fix", window.scrollY > 40)

    var coor1 = document.querySelector(".detail-product .summary-inner")
    var coor2 = document.querySelector(".detail-product")
    headding.classList.toggle("headding-absolute", window.scrollY > coor2.clientHeight - coor1.clientHeight + 40)
});


var items = document.querySelectorAll(".product-image-thumbnail img");
var previewImg = document.querySelector(".product-image-wrap img")
var seeMore = document.getElementById('see-more')

items.forEach(item => {
    item.addEventListener("click", e => {
        previewImg.src = e.target.src
        seeMore.href = e.target.src
    })
});

var containerImg = document.querySelector('.product-image-wrap')

containerImg.addEventListener("mousemove", e => {
    var x = e.clientX - 100;
    var y = e.clientY - 90;

    previewImg.style.transformOrigin = `${x}px ${y}px`
    previewImg.style.transform = "scale(1.3)"
})

containerImg.addEventListener("mouseleave", e => {
    previewImg.style.transformOrigin = "center"
    previewImg.style.transform = "scale(1)"
})

// add to cart

addToCartButton = document.querySelectorAll(".add-to-cart-button");

document.querySelectorAll('.add-to-cart-button').forEach(function(addToCartButton) {
    addToCartButton.addEventListener('click', function() {
        addToCartButton.classList.add('added');
        setTimeout(function(){
            addToCartButton.classList.remove('added');
        }, 2000);
    });
});



