package com.example.tp4_exo2

import java.io.Serializable

class Livre(titre : String, auther : String, photo : String, resume : String) : Serializable{
    var titre = titre
    var autheur = auther
    var photo = photo
    var resume = resume
}