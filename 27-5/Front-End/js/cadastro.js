async function register() {

    const name =
        document.getElementById("name").value;

    const email =
        document.getElementById("email")
            .value
            .toLowerCase();

    const password =
        document.getElementById("password").value;

    const confirmPassword =
        document.getElementById("confirmPassword").value;

    const msg =
        document.getElementById("msg");

    if (!name || !email || !password || !confirmPassword) {

        msg.innerText =
            "Preencha todos os campos!";

        return;
    }

    if (password !== confirmPassword) {

        msg.innerText =
            "As senhas não coincidem!";

        return;
    }

    try {

        const response =
            await fetch(
                "http://localhost:8080/auth/register",
                {
                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify({
                        name,
                        email,
                        password
                    })
                }
            );

        if (response.ok) {

            msg.style.color = "lightgreen";

            msg.innerText =
                "Cadastro realizado com sucesso!";

            setTimeout(() => {

                window.location.href =
                    "login.html";

            }, 1500);

        } else {

            msg.style.color = "red";

            msg.innerText =
                "Erro ao cadastrar usuário!";
        }

    } catch (error) {

        msg.style.color = "red";

        msg.innerText =
            "Erro ao conectar com o servidor!";
    }
}

function togglePassword(id) {

    const input =
        document.getElementById(id);

    if (input.type === "password") {

        input.type = "text";

    } else {

        input.type = "password";
    }
}