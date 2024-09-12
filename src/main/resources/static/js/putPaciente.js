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

    const selectForm = document.querySelector('#select_paciente_form');
    selectForm.addEventListener('submit', function (event) {
        event.preventDefault();

        let selectedId = document.querySelector('#paciente_select').value;
        if (!selectedId) {
            alert("Seleccione un paciente válido.");
            return;
        }

        fetch(`${url}/${selectedId}`, settings)
            .then(response => response.json())
            .then(paciente => {
                document.querySelector('#paciente_id').value = paciente.id;
                document.querySelector('#paciente_nombre').value = paciente.nombre;
                document.querySelector('#paciente_apellido').value = paciente.apellido;
                document.querySelector('#paciente_dni').value = paciente.dni;
                document.querySelector('#paciente_calle').value = paciente.domicilio.calle;
                document.querySelector('#paciente_numero').value = paciente.domicilio.numero;
                document.querySelector('#paciente_localidad').value = paciente.domicilio.localidad;
                document.querySelector('#paciente_provincia').value = paciente.domicilio.provincia;

                document.querySelector('#update_paciente_form_section').style.display = "block";
            })
            .catch(error => {
                alert("Error al cargar la información del paciente.");
                console.error('Error:', error);
            });
    });

    const updateForm = document.querySelector('#update_paciente_form');
    updateForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const updatedData = {
            id: document.querySelector('#paciente_id').value,
            nombre: document.querySelector('#paciente_nombre').value,
            apellido: document.querySelector('#paciente_apellido').value,
            dni: document.querySelector('#paciente_dni').value,
            domicilio: {
                calle: document.querySelector('#paciente_calle').value,
                numero: parseInt(document.querySelector('#paciente_numero').value),
                localidad: document.querySelector('#paciente_localidad').value,
                provincia: document.querySelector('#paciente_provincia').value
            }
        };

        const putSettings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(updatedData)
        };

        fetch(`${url}/${updatedData.id}`, putSettings)
            .then(response => response.json())
            .then(data => {
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong>Paciente actualizado correctamente</strong></div>';

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
            })
            .catch(error => {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong>Error al actualizar el paciente. Intente nuevamente.</strong></div>';

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                console.error('Error:', error);
            });
    });
});
