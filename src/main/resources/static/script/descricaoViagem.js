document.getElementById('arrow-up').addEventListener('click', function() {
    window.scrollTo({ top: 0, behavior: 'smooth' });
});

const params = new URLSearchParams (window.location.search);
const id = params.get ('id');

axios.get('/trip_id/' + id)
    .then(function (response){
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

    .catch(function (error){
        console.error(error);
        console.log("Deu erro")
    })

console.log ("Esse é o id recuperado da url: " + id);

// <!-- Pop up Script -->
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

