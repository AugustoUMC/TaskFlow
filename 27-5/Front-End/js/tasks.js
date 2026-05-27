const user =
    JSON.parse(localStorage.getItem("user"));

if (!user) {
    window.location.href = "login.html";
}

async function loadTasks() {

    const tasksList =
        document.getElementById("tasksList");

    const msg =
        document.getElementById("msg");

    try {

        const response =
            await fetch(
                `http://localhost:8080/tasks/user/${user.userId}`,
                {
                    credentials: "include"
                }
            );

        if (!response.ok) {
            msg.innerText = "Erro ao carregar tarefas.";
            return;
        }

        const tasks =
            await response.json();

        tasksList.innerHTML = "";

        if (tasks.length === 0) {
            tasksList.innerHTML =
                "<p>Nenhuma tarefa cadastrada.</p>";
            return;
        }

        tasks.forEach(task => {

            tasksList.innerHTML += `
                <div class="task">
                    <h3>${task.title}</h3>
                    <p>${task.description || ""}</p>
                    <p><strong>Prioridade:</strong> ${task.priority}</p>
                    <p><strong>Prazo:</strong> ${task.dueDate}</p>

                    <button onclick="deleteTask(${task.taskId})">
                        Excluir
                    </button>
                </div>
            `;
        });

    } catch (error) {
        msg.innerText = "Erro ao conectar com o servidor.";
    }
}

async function deleteTask(id) {

    await fetch(
        `http://localhost:8080/tasks/${id}`,
        {
            method: "DELETE",
            credentials: "include"
        }
    );

    loadTasks();
}

function logout() {
    localStorage.removeItem("user");
    window.location.href = "login.html";
}

loadTasks();