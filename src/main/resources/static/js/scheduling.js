const bt_get = document.getElementById("get-schedulings-button");
bt_get.addEventListener("click", getSchedulings);

bt_post = document.getElementById("post-scheduling-button");
bt_post.addEventListener("click", postSchedulings);

const bt_delete = document.getElementById("delete-scheduling-button");
bt_delete.addEventListener("click", deleteScheduling);

const bt_update = document.getElementById("update-scheduling-button");
bt_update.addEventListener("click", updateScheduling);

async function getSchedulings(){

    const table_body = document.getElementById("schedulings-table");
    table_body.innerHTML = "";

    const response = await fetch("http://191.52.6.227:8080/schedulings");
    const data = await response.json();

    if (table_body.getElementsByTagName("tr").length === 0){

        data.map(s=>{
        let row = table_body.insertRow();
        let id = row.insertCell(0);
        id.innerHTML = s.id;
        let initialDate = row.insertCell(1);
        initialDate.innerHTML = s.initialDate;
        let finalDate = row.insertCell(2);
        finalDate.innerHTML = s.finalDate;
        let teacher_id = row.insertCell(3);
        teacher_id.innerHTML = s.teacher.id;
        let classroom_id = row.insertCell(4);
        classroom_id.innerHTML = s.classroom.id;
        equipment = row.insertCell(5);
        for (var equip of s.equipment){
            equipment.innerHTML += equip.id + " ";
        }
        });
    }

}

async function postSchedulings(){

    let initialDate = document.getElementById("initialDate").value + "T" + document.getElementById("initialHour").value + ":00Z";
    let finalDate = document.getElementById("finalDate").value + "T" + document.getElementById("finalHour").value + ":00Z";
    let teacherId = document.getElementById("teacherId").value;
    let classroomId = document.getElementById("classroomId").value;
    let equipmentsId = document.getElementById("equipmentsId").value.split(" ");
    
    const data = {
        initialDate: initialDate,
        finalDate: finalDate,
        teacher: {"id": teacherId},
        classroom: {"id": classroomId},
        equipment: []
    }

    for (var i of equipmentsId){
        data.equipment.push({"id": i.toString()});
    }

    const response = await fetch("http://191.52.6.227:8080/schedulings/insertScheduling", {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json'
        }
    });

    const responseText = await response.text();
    console.log(responseText);

}

async function deleteScheduling(){

    let scheduling_id = document.getElementById("schedulingId").value;

    const response = await fetch(`http://191.52.6.227:8080/schedulings/deleteScheduling/${scheduling_id}`, {
        method: "DELETE"
    });

    const responseText = await response.text();
    console.log(responseText);

}

async function updateScheduling(){

    let scheduling_id = document.getElementById("schedulingIdUpdate").value;
    let initialDate = document.getElementById("initialDateUpdate").value + "T" + document.getElementById("initialHourUpdate").value + ":00Z";
    let finalDate = document.getElementById("finalDateUpdate").value + "T" + document.getElementById("finalHourUpdate").value + ":00Z";
    let teacherId = document.getElementById("teacherIdUpdate").value;
    let classroomId = document.getElementById("classroomIdUpdate").value;
    let equipmentsId = document.getElementById("equipmentsIdUpdate").value.split(" ");
    
    console.log(initialDate, finalDate);
    
    const data = {
        initialDate: initialDate,
        finalDate: finalDate,
        teacher: {"id": teacherId},
        classroom: {"id": classroomId},
        equipment: []
    }

    for (var i of equipmentsId){
        data.equipment.push({"id": i.toString()});
    }

    console.log(data);

    const response = await fetch(`http://191.52.6.227:8080/schedulings/updateScheduling/${scheduling_id}`, {
        method: "PUT",
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json'
        }
    });

    const responseText = await response.text();
    console.log(responseText);

}