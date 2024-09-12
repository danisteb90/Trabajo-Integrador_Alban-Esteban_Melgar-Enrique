window.addEventListener('load', function () {
    const urlTurnos = '/turnos';
    const settings = {
        method: 'GET'
    };

    fetch(urlTurnos, settings)
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
                    mostrarDetallesTurno(turno);
                });
                turnoTableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Error al obtener los turnos:', error));

    function mostrarDetallesTurno(turno) {
        document.querySelector('#turno_id').textContent = turno.id;
        document.querySelector('#turno_fecha').textContent = turno.fecha;

        document.querySelector('#paciente_nombre').textContent = turno.paciente.nombre;
        document.querySelector('#paciente_apellido').textContent = turno.paciente.apellido;
        document.querySelector('#paciente_dni').textContent = turno.paciente.dni;
        document.querySelector('#paciente_domicilio').textContent = `${turno.paciente.domicilio.calle} ${turno.paciente.domicilio.numero}, ${turno.paciente.domicilio.localidad}, ${turno.paciente.domicilio.provincia}`;

        document.querySelector('#odontologo_nombre').textContent = turno.odontologo.nombre;
        document.querySelector('#odontologo_apellido').textContent = turno.odontologo.apellido;
        document.querySelector('#odontologo_matricula').textContent = turno.odontologo.matricula;

        document.querySelector('#turno_info_section').style.display = 'block';
    }
});
