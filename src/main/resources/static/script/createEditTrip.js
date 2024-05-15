const urlParams = new URLSearchParams(window.location.search);
const id = parseInt(urlParams.get('id'), 10);

let objetoOriginal = null;

function redirecionarParaListar() {
    window.location.href = 'gerenciar.html';
}


function salvar() {

    const trip = {
        qtdPessoas: document.querySelector("#qtdPessoas").value,
        local: document.querySelector("#local").value,
        guiaResponsavel: document.querySelector("#guiaResponsavel").value,
        valorUnitario: parseFloat(document.querySelector("#vlrUnitario").value),
        dataInicio: document.querySelector("#dtInicio").value,
        dataFinal: document.querySelector("#dtFinal").value,
        descricao: document.querySelector("#descricao").value,
    };

    var files = document.querySelector("#formFile").files[0];

    var formData = new FormData();

    console.log(files)

    console.log(urlParams)
    console.log(id)
    console.log(formData)


    if(urlParams != 0) {

        let objetoPatch = criarObjetoPatch(trip);
        formData.append("data", JSON.stringify(objetoPatch));

        fetch(`/trips/edit/${id}`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
            },
            body: formData
        }).then(function (response) {
            if (response.status === 200) {
                window.location.href = "gerenciar.html";
            }
        }).catch(error => {
            console.error("Erro ao cadastrar: ", error);
        });

    } else {
        formData.append("data", JSON.stringify(trip));
        formData.append("files", files);

        fetch(`/trips/create`, {
            method: 'POST',
            body: formData,
        }).then(function (response) {
            if (response.status === 201) {
                window.location.href = "gerenciar.html";
            }
        })
            .catch(error => {
                console.error("Erro ao cadastrar: ", error);
            });
    }


}

function criarObjetoPatch(objetoEditado) {
    let objetoPatch = [];

    for (let chave in objetoEditado) {
        if (objetoOriginal[chave] !== objetoEditado[chave]) {
            objetoPatch.push({
                op: 'replace',
                path: '/' + chave,
                value: objetoEditado[chave]
            });
        }
    }

    return objetoPatch;
}

if(urlParams != 0) {
    window.addEventListener('load', editarTrip);
}

async function editarTrip() {
    try {
        const response = await fetch(`/trip_id/${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        });
        const trip_details = await response.json();

        objetoOriginal = trip_details;

        console.log(trip_details)

        document.querySelector("#local").value = trip_details.local;
        document.querySelector("#qtdPessoas").value = trip_details.qtdPessoas;
        document.querySelector("#guiaResponsavel").value = trip_details.guiaResponsavel;
        document.querySelector("#vlrUnitario").value = trip_details.valorUnitario;
        document.querySelector("#dtInicio").value = trip_details.dataInicio;
        document.querySelector("#dtFinal").value = trip_details.dataFinal;
        document.querySelector("#descricao").value = trip_details.descricao;

        const img = document.createElement("img");

        img.src = trip_details.image;
        img.width = 200;
        img.height = 150;

        document.querySelector("#imagem-container").appendChild(img);


    } catch (error) {
        console.error('Erro ao buscar usuário:', error);
    }
}
