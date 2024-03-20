document.addEventListener('DOMContentLoaded', function() {
    const loginBtn = document.getElementById("loginBtn");
    if (loginBtn) {
        loginBtn.addEventListener("click", function() {
            window.location.href = "/login";
        });
    }

    const arrowUp = document.getElementById('arrow-up');
    if (arrowUp) {
        arrowUp.addEventListener('click', function() {
            window.scrollTo({ top: 0, behavior: 'smooth' });
        });
    }
});
