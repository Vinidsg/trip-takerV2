
function validarCPF(cpf) {
    cpf = cpf.replace(/[^\d]+/g, ''); // Remove formatação
    if (cpf.length !== 11 || /^(\d)\1{10}$/.test(cpf)) return false; // Verifica tamanho e sequências

    let soma = 0;
    let resto;

    // Validação do primeiro dígito verificador
    for (let i = 1; i <= 9; i++)
        soma = soma + parseInt(cpf.substring(i-1, i)) * (11 - i);
    resto = (soma * 10) % 11;

    if ((resto === 10) || (resto === 11)) resto = 0;
    if (resto !== parseInt(cpf.substring(9, 10))) return false;

    soma = 0;
    // Validação do segundo dígito verificador
    for (let i = 1; i <= 10; i++)
        soma = soma + parseInt(cpf.substring(i-1, i)) * (12 - i);
    resto = (soma * 10) % 11;

    if ((resto === 10) || (resto === 11))  resto = 0;
    if (resto !== parseInt(cpf.substring(10, 11))) return false;

    return true;
}

document.getElementById('signupForm').addEventListener('submit', async function(e) {
    e.preventDefault();

    const cpf = document.getElementById('cpf').value;
    if (!validarCPF(cpf)) {
        alert('CPF inválido.');
        return;
    }

    const formData = {
        email: document.getElementById('email').value,
        username: document.getElementById('username').value,
        cpf: cpf,
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
