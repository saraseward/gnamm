package com.sara.gnamm.models.user

//Only one primary constructor
data class SocialInfo(

        val idToken: String,

        var socialType: SocialType
)

enum class SocialType { GOOGLE, FACEBOOK }