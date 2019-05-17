package com.example.chucknorrisfiction.responce

//import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

//@Generated("com.robohorse.robopojogenerator")
data class Value(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("categories")
	val categories: List<String?>? = null,

	@field:SerializedName("joke")
	val joke: String? = null
)