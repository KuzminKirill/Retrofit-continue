package com.example.kirill.retrofittry.Structures

class RegistrationBody(name : String, email : String, pass : String, confirm :String) {
    var username = name
    var email = email
    var password1 = pass
    var password2 = confirm
}