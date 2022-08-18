const bt = document.getElementById("open-modal"); //pegando o botão que ativa a janela modal
bt.addEventListener("click", loadData); // atribuindo a função de click ao botão
const bt_submit = document.getElementById("submit-button");
bt_submit.addEventListener("click", sendData);

async function loadData() {
    //Função que popula/preenche os dropdowns (select) da janela modal

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
    classesDropDown.innerHTML = "";

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

async function sendData(){

    // pegando os id's dos elementos selecionados:
    let initialHour = document.getElementById("initialHour").value;
    let finalHour = document.getElementById("finalHour").value;
    let classroom = document.getElementById("classroom-list").value;
    let classs = document.getElementById("class-list").value;
    let equipments = document.getElementById("equipment-list").value;

    let data = JSON.stringify({
        initialDate: `2022-08-17T${initialHour}:00Z`, // terá que pegar data dos id's 
        finalDate:`2022-08-17T${finalHour}:00Z`,
        teacher: {"id": "1"}, // terá que pegar da sessão (ainda n sei como)
        classroom: {"id": classroom},
        class: {"id": classs},
        equipment: [{"id": equipments}]
    });

    console.log(classroom, classs, equipments);
    console.log(data);

    const response = await fetch("http://localhost:8080/schedulings/insertScheduling", {
        method: "POST",
        body: data,
        headers: {
            'Content-Type': 'application/json'
        }
    });

    const responseText = await response.text();
    console.log(responseText);


}