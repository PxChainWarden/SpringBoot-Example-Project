$('.editActorButton').on('click', function (event){
    event.preventDefault();
    var href = $(this).attr('href');
    $('#actorForm')[0].action = href;
    $('#actorAddModalLabel')[0].innerHTML = "Edit Actor";
    $.get("/api/actor/find?"+href.split("?")[1],function (actor,status){
        $('.actor-name')[0].value= actor.name;
        $('.actor-surname')[0].value = actor.surname;
    });
});

$('.actorAddButton').on('click',function (event){
    clearActorForm();
})

$('.deleteActorButton').on('click',function (event){
    event.preventDefault();
    // var movieDeleteId = $(this).attr('href').split('?')[1];
    $('#actorDeleteForm')[0].action = $(this).attr('href');
});

function clearActorForm(){
    $('#actorAddModalLabel')[0].innerHTML = 'New Actor';
    $('#actorForm')[0].action = "/api/actor/add";
    $('.actor-name')[0].value= '';
    $('.actor-surname')[0].value = '';
}

function sortActors(n) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("Actors");
    switching = true;
    // Set the sorting direction to ascending:
    dir = "asc";
    if(dir == "asc")
        // var arrowDown = document.createElement("")
        // console.log(table.rows[0].innerText.split('\t')[n]);

    /* Make a loop that will continue until
    no switching has been done: */
    while (switching) {
        // Start by saying: no switching is done:
        switching = false;
        rows = table.rows;
        /* Loop through all table rows (except the
        first, which contains table headers): */
        for (i = 1; i < (rows.length - 1); i++) {
            // Start by saying there should be no switching:
            shouldSwitch = false;
            /* Get the two elements you want to compare,
            one from current row and one from the next: */
            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];
            /* Check if the two rows should switch place,
            based on the direction, asc or desc: */
            if (dir == "asc") {
                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    // If so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            } else if (dir == "desc") {
                if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                    // If so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            }
        }
        if (shouldSwitch) {
            /* If a switch has been marked, make the switch
            and mark that a switch has been done: */
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            // Each time a switch is done, increase this count by 1:
            switchcount ++;
        } else {
            /* If no switching has been done AND the direction is "asc",
            set the direction to "desc" and run the while loop again. */
            if (switchcount == 0 && dir == "asc") {
                dir = "desc";
                switching = true;
            }
        }
    }
}