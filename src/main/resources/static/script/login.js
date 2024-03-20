document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById('loginForm');
    form.onsubmit = async (e) => {
        e.preventDefault();
        const formData = {
            username: document.getElementById('username').value,
            password: document.getElementById('password').value
        };

        try {
            const response = await fetch('/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData)
            });

            if (response.ok) {
                // Redirect to another page or show success message
                alert('Login bem-sucedido!');
            } else {
                const message = await response.text();
                alert('Falha no login: ' + message);
            }
        } catch (error) {
            alert('Erro ao enviar o pedido de login.');
        }
    };
});
