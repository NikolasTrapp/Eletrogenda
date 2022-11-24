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
            const tr = createMarker(scheduling);
            schedules.appendChild(tr); // Linha a tabela
            count++;
        }
    }

    if(count === 0) schedules.innerHTML = "<tr><td colspan=14>Any schedule found!</td></tr>";
    document.getElementById("bt-add").setAttribute("rel", this.id);
}

function createMarker(scheduling){
    let div = document.createElement("div"); // Criando o marcador
    div.addEventListener("click", () => {
        showAlert(scheduling, "danger");
    });
    div.className = "day-marker"; // Adicionando a classe de estilos ao marcador
    div.style = `background-color: ${scheduling.classroom.color};` // Adicionando cor ao marcador
    let tr = document.createElement("tr"); // Criando linha do marcador
    let td = document.createElement("td"); // Criando a celula do marcador
    td.colSpan=getColSpan(scheduling.initialDate, scheduling.finalDate)+1; // Pegando o tamanho do marcador
    getPositionFromMarker(schedules, tr, scheduling.initialDate, scheduling.finalDate);
    td.appendChild(div); // Adicionando o marcador a celula
    tr.appendChild(td); // Adicionando a celula a linha
    return tr;
}

function getColSpan(inititalHour, finalHour){
    let i = new Date(convertData(inititalHour)).getTime() / 60000;
    let f = new Date(convertData(finalHour)).getTime() / 60000;
    return Math.floor((f-i)/45);
}

function getPositionFromMarker(tbody, tr, initialHour, finalHour){
    let partesI = initialHour.substring(11).split(":");
    let partesF = finalHour.substring(11).split(":");
    let minutes = partesI[0] * 60;
    let diference = (partesF[0] > "12") ? minutes + parseInt(partesI[1]) - (13*60+30) : minutes + parseInt(partesI[1]) - 8*60;
    let position = (partesF[0] > "12") ? Math.floor(diference / 45) + 6 : Math.floor(diference / 45);

    for (let i = 0; i < position; i++){
        let td = document.createElement("td");
        tr.appendChild(td);
    }
    tbody.appendChild(tr);
}

function convertData(data){
    parts = data.split(/[ /]/);
    return parts[2]+"-"+parts[1]+"-"+parts[0]+" "+parts[3];
}

function getValues(){
    let initialDate = document.querySelector("#initialHour");
    let finalDate = document.querySelector("#finalHour");
    let group = document.querySelector("select[name=classes-select]").value;
    let classroom = document.querySelector("select[name=classrooms-select]").value;   
    let teacher = JSON.parse(sessionStorage.getItem("teacher"));
    let nodeEquipments = document.querySelectorAll(".checked .item-text");
    let equipments = [];

    if (group == 0 || classroom == 0 || teacher.id == 0) return false;

    nodeEquipments.forEach(e => {
        equipments.push({
            id: e.getAttribute("rel")
        });
    });

    sendData(initialDate.options[initialDate.selectedIndex].value, finalDate.options[finalDate.selectedIndex].value, group, classroom, teacher, equipments)
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
        option.value = element.id;
        option.text = element.name;
        dropdown.appendChild(option);
    });

}

const alertPlaceholder = document.getElementById('alertPlaceholder');

// const showAlert = (message, type) => {
//   const wrapper = document.createElement('div')
//   wrapper.innerHTML = [
//     `<div class="alert alert-${type} alert-dismissible" role="alert">`,
//     `   <div>${message}</div>`,
//     '   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>',
//     '</div>'
//   ].join('');

//   alertPlaceholder.append(wrapper);
// }

function showAlert(scheduling, type){
    alertPlaceholder.innerHTML = "";
    const wrapper = document.createElement('div');
    wrapper.classList = `alert alert-${type} alert-dismissible`;
    wrapper.setAttribute("role", "alert");
    const message = document.createElement("div");
    const initialDate = document.createElement("span").textContent = "Initial date: " + scheduling.initialDate;
    const finalDate = document.createElement("span").textContent = "Final date: " +  scheduling.finalDate; 
    const teacher = document.createElement('span').textContent = "Teacher name: " +  scheduling.teacher.name;
    const group = document.createElement('span').textContent = "Class: " +  scheduling.group.name;
    const classroom = document.createElement('span').textContent = "Classroom: " +  scheduling.classroom.name;
    const equipments = document.createElement('span').textContent = "Equipments: " +  scheduling.equipment.map(e => `${e.name} x ${e.quantity}`).join(`   `);
    message.append(initialDate, finalDate, teacher, group, classroom, equipments);
    wrapper.appendChild(message);
    wrapper.innerHTML += `<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>`;
    alertPlaceholder.append(wrapper);
}