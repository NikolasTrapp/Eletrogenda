/*Urls:
    Spring-boot server:
    http://localhost:3000/name

    JSON server:
    Para usar o json server é preciso ter o Node.js instalado
    Instalar o serviço do JSON server: 
    npm install -g json-server
    rodar o JSON server:
    navegar até o diretório "src\main\resources\static\js" e digitar o comando:
    json-server --watch db.json
    padrão de rota do JSON server:
    http://localhost:3000/name

    Routes names:
    (get)
    class -> turmas
    classrooms -> salas de aula
    equipments -> equipamentos
    schedulings -> agendamentos
    teachers -> professores

    (post)
    name(s)/insertName -> inserir algo no banco
    ex: schedulings/insertScheduling -> insere um agendamento

*/

//Para caso de não querer fazer login manualmente
sessionStorage.setItem("teacher", JSON.stringify({ "id": "1", "name": "Nikolas", "email": "nikolas@gmail.com" }));

const adress = "localhost:3000";

async function getData(entity) {
    const request = await fetch(`http://${adress}/${entity}/`); // pegando os dados do backend
    const data = await request.json(); // transformando os dados em json
    return data; // Retornando os dados
}

async function postData(url, data) {
    /**
     * Esta função recebe como parametros a url da rota a ser enviada os dados
     * e os dados a serem enviados e retorna a resposta que o servidor deu
     */
    // Enviando os dados:
    const response = await fetch(url, {
        method: "POST",
        body: data,
        headers: {
            'Content-Type': 'application/json'
        }
    });

    // Pegando a resposta
    const responseText = await response.text();
    return responseText; // Retornando a resposta
}

async function sendData(initialHour, finalHour, group, classroom, teacher, equipments) {
    // pegando os id's dos elementos selecionados:
    let day = document.getElementById("add-schedule-modal-label").textContent;

    // Verificando se a hora compreende entre (08:00 e 12:00) ou (13:30 e 17:30)
    if (verificarHora(initialHour, finalHour)){
        alert("Invalid hour!");
        return null; // Sair da função
    }

    // Empacotando os dados em JSON para enviar para o backend
    let data = JSON.stringify({
        initialDate: `${day} ${initialHour}`,
        finalDate: `${day} ${finalHour}`,
        teacher: teacher,
        classroom: { "id": classroom },
        group: { "id": group },
        equipment: equipments
    });

    const responseText = await postData(`http://${adress}/schedulings`, data);
    console.log(responseText);
}

function verificarHora(initialHour, finalHour) {
    let initialH = new Date('2022-01-01 ' + initialHour);
    let finalH = new Date('2022-01-01 ' + finalHour);

    let firstHour = new Date('2022-01-01 08:00');
    let secondHour = new Date('2022-01-01 12:00');
    let thirdHour = new Date('2022-01-01 13:30');
    let fourthHour = new Date('2022-01-01 17:30');

    return !(initialH >= firstHour && initialH <= secondHour && finalH >= firstHour && finalH <= secondHour)||
    (initialH >= thirdHour && initialH <= fourthHour && finalH >= thirdHour && finalH <= fourthHour);
    
}