const bt_get = document.getElementById("get-classrooms-button");
bt_get.addEventListener("click", getClassrooms);

const bt_post = document.getElementById("post-classroom-button");
bt_post.addEventListener("click", postClassroom);

const bt_delete = document.getElementById("delete-classroom-button");
bt_delete.addEventListener("click", deleteClassroom);

const bt_update = document.getElementById("update-classroom-button");
bt_update.addEventListener("click", updateClassroom);

async function getClassrooms(){

    const table_body = document.getElementById("classrooms-table");
    table_body.innerHTML = "";

    const response = await fetch("http://191.52.6.227:8080/classrooms");
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

async function postClassroom(){
    
    let name = document.getElementById("name").value;
    
    const data = JSON.stringify({
        name: name,
    });

    const response = await fetch("http://191.52.6.227:8080/classrooms/insertClassroom", {
        method: "POST",
        body: data,
        headers: {
            'Content-Type': 'application/json'
        }
    });

    const responseText = await response.text();
    console.log(responseText);

}

async function deleteClassroom(){

    let classroom_id = document.getElementById("classroomId").value;

    const response = await fetch(`http://191.52.6.227:8080/classrooms/deleteClassroom/${classroom_id}`, {
        method: "DELETE"
    });

    const responseText = await response.text();
    console.log(responseText);

}

async function updateClassroom(){

    let classroom_id = document.getElementById("classroomIdUpdate").value;
    let name = document.getElementById("nameUpdate").value;
    
    const data = JSON.stringify({
        name: name
    });

    const response = await fetch(`http://191.52.6.227:8080/classrooms/updateClassroom/${classroom_id}`, {
        method: "PUT",
        body: data,
        headers: {
            'Content-Type': 'application/json'
        }
    });

    const responseText = await response.text();
    console.log(responseText);

}