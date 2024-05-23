document.addEventListener('DOMContentLoaded', function() {
    carregarViagens(); // Carregar as viagens quando o DOM estiver pronto
});

// Função para carregar as viagens do backend
function carregarViagens() {
    fetch('/trip_list') // Rota do backend para obter as viagens
        .then(response => response.json())
        .then(trips => {
            const container = document.querySelector('.row.imagem');
            container.innerHTML = '';

            trips.forEach(trip => {
                const card = document.createElement('div');
                card.classList.add('col-md-4');
                card.innerHTML = `
                    <div class="card mb-3">
                        <a href="/createEditTrip.html?id=${trip.id}">
                            <img src="${trip.image}" alt="Imagem da Viagem" class="card-img-top">
                        </a>
                        <div class="card-body">
                            <form onsubmit="deleteTrip(event, ${trip.id})">
                                <button type="submit" class="btn btn-danger" id="deleteBtn_${trip.id}">Deletar</button>
                            </form>
                        </div>
                    </div>
                    <div class="padding-top"></div>
                `;
                container.appendChild(card);
            });
        })
        .catch(error => console.error('Erro ao carregar viagens:', error));
}

// Função para deletar uma viagem
function deleteTrip(event, id) {
    event.preventDefault(); // Impede o envio tradicional do formulário

    fetch(`/trips/${id}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                // Atualizar a interface do usuário após a exclusão
                const cardToRemove = document.querySelector(`#deleteBtn_${id}`).closest('.col-md-4');
                cardToRemove.remove();
            } else {
                console.error('Erro ao deletar a viagem:', response.statusText);
            }
        })
        .catch(error => console.error('Erro ao deletar a viagem:', error));
}

// Event listener para o botão de logout
document.getElementById("loginBtn").addEventListener("click", function() {
    window.location.href = "/logout";
});
