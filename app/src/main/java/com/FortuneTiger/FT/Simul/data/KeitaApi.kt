package com.FortuneTiger.FT.Simul.data

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Url

interface KeitaApi {

    @POST
    suspend fun grayAskAp(
        @Url url : String
    ) : Response<String>
}