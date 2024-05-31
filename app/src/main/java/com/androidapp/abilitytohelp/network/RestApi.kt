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
import com.androidapp.abilitytohelp.model.parseapismodels.ParseApiResponse
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
        @Query("limit") limit: Int = 600,
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String
    ) : Response<ConvoResponse>


    ///////////////////////////////  Parse APIs ////////////////////////////////////////

    @GET
    suspend fun getCategories(
        @Url baseUrl: String = "https://parseapi.back4app.com/classes/Category",
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String
    ) : Response<ParseApiResponse>

    @GET
    suspend fun hitParseApi(
        @Url baseUrl: String,
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String
    ) : Response<ParseApiResponse>

    @GET
    suspend fun getNumbers(
        @Url baseUrl: String = "https://parseapi.back4app.com/classes/Numbers",
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String
    ) : Response<ParseApiResponse>

    @GET
    suspend fun getColors(
        @Url baseUrl: String = "https://parseapi.back4app.com/classes/Colors",
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String
    ) : Response<ParseApiResponse>

    @GET
    suspend fun getShapes(
        @Url baseUrl: String = "https://parseapi.back4app.com/classes/Shapes",
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String
    ) : Response<ParseApiResponse>

    @GET
    suspend fun getAnimals(
        @Url baseUrl: String = "https://parseapi.back4app.com/classes/Animals",
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String
    ) : Response<ParseApiResponse>

    @GET
    suspend fun getBirds(
        @Url baseUrl: String = "https://parseapi.back4app.com/classes/Birds",
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String
    ) : Response<ParseApiResponse>

    @GET
    suspend fun getFlowers(
        @Url baseUrl: String = "https://parseapi.back4app.com/classes/Flowers",
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String
    ) : Response<ParseApiResponse>

    @GET
    suspend fun getFruits(
        @Url baseUrl: String = "https://parseapi.back4app.com/classes/Fruits",
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String
    ) : Response<ParseApiResponse>

    @GET
    suspend fun getMonths(
        @Url baseUrl: String = "https://parseapi.back4app.com/classes/Months",
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String
    ) : Response<ParseApiResponse>

    @GET
    suspend fun getVegetables(
        @Url baseUrl: String = "https://parseapi.back4app.com/classes/Vegetables",
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String
    ) : Response<ParseApiResponse>

    @GET
    suspend fun getBodyParts(
        @Url baseUrl: String = "https://parseapi.back4app.com/classes/BodyParts",
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String
    ) : Response<ParseApiResponse>

    @GET
    suspend fun getClothes(
        @Url baseUrl: String = "https://parseapi.back4app.com/classes/Clothes",
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String
    ) : Response<ParseApiResponse>

    @GET
    suspend fun getCountry(
        @Url baseUrl: String = "https://parseapi.back4app.com/classes/Country",
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String
    ) : Response<ParseApiResponse>

    @GET
    suspend fun getFoods(
        @Url baseUrl: String = "https://parseapi.back4app.com/classes/Foods",
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String
    ) : Response<ParseApiResponse>

    @GET
    suspend fun getGeometry(
        @Url baseUrl: String = "https://parseapi.back4app.com/classes/Geometry",
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String
    ) : Response<ParseApiResponse>

    @GET
    suspend fun getHouses(
        @Url baseUrl: String = "https://parseapi.back4app.com/classes/Houses",
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String
    ) : Response<ParseApiResponse>

    @GET
    suspend fun getJobs(
        @Url baseUrl: String = "https://parseapi.back4app.com/classes/Jobs",
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String
    ) : Response<ParseApiResponse>

    @GET
    suspend fun getSchool(
        @Url baseUrl: String = "https://parseapi.back4app.com/classes/School",
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String
    ) : Response<ParseApiResponse>

    @GET
    suspend fun getSports(
        @Url baseUrl: String = "https://parseapi.back4app.com/classes/Sports",
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String
    ) : Response<ParseApiResponse>

    @GET
    suspend fun getVehicles(
        @Url baseUrl: String = "https://parseapi.back4app.com/classes/Vehicles",
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String
    ) : Response<ParseApiResponse>

    @GET
    suspend fun getDailyRoutine(
        @Url baseUrl: String = "https://parseapi.back4app.com/classes/DailyRoutine",
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String
    ) : Response<ParseApiResponse>

    @GET
    suspend fun getFaceExpressions(
        @Url baseUrl: String = "https://parseapi.back4app.com/classes/FaceExpressions",
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String
    ) : Response<ParseApiResponse>

    @GET
    suspend fun getAnimalHouses(
        @Url baseUrl: String = "https://parseapi.back4app.com/classes/AnimalHouses",
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String
    ) : Response<ParseApiResponse>
}