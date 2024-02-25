async function setup(){
    var url = "http://localhost:8989/candy-shop/store";
    var response = await fetch(url, { method: "GET" });
    var contact1= await response.text();
    const contact2=contact1.split(",");
    for(var i=0;i<contact2.length;i++){
        addMessage(contact2[i]);
    }

}

function addMessage(contact){
    var newDiv = document.createElement("div");
    var p= document.createElement("p");
    var div=document.getElementById("tbl");
    var button=document.createElement("button");
    button.className="purchase";
    newDiv.className="productList";
    p.className="candy";
    button.textContent="purchase";
    button.onclick = purchase;
    p.textContent=contact;
    newDiv.appendChild(p);
    newDiv.appendChild(button);
    div.appendChild(newDiv);
}

async function addContact(){
    var name=document.getElementById("name").value;
    var number=document.getElementById("number").value;
    if(name==""||number==""){
        clearList();
        addMessage("body2134234");
        alert("Name and NUmber is Required");
        return;
    }
    var url = "http://localhost:8989/phoneBook/manageNumber?number="+number+"&name="+name;
    document.getElementById("name").value="";
    document.getElementById("number").value=""
    var response = await fetch(url, { method: "POST" });
    if(response.ok){
        alert("Contact successfully saved")
    }else if(response.status===403){
        alert("Problem encouterd when saving the number");
    }
    findNumbers();
}
async function purchase(){
    window.open(href="http://localhost:8989/candy-shop/index.html", windowname="asdfsd", 'width=400,height=150,status=yes,scrollbars=yes');
}
