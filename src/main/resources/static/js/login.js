const bt = document.getElementById("submit-button");
bt.addEventListener("click", checkLogin);

console.log("Oi");

async function checkLogin(){
	
    let name = document.getElementById("name").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;

    const data = JSON.stringify({
        name: name,
        email: email,
        password: password 
    });

    console.log(data);

    const request = await fetch("http://localhost:8080/validateLogin", {
        method: "POST",
        body: data,
        headers: {
            'Content-Type': 'application/json'
        }
    });

    const response = await request.json();
    
    
    if (response.result === "ok"){
        window.location.assign("/mainPage")
    } else {
        alert(response.details);
    }

}