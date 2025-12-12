package com.keikuethas.gallery

class GalleryItem(
    val name:String,
    var description:String="",
    val imgID:Int, //image?
    var important:Boolean=false,
    var rate:Int=1,
    var shortDesc:String=""
) {

}