async function addProduct(){
    var name=document.getElementById("name").value;
    var password=document.getElementById("password").value;
    var amount=document.getElementById(amount).value;
    if(name==""||number==""){
        alert("Name, Password and Amoint is Required");
        return;
    }
    var url = "http://localhost:8989/phoneBook/manageNumber?password="+password+"&name="+name+"&amount="+amount;
    document.getElementById("name").value="";
    document.getElementById("password").value="";
    document.getElementById("amount").value="";
    var response = await fetch(url, { method: "PUT" });
    if(response.ok){
        alert("Product successfully saved")
    }else if(response.status===403){
        alert("Incorrect password");
    }else{
        alert("Candy name not found");
    }
}