document.getElementById('signupForm').addEventListener('submit', async function(e) {
    e.preventDefault();

    const formData = {
        email: document.getElementById('email').value,
        username: document.getElementById('username').value,
        cpf: document.getElementById('cpf').value,
        password: document.getElementById('password').value
    };

    console.log('Enviando dados do formulário:', formData);

    try {
        const response = await fetch('/register', { // Esta URL deve corresponder ao seu endpoint Spring Boot
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        });

        const responseData = await response.json(); // Assumindo que a resposta é um JSON

        if (response.ok) {
            alert('Cadastro realizado com sucesso!');
            console.log('Resposta do servidor:', responseData);
            window.location.href = 'login.html'; // Redirecionar para login após cadastro
        } else {
            alert('Erro no cadastro: ' + responseData.message);
            console.error('Erro na resposta do servidor:', responseData);
        }
    } catch (error) {
        alert('Falha na comunicação com o servidor.');
        console.error('Erro na comunicação:', error);
    }
});
