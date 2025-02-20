window.addEventListener('scroll', () => {
    var header = document.querySelector('.container')
    header.classList.toggle('sticky', window.scrollY > 100)
})

function resetPassword() {
    var emailInput = document.getElementById("email-forgot");
    var errorDiv = document.getElementById("email-error");

    if (emailInput.value === "") {
        errorDiv.textContent = "Vui lòng nhập email.";
        errorDiv.style.display = "block";
        emailInput.style.borderColor = "red";
    } else {
        errorDiv.style.display = "none";
        emailInput.style.borderColor = "";
    }
}


function validateSignin() {
    var emailInput = document.getElementById("email");
    var emailErrorDiv = document.getElementById("email-error");
    var passwordInput = document.getElementById("password");
    var passwordErrorDiv = document.getElementById("password-error");

    var emailValue = emailInput.value;
    var passwordValue = passwordInput.value;

    if (emailValue.trim() === "") {
        emailErrorDiv.textContent = "Vui lòng nhập email.";
        emailErrorDiv.style.display = "block";
        emailInput.style.borderColor = "red";
    }
    else {
        emailErrorDiv.style.display = "none";
        emailInput.style.borderColor = "";
    }

    if (passwordValue.trim() === "") {
        passwordErrorDiv.textContent = "Vui lòng nhập mật khẩu.";
        passwordErrorDiv.style.display = "block";
        passwordInput.style.borderColor = "red";
    } else {
        passwordErrorDiv.style.display = "none";
        passwordInput.style.borderColor = "";
    }
}

function signUpValidate() {
    var emailInput = document.getElementById("email-forgot");
    var errorDiv = document.getElementById("email-error");

    if (emailInput.value === "") {
        errorDiv.textContent = "Vui lòng nhập email.";
        errorDiv.style.display = "block";
        emailInput.style.borderColor = "red";
    } else {
        errorDiv.style.display = "none";
        emailInput.style.borderColor = "";
    }
}

