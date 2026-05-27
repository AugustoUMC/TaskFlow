async function login() {

    const email =
        document.getElementById("email").value;

    const password =
        document.getElementById("password").value;

    const msg =
        document.getElementById("msg");

    if (!email || !password) {

        msg.innerText =
            "Preencha todos os campos!";

        return;
    }

    try {

        const response =
            await fetch(
                "http://localhost:8080/auth/login",
                {
                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify({
                        email,
                        password
                    })
                }
            );

        if (response.ok) {

            const user =
                await response.json();

            localStorage.setItem(
                "user",
                JSON.stringify(user)
            );

            msg.innerText =
                "Login realizado com sucesso!";

            setTimeout(() => {

                window.location.href =
                    "tasks.html";

            }, 1000);

        } else {

            msg.innerText =
                "Email ou senha inválidos!";
        }

    } catch (error) {

        msg.innerText =
            "Erro ao conectar com o servidor!";
    }
}