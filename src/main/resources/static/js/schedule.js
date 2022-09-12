sessionStorage.setItem("teacher", JSON.stringify({"id": "1", "name": "Nikolas", "email": "nikolas@gmail.com"}));
let today = new Date(); // Pegando o dia de hoje
let currentMonth = today.getMonth(); // Mes atual (-1)
let currentYear = today.getFullYear(); // Ano atual
let months = ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"];

let monthAndYear = document.getElementById("monthAndYear"); // Titulo do mes do calendario
let data; // Agendamentos
springApiUrl = "http://localhost:8080";
jsonServerApiUrl = "http://localhost:3000";

async function getData() {
    const request = await fetch(`${jsonServerApiUrl}/schedulings`); // Pegando os agendamentos
    const schedulings = await request.json(); // Guardando-os em uma variavel no escopo globar para serem usandos quantas vezes necessário
    return schedulings; // Retornando os dados
}

function generateCalendar(month, year) {
    //Pegando o dia da semana que a mesma começa
    let firstDay = (new Date(year, month).getDay());
    //Numero de dias no mes atual
    let daysInMonth = 32 - new Date(year, month, 32).getDate();

    //Calendario
    let calendar = document.getElementById("calendar");
    calendar.innerHTML = ""; // Limpar calendário

    monthAndYear.innerHTML = months[month] + " " + year; // Ajeitando o titulo do calendário

    let date = 1; // Dia do mes atual
    let previousMonthDate = 1; // Dia do mes anterior
    let nextMonthDate = 1; // Dia do próximo mes
    for (let i = 0; i < 6; i++) {
        //Criando linha das semanas
        let row = document.createElement("div");
        row.setAttribute("class", "row");

        //Criando as colunas (dias)
        for (let j = 0; j < 7; j++) {
            let col = document.createElement("div"); // Cria a coluna/celula
            col.setAttribute("class", "col"); // Adiciona a classe col para a coluna
            col.setAttribute("onclick", "showListModal(this)");
            if (i === 0 && j < firstDay) { // Se estiver na primeira coluna e j for menor que o dia da semana que a mesma incia:
                let daysFromPreviousMonth = 32 - new Date(year, month - 1, 32).getDate(); // Numero de dias do mes passado
                let colText = document.createTextNode(daysFromPreviousMonth - firstDay + previousMonthDate); // Criando texto dos dias da semana do mes passado
                col.appendChild(colText); // Adicionando o texto na celula
                day = ((month === 0) ? year - 1 : year) + "-" +
                    ((month < 10) ? (month === 0) ? "12" : "0" + month : month) + "-" +
                    (daysFromPreviousMonth - firstDay + previousMonthDate); // Ajustando o id deste dia
                col.id = day;
                row.appendChild(col); // Adicionando celula do dia na semana
                previousMonthDate++;
            }
            else if (date > daysInMonth) {
                let colText = document.createTextNode(nextMonthDate); // Criando texto dos dias da semana do mes seguinte
                col.appendChild(colText); // Adicionando o texto na celula
                day = ((month + 1 === 12) ? year + 1 : year) + "-" +
                    ((month + 2 < 10) ? "0" + (month + 2) : (month + 2 === 13) ? "01" : month + 2) + "-" +
                    ((nextMonthDate < 10) ? "0" + nextMonthDate : nextMonthDate);
                col.id = day; // Atribuindo o id
                row.appendChild(col); // Adicionando celula do dia na semana
                nextMonthDate++;
            }
            else {
                day = `${currentYear}-${(month + 1 < 10) ? "0" + (month + 1) : month + 1}-${(date < 10) ? "0" + date : date}`;
                col.id = day;
                let colText = document.createTextNode(date);  // Criando texto dos dias da semana
                col.appendChild(colText); // Adicionando o texto na celula
                row.appendChild(col); // Adicionando celula do dia na semana
                date++; // Somando mais um dia
            }
        }
        calendar.appendChild(row); // Adicionando a semana no calendário
    }

    populateSchedulings(); // Colocar os agendamentos nas celulas
}


function populateSchedulings() {
    //Função para adicionar os agendamentos ao calendário
    for (let scheduling of data) { //Para cada agendamento nos dados recebidos
        //Pegar o id do dia do agendamento (caso este dia esteja na tela)
        let col = document.getElementById(scheduling.initialDate.substring(0, 10))
        if (col != null) {
            let c = document.createTextNode(" " + scheduling.id); // Criar um texto com o id do agendamento
            col.appendChild(c); // Adicionando o id do agendamento da coluna
        }
    }
}

function next() {
    // Função para passar os meses
    // Se o mes atual for 11 (Janeiro) o ano atual será acrescentado em 1 caso contrário manterá o valor
    currentYear = (currentMonth === 11) ? currentYear + 1 : currentYear;
    // Define o mes atual
    currentMonth = (currentMonth + 1) % 12;
    generateCalendar(currentMonth, currentYear); // Invoca a função de gerar o calendário
}


function previous() {
    // Função para retartar um mes
    // Se o mes for == 0 (Janeiro) o ano será reduzido em 1 caso contrário menterá o valor
    currentYear = (currentMonth === 0) ? currentYear - 1 : currentYear;
    // Caso o mes seja janeiro, o mes será definido para 11 (dezembro) senão só reduzirá 1
    currentMonth = (currentMonth === 0) ? 11 : currentMonth - 1;
    generateCalendar(currentMonth, currentYear); // Invoca função de gerar calendário
}

