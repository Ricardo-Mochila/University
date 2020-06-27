function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition, showError);
    } else {
        showWithoutPosition();
    }
}

function showError(error) {
    switch(error.code) {
        case error.PERMISSION_DENIED:
            showWithoutPosition();
            break;
        case error.POSITION_UNAVAILABLE:
            showWithoutPosition();
            break;
        case error.TIMEOUT:
            showWithoutPosition();
            break;
        case error.UNKNOWN_ERROR:
            showWithoutPosition();
            break;
    }
}

function showPosition(position) {

    var mymap = L.map('mapid').setView([position.coords.latitude, position.coords.longitude], 15);

    L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
        maxZoom: 18,
        attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
            '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
            'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
        id: 'mapbox/streets-v11',
        tileSize: 512,
        zoomOffset: -1
    }).addTo(mymap);


    fetch('http://localhost:8080/Stores')
        .then(response => response.json())
        .then(data => markerPlacement(data));

    var popup = L.popup();

    var circle = L.circle([position.coords.latitude, position.coords.longitude], {
        color: 'red',
        fillColor: '#f03',
        fillOpacity: 0.5,
        radius: 500
    }).addTo(mymap);

    mymap.on('click', onMapClick);



    function onMapClick(e) {
        popup
            .setLatLng(e.latlng)
            .setContent("<form action=\"Stores\" method=\"post\" id=\"form\" >\n" +
                "        <div class=\"inputField\">\n" +
                "            <label class=\"boxLabel\">Nome Da Loja</label><br/>\n" +
                "            <input  type=\"text\" id='name' name=\"StoreName\" class=\"inputSpace\"/> \n" +
                "        </div>\n" +
                "        <div style ='display: none'class=\"inputField\">\n" +
                "            <label class=\"boxLabel\">Latitude</label><br/>\n" +
                "           <input type=\"text\" name=\"latitude\" value= "+e.latlng.lat+" class=\"inputSpace\"/>  \n" +
                "        </div>\n" +
                "        <div style ='display: none' class=\"inputField\">\n" +
                "            <label class=\"boxLabel\">Longitude</label><br/>\n" +
                "            <input id = \"lg\" type=\"text\" name=\"longitude\" value= "+e.latlng.lng+" class=\"inputSpace\"/> \n" +
                "        </div>\n" +
                "        <div id = \"radioButtonArea\">\n" +
                "            <div class=\"radioButton\">\n" +
                "                <input  type=\"radio\" name=\"ocupationLevel\" value = \"empty\"/> <label>Vazio ou com minima lotação</label>\n" +
                "            </div>\n" +
                "            <div class=\"radioButton\">\n" +
                "                <input type=\"radio\" name=\"ocupationLevel\" value = \"enough_space\"/> <label>Com pessoas mas espaço suficiente</label>\n" +
                "            </div>\n" +
                "            <div class=\"radioButton\">\n" +
                "                <input type=\"radio\" name=\"ocupationLevel\" value = \"full_space\"/> <label>Muito Cheio</label>\n" +
                "            </div>\n" +
                "            <div class=\"radioButton\">\n" +
                "                <input type=\"radio\" name=\"ocupationLevel\" value = \"queue\"/> <label>Muito Cheio e com fila de espera</label>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div>\n" +
                "            <input type=\"submit\" value=\"Submeter\" id=\"submit\"/>\n" +
                "        </div>\n" +
                "    </form>")
            .openOn(mymap);

        console.log(e.latlng)
    }


    function markerPlacement(data){

        data.forEach(elem => {
            let latitude = elem.latitude
            let longitude = elem.longitude
            let name = elem.storeName
            let empty = elem.empty
            let enough = elem.enough_space
            let full = elem.full_space
            let queue = elem.queue
            var marker = L.marker([latitude, longitude]).addTo(mymap);
            marker.bindPopup("<script>function myFunc(){ } </script>"+
                "<b>"+name+"</b><br>Vazio: "+ empty + "<br>Com Espaço: " + enough+ "<br>Cheio: " + full+ "<br>Com Fila: "
                + queue + " <br>"+"<button id='buttonAdd'onclick='document.getElementById(`form`).style.display = `block`; document.getElementById(`buttonAdd`).style.display = `none`' style='border: none; " +
                "border-radius: 5px; background-color:rgb(85, 179, 255);  margin-top: 10px; color: aliceblue;height: 30px; ' >Adicionar </button>"+
            "<form action=\"Stores\" method=\"post\" id=\"form\" style='display: none'> \n" +
            "        <div style ='display: none' class=\"inputField\">\n" +
            "            <label class=\"boxLabel\">Nome Da Loja</label><br/>\n" +
            "            <input  type=\"text\" id='name' name=\"StoreName\" value= \"" +name+ "\" class=\"inputSpace\"/> \n" +
            "        </div>\n" +
            "        <div style ='display: none' class=\"inputField\">\n" +
            "            <label class=\"boxLabel\">Latitude</label><br/>\n" +
            "           <input type=\"text\" name=\"latitude\" value= "+latitude+" class=\"inputSpace\"/>  \n" +
            "        </div>\n" +
            "        <div style ='display: none' class=\"inputField\">\n" +
            "            <label class=\"boxLabel\">Longitude</label><br/>\n" +
            "            <input id = \"lg\" type=\"text\" name=\"longitude\" value= "+longitude+" class=\"inputSpace\"/> \n" +
            "        </div>\n" +
            "        <div id = \"radioButtonArea\">\n" +
            "            <div class=\"radioButton\">\n" +
            "                <input  type=\"radio\" name=\"ocupationLevel\" value = \"empty\"/> <label>Vazio</label>\n" +
            "            </div>\n" +
            "            <div class=\"radioButton\">\n" +
            "                <input type=\"radio\" name=\"ocupationLevel\" value = \"enough_space\"/> <label>C/Espaço</label>\n" +
            "            </div>\n" +
            "            <div class=\"radioButton\">\n" +
            "                <input type=\"radio\" name=\"ocupationLevel\" value = \"full_space\"/> <label>Cheio</label>\n" +
            "            </div>\n" +
            "            <div class=\"radioButton\">\n" +
            "                <input type=\"radio\" name=\"ocupationLevel\" value = \"queue\"/> <label>C/Fila</label>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "        <div>\n" +
            "            <input type=\"submit\" value=\"Submeter\" id=\"submit\"/>\n" +
            "        </div>\n" +
            "    </form>" ).openPopup();
        })

    }

    document.getElementById("listByLocation").src = `http://localhost:8080/StoreByLocation?latitude=${position.coords.latitude}&longitude=${position.coords.longitude}`


}

