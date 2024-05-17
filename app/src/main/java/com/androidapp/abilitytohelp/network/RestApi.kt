package com.androidapp.abilitytohelp.network

import com.androidapp.abilitytohelp.model.AbbreviationsResponse
import com.androidapp.abilitytohelp.model.AntonymsSynonymsResponse
import com.androidapp.abilitytohelp.model.ConvoResponse
import com.androidapp.abilitytohelp.model.LiteratureResponse
import com.androidapp.abilitytohelp.model.PartOfSpeechResponse
import com.androidapp.abilitytohelp.model.PeriodicElementResponse
import com.androidapp.abilitytohelp.model.RiddleResponse
import com.androidapp.abilitytohelp.model.SynonymsResponse
import com.androidapp.abilitytohelp.model.WordImageResponse
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Url

interface RestApi {

    @GET
    suspend fun getSynonyms(
        @Url baseUrl: String,
        @Header("X-RapidAPI-Key") key: String,
        @Header("X-RapidAPI-Host") host: String,
        @Query("word") word: String
    ): Response<SynonymsResponse>

    @GET("https://riddlie.p.rapidapi.com/api/v1/riddles/random")
    suspend fun getRiddle(
        @Header("X-RapidAPI-Key") key: String,
        @Header("X-RapidAPI-Host") host: String
    ): Response<RiddleResponse>

    @GET
    suspend fun getPeriodicTable(
        @Url baseUrl: String,
        @Header("X-RapidAPI-Key") key: String,
        @Header("X-RapidAPI-Host") host: String
    ): Response<PeriodicElementResponse>

    @GET
    suspend fun getWordImage(
        @Url baseUrl: String,
        @Header("X-RapidAPI-Key") key: String,
        @Header("X-RapidAPI-Host") host: String
    ): Response<WordImageResponse>

    @GET
    suspend fun getAntonymsSynonyms(
        @Url baseUrl: String,
        @Header("X-RapidAPI-Key") key: String,
        @Header("X-RapidAPI-Host") host: String,
        @Query("word") word: String
    ): Response<AntonymsSynonymsResponse>

    @GET
    suspend fun getPartsOfSpeech(
        @Url baseUrl: String,
        @Header("X-RapidAPI-Key") key: String,
        @Header("X-RapidAPI-Host") host: String,
        @Query("word") word: String
    ): Response<PartOfSpeechResponse>

    @GET
    suspend fun getLiterature(
        @Url baseUrl: String,
        @Query("uid") uid: String,
        @Query("tokenid") tokenId: String,
        @Query("term") term: String,
        @Query("format") format: String
    ): Response<LiteratureResponse>

    @GET
    suspend fun getDictionary(
        @Url baseUrl: String,
        @Query("uid") uid: String,
        @Query("tokenid") tokenId: String,
        @Query("word") word: String,
        @Query("format") format: String
    ): Response<JsonObject>

    @GET
    suspend fun getAbbreviations(
        @Url baseUrl: String,
        @Query("uid") uid: String,
        @Query("tokenid") tokenId: String,
        @Query("term") term: String,
        @Query("format") format: String
    ): Response<AbbreviationsResponse>

    @GET
    suspend fun getPhrases(
        @Url baseUrl: String,
        @Query("uid") uid: String,
        @Query("tokenid") tokenId: String,
        @Query("phrase") phrase: String,
        @Query("format") format: String
    ): Response<JsonObject>

    @GET
    suspend fun getBasicConvo(
        @Url baseUrl: String,
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String
    ) : Response<ConvoResponse>
}