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

    const classrooms = await getData("http://191.52.6.109:8080/classrooms"); // Pegando os dados para json
    const classroomsDropDown = document.getElementById("classroom-list"); // pegando o dropdown das salas de aula
    populateDropDowns(classroomsDropDown, classrooms);

    const classes = await getData("http://191.52.6.109:8080/class");
    const classesDropDown = document.getElementById("class-list");
    populateDropDowns(classesDropDown, classes);

    const equipments = await getData("http://191.52.6.109:8080/equipments");
    const equipmentsDropDown = document.getElementById("equipment-list");
    populateDropDowns(equipmentsDropDown, equipments);
    equipmentsDropDown.addEventListener("change", addEquipment);
}

// Função para apresentar a janela modal referente aos agendamentos do dia pressionado
function showListModal() {
    // Pegar a janela modal
    const modal = document.getElementById("listOfSchedulingsPerDay");
    //Adicionar o titulo à janela (data pressionada)
    document.getElementById("listModalTitle").textContent = this.id;
    // Adicionar os agendamentos a uma lista no corpo da janela modal
    let list = document.getElementById("schedulings-list");
    list.innerHTML = ""; // Limpando a janela para evitar itens duplicados
    for (scheduling of data) {
        if (scheduling.initialDate.substring(0, 10) === this.id) {
            let li = document.createElement("li");
            let text = document.createTextNode(`${scheduling.initialDate} - ${scheduling.finalDate}`);
            li.appendChild(text);
            list.appendChild(li);
        }
    }
    modal.style.display = "flex"; // Fazendo a modal aparecer
}

function addEquipment() {
    /**
     * Esta função pega os equipamentos que o usuário selecionou da lista de
     * equipamentos e o insere em uma tabela
     */
    const equipmentsList = document.getElementById("listOfEquipments");
    let idList = [];
    // Verificando se o equipamento já foi selecionado alguma vez, se não é adicionado à lista de id's
    Array.from(equipmentsList.rows).forEach(tr => (idList.includes(tr.cells[0].id)) ? null : idList.push(tr.cells[0].id));

    // this = seleção de equipamentos (dropdown)
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