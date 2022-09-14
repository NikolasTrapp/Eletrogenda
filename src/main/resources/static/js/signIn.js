const bt = document.getElementById("submit-button");
bt.addEventListener("click", checkSignIn);

async function checkSignIn(){
	
    let name = document.getElementById("name").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;

    const data = JSON.stringify({
        name: name,
        email: email,
        password: password 
    });

    console.log(data);

    //Esta rota não tem como por para o json server, ela consta no arquivo UserResources
    //em: src\main\java\com\agendaeletro\project\resources\UserResources.java
    const request = await fetch("http://191.52.6.109:8080/teachers/insertTeacher", {
        method: "POST",
        body: data,
        headers: {
            'Content-Type': 'application/json'
        }
    });

    const response = await request.json();

    console.log(response);
    
    if (response.result === "ok"){
        sessionStorage.setItem("teacher", JSON.stringify({"id": response.teacher_id, "name": name, "email": email}));
        window.location.assign("/mainPage");
    } else {
        alert(response.details);
    }

}

function clear(text){
    let newtext = text.replace('>', '').replace('<', '').replace('&lt;', '').replace('&gt;', '');
    return newtext;
}