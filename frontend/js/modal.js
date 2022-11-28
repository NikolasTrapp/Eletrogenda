function showListModal() {
    document.querySelector("#show-details").innerHTML = "";

    // Pegar a div que conterá a lista de agendamentos deste dia
    const schedules = document.getElementById("schedules");
    //Adicionar o titulo à janela (data pressionada)
    document.getElementById("schedule-modal-label").textContent = this.id;
    document.getElementById("add-schedule-modal-label").textContent = this.id;
    // Adicionar os agendamentos a uma lista no corpo da janela modal
    schedules.innerHTML = ""; // Limpando a janela para evitar itens duplicados
    let count = 0;
    for (scheduling of data) {
        if (scheduling.initialDate.substring(0, 10) === this.id) {
            const tr = createMarker(scheduling);
            schedules.appendChild(tr); // Linha a tabela
            count++;
        }
    }

    if (count === 0) schedules.innerHTML = "<tr><td colspan=14>Any schedule found!</td></tr>";
    document.getElementById("bt-add").setAttribute("rel", this.id);
}

function createMarker(scheduling) {
    const div = document.createElement("div"); // Criando o marcador
    div.addEventListener("click", () => {
        showScheduleDetails(scheduling);
    });
    div.className = "day-marker"; // Adicionando a classe de estilos ao marcador
    div.style.backgroundColor = scheduling.classroom.color; // Adicionando cor ao marcador
    div.textContent = scheduling.classroom.name;
    const tr = document.createElement("tr"); // Criando linha do marcador
    const td = document.createElement("td"); // Criando a celula do marcador
    const colspanValue = getColSpan(scheduling.initialDate, scheduling.finalDate) + 1; // Pegando o tamanho do marcador
    td.colSpan = colspanValue;
    const positionFromMarker = getPositionFromMarker(tr, scheduling.initialDate, scheduling.finalDate);
    td.appendChild(div); // Adicionando o marcador a celula
    tr.appendChild(td); // Adicionando a celula a linha
    fillRow(positionFromMarker, colspanValue, tr);
    return tr;
}

function getColSpan(inititalHour, finalHour) {
    const i = new Date(convertData(inititalHour)).getTime() / 60000;
    const f = new Date(convertData(finalHour)).getTime() / 60000;
    let d = Math.floor((f - i) / 45);
    d += (checkHour(inititalHour) < "09:45" && checkHour(finalHour) >= "09:45" && checkHour(inititalHour) < "13:30") ? 1 : 0;
    d += (checkHour(finalHour) >= "16:00") ? 1 : 0;
    return d;
}

function getPositionFromMarker(tr, initialHour, finalHour) {
    const partsI = initialHour.substring(11).split(":");
    const partsF = finalHour.substring(11).split(":");
    const minutes = partsI[0] * 60 + parseInt(partsI[1]);
    let diference = (partsF[0] > "12") ? minutes - (13 * 60 + 30) : minutes - 8 * 60;
    let position = (partsF[0] > "12") ? Math.floor(diference / 45) + 7 : Math.floor(diference / 45);
    position += (partsI[0] == "09" && partsI[1] == "45") ? 1 : 0;
    position += (partsI[0] == "16" && partsI[1] == "00") ? 1 : 0;

    for (let i = 0; i < position; i++) {
        const td = document.createElement("td");
        tr.appendChild(td);
    }

    return position;
}

function convertData(data) {
    const parts = data.split(/[ /]/);
    return parts[2] + "-" + parts[1] + "-" + parts[0] + " " + parts[3];
}

function checkHour(data){
    const parts = data.split(/[ /]/);
    return parts[3];
}

function fillRow(positionFromMarker, colspanValue, tr){
    for (let i = 0; i < 14 - positionFromMarker - colspanValue; i++){
        const td = document.createElement("td");
        tr.appendChild(td);
    }
}

function getValues() {
    let initialDate = document.querySelector("#initialHour");
    let finalDate = document.querySelector("#finalHour");
    let group = document.querySelector("select[name=classes-select]").value;
    let classroom = document.querySelector("select[name=classrooms-select]").value;
    let teacher = JSON.parse(sessionStorage.getItem("teacher"));
    let nodeEquipments = document.querySelectorAll(".checked .item-text");
    let equipments = [];

    if (group == 0 || classroom == 0 || teacher.id == 0) {
        showAlert("Hey!", "You left some field empty.", "warning", "Close");
        return false;
    };

    nodeEquipments.forEach(e => {
        equipments.push({
            id: e.getAttribute("rel")
        });
    });

    sendData(initialDate.options[initialDate.selectedIndex].value, finalDate.options[finalDate.selectedIndex].value, group, classroom, teacher, equipments)
}

async function loadValuesToDropdowns() {
    const classes = await getData("class");
    const classesSelect = document.querySelector("select[name=classes-select]");
    populateDropDowns(classesSelect, classes);

    const classrooms = await getData("classrooms");
    const classroomsSelect = document.querySelector("select[name=classrooms-select]");
    populateDropDowns(classroomsSelect, classrooms);

    const equipments = await getData("equipments");
    const equipmentsSelect = document.querySelector(".list-items");
    equipments.map(e => {
        const li = document.createElement("li");
        li.className = "item";
        const spanCheckbox = document.createElement("span");
        spanCheckbox.className = "checkbox";
        const spanText = document.createElement("span");
        spanText.className = "item-text";
        spanText.textContent = e.name;
        spanText.setAttribute("rel", e.id);
        li.appendChild(spanCheckbox);
        li.appendChild(spanText);
        equipmentsSelect.appendChild(li);
    });
    configurateDropdown();
}

function populateDropDowns(dropdown, data) {
    data.map(element => {
        const option = document.createElement("option");
        option.value = element.id;
        option.text = element.name;
        dropdown.appendChild(option);
    });

}

const alertPlaceholder = document.getElementById('show-details');


function showScheduleDetails(scheduling) {

    alertPlaceholder.innerHTML = "";
    createLi("Id:", scheduling.id);
    createLi("Initial date:", scheduling.initialDate);
    createLi("Final date:", scheduling.finalDate);
    createLi("Classroom:", scheduling.classroom.name);
    createLi("Class:", scheduling.group.name);
    createLi("Teacher:", scheduling.teacher.name);
    if (scheduling.equipment.length > 0){
        alertPlaceholder.appendChild(document.createTextNode("Equipments:"));
        for (key in scheduling.equipment) {
            createLi(`${scheduling.equipment[key].quantity} x `, scheduling.equipment[key].name);
        }
    }
}

function createLi(key, attribute) {
    const li = document.createElement("li");
    const text = document.createTextNode(`${key} ${attribute}`);
    li.appendChild(text);
    alertPlaceholder.appendChild(li)
}