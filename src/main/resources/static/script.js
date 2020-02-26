const API_URL = 'http://localhost:8080/cars';
const carsDiv = document.getElementById('cars');

getAllCars();

function getAllCars() {
    fetch(API_URL).then(response => response.json()).then((carsArr) => {
        const cardElements = carsArr.map(car =>
            createCard(car)
        ).join('\n');
        carsDiv.innerHTML = cardElements;
    });
}

function createCard(car) {
    return `
    <div class="card" id="card${car.id}">
                <div class="card-header row d-flex justify-content-center" id="heading${car.id}">
                    <div class="col-2">
                        <button type="button" class="btn btn-outline-danger btn-sm" onclick="deleteCar(${car.id})">Delete</button>
                    </div>
                    <div class="col-10 text-center">
                        <h2 class="mb-0">
                            <button class="btn btn-link collapsed" type="button" data-toggle="collapse"
                                data-target="#collapse${car.id}" aria-expanded="false" aria-controls="collapse${car.id}">
                                <span id="cardCarId${car.id}">${car.id}</span>  <span id="cardCarMark${car.id}">${car.mark}</span> <span id="cardCarModel${car.id}">${car.model}</span>  <span id="cardCarColor${car.id}">${car.color}</span> <span id="cardCarProdYear${car.id}">${car.productionYear}</span>
                            </button>
                        </h2>
                    </div>
                </div>
                <div id="collapse${car.id}" class="collapse" aria-labelledby="heading${car.id}"
                    data-parent="#cars">
    
                </div>
    </div>
    `
}

function deleteCar(id) {
    fetch(`${API_URL}/${id}`, {
        method: 'DELETE',
    }).then(processOkResponse)
        .then(removeCarCard(id))
        .catch(console.warn);

}

function removeCarCard(id) {
    const cardToRemove = document.getElementById('card' + id);
    carsDiv.removeChild(cardToRemove);
}

function getParam() {
    return param = {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loadCarFields())
    }
}

function loadCarFields() {
    return car = {
        //id: document.getElementById('idFormInput').value,
        mark: document.getElementById('markFormInput').value,
        model: document.getElementById('modelFormInput').value,
        color: document.getElementById('colorFormInput').value,
        productionYear: document.getElementById('prodYearFormInput').value
    }
}

function showModalAddMenu() {
    fetch(API_URL, getParam())
        //.then(processOkResponse)
        .then(response => {
            return response.json()
        })
        .then(car => createNewCar(car))
        .catch(console.warn);
}

function searchCarByProdYear() {
    const fromProdYear = document.getElementById('fromProdYearInput').value;
    const toProdYear = document.getElementById('toProdYearInput').value;

    fetch(API_URL + '/year?fromYear=' + fromProdYear + '&toYear=' + toProdYear)
        .then(response => response.json())
        .then((carsArr) => {
            const cardElements = carsArr.map(car =>
                createCard(car)
            ).join('\n');
            carsDiv.innerHTML = cardElements;
        });
}

function createNewCar(car) {
    let doc = new DOMParser().parseFromString(createCard(car), 'text/html');
    carsDiv.append((doc.body.firstChild));
}

function processOkResponse(response = {}) {
    if (response.ok) {
        console.log(response);
        return true;
    }
    throw new Error(`Status not 200 (${response.status})`);
}