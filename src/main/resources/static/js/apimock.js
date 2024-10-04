// Definición de un módulo llamado 'apimock'.
var apimock = (function(){
    "use strict";

    // Objeto para almacenar los blueprints por autor
    var mockdata = {};

    // Blueprints para el autor "johnconnor"
    mockdata["johnconnor"] = [
        {author: "johnconnor", points: [{"x":150,"y":120}, {"x":215,"y":115}], name: "house"},
        {author: "johnconnor", points: [{"x":340,"y":240}, {"x":15,"y":215}], name: "gear"}
    ];

    // Blueprints para el autor "maryweyland"
    mockdata["maryweyland"] = [
        {author: "maryweyland", points: [{"x":140,"y":140}, {"x":115,"y":115}], name: "house2"},
        {author: "maryweyland", points: [{"x":140,"y":140}, {"x":115,"y":115}], name: "gear2"}
    ];

    // Blueprints para el autor "juan"
    mockdata["juan"] = [
        {author: "juan", points: [{"x":180,"y":150}, {"x":115,"y":120}], name: "prueba1"}
    ];

    // Blueprints para el autor "david"
    mockdata["david"] = [
        {author: "david", points: [{"x":18,"y":15}, {"x":195,"y":140}], name: "prueba2"}
    ];

    return {
        /**
         * Obtiene todos los blueprints de un autor específico.
         * @param {string} authname - Nombre del autor.
         * @param {function} callback - Función de callback que recibe los blueprints.
         */
        getBlueprintsByAuthor: function(authname, callback){
            setTimeout(function(){
                if (mockdata.hasOwnProperty(authname)) {
                    callback(mockdata[authname]);
                } else {
                    callback([]); // Retorna un array vacío si el autor no existe
                }
            }, 500); // Simula un retraso de 500ms
        },

        /**
         * Obtiene un blueprint específico por nombre y autor.
         * @param {string} authname - Nombre del autor.
         * @param {string} bpname - Nombre del blueprint.
         * @param {function} callback - Función de callback que recibe el blueprint.
         */
        getBlueprintsByNameAndAuthor: function(authname, bpname, callback){
            setTimeout(function(){
                if (mockdata.hasOwnProperty(authname)) {
                    var blueprint = mockdata[authname].find(function(e){ return e.name === bpname });
                    callback(blueprint || null); // Retorna null si no se encuentra el blueprint
                } else {
                    callback(null); // Retorna null si el autor no existe
                }
            }, 500); // Simula un retraso de 500ms
        }
    }

})();


