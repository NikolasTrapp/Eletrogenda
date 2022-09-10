let today = new Date(); // Pegando o dia de hoje
let currentMonth = today.getMonth(); // Mes atual (-1)
let currentYear = today.getFullYear(); // Ano atual
let months = ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"];

let monthAndYear = document.getElementById("monthAndYear"); // Titulo do mes do calendario
let data; // Agendamentos

async function getData() {
    const request = await fetch("http://localhost:8080/schedulings"); // Pegando os agendamentos
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
            col.setAttribute("onclick", "showListModal(this)")
            if (i === 0 && j < firstDay) { // Se estiver na primeira coluna e j for menor que o dia da semana que a mesma incia:
                let daysFromPreviousMonth = 32 - new Date(year, month - 1, 32).getDate(); // Numero de dias do mes passado
                let colText = document.createTextNode(daysFromPreviousMonth - firstDay + previousMonthDate); // Criando texto dos dias da semana do mes passado
                col.appendChild(colText); // Adicionando o texto na celula
                day = ((month === 0) ? year - 1 : year) + "-" +
                    ((month < 10) ? (month === 0) ? "12" : "0" + month : month) + "-" +
                    (daysFromPreviousMonth - firstDay + previousMonthDate); // Ajustando o id deste dia
                col.id = day;
                row.appendChild(col); // Adicionando celula do dia na semana
                previousMonthDate++
            }
            else if (date > daysInMonth) {
                let colText = document.createTextNode(nextMonthDate); // Criando texto dos dias da semana do mes seguinte
                col.appendChild(colText); // Adicionando o texto na celula
                day = ((month + 1 === 12) ? year + 1 : year) + "-" +
                    ((month + 2 < 10) ? "0" + (month + 2) : (month + 2 === 13) ? "01" : month + 2) + "-" +
                    ((nextMonthDate < 10) ? "0" + nextMonthDate : nextMonthDate)
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
            let c = document.createTextNode(scheduling.id); // Criar um texto com o id do agendamento
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
    const modal = document.getElementById("listOfSchedulingsPerDay");
    document.getElementById("listModalTitle").textContent = pressedDiv.id;
    let list = document.getElementById("schedulings-list");
    list.innerHTML = "";
    for (scheduling of data) {
        if (scheduling.initialDate.substring(0, 10) === pressedDiv.id) {
            let li = document.createElement("li");
            let text = document.createTextNode(scheduling.initialDate);
            li.appendChild(text);
            list.appendChild(li);
        }
    }
    modal.style.display = "block";
}

function hideListModal() {
    const modal = document.getElementById("listOfSchedulingsPerDay");
    modal.style.display = "none";
}

async function showAddModal() {
    const listModal = document.getElementById("listOfSchedulingsPerDay");
    listModal.style.display = "none";
    const addModal = document.getElementById("addNewScheduling");
    const listModalTitle = document.getElementById("listModalTitle").textContent;
    document.getElementById("addModalTitle").textContent = listModalTitle;
    addModal.style.display = "block";

    const getClassroomsFromBack = await fetch("http://localhost:8080/classrooms"); // pegando os dados do backend
    const classrooms = await getClassroomsFromBack.json(); // reconvertendo os dados para json

    const classroomsDropDown = document.getElementById("classroom-list"); // pegando o dropdown das salas de aula
    classroomsDropDown.innerHTML = ""; // limpando o dropdown caso já houvesse dados populados

    // função map que percorre cada elemento da lista e aplica a função lambda para cada elemento da lista
    classrooms.map(c => {
        let option = document.createElement("option"); // criando uma opção
        option.setAttribute("value", c.id); // atribuindo o id da sala como id da opção
        option.append(c.name); // adicionando o nome da sala na opção
        classroomsDropDown.appendChild(option); // adicionando a opção no dropdown
    });

    //tudo se repete para as turmas e equipamentos:
    const getClassesFromBack = await fetch("http://localhost:8080/class");
    const classes = await getClassesFromBack.json();

    const classesDropDown = document.getElementById("class-list");
    classesDropDown.innerHTML = ""; // Limpando dropdows caso haja algum item (pra não duplicar)

    // Para cada objeto recebido do back será feito a lógica de comandos dentro das chaves
    classes.map(c => {
        let option = document.createElement("option");
        option.setAttribute("value", c.id);
        option.append(c.name);
        classesDropDown.appendChild(option);
    });

    const getEquipmentsFromBack = await fetch("http://localhost:8080/equipments");
    const equipments = await getEquipmentsFromBack.json();

    const equipmentsDropDown = document.getElementById("equipment-list");
    equipmentsDropDown.innerHTML = "";

    equipments.map(e => {
        let option = document.createElement("option");
        option.setAttribute("value", e.id);
        option.append(e.name);
        equipmentsDropDown.appendChild(option);
    });

}

function hideAddModal() {
    const addModal = document.getElementById("addNewScheduling")
    addModal.style.display = "none";
}

async function sendData() {
    // pegando os id's dos elementos selecionados:
    let initialHour = document.getElementById("initialHour").value;
    let finalHour = document.getElementById("finalHour").value;
    let classroom = document.getElementById("classroom-list").value;
    let classs = document.getElementById("class-list").value;
    let equipments = document.getElementById("equipment-list").value;
    let day = document.getElementById("addModalTitle").textContent;
    console.log(day);

    let data = JSON.stringify({
        initialDate: `${day}T${initialHour}:00Z`, // terá que pegar data dos id's 
        finalDate: `${day}T${finalHour}:00Z`,
        teacher: { "id": "1" }, // terá que pegar da sessão (ainda n sei como)
        classroom: { "id": classroom },
        group: { "id": classs },
        equipment: [{ "id": equipments }]
    });

    // Enviando os dados para o backend
    const response = await fetch("http://localhost:8080/schedulings/insertScheduling", {
        method: "POST",
        body: data,
        headers: {
            'Content-Type': 'application/json'
        }
    });

    const responseText = await response.text();
    window.location.reload();
}

// Chamar a função getData, esperar ela retornar os valores
getData().then(
    (schedulings) => {
        data = schedulings // Atribuir os valores à variável no escopo global
        generateCalendar(currentMonth, currentYear) // Chamar a função de gerar o calendario
    }).catch( // Se der erro:
        (err) => alert(err) // Mostra o erro podre
    );