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

    const deleteForm = document.querySelector('#delete_paciente_form');
    deleteForm.addEventListener('submit', function (event) {
        event.preventDefault();

        let selectedId = document.querySelector('#paciente_select').value;
        if (!selectedId) {
            alert("Seleccione un paciente válido.");
            return;
        }

        const deleteSettings = {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            }
        };

        fetch(`${url}/${selectedId}`, deleteSettings)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al eliminar el paciente');
                }
                return response.text().then(text => {
                    return text ? JSON.parse(text) : {};
                });
            })
            .then(data => {
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong>Paciente eliminado correctamente</strong></div>';

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";

                let pacienteSelect = document.querySelector('#paciente_select');
                let optionToRemove = pacienteSelect.querySelector(`option[value="${selectedId}"]`);
                optionToRemove.remove();
            })
            .catch(error => {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong>Error al eliminar el paciente. Intente nuevamente.</strong></div>';

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                console.error('Error:', error);
            });
    });
});
