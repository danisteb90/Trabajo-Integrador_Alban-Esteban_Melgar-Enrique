window.addEventListener('load', function () {
    const urlTurnos = '/turnos';
    const urlOdontologos = '/odontologos';
    let turnoSeleccionado = null;

    fetch(urlTurnos, { method: 'GET' })
        .then(response => response.json())
        .then(data => {
            let turnoTableBody = document.querySelector('#turnoTableBody');
            data.forEach(turno => {
                let row = document.createElement('tr');
                row.innerHTML = `
                    <td>${turno.id}</td>
                    <td>${turno.fecha}</td>
                    <td>${turno.paciente.nombre} ${turno.paciente.apellido}</td>
                    <td>${turno.odontologo.nombre} ${turno.odontologo.apellido}</td>
                `;
                row.addEventListener('click', function() {
                    cargarDetallesTurno(turno);
                });
                turnoTableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Error al obtener los turnos:', error));

    fetch(urlOdontologos, { method: 'GET' })
        .then(response => response.json())
        .then(data => {
            let odontologoSelect = document.querySelector('#odontologo_select');
            data.forEach(odontologo => {
                let option = document.createElement('option');
                option.value = odontologo.id;
                option.text = `${odontologo.nombre} ${odontologo.apellido}`;
                odontologoSelect.appendChild(option);
            });
        })
        .catch(error => console.error('Error al obtener los odontólogos:', error));

    function cargarDetallesTurno(turno) {
        turnoSeleccionado = turno;
        document.querySelector('#turno_id').value = turno.id;
        document.querySelector('#turno_fecha').value = turno.fecha;
        document.querySelector('#update_turno_section').style.display = 'block';
    }

    const updateForm = document.querySelector('#update_turno_form');
    updateForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const odontologoId = document.querySelector('#odontologo_select').value;
        const nuevaFecha = document.querySelector('#turno_fecha').value;

        const updatedTurno = {
            id: turnoSeleccionado.id,
            odontologo: {
                id: odontologoId,
                nombre: document.querySelector(`#odontologo_select option[value='${odontologoId}']`).text.split(' ')[0],
                apellido: document.querySelector(`#odontologo_select option[value='${odontologoId}']`).text.split(' ')[1],
                matricula: turnoSeleccionado.odontologo.matricula
            },
            paciente: turnoSeleccionado.paciente,
            fecha: nuevaFecha
        };

        const url = `/turnos/${turnoSeleccionado.id}`;
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(updatedTurno)
        };

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong>Turno actualizado correctamente</strong></div>';

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
            })
            .catch(error => {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong>Error al actualizar el turno. Intente nuevamente.</strong></div>';

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                console.error('Error:', error);
            });
    });
});
