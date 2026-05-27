async function createTask() {

    const user =
        JSON.parse(localStorage.getItem("user"));

    if (!user) {
        window.location.href = "login.html";
        return;
    }

    const title =
        document.getElementById("title").value;

    const description =
        document.getElementById("description").value;

    const priority =
        document.getElementById("priority").value;

    const dueDate =
        document.getElementById("dueDate").value;

    const msg =
        document.getElementById("msg");

    if (!title || !dueDate) {
        msg.innerText = "Preencha os campos obrigatórios!";
        return;
    }

    try {

        const response =
            await fetch(
                "http://localhost:8080/tasks",
                {
                    method: "POST",

                    credentials: "include",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify({
                        title,
                        description,
                        priority,
                        dueDate,
                        user: {
                            userId: user.userId
                        }
                    })
                }
            );

        if (response.ok) {
            msg.innerText = "Tarefa criada com sucesso!";

            setTimeout(() => {
                window.location.href = "tasks.html";
            }, 1000);

        } else {
            msg.innerText = "Erro ao criar tarefa.";
        }

    } catch (error) {
        msg.innerText = "Erro ao conectar com o servidor.";
    }
}

function logout() {
    localStorage.removeItem("user");
    window.location.href = "login.html";
}