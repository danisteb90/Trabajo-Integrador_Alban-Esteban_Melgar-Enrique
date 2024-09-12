document.getElementById("add_new_paciente").onsubmit = function(e) {
    e.preventDefault();
};

window.addEventListener('load', function () {

    const formulario = document.querySelector('#add_new_paciente');

    // Al enviar el formulario, creamos un JSON con los datos del nuevo paciente
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            fechaAlta: new Date().toISOString().slice(0, 10), // Fecha actual en formato yyyy-mm-dd
            domicilio: {
                calle: document.querySelector('#calle').value,
                numero: parseInt(document.querySelector('#numero').value),
                localidad: document.querySelector('#localidad').value,
                provincia: document.querySelector('#provincia').value
            }
        };

        const url = '/pacientes';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        };

        // Enviar la solicitud para agregar el nuevo paciente
        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                // Si no hay errores, mostrar un mensaje de éxito
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong>Paciente agregado correctamente</strong></div>';

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                resetForm();

            })
            .catch(error => {
                // Si hay errores, mostrar un mensaje de error
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong>Error al agregar el paciente. Intente nuevamente.</strong></div>';

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
            });
    });

    // Función para resetear el formulario después de agregar el paciente
    function resetForm() {
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#dni').value = "";
        document.querySelector('#calle').value = "";
        document.querySelector('#numero').value = "";
        document.querySelector('#localidad').value = "";
        document.querySelector('#provincia').value = "";
    }

    // Resaltar el enlace activo en la barra de navegación
    (function() {
        let pathname = window.location.pathname;
        if (pathname === "/views/crearPaciente.html") {
            document.querySelector(".nav .nav-item a:first").classList.add("active");
        }
    })();
});
