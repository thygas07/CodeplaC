window.addEventListener("DOMContentLoaded", getEventData);

function getEventData() {
	fetch("https://codeplac-c7hy.onrender.com/event/list", {
		method: "GET",
		headers: {
			"Content-Type": "application/json",
		},
	})
		.then((response) => {
			if (response.ok) {
				return response.json();
			} else {
				throw new Error("Falha na busca de eventos.");
			}
		})
		.then((data) => {
			renderEvents(data);
		})
		.catch((error) => {
			alert(error.message);
		});
}

function renderEvents(data) {
	const eventList = document.querySelector("#eventos-lista");

	if (data.length == 0) {
		return (eventList.innerHTML = `
			<div class="evento-item">
        <p>Não há eventos no momento!</p>
			</div>
		`);
	}

	data.forEach((event) => {
		eventList.innerHTML += `
      <div class="evento-item">
        <h4>${event.nome}</h4>
        <p>Provável Data: ${new Date(event.dataEvento).toLocaleDateString("pt-BR")}</p>
        <p>
          Descrição: ${event.descricao}
        </p>
        <a href="inscricao.html" class="btn">Inscreva sua equipe!</a>
			</div>
    `;
	});
}