(document.getElementsByClassName("close")[0]).onclick = function() {
    (document.getElementById('myModal')).style.display = "none";
}
window.onclick = function(event) {
    var modal = (document.getElementById('myModal'));
    if (event.target == modal) {
        modal.style.display = "none";
    }
}