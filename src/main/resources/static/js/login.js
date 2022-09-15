const bt = document.getElementById("submit-button");
bt.addEventListener("click", checkLogin);

async function checkLogin(){
	
    let name = document.getElementById("name").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;

    const data = JSON.stringify({
        name: name,
        email: email,
        password: password 
    });

    //Esta rota não tem como por para o json server, ela consta no arquivo UserResources
    //em: src\main\java\com\agendaeletro\project\resources\UserResources.java
    const request = await fetch("http://localhost:8080/validateLogin", {
        method: "POST",
        body: data,
        headers: {
            'Content-Type': 'application/json'
        }
    });

    const response = await request.json();
    
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