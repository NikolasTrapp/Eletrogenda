let today = new Date(); // Pegando o dia de hoje
let currentMonth = today.getMonth(); // Mes atual (-1)
let currentYear = today.getFullYear(); // Ano atual
let months = ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"];

let monthAndYear = document.getElementById("monthAndYear"); // Titulo do mes do calendario
let data; // Agendamentos

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
            if (i === 0 && j < firstDay) { // Se estiver na primeira coluna e j for menor que o dia da semana que a mesma incia:
                let daysFromPreviousMonth = 32 - new Date(year, month - 1, 32).getDate(); // Numero de dias do mes passado
                let colText = document.createTextNode(daysFromPreviousMonth - firstDay + previousMonthDate); // Criando texto dos dias da semana do mes passado
                col.appendChild(colText); // Adicionando o texto na celula
                day = (daysFromPreviousMonth - firstDay + previousMonthDate) + "/" + // Ajustando o id deste dia
                    ((month < 10) ? (month === 0) ? "12" : "0" + month : month) + "/" + // Mes do dia
                    ((month === 0) ? year - 1 : year); // Ano do dia
                col.id = day;
                row.appendChild(col); // Adicionando celula do dia na semana
                previousMonthDate++;
            }
            else if (date > daysInMonth) {
                let colText = document.createTextNode(nextMonthDate); // Criando texto dos dias da semana do mes seguinte
                col.appendChild(colText); // Adicionando o texto na celula
                day = ((nextMonthDate < 10) ? "0" + nextMonthDate : nextMonthDate) + "/" +
                    ((month + 2 < 10) ? "0" + (month + 2) : (month + 2 === 13) ? "01" : month + 2) + "/" +
                    ((month + 1 === 12) ? year + 1 : year);
                col.id = day; // Atribuindo o id
                row.appendChild(col); // Adicionando celula do dia na semana
                nextMonthDate++;
            }
            else {
                day = `${(date < 10) ? "0" + date : date}/${(month + 1 < 10) ? "0" + (month + 1) : month + 1}/${currentYear}`;
                col.id = day;
                let colText = document.createTextNode(date);  // Criando texto dos dias da semana
                col.appendChild(colText); // Adicionando o texto na celula
                row.appendChild(col); // Adicionando celula do dia na semana
                date++; // Somando mais um dia
            }
        }
        calendar.appendChild(row); // Adicionando a semana no calendário
    }
    const divs = document.querySelectorAll(".col");
    divs.forEach(e => e.addEventListener('click', showListModal));
    populateSchedulings(); // Colocar os agendamentos nas celulas
}

function populateSchedulings() {
    //Função para adicionar os agendamentos ao calendário
    for (let scheduling of data) { //Para cada agendamento nos dados recebidos
        //Pegar o id do dia do agendamento (caso este dia esteja na tela)
        let col = document.getElementById(scheduling.initialDate.substring(0, 10));
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

// Chamar a função getData, esperar ela retornar os valores
getData("http://localhost:3000/schedulings").then(
    (schedulings) => {
        data = schedulings; // Atribuir os valores à variável no escopo global
    }).catch( // Se der erro:
        (err) => alert(err) // Mostra o erro podre
    ).finally(() => {
        generateCalendar(currentMonth, currentYear); // Chamar a função de gerar o calendario
    });