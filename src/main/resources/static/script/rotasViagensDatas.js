// JavaScript to fetch trips data from REST API and populate the HTML
document.addEventListener('DOMContentLoaded', function() {
    fetchTrips();
});

function fetchTrips() {
    fetch('/api/trips') // Adjust the API endpoint as needed
        .then(response => response.json())
        .then(data => {
            const container = document.getElementById('tripsContainer');
            data.forEach(trip => {
                const tripElement = `
                    <div class="col-12 container">
                        <div class="row">
                            <div class="col-12 col-md-5 hover-zoomin" id="info-banner">
                                <img class="img-thumbnail" width="500" height="450" src="${trip.image}" alt="...">
                            </div>
                            <div class="col-12 col-md-7 bg-secondary-color" id="info-content">
                                <div class="row">
                                    <div class="col-12">
                                        <div class="font-descricao">
                                            <h2 class="title secondary-color">${trip.local}</h2>
                                            <p class="subtitle secondary-color">${trip.descricao}</p>
                                            <p class="subtitle secondary-color">Data início: ${trip.dataInicio} - Data final: ${trip.dataFinal}</p>
                                            <p class="subtitle secondary-color">Quantidade: ${trip.qtdPessoas} - Valor: ${trip.valorUnitario}</p>
                                            <p class="subtitle secondary-color">Guia: ${trip.guiaResponsavel}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>`;
                container.innerHTML += tripElement;
            });
        }).catch(error => console.error('Error fetching trips:', error));
}

document.getElementById("loginBtn").addEventListener("click", function() {
    window.location.href = "/login";
});
document.getElementById('arrow-up').addEventListener('click', function() {
    window.scrollTo({ top: 0, behavior: 'smooth' });
});