function showListModal(pressedDiv) {
    // Função para apresentar a janela modal referente aos agendamentos do dia pressionado
    // Pegar a janela modal
    const modal = document.getElementById("listOfSchedulingsPerDay");
    //Adicionar o titulo à janela (data pressionada)
    document.getElementById("listModalTitle").textContent = pressedDiv.id;
    // Adicionar os agendamentos a uma lista no corpo da janela modal
    let list = document.getElementById("schedulings-list");
    list.innerHTML = ""; // Limpando a janela para evitar itens duplicados
    for (scheduling of data) {
        if (scheduling.initialDate.substring(0, 10) === pressedDiv.id) {
            let li = document.createElement("li");
            let text = document.createTextNode(`${scheduling.initialDate} - ${scheduling.finalDate}`);
            li.appendChild(text);
            list.appendChild(li);
        }
    }
    modal.style.display = "flex"; // Fazendo a modal aparecer
}

function hideModal(id) {
    // Função para esconder a modal através do id fornecido
    const modal = document.getElementById(id);
    modal.style.display = "none";
    if (id === "addNewScheduling") {
        modal.removeEventListener("change", addEquipment);
    }
}

async function showAddModal() {
    hideModal('listOfSchedulingsPerDay'); // Escondendo a modal de lista de equipamentos
    const addModal = document.getElementById("addNewScheduling");
    const listModalTitle = document.getElementById("listModalTitle").textContent;
    document.getElementById("addModalTitle").textContent = listModalTitle;
    const equipmentsList = document.getElementById("listOfEquipments");
    equipmentsList.innerHTML = "";
    addModal.style.display = "flex";

    const getClassroomsFromBack = await fetch(`${jsonServerApiUrl}/classrooms`); // pegando os dados do backend
    const classrooms = await getClassroomsFromBack.json(); // reconvertendo os dados para json
    const classroomsDropDown = document.getElementById("classroom-list"); // pegando o dropdown das salas de aula
    populateDropDowns(classroomsDropDown, classrooms);

    //tudo se repete para as turmas e equipamentos:
    const getClassesFromBack = await fetch(`${jsonServerApiUrl}/class`);
    const classes = await getClassesFromBack.json();
    const classesDropDown = document.getElementById("class-list");
    populateDropDowns(classesDropDown, classes);

    const getEquipmentsFromBack = await fetch(`${jsonServerApiUrl}/equipments`);
    const equipments = await getEquipmentsFromBack.json();
    const equipmentsDropDown = document.getElementById("equipment-list");
    populateDropDowns(equipmentsDropDown, equipments);
    equipmentsDropDown.addEventListener("change", addEquipment);
}

function addEquipment() {
    const equipmentsList = document.getElementById("listOfEquipments");
    let idList = [];
    Array.from(equipmentsList.rows).forEach(tr => (idList.includes(tr.cells[0].id)) ? null : idList.push(tr.cells[0].id));

    if (this.options[this.selectedIndex].text != "nenhum(a)" && !idList.includes(this.value)) {
        let row = equipmentsList.insertRow();
        let name = row.insertCell(0);
        name.innerHTML = this.options[this.selectedIndex].text;
        name.id = this.value;
        let del = row.insertCell(1);
        let del_link = document.createElement("a");
        del_link.setAttribute("href", "#");
        del_link.innerHTML = "remove";
        del_link.addEventListener("click", function () {
            equipmentsList.removeChild(row);
        });
        del.appendChild(del_link);
    }
}

function populateDropDowns(dropdown, data) {
    dropdown.innerHTML = "";
    let noneOption = document.createElement("option");
    noneOption.value = "0";
    noneOption.text = "nenhum(a)";
    dropdown.appendChild(noneOption);
    noneOption.selected;
    data.map(item => {
        let option = document.createElement("option");
        option.value = item.id;
        option.text = item.name;
        dropdown.appendChild(option);
    })
}

async function sendData() {
    // pegando os id's dos elementos selecionados:
    let initialHour = document.getElementById("initialHour").value;
    let finalHour = document.getElementById("finalHour").value;
    let classroom = document.getElementById("classroom-list").value;
    let classs = document.getElementById("class-list").value;
    let equipments = document.getElementById("listOfEquipments");
    let day = document.getElementById("addModalTitle").textContent;

    let data = JSON.stringify({
        initialDate: `${day}T${initialHour}:00`,
        finalDate: `${day}T${finalHour}:00`,
        teacher: { "id": JSON.parse(sessionStorage.getItem("teacher")).id },
        classroom: { "id": classroom },
        group: { "id": classs },
        equipment: Array.from(equipments.rows).map((tr) => ({"id": tr.cells[0].id}))
    });

    // Enviando os dados para o backend
    const response = await fetch(`${jsonServerApiUrl}/schedulings`, {
        method: "POST",
        body: data,
        headers: {
            'Content-Type': 'application/json'
        }
    });

    const responseText = await response.text();
    console.log(responseText);
    const addModal = document.getElementById("addNewScheduling")
    addModal.style.display = "none";
}



// Chamar a função getData, esperar ela retornar os valores
getData().then(
    (schedulings) => {
        data = schedulings; // Atribuir os valores à variável no escopo global
        generateCalendar(currentMonth, currentYear); // Chamar a função de gerar o calendario
    }).catch( // Se der erro:
        (err) => alert(err) // Mostra o erro podre
    );