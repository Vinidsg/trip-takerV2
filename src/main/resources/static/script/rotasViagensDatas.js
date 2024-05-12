// JavaScript to fetch trips data from REST API and populate the HTML
document.addEventListener('DOMContentLoaded', function() {
    fetchTrips();
});


function fetchTrips() {
    fetch('/trip_list')
        .then(response => response.json())
        .then(data => {
            const container = document.getElementById('tripsContainer');
            data.forEach(trip => {

                function formatDate(dateString) {
                    const [year, month, day] = dateString.split('-');
                    return `${day}/${month}/${year}`;
                }

                function formatarParaReal(valor) {
                    return valor.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
                }

                const tripElement = `
                    <div class="col-12 container trip" xmlns="http://www.w3.org/1999/html">
                        <div class="row">
                            <div class="col-12 col-md-5 hover-zoomin" id="info-banner">
                                <img class="img-thumbnail trip-image" width="500" height="450" src="${trip.image}" alt="...">
                            </div>
                            <div class="col-12 col-md-7 bg-secondary-color trip-info" id="info-content">
                                <div class="row">
                                    <div class="col-12">
                                        <div class="font-descricao">
                                            <h2 class="title secondary-color trip-local">${trip.local}</h2>
                                            <p class="subtitle secondary-color trip-description">${trip.descricao}</p>
                                            
                                            <div class="container-01">
                                                <div class="coluna-card-horizontal">
                                                         <div class="coluna-card-vertical">
                                                            <b><p class="subtitle secondary-color trip-dates-inicio">Partida</p></b>
                                                            <p class="subtitle secondary-color trip-dates-inicio">${formatDate(trip.dataInicio)}</p>
                                                         </div>
                                                         
                                                         <div class="coluna-card-vertical">
                                                           <b><p class="subtitle secondary-color trip-dates-final">Volta</p></b>
                                                            <p class="subtitle secondary-color trip-dates-final">${formatDate(trip.dataFinal)}</p>
                                                        </div>
                                                    
                                                        <div class="coluna-card-vertical">
                                                            <b><p class="subtitle secondary-color trip-details">Quantidade</p></b>
                                                            <p class="subtitle secondary-color trip-details">${trip.qtdPessoas}</p>
                                                        </div>
                                                </div>
                                                
                                                        <div class="second-container">
                                                            <div class="coluna-card-horizontal-second">
                                                                <div class="coluna-card-vertical-second">
                                                                    <b><p class="subtitle secondary-color trip-valor">Guia</p></b>
                                                                    <p class="subtitle secondary-color trip-guide">${trip.guiaResponsavel}</p>  
                                                                </div>
                                                            </div>
                                                                
                                                             <div class="coluna-card-horizontal-second-column-value">
            
                                                                 <div class="coluna-card-vertical-second">
                                                                    <b><p class="subtitle secondary-color trip-valor">Valor</p></b>
                                                                    <p class="subtitle secondary-color trip-valor">${formatarParaReal(trip.valorUnitario)}</p>    
                                                                </div>
                                                             </div>   
                                                        </div>  
                                            </div>   
                                        </div>
                                        
                                            <div class="container-button">
                                                <a href="http://localhost:8080/descricaoViagem.html?id=${trip.id}" class="buttonAgendar" id="loginBtn" style="text-decoration: none !important;">Agendar</a>
                                            </div>

                                         
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>`;
                container.insertAdjacentHTML('beforeend', tripElement);
            });
        }).catch(error => console.error('Error fetching trips:', error));
}


document.getElementById("loginBtn").addEventListener("click", function() {
    window.location.href = "/login";
});
document.getElementById('arrow-up').addEventListener('click', function() {
    window.scrollTo({ top: 0, behavior: 'smooth' });
});
