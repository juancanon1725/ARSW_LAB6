var apiclient = (function () {
    // Definición de la URL base de la API REST, ajustada según sea necesario.
    var apiUrl = "http://localhost:8080/blueprints/";

    return {
        // Función para obtener planos por autor.
        getBlueprintsByAuthor: function (authname, callback) {
            $.ajax({
                url: apiUrl + authname,
                type: 'GET',
                dataType: 'json',
                success: function (data) {
                    // Verifica que callback esté definido antes de llamarlo.
                    if (typeof callback === 'function') {
                        callback(data);
                    }
                },
                error: function (error) {
                    console.error('Error al obtener planos por autor:', error);
                    if (typeof callback === 'function') {
                        callback([]);  // Llama al callback con una lista vacía en caso de error.
                    }
                }
            });
        },

        // Función para obtener un plano por nombre y autor.
        getBlueprintsByNameAndAuthor: function (authname, bpname, callback) {
            $.ajax({
                url: apiUrl + authname + '/' + bpname,
                type: 'GET',
                dataType: 'json',
                success: function (data) {
                    if (typeof callback === 'function') {
                        callback(data);
                    }
                },
                error: function (error) {
                    console.error('Error al obtener plano por nombre y autor:', error);
                    if (typeof callback === 'function') {
                        callback(null);  // Llama al callback con null en caso de error.
                    }
                }
            });
        }
    };
})();
