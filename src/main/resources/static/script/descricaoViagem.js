document.getElementById('arrow-up').addEventListener('click', function() {
    window.scrollTo({ top: 0, behavior: 'smooth' });
});

const params = new URLSearchParams(window.location.search);
const id = params.get('id');

axios.get('/trip_id/' + id)
    .then(function(response) {
        const data = response.data;
        document.getElementById('local').innerText = data.local;
        document.getElementById('descricao').innerText = data.descricao;
        document.getElementById('guiaResponsavel').innerText = data.guiaResponsavel;
        document.getElementById('qtdPessoa').innerText = data.qtdPessoas;
        document.getElementById('dtInicio').innerText = data.dataInicio;
        document.getElementById('dtFinal').innerText = data.dataFinal;
        document.getElementById('vlrUnitario').innerText = `R$ ${data.valorUnitario}`;
        document.getElementById('image').src = data.image;
    })
    .catch(function(error) {
        console.error("Erro ao recuperar os detalhes da viagem:", error);
        console.log("Deu erro");
    });

console.log("Esse é o id recuperado da url: " + id);

$(document).ready(function() {
    $('#reservarBtn').click(function() {
        $('#reservarModal').modal('show');
    });

    $('#fecharBtn').click(function() {
        $('#reservarModal').modal('hide');
    });

    $('#reservarForm').submit(function(event) {
        var numeroReserva = gerarNumeroReserva();
        document.getElementById('reservationNumber').value = numeroReserva;

        document.getElementById('mensagemEnviado').style.display = 'block';
        document.getElementById('enviarBtn').style.display = 'none';

        event.preventDefault();

        return false;
    });
});

function gerarNumeroReserva() {
    return Math.floor(1000 + Math.random() * 9000);
}

document.getElementById("loginBtn").addEventListener("click", function() {
    window.location.href = "/login";
});

document.getElementById('arrow-up').addEventListener('click', function() {
    window.scrollTo({ top: 0, behavior: 'smooth' });
});

document.getElementById('reservarForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const formData = new FormData(this);

    const dynamicTripId = id;

    if (!dynamicTripId) {
        alert('Houve um problema ao obter o ID da viagem. Por favor, tente novamente.');
        return;
    }

    fetch(event.target.action, {
        method: 'POST',
        body: formData,
    }).then(response => {
        if (response.ok) {
            document.getElementById('confirmationPopup').style.display = 'block';

            document.getElementById('closePopupBtn').addEventListener('click', function() {
                document.getElementById('confirmationPopup').style.display = 'none';
                window.location.href = `http://localhost:8080/descricaoViagem.html?id=${dynamicTripId}`;
            });
        } else {
            console.error("Erro na resposta do servidor:", response.statusText);
            alert('Houve um problema com o envio do formulário. Por favor, tente novamente.');
        }
    }).catch(error => {
        console.error('Erro ao enviar o formulário:', error);
        alert('Houve um problema com o envio do formulário. Por favor, tente novamente.');
    });
});