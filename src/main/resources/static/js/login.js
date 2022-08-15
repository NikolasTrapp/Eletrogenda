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

    console.log(data);

    const request = await fetch("http://191.52.6.227:8080/validateLogin", {
        method: "POST",
        body: data,
        headers: {
            'Content-Type': 'application/json'
        }
    });

    const response = await request.json();
    console.log(response);

}