function showWithoutPosition() {

    var mymap = L.map('mapid').setView([38.5714, -7.9135], 15);

    L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
        maxZoom: 18,
        attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
            '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
            'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
        id: 'mapbox/streets-v11',
        tileSize: 512,
        zoomOffset: -1
    }).addTo(mymap);


    fetch('http://localhost:8080/Stores')
        .then(response => response.json())
        .then(data => markerPlacement(data));

    var popup = L.popup();

    mymap.on('click', onMapClick);



    function onMapClick(e) {
        popup
            .setLatLng(e.latlng)
            .setContent("<form action=\"Stores\" method=\"post\" id=\"form\" >\n" +
                "        <div class=\"inputField\">\n" +
                "            <label class=\"boxLabel\">Nome Da Loja</label><br/>\n" +
                "            <input  type=\"text\" id='name' name=\"StoreName\" class=\"inputSpace\"/> \n" +
                "        </div>\n" +
                "        <div style ='display: none'class=\"inputField\">\n" +
                "            <label class=\"boxLabel\">Latitude</label><br/>\n" +
                "           <input type=\"text\" name=\"latitude\" value= "+e.latlng.lat+" class=\"inputSpace\"/>  \n" +
                "        </div>\n" +
                "        <div style ='display: none' class=\"inputField\">\n" +
                "            <label class=\"boxLabel\">Longitude</label><br/>\n" +
                "            <input id = \"lg\" type=\"text\" name=\"longitude\" value= "+e.latlng.lng+" class=\"inputSpace\"/> \n" +
                "        </div>\n" +
                "        <div id = \"radioButtonArea\">\n" +
                "            <div class=\"radioButton\">\n" +
                "                <input  type=\"radio\" name=\"ocupationLevel\" value = \"empty\"/> <label>Vazio ou com minima lotação</label>\n" +
                "            </div>\n" +
                "            <div class=\"radioButton\">\n" +
                "                <input type=\"radio\" name=\"ocupationLevel\" value = \"enough_space\"/> <label>Com pessoas mas espaço suficiente</label>\n" +
                "            </div>\n" +
                "            <div class=\"radioButton\">\n" +
                "                <input type=\"radio\" name=\"ocupationLevel\" value = \"full_space\"/> <label>Muito Cheio</label>\n" +
                "            </div>\n" +
                "            <div class=\"radioButton\">\n" +
                "                <input type=\"radio\" name=\"ocupationLevel\" value = \"queue\"/> <label>Muito Cheio e com fila de espera</label>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div>\n" +
                "            <input type=\"submit\" value=\"Submeter\" id=\"submit\"/>\n" +
                "        </div>\n" +
                "    </form>")
            .openOn(mymap);

        console.log(e.latlng)
    }


    function markerPlacement(data){

        data.forEach(elem => {

            let latitude = elem.latitude
            let longitude = elem.longitude
            let name = elem.storeName
            let empty = elem.empty
            let enough = elem.enough_space
            let full = elem.full_space
            let queue = elem.queue
            var marker = L.marker([latitude, longitude]).addTo(mymap);

            marker.bindPopup("<script>function myFunc(){ } </script>"+
                "<b>"+name+"</b><br>Vazio: "+ empty + "<br>Com Espaço: " + enough+ "<br>Cheio: " + full+ "<br>Com Fila: "
                + queue + " <br>"+"<button id='buttonAdd'onclick='document.getElementById(`form`).style.display = `block`; document.getElementById(`buttonAdd`).style.display = `none`' style='border: none; " +
                "border-radius: 5px; background-color:rgb(85, 179, 255);  margin-top: 10px; color: aliceblue;height: 30px; ' >Adicionar </button>"+
                "<form action=\"Stores\" method=\"post\" id=\"form\" style='display: none'> \n" +
                "        <div style ='display: none' class=\"inputField\">\n" +
                "            <label class=\"boxLabel\">Nome Da Loja</label><br/>\n" +
                "            <input  type=\"text\" id='name' name=\"StoreName\" value= \"" +name+ "\" class=\"inputSpace\"/> \n" +
                "        </div>\n" +
                "        <div style ='display: none' class=\"inputField\">\n" +
                "            <label class=\"boxLabel\">Latitude</label><br/>\n" +
                "           <input type=\"text\" name=\"latitude\" value= "+latitude+" class=\"inputSpace\"/>  \n" +
                "        </div>\n" +
                "        <div style ='display: none' class=\"inputField\">\n" +
                "            <label class=\"boxLabel\">Longitude</label><br/>\n" +
                "            <input id = \"lg\" type=\"text\" name=\"longitude\" value= "+longitude+" class=\"inputSpace\"/> \n" +
                "        </div>\n" +
                "        <div id = \"radioButtonArea\">\n" +
                "            <div class=\"radioButton\">\n" +
                "                <input  type=\"radio\" name=\"ocupationLevel\" value = \"empty\"/> <label>Vazio</label>\n" +
                "            </div>\n" +
                "            <div class=\"radioButton\">\n" +
                "                <input type=\"radio\" name=\"ocupationLevel\" value = \"enough_space\"/> <label>C/Espaço</label>\n" +
                "            </div>\n" +
                "            <div class=\"radioButton\">\n" +
                "                <input type=\"radio\" name=\"ocupationLevel\" value = \"full_space\"/> <label>Cheio</label>\n" +
                "            </div>\n" +
                "            <div class=\"radioButton\">\n" +
                "                <input type=\"radio\" name=\"ocupationLevel\" value = \"queue\"/> <label>C/Fila</label>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div>\n" +
                "            <input type=\"submit\" value=\"Submeter\" id=\"submit\"/>\n" +
                "        </div>\n" +
                "    </form>" ).openPopup();
        })

        document.getElementById("leafletButton").addEventListener("click", myfunc())


    }



}

getLocation()