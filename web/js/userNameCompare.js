window.addEventListener("load", function () {
    function setCheckEvent(){
        // let button = document.getElementById("isExist");
        let newUsername = document.getElementById('newUsername')
        newUsername.addEventListener('input',async function() {
            let messageText = document.getElementById('messageText');
            messageText.innerHTML = "";
            let newUsername = document.getElementById('newUsername').value;
            if(newUsername === ""){
                disableSubmit()
            } else {
                let newMessageTextContainer = document.createElement("div")
                messageText.append(newMessageTextContainer);
                let newMessageText = document.createElement('p');
                let isExist = await checkUsername(newUsername)
                if (!isExist) {
                    newMessageText.innerText = "This username has been registered."
                    newMessageText.style = "color: red";
                    disableSubmit()
                } else {
                    newMessageText.innerText = "This is available."
                    newMessageText.style = "color: green";
                    ableSubmit()
                }
                newMessageTextContainer.append(newMessageText);
            }
        });
    }
    setCheckEvent();
    async function checkUsername(username) {
        let check = await fetch("./username-compare?username=" + username);
        return check.json();
    }
    function disableSubmit(){
        getElement().disabled = "disabled";
    }
    function ableSubmit() {
        getElement().removeAttribute("disabled");
    }
    function getElement() {
        let updateBtn = document.getElementById("updateBtn");
        if(updateBtn !== null) {
            return updateBtn;
        }
        let register = document.getElementById("register");
        if(register !== null) {
            return register;
        }
    }
})