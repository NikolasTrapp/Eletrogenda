const bt_get = document.getElementById("get-classes-button");
bt_get.addEventListener("click", getClasss);

const bt_post = document.getElementById("post-class-button");
bt_post.addEventListener("click", postClass);

const bt_delete = document.getElementById("delete-class-button");
bt_delete.addEventListener("click", deleteClass);

const bt_update = document.getElementById("update-class-button");
bt_update.addEventListener("click", updateClass);

async function getClasss(){

    const table_body = document.getElementById("classes-table");
    table_body.innerHTML = "";

    const response = await fetch("http://localhost:8080/class");
    const data = await response.json();

    if (table_body.getElementsByTagName("tr").length === 0){

        data.map(c=>{
        let row = table_body.insertRow();
        let id = row.insertCell(0);
        id.innerHTML = c.id;
        let name = row.insertCell(1);
        name.innerHTML = c.name;
        });
    }

}

async function postClass(){
    
    let name = document.getElementById("name").value;
    
    const data = JSON.stringify({
        name: name,
    });

    const response = await fetch("http://localhost:8080/class/insertClass", {
        method: "POST",
        body: data,
        headers: {
            'Content-Type': 'application/json'
        }
    });

    const responseText = await response.text();
    console.log(responseText);

}

async function deleteClass(){

    let class_id = document.getElementById("classId").value;

    const response = await fetch(`http://localhost:8080/class/deleteClass/${class_id}`, {
        method: "DELETE"
    });

    const responseText = await response.text();
    console.log(responseText);

}

async function updateClass(){

    let class_id = document.getElementById("classIdUpdate").value;
    let name = document.getElementById("nameUpdate").value;
    
    const data = JSON.stringify({
        name: name
    });

    const response = await fetch(`http://localhost:8080/class/updateClass/${class_id}`, {
        method: "PUT",
        body: data,
        headers: {
            'Content-Type': 'application/json'
        }
    });

    const responseText = await response.text();
    console.log(responseText);

}