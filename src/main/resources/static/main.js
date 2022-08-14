const bt = document.getElementById("get-teachers-button");
bt.addEventListener("click", getTeachers);

async function getTeachers(){

    const table_body = document.getElementById("teachers-table");

    const response = await fetch("http://localhost:8080/teachers");
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