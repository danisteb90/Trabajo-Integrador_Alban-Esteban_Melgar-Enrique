window.addEventListener('load', function () {
    const url = '/pacientes';
    const settings = {
        method: 'GET'
    };

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            let pacienteSelect = document.querySelector('#paciente_select');
            data.forEach(paciente => {
                let option = document.createElement('option');
                option.value = paciente.id;
                option.text = `${paciente.nombre} ${paciente.apellido}`;
                pacienteSelect.appendChild(option);
            });
        });

    const formulario = document.querySelector('#buscar_paciente_form');
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        let selectedId = document.querySelector('#paciente_select').value;
        if (!selectedId) {
            alert("Seleccione un paciente válido.");
            return;
        }

        fetch(`${url}/${selectedId}`, settings)
            .then(response => response.json())
            .then(paciente => {
                document.querySelector('#paciente_id').innerText = paciente.id;
                document.querySelector('#paciente_nombre').innerText = paciente.nombre;
                document.querySelector('#paciente_apellido').innerText = paciente.apellido;
                document.querySelector('#paciente_dni').innerText = paciente.dni;
                document.querySelector('#paciente_fechaAlta').innerText = paciente.fechaAlta;
                document.querySelector('#paciente_domicilio').innerText = `${paciente.domicilio.calle} ${paciente.domicilio.numero}, ${paciente.domicilio.localidad}, ${paciente.domicilio.provincia}`;
                document.querySelector('#paciente_info').style.display = "block";
            })
            .catch(error => {
                alert("Error al obtener la información del paciente. Intente nuevamente.");
                console.error('Error:', error);
            });
    });
});
