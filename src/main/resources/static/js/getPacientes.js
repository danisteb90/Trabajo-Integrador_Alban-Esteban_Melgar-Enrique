window.addEventListener('load', function () {

    const url = '/pacientes';
    const settings = {
        method: 'GET'
    };

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            let pacienteTable = document.getElementById("pacienteTable");


            data.forEach(paciente => {
                let pacienteRow = pacienteTable.insertRow();
                pacienteRow.id = 'tr_' + paciente.id;

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


    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/views/listarPacientes.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
        }
    })();

});
