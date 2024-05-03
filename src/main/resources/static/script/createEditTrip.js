
function redirecionarParaListar() {
    window.location.href = 'gerenciar.html';
}

function salvar() {
    const Tripdata = {
        guiaResponsavel: document.querySelector("#guiaResponsavel").value,
        valorUnitario: parseFloat(document.querySelector("#vlrUnitario").value),
        dataInicio: document.querySelector("#dtInicio").value,
        dataFinal: document.querySelector("#dtFinal").value,
        descricao: document.querySelector("#descricao").value,
    };

    // Obtendo o arquivo do input do formulário
    var file = document.getElementById("formFile").files[0];

    // Criando um novo objeto FormData
    var formData = new FormData();

    // Adicionando a string 'data' como um parâmetro
    formData.append("data", JSON.stringify(Tripdata));

    // Adicionando o arquivo ao FormData
    formData.append("files", file);

    fetch(`http://localhost:8080/trips`, {
        method: 'POST',
        body: formData
    }).then(function (response) {
        if (response.status === 201) {
            window.location.href = "gerenciar.html";
        }
    }).catch(error => {
        console.error("Erro ao cadastrar: ", error);
    });
}


