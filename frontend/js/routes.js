async function getData(url){
    const request = await fetch(url);
    const answer = await request.json();
    return answer;
}

async function postData(url, data){
    const request = await fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: data
    });
    const answer = await request.json();
    return answer;
}

async function putData(url, data, id){
    const request = await fetch(`${url}/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: data
    });
    const answer = await request.json();
    return answer;
}

async function deleteData(url, id){
    const request = await fetch(`${url}/${id}`, {
        method: "DELETE",
    });
    const answer = await request.json();
    return answer;
}