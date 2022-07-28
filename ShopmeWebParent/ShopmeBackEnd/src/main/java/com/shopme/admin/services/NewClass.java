package com.shopme.admin.services;

//
//
//
//
//---------------------
//
//
//    
//
//
//function formatDate(date) {
//    var d = new Date(date),
//        month = '' + (d.getMonth() + 1),
//        day = '' + d.getDate(),
//        year = d.getFullYear();
//
//    if (month.length < 2) 
//        month = '0' + month;
//    if (day.length < 2) 
//        day = '0' + day;
//
//    return [year, month, day].join('-');
//}
//
//function Edit(id, user) {
//    $("#user-form-modal").modal()
//    console.log("click edit button");
//    console.log("id", id);
//    console.log(user);
//    const username = document.querySelector('#username').value = user.name || ""
//    document.querySelector('#password').value = user.password || ""
//    document.querySelector('#role').value = user.role || ""      
//    document.querySelector('#createDate').value = formatDate(user.createDate) || ""
//    document.querySelector('#id').value = id || 0
//    validates();
//  
//}
//function validates(){
//    document.getElementById("userForm").addEventListener('submit', (event) => {    
//    event.preventDefault();
//    const username = document.querySelector('#username').value;
//    const password = document.querySelector('#password').value;
//    
//    
//const usernameError = document.querySelector('#error-username')
//const passwordError = document.querySelector('#error-password')
//
//  if(username===""){
//    usernameError.innerHTML="noname"
//} else if (password===""){
//    passwordError.innerHTML="nopw"
//} else {
//    
//event.currentTarget.submit();
//}})
//}
//function Create(){
//$("#user-form-modal").modal()
//date = new Date();
//year = date.getFullYear();
//month = date.getMonth() + 1;
//day = date.getDate();
//document.querySelector('#username').value = ""
// document.querySelector('#password').value =""
//document.querySelector('#role').value =  ""
//document.querySelector('#id').value = ""
//document.querySelector("#createDate").value = formatDate(date) ;
//
//    validates();
// 
//
//    
//    }
//
//
//
