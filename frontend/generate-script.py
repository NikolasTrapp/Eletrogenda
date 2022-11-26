
teachers = [
    {
        "id": 1,
        "name": "Nikolas",
        "email": "nikolas@gmail.com",
        "password": "123",
        "role": "TEACHER"
    },
    {
        "id": 2,
        "name": "Paulo",
        "email": "paulo@gmail.com",
        "password": "123",
        "role": "TEACHER"
    },
    {
        "id": 3,
        "name": "Carlos",
        "email": "carlos@gmail.com",
        "password": "123",
        "role": "TEACHER"
    },
    {
        "id": 4,
        "name": "Gustavo",
        "email": "gustavo@gmail.com",
        "password": "123",
        "role": "TEACHER"
    },
    {
        "id": 5,
        "name": "Eder",
        "email": "eder@gmail.com",
        "password": "123",
        "role": "TEACHER"
    },
    {
        "id": 6,
        "name": "Uriarte",
        "email": "uriarte@gmail.com",
        "password": "123",
        "role": "TEACHER"
    },
    {
        "id": 7,
        "name": "Hylson",
        "email": "hylson@gmail.com",
        "password": "123",
        "role": "TEACHER"
    },
    {
        "id": 8,
        "name": "Ladeira",
        "email": "ladeira@gmail.com",
        "password": "123",
        "role": "TEACHER"
    },
    {
        "id": 9,
        "name": "Rodacki",
        "email": "rodacki@gmail.com",
        "password": "123",
        "role": "TEACHER"
    },
    {
        "id": 10,
        "name": "Vital",
        "email": "vital@gmail.com",
        "password": "123",
        "role": "TEACHER"
    }
]
classrooms = [
    {
        "id": 1,
        "name": "D01",
        "color": "BLACK"
    },
    {
        "id": 2,
        "name": "D02",
        "color": "YELLOW"
    },
    {
        "id": 3,
        "name": "D03",
        "color": "RED"
    },
    {
        "id": 4,
        "name": "D04",
        "color": "DARKGRAY"
    },
    {
        "id": 5,
        "name": "D05",
        "color": "GREEN"
    },
    {
        "id": 6,
        "name": "D06",
        "color": "BLUE"
    },
    {
        "id": 7,
        "name": "D07",
        "color": "DARKRED"
    },
    {
        "id": 8,
        "name": "D08",
        "color": "PURPLE"
    },
    {
        "id": 9,
        "name": "D09",
        "color": "PINK"
    },
    {
        "id": 10,
        "name": "D10",
        "color": "ORANGE"
    },
    {
        "id": 11,
        "name": "D11",
        "color": "BROWN"
    }
]
group = [
    {
        "id": 1,
        "name": "301 Eletro"
    },
    {
        "id": 2,
        "name": "302 Eletro"
    },
    {
        "id": 3,
        "name": "201 Eletro"
    },
    {
        "id": 4,
        "name": "202 Eletro"
    },
    {
        "id": 5,
        "name": "101 Eletro"
    },
    {
        "id": 6,
        "name": "102 Eletro"
    },
    {
        "id": 7,
        "name": "301 Meca"
    },
    {
        "id": 8,
        "name": "302 Meca"
    },
    {
        "id": 9,
        "name": "201 Meca"
    },
    {
        "id": 10,
        "name": "202 Meca"
    },
    {
        "id": 11,
        "name": "101 Meca"
    },
    {
        "id": 12,
        "name": "102 Meca"
    }
]
equipments = [
    {
        "id": 1,
        "name": "Martelo",
        "description": "Poc poc poc poc",
        "quantity": 12
    },
    {
        "id": 2,
        "name": "Serrote",
        "description": "",
        "quantity": 15
    },
    {
        "id": 3,
        "name": "Ferro de solda",
        "description": "",
        "quantity": 31
    },
    {
        "id": 4,
        "name": "Fita dupla face",
        "description": "",
        "quantity": 80
    },
    {
        "id": 5,
        "name": "Marreta",
        "description": "",
        "quantity": 25
    },
    {
        "id": 6,
        "name": "Prego",
        "description": "",
        "quantity": 300
    },
    {
        "id": 7,
        "name": "Furadeira",
        "description": "",
        "quantity": 5
    },
    {
        "id": 8,
        "name": "Alicate",
        "description": "",
        "quantity": 20
    },
    {
        "id": 9,
        "name": "Chave de boca",
        "description": "Tamanho 14",
        "quantity": 7
    },
    {
        "id": 10,
        "name": "Luvas de proteção",
        "description": "",
        "quantity": 36
    },
    {
        "id": 11,
        "name": "Mascaras de proteção",
        "description": "",
        "quantity": 36
    },
    {
        "id": 12,
        "name": "Maçarico",
        "description": "Não esquentar muito",
        "quantity": 3
    }
]

print("INSERT INTO class (id, name) VALUES")
for i in group:
    print("(" + str(i.get("id")) + ", " + "'" + i.get("name") + "'" + "),")
    
print("\n\n")

c = 1
print("INSERT INTO classroom (id, name, color) VALUES")
for i in classrooms:
    print("(" + str(i.get("id")) + ", " + "'" + i.get("name") + "'" + ", " + str(c) + "),")
    c+= 1

print("\n\n")

print("INSERT INTO equipment (id, name, quantity, description) VALUES")
for i in equipments:
    print("(" + str(i.get("id")) + ", " + "'" + i.get("name") + "'" + ", " + str(i.get("quantity")) + ", '" + i.get("description") + "'),")

