// Teachers:
let teachers = [
    {
        "id": 1,
        "name": "Nikolas",
        "email": "nikolas@gmail.com",
        "password": "$2a$10$7e8tpicL6nWGa0drRFCnde2q8j5l3MyE.rrkNtJIQlKhae2Xs23ka",
        "role": "ADMIN"
    },
    {
        "id": 2,
        "name": "Eder",
        "email": "eder@gmail.com",
        "password": "$2a$10$8bQjFloiEnb0oYCX954AZuYQfQyc9qjLoZEs/9rZ2u10IEzb5Jstm",
        "role": "TEACHER"
    },
    {
        "id": 3,
        "name": "Hylson",
        "email": "hylson@gmail.com",
        "password": "$2a$10$hPWOR/lTSi6OkabOZn0rTuxU5vjs2C9QhKLRNEBJTWz1CmeZjAtJa",
        "role": "TEACHER"
    },
    {
        "id": 4,
        "name": "Cleber",
        "email": "cleber@gmail.com",
        "password": "$2a$10$iCMFGDuFEWSQWciv4ei/SOpob5MbX6uhXjuJzA.dpKqSZWgCW18tu",
        "role": "TEACHER"
    },
    {
        "id": 5,
        "name": "Riad",
        "email": "riad@gmail.com",
        "password": "$2a$10$F8b5UH5oMbseqnRBO6YVROpVxlmtc08CRE0NtO2Lk0ndAl1Oa8UEC",
        "role": "TEACHER"
    },
    {
        "id": 6,
        "name": "Dalton",
        "email": "dalton@gmail.com",
        "password": "$2a$10$ab.Q7PKf3WDs5MCP39SIrOwKuCA0hsqreH7poq21H/PY7EeWQGMNG",
        "role": "TEACHER"
    },
    {
        "id": 11,
        "name": "Nikolass",
        "email": "nikolas@gmsail.com",
        "password": "$2a$10$P9yY0nm5TD6xZoP6PAEYN.XgGnKoQW3dPLHQwsoewfm5V7S1H90cO",
        "role": "TEACHER"
    }
];

// Equipments:
let equipments = [
    {
        "id": 1,
        "name": "Martelo",
        "description": "poc poc poc poc",
        "quantity": 15
    },
    {
        "id": 2,
        "name": "Serrote",
        "description": "",
        "quantity": 20
    },
    {
        "id": 3,
        "name": "Solda",
        "description": "",
        "quantity": 25
    },
    {
        "id": 4,
        "name": "Ferro",
        "description": "",
        "quantity": 30
    },
    {
        "id": 5,
        "name": "Madeira",
        "description": "30 cm",
        "quantity": 40
    },
    {
        "id": 6,
        "name": "Prego",
        "description": "5 tortos",
        "quantity": 50
    }
];

// Classrooms:
let classrooms = [
    {
        "id": 1,
        "name": "A01"
    },
    {
        "id": 2,
        "name": "A02"
    },
    {
        "id": 3,
        "name": "A03"
    },
    {
        "id": 4,
        "name": "A04"
    },
    {
        "id": 5,
        "name": "A05"
    },
    {
        "id": 6,
        "name": "A06"
    }
];

// Classes:
let classes = [
    {
        "id": 1,
        "name": "301 INFO"
    },
    {
        "id": 2,
        "name": "302 INFO"
    },
    {
        "id": 3,
        "name": "201 INFO"
    },
    {
        "id": 4,
        "name": "202 INFO"
    },
    {
        "id": 5,
        "name": "101 INFO"
    },
    {
        "id": 6,
        "name": "102 ELETRO"
    }
];

