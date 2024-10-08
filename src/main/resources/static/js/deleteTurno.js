window.addEventListener("load", function () {
	const urlTurnos = "/turnos";

	function cargarTurnos() {
		fetch(urlTurnos, { method: "GET" })
			.then((response) => response.json())
			.then((data) => {
				let turnoTableBody = document.querySelector("#turnoTableBody");
				turnoTableBody.innerHTML = ""; // Limpiar la tabla antes de cargar los datos
				data.forEach((turno) => {
					let row = document.createElement("tr");
					row.innerHTML = `
						<td>${turno.id}</td>
						<td>${turno.fecha}</td>
						<td>${turno.paciente.nombre} ${turno.paciente.apellido}</td>
						<td>${turno.odontologo.nombre} ${turno.odontologo.apellido}</td>
					`;
					row.addEventListener("click", function () {
						mostrarDetallesTurno(turno);
					});
					turnoTableBody.appendChild(row);
				});
			})
			.catch((error) => console.error("Error al obtener los turnos:", error));
	}

	cargarTurnos();

	function mostrarDetallesTurno(turno) {
		document.querySelector("#turno_id").textContent = turno.id;
		document.querySelector("#turno_fecha").textContent = turno.fecha;
		document.querySelector(
			"#turno_paciente"
		).textContent = `${turno.paciente.nombre} ${turno.paciente.apellido}`;
		document.querySelector(
			"#turno_odontologo"
		).textContent = `${turno.odontologo.nombre} ${turno.odontologo.apellido}`;
		document.querySelector("#delete_turno_section").style.display = "block";
	}

	const deleteButton = document.querySelector("#delete_turno_button");
	deleteButton.addEventListener("click", function () {
		const turnoId = document.querySelector("#turno_id").textContent;

		const url = `/turnos/${turnoId}`;
		const settings = {
			method: "DELETE",
			headers: {
				"Content-Type": "application/json",
			},
		};

		fetch(url, settings)
			.then((response) => {
				if (response.ok) {
					let successAlert =
						'<div class="alert alert-success alert-dismissible" style="max-width: 300px;">' +
						'<button type="button" class="close" data-dismiss="alert">&times;</button>' +
						"<strong>Turno eliminado correctamente</strong></div>";

					// Mueve la alerta justo después del div con display: none
					document
						.querySelector("#delete_turno_section")
						.insertAdjacentHTML("afterend", successAlert);
					document.querySelector("#delete_turno_section").style.display =
						"none";
					cargarTurnos(); // Recargar la lista de turnos
				} else {
					throw new Error("Error al eliminar el turno");
				}
			})
			.catch((error) => {
				let errorAlert =
					'<div class="alert alert-danger alert-dismissible" style="max-width: 300px;">' +
					'<button type="button" class="close" data-dismiss="alert">&times;</button>' +
					"<strong>Error al eliminar el turno. Intente nuevamente.</strong></div>";

				document
					.querySelector("#delete_turno_section")
					.insertAdjacentHTML("afterend", errorAlert);
				console.error("Error:", error);
			});
	});
});
