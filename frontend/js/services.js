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
    json-server --host 192.168.1.XXX db.json
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
sessionStorage.setItem("teacher", JSON.stringify({ "id": "1" }));

const adress = "https://agenda-eletro.herokuapp.com";
// const adress = "http://localhost:8080";

async function getData(entity) {
    const request = await fetch(`${adress}/${entity}/`); // pegando os dados do backend
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
    const responseText = await response.json();
    return responseText; // Retornando a resposta
}

async function sendData(initialHour, finalHour, group, classroom, teacher, equipments) {
    // pegando os id's dos elementos selecionados:
    let day = document.getElementById("add-schedule-modal-label").textContent;

    // Verificando se a hora compreende entre (08:00 e 12:00) ou (13:30 e 17:30)
    if (verificarHora(initialHour, finalHour)) {
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
    const responseText = await postData(`${adress}/schedulings/insertScheduling`, data);
    if (responseText.status === 400 || responseText.status === 500 || responseText.status === 409) {
        showAlert("Error!", `${responseText.status} - ${responseText.error}`, "error", "Close");
    } else {
        showAlert("Success!", "Your registered a new schedule", "success", "Close");
    }
}

function showAlert(title, text, icon, confirmButtonText){
    Swal.fire({
        title: title,
        text: text,
        icon: icon,
        confirmButtonText: confirmButtonText
    });
}

function verificarHora(initialHour, finalHour) {
    let initialH = new Date('2022-01-01 ' + initialHour);
    let finalH = new Date('2022-01-01 ' + finalHour);

    return initialH >= finalH;

}