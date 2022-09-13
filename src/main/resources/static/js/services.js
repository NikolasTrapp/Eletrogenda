/*Urls:
    Spring-boot server:
    http://localhost:8080/name

    JSON server:
    json-server --watch db.json
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
sessionStorage.setItem("teacher", JSON.stringify({"id": "1", "name": "Nikolas", "email": "nikolas@gmail.com"}));

async function getData(url) {
    const request = await fetch(url); // pegando os dados do backend
    const data = await request.json(); // transformando os dados em json
    return data // Retornando os dados
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

async function sendData() {
    /**
     * Esta função está ligada ao clique de enviar da janela modal addNewScheduling
     */
    // pegando os id's dos elementos selecionados:
    let day = document.getElementById("addModalTitle").textContent;
    let initialHour = document.getElementById("initialHour").value;
    let finalHour = document.getElementById("finalHour").value;
    let classroom = document.getElementById("classroom-list").value;
    let classs = document.getElementById("class-list").value;
    let equipmentsList = document.getElementById("listOfEquipments");
    let equipments = Array.from(equipmentsList.rows).map(
        (tr) => ({"id": tr.cells[0].id})
    );
    
    let data = JSON.stringify({
        initialDate: `${day} ${initialHour}`,
        finalDate: `${day} ${finalHour}`,
        teacher: { "id": JSON.parse(sessionStorage.getItem("teacher")).id },
        classroom: { "id": classroom },
        group: { "id": classs },
        equipment: equipments
    });

    console.log(data);

    const responseText = await postData("http://localhost:3000/schedulings", data);
    const addModal = document.getElementById("addNewScheduling");
    addModal.style.display = "none";
}