const bt_get = document.getElementById("get-teachers-button");
bt_get.addEventListener("click", getTeachers);

const bt_post = document.getElementById("post-teacher-button");
bt_post.addEventListener("click", postTeacher);

const bt_delete = document.getElementById("delete-teacher-button");
bt_delete.addEventListener("click", deleteTeacher);

const bt_update = document.getElementById("update-teacher-button");
bt_update.addEventListener("click", updateTeacher);

async function getTeachers(){

    const table_body = document.getElementById("teachers-table");
    table_body.innerHTML = "";

    const response = await fetch("http://191.52.6.227:8080/teachers");
    const data = await response.json();

    if (table_body.getElementsByTagName("tr").length === 0){
        
        data.map(t=>{
        let row = table_body.insertRow();
        let id = row.insertCell(0);
        id.innerHTML = t.id;
        let name = row.insertCell(1);
        name.innerHTML = t.name;
        let email = row.insertCell(2);
        email.innerHTML = t.email;
        let password = row.insertCell(3);
        password.innerHTML = t.password;
        let role = row.insertCell(4);
        role.innerHTML = t.role;
        });
    }

}

async function postTeacher(){
    
    let name = document.getElementById("name").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    let role = document.getElementById("role").value;
    
    const data = JSON.stringify({
        name: name,
        email: email,
        password: password,
        role: role
    });

    const response = await fetch("http://191.52.6.227:8080/teachers/insertTeacher", {
        method: "POST",
        body: data,
        headers: {
            'Content-Type': 'application/json'
        }
    });

    const responseText = await response.text();
    console.log(responseText);

}

async function deleteTeacher(){

    let teacher_id = document.getElementById("teacherId").value;

    const response = await fetch(`http://191.52.6.227:8080/teachers/deleteTeacher/${teacher_id}`, {
        method: "DELETE"
    });

    const responseText = await response.text();
    console.log(responseText);

}

async function updateTeacher(){

    let teacher_id = document.getElementById("teacherIdUpdate").value;
    let name = document.getElementById("nameUpdate").value;
    let email = document.getElementById("emailUpdate").value;
    let password = document.getElementById("passwordUpdate").value;
    let role = document.getElementById("roleUpdate").value;
    
    const data = JSON.stringify({
        name: name,
        email: email,
        password: password,
        role: role
    });

    const response = await fetch(`http://191.52.6.227:8080/teachers/updateTeacher/${teacher_id}`, {
        method: "PUT",
        body: data,
        headers: {
            'Content-Type': 'application/json'
        }
    });

    const responseText = await response.text();
    console.log(responseText);

}