// Schedulings:
let schedulings = [
    {
        "id": 1,
        "initialDate": "2022-08-18T15:00:00",
        "finalDate": "2022-08-18T15:45:00",
        "teacher": {
            "id": 1,
            "name": "Nikolas",
            "email": "nikolas@gmail.com",
            "password": "$2a$10$7e8tpicL6nWGa0drRFCnde2q8j5l3MyE.rrkNtJIQlKhae2Xs23ka",
            "role": "ADMIN"
        },
        "classroom": {
            "id": 1,
            "name": "A01"
        },
        "group": {
            "id": 1,
            "name": "301 INFO"
        },
        "equipment": [
            {
                "id": 1,
                "name": "Martelo",
                "description": "poc poc poc poc",
                "quantity": 15
            },
            {
                "id": 2,
                "name": "Serrote",
                "description": "",
                "quantity": 20
            },
            {
                "id": 3,
                "name": "Solda",
                "description": "",
                "quantity": 25
            },
            {
                "id": 4,
                "name": "Ferro",
                "description": "",
                "quantity": 30
            }
        ]
    },
    {
        "id": 2,
        "initialDate": "2022-08-19T16:00:00",
        "finalDate": "2022-08-19T16:45:00",
        "teacher": {
            "id": 3,
            "name": "Hylson",
            "email": "hylson@gmail.com",
            "password": "$2a$10$hPWOR/lTSi6OkabOZn0rTuxU5vjs2C9QhKLRNEBJTWz1CmeZjAtJa",
            "role": "TEACHER"
        },
        "classroom": {
            "id": 2,
            "name": "A02"
        },
        "group": {
            "id": 5,
            "name": "101 INFO"
        },
        "equipment": [
            {
                "id": 1,
                "name": "Martelo",
                "description": "poc poc poc poc",
                "quantity": 15
            },
            {
                "id": 2,
                "name": "Serrote",
                "description": "",
                "quantity": 20
            },
            {
                "id": 3,
                "name": "Solda",
                "description": "",
                "quantity": 25
            },
            {
                "id": 4,
                "name": "Ferro",
                "description": "",
                "quantity": 30
            },
            {
                "id": 5,
                "name": "Madeira",
                "description": "30 cm",
                "quantity": 40
            },
            {
                "id": 6,
                "name": "Prego",
                "description": "5 tortos",
                "quantity": 50
            }
        ]
    },
    {
        "id": 3,
        "initialDate": "2022-08-20T13:00:00",
        "finalDate": "2022-08-20T13:45:00",
        "teacher": {
            "id": 2,
            "name": "Eder",
            "email": "eder@gmail.com",
            "password": "$2a$10$8bQjFloiEnb0oYCX954AZuYQfQyc9qjLoZEs/9rZ2u10IEzb5Jstm",
            "role": "TEACHER"
        },
        "classroom": {
            "id": 5,
            "name": "A05"
        },
        "group": {
            "id": 1,
            "name": "301 INFO"
        },
        "equipment": [
            {
                "id": 1,
                "name": "Martelo",
                "description": "poc poc poc poc",
                "quantity": 15
            },
            {
                "id": 2,
                "name": "Serrote",
                "description": "",
                "quantity": 20
            }
        ]
    },
    {
        "id": 4,
        "initialDate": "2022-08-17T10:12:00",
        "finalDate": "2022-08-17T18:12:00",
        "teacher": {
            "id": 1,
            "name": "Nikolas",
            "email": "nikolas@gmail.com",
            "password": "$2a$10$7e8tpicL6nWGa0drRFCnde2q8j5l3MyE.rrkNtJIQlKhae2Xs23ka",
            "role": "ADMIN"
        },
        "classroom": {
            "id": 1,
            "name": "A01"
        },
        "group": {
            "id": 1,
            "name": "301 INFO"
        },
        "equipment": [
            {
                "id": 1,
                "name": "Martelo",
                "description": "poc poc poc poc",
                "quantity": 15
            }
        ]
    },
    {
        "id": 5,
        "initialDate": "2022-08-11T19:13:00",
        "finalDate": "2022-08-11T20:13:00",
        "teacher": {
            "id": 6,
            "name": "Dalton",
            "email": "dalton@gmail.com",
            "password": "$2a$10$ab.Q7PKf3WDs5MCP39SIrOwKuCA0hsqreH7poq21H/PY7EeWQGMNG",
            "role": "TEACHER"
        },
        "classroom": {
            "id": 5,
            "name": "A05"
        },
        "group": {
            "id": 4,
            "name": "202 INFO"
        },
        "equipment": [
            {
                "id": 1,
                "name": "Martelo",
                "description": "poc poc poc poc",
                "quantity": 15
            },
            {
                "id": 2,
                "name": "Serrote",
                "description": "",
                "quantity": 20
            },
            {
                "id": 3,
                "name": "Solda",
                "description": "",
                "quantity": 25
            }
        ]
    },
    {
        "id": 6,
        "initialDate": "2022-08-11T18:16:00",
        "finalDate": "2022-08-11T19:16:00",
        "teacher": {
            "id": 2,
            "name": "Eder",
            "email": "eder@gmail.com",
            "password": "$2a$10$8bQjFloiEnb0oYCX954AZuYQfQyc9qjLoZEs/9rZ2u10IEzb5Jstm",
            "role": "TEACHER"
        },
        "classroom": {
            "id": 3,
            "name": "A03"
        },
        "group": {
            "id": 4,
            "name": "202 INFO"
        },
        "equipment": [
            {
                "id": 5,
                "name": "Madeira",
                "description": "30 cm",
                "quantity": 40
            },
            {
                "id": 6,
                "name": "Prego",
                "description": "5 tortos",
                "quantity": 50
            }
        ]
    },
    {
        "id": 7,
        "initialDate": "2022-08-11T18:16:00",
        "finalDate": "2022-08-11T09:16:00",
        "teacher": {
            "id": 2,
            "name": "Eder",
            "email": "eder@gmail.com",
            "password": "$2a$10$8bQjFloiEnb0oYCX954AZuYQfQyc9qjLoZEs/9rZ2u10IEzb5Jstm",
            "role": "TEACHER"
        },
        "classroom": {
            "id": 3,
            "name": "A03"
        },
        "group": {
            "id": 4,
            "name": "202 INFO"
        },
        "equipment": [
            {
                "id": 5,
                "name": "Madeira",
                "description": "30 cm",
                "quantity": 40
            },
            {
                "id": 6,
                "name": "Prego",
                "description": "5 tortos",
                "quantity": 50
            }
        ]
    },
    {
        "id": 8,
        "initialDate": "2022-08-20T09:25:00",
        "finalDate": "2022-08-20T10:25:00",
        "teacher": {
            "id": 1,
            "name": "Nikolas",
            "email": "nikolas@gmail.com",
            "password": "$2a$10$7e8tpicL6nWGa0drRFCnde2q8j5l3MyE.rrkNtJIQlKhae2Xs23ka",
            "role": "ADMIN"
        },
        "classroom": {
            "id": 1,
            "name": "A01"
        },
        "group": {
            "id": 1,
            "name": "301 INFO"
        },
        "equipment": [
            {
                "id": 1,
                "name": "Martelo",
                "description": "poc poc poc poc",
                "quantity": 15
            }
        ]
    },
    {
        "id": 9,
        "initialDate": "2022-08-25T20:00:00",
        "finalDate": "2022-08-25T20:45:00",
        "teacher": {
            "id": 1,
            "name": "Nikolas",
            "email": "nikolas@gmail.com",
            "password": "$2a$10$7e8tpicL6nWGa0drRFCnde2q8j5l3MyE.rrkNtJIQlKhae2Xs23ka",
            "role": "ADMIN"
        },
        "classroom": {
            "id": 1,
            "name": "A01"
        },
        "group": {
            "id": 1,
            "name": "301 INFO"
        },
        "equipment": [
            {
                "id": 1,
                "name": "Martelo",
                "description": "poc poc poc poc",
                "quantity": 15
            }
        ]
    },
    {
        "id": 21,
        "initialDate": "2022-10-01T13:30:00",
        "finalDate": "2022-10-01T14:15:00",
        "teacher": {
            "id": 1,
            "name": "Nikolas",
            "email": "nikolas@gmail.com",
            "password": "$2a$10$7e8tpicL6nWGa0drRFCnde2q8j5l3MyE.rrkNtJIQlKhae2Xs23ka",
            "role": "ADMIN"
        },
        "classroom": {
            "id": 5,
            "name": "A05"
        },
        "group": {
            "id": 3,
            "name": "201 INFO"
        },
        "equipment": [
            {
                "id": 3,
                "name": "Solda",
                "description": "",
                "quantity": 25
            }
        ]
    },
    {
        "id": 22,
        "initialDate": "2022-09-20T16:00:00",
        "finalDate": "2022-09-20T16:45:00",
        "teacher": {
            "id": 1,
            "name": "Nikolas",
            "email": "nikolas@gmail.com",
            "password": "$2a$10$7e8tpicL6nWGa0drRFCnde2q8j5l3MyE.rrkNtJIQlKhae2Xs23ka",
            "role": "ADMIN"
        },
        "classroom": {
            "id": 4,
            "name": "A04"
        },
        "group": {
            "id": 5,
            "name": "101 INFO"
        },
        "equipment": [
            {
                "id": 3,
                "name": "Solda",
                "description": "",
                "quantity": 25
            }
        ]
    }
];