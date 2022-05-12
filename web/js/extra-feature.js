window.addEventListener("load", function () {
    function commentButton() {
        let commentButtons = document.getElementsByClassName('comment');
        for(let button of commentButtons){
            let container = button.parentNode;
            button.onclick = function () {
                deleteTestArea();
                createTestArea(container,button);
            }
        }
    }

    function deleteTestArea() {
        let textArea = document.getElementById('nestedComment');
        if(textArea != null) {
            textArea.parentNode.removeChild(textArea);
        }
    }
    function createTestArea(container, button) {
        let textArea = document.createElement('div');
        textArea.id = 'nestedComment';
        let userId = document.getElementById('userId').value;
        let artId = document.getElementById('artId').value;
        let username = document.getElementById('username').value;
        textArea.innerHTML = `<form action="./comment-add?artId=${artId}&userId=${userId}&parentId=${container.id}&username=${username}" method="post" >\n` +
            `            <div>\n` +
            `                <textarea name="comContent" id="new-comment" style="resize: none" placeholder="To ${button.value}" rows="1" cols="30" required></textarea>\n` +
            `            </div>\n` +
            `            <div>\n` +
            `                <input type="checkbox" name="hidden" id="hidden" value="hidden">\n`+
            `                <label for="hidden">Only visible to author</label>\n`+
            `                <button class="btn btn-outline-dark btn-sm" type="submit">reply</button>\n` +
            `            </div>\n` +
            `        </form>`
        container.append(textArea);
    }

    function nestedComment(){
        let show = document.getElementById("nested-comment");
        let hide = document.getElementById("hide-comment");

        show.addEventListener("click", function () {
            hide.removeAttribute("hidden");
            show.setAttribute("hidden","true");
            document.getElementById("comments").removeAttribute("hidden");

        })

        hide.addEventListener("click", function () {
            show.removeAttribute("hidden");
            hide.setAttribute("hidden","true");
            document.getElementById("comments").setAttribute("hidden", "true");
        })
    }

    commentButton();
    nestedComment();
})