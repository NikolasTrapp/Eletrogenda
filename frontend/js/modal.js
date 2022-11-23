// Função para apresentar a janela modal referente aos agendamentos do dia pressionado
function showListModal() {
    // Pegar a div que conterá a lista de agendamentos deste dia modal
    const schedules = document.getElementById("schedules");
    //Adicionar o titulo à janela (data pressionada)
    document.getElementById("schedule-modal-label").textContent = this.id;
    document.getElementById("add-schedule-modal-label").textContent = this.id;
    // Adicionar os agendamentos a uma lista no corpo da janela modal
    schedules.innerHTML = ""; // Limpando a janela para evitar itens duplicados
    let count = 0;
    for (scheduling of data) {
        if (scheduling.initialDate.substring(0, 10) === this.id) {
            let span = document.createElement("span");
            span.setAttribute("class", "d-block");
            let text = document.createTextNode(`${scheduling.initialDate} - ${scheduling.finalDate}`);
            span.appendChild(text);
            schedules.appendChild(span);
            count++;
        }
    }

    if(count === 0) schedules.appendChild(document.createTextNode("This day has no schedules!"));
    document.getElementById("bt-add").setAttribute("rel", this.id);
}

function getValues(){
    let initialDate = document.getElementById("initialDate").value;
    let finalDate = document.getElementById("finalDate").value;
    let group = document.querySelector("select[name=classes-select]").value;
    let classroom = document.querySelector("select[name=classrooms-select]").value;   
    let teacher = JSON.parse(sessionStorage.getItem("teacher"));
    let nodeEquipments = document.querySelectorAll(".checked .item-text");
    let equipments = [];

    nodeEquipments.forEach(e => {
        equipments.push({
            id: e.getAttribute("rel")
        });
    });

    sendData(initialDate, finalDate, group, classroom, teacher, equipments)
}

async function loadValuesToDropdowns(){
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
        option.value = element.name;
        option.text = element.name;
        dropdown.appendChild(option);
    });

}