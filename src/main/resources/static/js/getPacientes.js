window.addEventListener('load', function () {

    // Obtener la lista de pacientes al cargar la página
    const url = '/pacientes';
    const settings = {
        method: 'GET'
    };

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            let pacienteTable = document.getElementById("pacienteTable");

            // Recorrer la colección de pacientes y armar cada fila de la tabla
            data.forEach(paciente => {
                let pacienteRow = pacienteTable.insertRow(); // Insertar una nueva fila
                pacienteRow.id = 'tr_' + paciente.id; // Asignar un id único a la fila

                pacienteRow.innerHTML =
                    '<td>' + paciente.id + '</td>' +
                    '<td>' + paciente.nombre.toUpperCase() + '</td>' +
                    '<td>' + paciente.apellido.toUpperCase() + '</td>' +
                    '<td>' + paciente.dni + '</td>' +
                    '<td>' + paciente.fechaAlta + '</td>' +
                    '<td>' + paciente.domicilio.calle + ' ' + paciente.domicilio.numero + ', ' +
                    paciente.domicilio.localidad + ', ' + paciente.domicilio.provincia + '</td>';
            });
        })
        .catch(error => {
            console.error("Error al obtener la lista de pacientes:", error);
        });

    // Resaltar el enlace activo en la barra de navegación
    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/views/listarPacientes.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
        }
    })();

});
