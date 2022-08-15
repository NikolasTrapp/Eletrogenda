const bt_get = document.getElementById("get-equipments-button");
bt_get.addEventListener("click", getEquipments);

const bt_post = document.getElementById("post-equipment-button");
bt_post.addEventListener("click", postEquipment);

const bt_delete = document.getElementById("delete-equipment-button");
bt_delete.addEventListener("click", deleteEquipment);

const bt_update = document.getElementById("update-equipment-button");
bt_update.addEventListener("click", updateEquipment);


async function getEquipments(){

    const table_body = document.getElementById("equipments-table");
    table_body.innerHTML = "";

    const response = await fetch("http://191.52.6.227:8080/equipments");
    const data = await response.json();

    if (table_body.getElementsByTagName("tr").length === 0){

        data.map(e=>{
        let row = table_body.insertRow();
        let id = row.insertCell(0);
        id.innerHTML = e.id;
        let name = row.insertCell(1);
        name.innerHTML = e.name;
        let description = row.insertCell(2);
        description.innerHTML = e.description;
        let quantity = row.insertCell(3);
        quantity.innerHTML = e.quantity;
        });
    }

}

async function postEquipment(){
    
    let name = document.getElementById("name").value;
    let description = document.getElementById("description").value;
    let quantity = document.getElementById("quantity").value;
    
    const data = JSON.stringify({
        name: name,
        description: description,
        quantity: quantity
    });

    const response = await fetch("http://191.52.6.227:8080/equipments/insertEquipment", {
        method: "POST",
        body: data,
        headers: {
            'Content-Type': 'application/json'
        }
    });

    const responseText = await response.text();
    console.log(responseText);

}

async function deleteEquipment(){

    let equipment_id = document.getElementById("equipmentId").value;

    const response = await fetch(`http://191.52.6.227:8080/equipments/deleteEquipment/${equipment_id}`, {
        method: "DELETE"
    });

    const responseText = await response.text();
    console.log(responseText);

}

async function updateEquipment(){

    let equipment_id = document.getElementById("equipmentIdUpdate").value;
    let name = document.getElementById("nameUpdate").value;
    let description = document.getElementById("descriptionUpdate").value;
    let quantity = document.getElementById("quantityUpdate").value;
    
    const data = JSON.stringify({
        name: name,
        description: description,
        quantity: quantity
    });

    const response = await fetch(`http://191.52.6.227:8080/equipments/updateEquipment/${equipment_id}`, {
        method: "PUT",
        body: data,
        headers: {
            'Content-Type': 'application/json'
        }
    });

    const responseText = await response.text();
    console.log(responseText);

}