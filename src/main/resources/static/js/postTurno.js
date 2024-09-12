document.getElementById("crear_turno_form").onsubmit = function(e) {
    e.preventDefault();
};

window.addEventListener('load', function () {
    const urlOdontologos = '/odontologos';
    const urlPacientes = '/pacientes';

    fetch(urlOdontologos, { method: 'GET' })
        .then(response => response.json())
        .then(data => {
            let odontologoSelect = document.querySelector('#odontologo_select');
            data.forEach(odontologo => {
                let option = document.createElement('option');
                option.value = JSON.stringify(odontologo);
                option.text = `${odontologo.nombre} ${odontologo.apellido}`;
                odontologoSelect.appendChild(option);
            });
        });

    fetch(urlPacientes, { method: 'GET' })
        .then(response => response.json())
        .then(data => {
            let pacienteSelect = document.querySelector('#paciente_select');
            data.forEach(paciente => {
                let option = document.createElement('option');
                option.value = JSON.stringify(paciente);
                option.text = `${paciente.nombre} ${paciente.apellido}`;
                pacienteSelect.appendChild(option);
            });
        });

    const formulario = document.querySelector('#crear_turno_form');
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        const odontologoSeleccionado = JSON.parse(document.querySelector('#odontologo_select').value);
        const pacienteSeleccionado = JSON.parse(document.querySelector('#paciente_select').value);
        const fechaSeleccionada = document.querySelector('#fecha_turno').value;

        const formData = {
            odontologo: odontologoSeleccionado,
            paciente: pacienteSeleccionado,
            fecha: fechaSeleccionada
        };

        const url = '/turnos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        };

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong>Turno creado correctamente</strong></div>';

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                resetForm();
            })
            .catch(error => {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong>Error al crear el turno. Intente nuevamente.</strong></div>';

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
            });
    });

    function resetForm() {
        document.querySelector('#odontologo_select').value = "";
        document.querySelector('#paciente_select').value = "";
        document.querySelector('#fecha_turno').value = "";
    }
});
