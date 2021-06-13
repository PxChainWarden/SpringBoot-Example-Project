var form = document.getElementById("movieform");

    $('#actorSelect').selectize({
        placeholder: 'Select an Actor/Actress',
        sortField: 'text',
        valueField: 'value'
    });
    $('#actorSearch').selectize({
        placeholder: 'Select an Actor/Actress',
        sortField: 'text',
        items: null,
        onChange: function (value){
            var newUrl = updateURLParameter(document.location.href,'page',0);
            newUrl = updateURLParameter(newUrl,'a',value);
            $('#movieSearchByActorButton').attr("href",newUrl)
        }
    });

function clearMovieForm(){
    // $('.movie-id')[0].value = '';
    $('.movie-name')[0].value = '';
    $('.movie-date')[0].value = '';
    $('.movie-url')[0].value = '';
    $('.movie-description')[0].value = '';
    $('.movie-type')[0].value = '';
    $('.movie-media')[0].value = '';
    $('.movie-language')[0].value = '';
    $('#actorSelect')[0].selectize.clear();
    $("button[name='removeRow']").click();
}

function add(name,text){
    var mainDiv = document.createElement("div");
    mainDiv.setAttribute("name",name+"Div");
    var inputDiv = document.createElement("div");
    inputDiv.setAttribute("class","input-group mb-3");
    var inputObj = document.createElement("input");
    inputObj.setAttribute("type","text");
    inputObj.setAttribute("name",name);
    inputObj.setAttribute("class","form-control m-input movie-"+name);
    inputObj.setAttribute("placeholder","Enter "+name);
    inputObj.required = true;
    inputObj.value = text;
    var buttonDiv = document.createElement("div");
    buttonDiv.setAttribute("class","input-group-append");
    var buttonObj = document.createElement("button");
    buttonObj.setAttribute("name","removeRow");
    buttonObj.setAttribute("class","btn btn-danger");
    buttonObj.setAttribute("type","button");
    buttonObj.innerHTML = "Delete";
    buttonObj.onclick = deleteForm;
    inputDiv.appendChild(inputObj);
    buttonDiv.appendChild(buttonObj);
    inputDiv.appendChild(buttonDiv);
    mainDiv.appendChild(inputDiv);
    form.insertBefore(mainDiv,document.getElementById(name));
}
function addActors(name,surname){
    var mainDiv = document.createElement("div");
    mainDiv.setAttribute("name","actorsDiv");
    var inputDiv = document.createElement("div");
    inputDiv.setAttribute("class","input-group mb-3");
    var inputObj = document.createElement("input");
    inputObj.setAttribute("type","text");
    inputObj.setAttribute("name","fname");
    inputObj.setAttribute("class","form-control");
    inputObj.setAttribute("placeholder","First Name");
    inputObj.required = true;
    inputObj.value = name;
    var inputObj2 = document.createElement("input");
    inputObj2.setAttribute("type","text");
    inputObj2.setAttribute("name","surname");
    inputObj2.setAttribute("placeholder","Last Name");
    inputObj2.setAttribute("class","form-control");
    inputObj2.required = true;
    inputObj2.value = surname;
    var buttonDiv = document.createElement("div");
    buttonDiv.setAttribute("class","input-group-append");
    var buttonObj = document.createElement("button");
    buttonObj.setAttribute("name","removeRow");
    buttonObj.setAttribute("class","btn btn-danger");
    buttonObj.setAttribute("type","button");
    buttonObj.innerHTML = "Delete";
    buttonObj.onclick = deleteForm;
    var prependDiv = document.createElement("div");
    prependDiv.setAttribute("class","input-group-prepend");
    var prependSpan = document.createElement("span");
    prependSpan.setAttribute("class","input-group-text");
    prependSpan.innerHTML = "Actor Name";
    prependDiv.appendChild(prependSpan);
    inputDiv.appendChild(prependDiv);
    inputDiv.appendChild(inputObj);
    inputDiv.appendChild(inputObj2);
    buttonDiv.appendChild(buttonObj);
    inputDiv.appendChild(buttonDiv);
    mainDiv.appendChild(inputDiv);
    form.insertBefore(mainDiv,document.getElementById("actors"));
}

function deleteForm(){
    this.closest("form>div").remove();
}

function filterFunction() {
  var input, filter, ul, li, a, i;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  div = document.getElementById("actorsSelect");
  a = div.getElementsByTagName("a");
  div.style.visibility = "visible"
  for (i = 0; i < a.length; i++) {
    txtValue = a[i].textContent || a[i].innerText;
    if (txtValue.toUpperCase().indexOf(filter) > -1) {
      a[i].style.display = "";
    } else {
      a[i].style.display = "none";
    }
  }
}

$('.movieAddButton').on('click',function (event){
   clearMovieForm();
    $('#movieAddModalLabel')[0].innerHTML = 'New Movie';
    $('#movieform')[0].action = "/api/movieadd/add";
});
$('.editButton').on('click',function (event){
    event.preventDefault();
    clearMovieForm();
    $('#movieAddModalLabel')[0].innerHTML = 'Edit Movie';
    var movieUpdateId = $(this).attr('href').split('?')[1];
    $('#movieform')[0].action = "/api/movieadd/update/?"+movieUpdateId;
    var href = $(this).attr('href');
    $.get(href,function (movie,status){
        $('.movie-name')[0].value = movie.name;
        $('.movie-date')[0].value = movie.release;
        $('.movie-description')[0].value = movie.description;
        $('.movie-url')[0].value = movie.url;
        movie.type.forEach(function (type,index){
            if(index == 0)
                $('.movie-type')[index].value = type;
            else
                add('type',type);
        });
        movie.media.forEach(function (media,index){
            if(index == 0)
                $('.movie-media')[index].value = media;
            else
                add('media',media);
        });
        movie.language.forEach(function (language,index){
            if (index==0)
                $('.movie-language')[index].value = language;
            else
                add('language',language)
        });
        var actorValues = [];
        movie.actors.forEach(function (actor){
            actorValues.push(actor.id);
        });
        $('#actorSelect')[0].selectize.setValue(actorValues);
    });
});
$('.deleteButton').on('click',function (event) {
    event.preventDefault();
    var movieDeleteId = $(this).attr('href').split('?')[1];
    $('#movieDeleteForm')[0].action = "/api/movieadd/delete/?"+movieDeleteId;
});
$('#movieSearchInput').on('input',function (event){
    var newUrl = updateURLParameter(document.location.href,'page',0);
    newUrl = updateURLParameter(newUrl,'q',$('#movieSearchInput')[0].value);
    // console.log(newUrl);
    $('#movieSearchButton') .attr('href', newUrl);
});

$('#movieSearchTypeInput').on('input',function (event){
    var newUrl = updateURLParameter(document.location.href,'page',0);
    newUrl = updateURLParameter(newUrl,'t',$('#movieSearchTypeInput')[0].value);
    $('#movieSearchTypeButton') .attr('href', newUrl);
});


/**
 * http://stackoverflow.com/a/10997390/11236
 */

function updateURLParameter(url, param, paramVal){
    var newAdditionalURL = "";
    var tempArray = url.split("?");
    var baseURL = tempArray[0];
    var additionalURL = tempArray[1];
    var temp = "";
    if (additionalURL) {
        tempArray = additionalURL.split("&");
        for (var i=0; i<tempArray.length; i++){
            if(tempArray[i].split('=')[0] != param){
                newAdditionalURL += temp + tempArray[i];
                temp = "&";
            }
        }
    }

    var rows_txt = temp + "" + param + "=" + paramVal;
    return baseURL + "?" + newAdditionalURL + rows_txt;
}