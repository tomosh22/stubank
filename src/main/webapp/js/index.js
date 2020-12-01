function Login(){
    if(!$("#username").val() ||
        !$("#password").val()
    ){
        alert("Please enter a username and password");
    }
    else{
        $("#signinform").submit();
    }
}
function EnableCreate(){
    if($("#create").attr("style") == "display:block;"){
        $("#create").attr("style", "display:none;");
        $("#switchButton").html("Sign Up");
        $("#existPrompt").html("Don't have an account?");
    }
    else{
        $("#create").attr("style", "display:block;");
        $("#switchButton").html("Log In");
        $("#existPrompt").html("Already have an account?");

    }


}
function createaccount(){
    if(!$("#firstname").val()||
        !$("#lastname").val()||
        !$("#email").val()||
        !$("#phone").val()||
        !$("#username").val()||
        !$("#password").val()
    ){
        alert("Please enter all fields")
    }
    else{
        if (isValidEmail($("#email").val())){
            if(isValidPhone($("#phone").val())){
                if(isValidPassword($("#password").val())){
                    $("#createaccform").submit();
                }
                else{
                    alert("password must be 8-15 characters and contain at least one uppercase letter and one lowercase letter")
                }

            }
            else{
                alert("invalid phone")
            }
        }
        else{
            alert("invalid email")
        }
    }
}

function isValidPassword(password){
    if (password.length < 8 || password.length > 15){
        return false
    }
    number = false
    upper = false
    lower = false
    for(x=0;x<password.length;x++){
        char = password.charCodeAt(x)
        if(char >= 48 && char <= 57){
            number = true
        }
        if(char >= 65 && char <= 90){
            upper = true
        }
        if(char >= 97 && char <= 122){
            lower = true
        }
    }
    return !(!number||!upper||!lower)
}

function isValidEmail(email){
    regex = new RegExp("[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-z]+")
    return regex.test(email)
}

function isValidPhone(phone){
    regex = new RegExp("[0-9]{2}-[0-9]{4}-[0-9]{7}")
    return regex.test(phone)
}