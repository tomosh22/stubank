function to2dps(){
    $("#balance").val(parseFloat($("#balance").val()).toFixed(2));
}
function Create(){
    if(!$("#name").val() || !$("#balance").val()
    ){
        alert("Please enter a name and balance");
    }
    else{
        $("#createaccountform").submit();
    }
}
function generateGraph(){

}