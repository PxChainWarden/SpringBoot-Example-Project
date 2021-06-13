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