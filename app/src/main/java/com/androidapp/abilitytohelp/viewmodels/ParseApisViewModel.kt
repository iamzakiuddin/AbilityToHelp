package com.androidapp.abilitytohelp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapp.abilitytohelp.model.DictionaryResponse
import com.androidapp.abilitytohelp.model.parseapismodels.ParseApiResponse
import com.androidapp.abilitytohelp.network.NetworkResources
import com.androidapp.abilitytohelp.network.NetworkUtil
import com.androidapp.abilitytohelp.network.Repository
import kotlinx.coroutines.launch

class ParseApisViewModel : ViewModel() {

    var repository: Repository? = null

    private var getCategoriesResponse = MutableLiveData<NetworkResources<ParseApiResponse>>()
    var categoriesResponse : LiveData<NetworkResources<ParseApiResponse>> = getCategoriesResponse

    private var getParseResponse = MutableLiveData<NetworkResources<ParseApiResponse>>()
    var parseResponse : LiveData<NetworkResources<ParseApiResponse>> = getParseResponse

    private var getNumbersResponse = MutableLiveData<NetworkResources<ParseApiResponse>>()
    var numbersResponse : LiveData<NetworkResources<ParseApiResponse>> = getNumbersResponse

    private var getColorsResponse = MutableLiveData<NetworkResources<ParseApiResponse>>()
    var colorsResponse : LiveData<NetworkResources<ParseApiResponse>> = getColorsResponse

    private var getAnimalsResponse = MutableLiveData<NetworkResources<ParseApiResponse>>()
    var animalsResponse : LiveData<NetworkResources<ParseApiResponse>> = getAnimalsResponse

    private var getBirdsResponse = MutableLiveData<NetworkResources<ParseApiResponse>>()
    var birdsResponse : LiveData<NetworkResources<ParseApiResponse>> = getBirdsResponse

    private var getFlowersResponse = MutableLiveData<NetworkResources<ParseApiResponse>>()
    var flowersResponse : LiveData<NetworkResources<ParseApiResponse>> = getFlowersResponse

    private var getFruitsResponse = MutableLiveData<NetworkResources<ParseApiResponse>>()
    var fruitsResponse : LiveData<NetworkResources<ParseApiResponse>> = getFruitsResponse

    private var getMonthsResponse = MutableLiveData<NetworkResources<ParseApiResponse>>()
    var monthsResponse : LiveData<NetworkResources<ParseApiResponse>> = getMonthsResponse

    private var getVegetablesResponse = MutableLiveData<NetworkResources<ParseApiResponse>>()
    var vegetablesResponse : LiveData<NetworkResources<ParseApiResponse>> = getVegetablesResponse

    private var getBodyPartsResponse = MutableLiveData<NetworkResources<ParseApiResponse>>()
    var bodyPartsResponse : LiveData<NetworkResources<ParseApiResponse>> = getBodyPartsResponse

    private var getClothesResponse = MutableLiveData<NetworkResources<ParseApiResponse>>()
    var clothesResponse : LiveData<NetworkResources<ParseApiResponse>> = getClothesResponse

    private var getCountryResponse = MutableLiveData<NetworkResources<ParseApiResponse>>()
    var countryResponse : LiveData<NetworkResources<ParseApiResponse>> = getCountryResponse

    private var getFoodsResponse = MutableLiveData<NetworkResources<ParseApiResponse>>()
    var foodsResponse : LiveData<NetworkResources<ParseApiResponse>> = getFoodsResponse

    private var getGeometryResponse = MutableLiveData<NetworkResources<ParseApiResponse>>()
    var geometryResponse : LiveData<NetworkResources<ParseApiResponse>> = getGeometryResponse

    private var getHousesResponse = MutableLiveData<NetworkResources<ParseApiResponse>>()
    var housesResponse : LiveData<NetworkResources<ParseApiResponse>> = getHousesResponse

    private var getJobsResponse = MutableLiveData<NetworkResources<ParseApiResponse>>()
    var jobsResponse : LiveData<NetworkResources<ParseApiResponse>> = getJobsResponse

    private var getSchoolResponse = MutableLiveData<NetworkResources<ParseApiResponse>>()
    var schoolResponse : LiveData<NetworkResources<ParseApiResponse>> = getSchoolResponse

    private var getSportsResponse = MutableLiveData<NetworkResources<ParseApiResponse>>()
    var sportsResponse : LiveData<NetworkResources<ParseApiResponse>>  = getSportsResponse

    private var getVehiclesResponse = MutableLiveData<NetworkResources<ParseApiResponse>>()
    var vehiclesResponse : LiveData<NetworkResources<ParseApiResponse>>  = getVehiclesResponse

    private var getDailyRoutineResponse = MutableLiveData<NetworkResources<ParseApiResponse>>()
    var dailyRoutineResponse : LiveData<NetworkResources<ParseApiResponse>> = getDailyRoutineResponse

    private var getFaceExpressionsResponse = MutableLiveData<NetworkResources<ParseApiResponse>>()
    var faceExpressionsResponse : LiveData<NetworkResources<ParseApiResponse>> = getFaceExpressionsResponse

    private var getAnimalHousesResponse = MutableLiveData<NetworkResources<ParseApiResponse>>()
    var animalHousesResponse : LiveData<NetworkResources<ParseApiResponse>> = getAnimalHousesResponse

    init {
        repository = NetworkUtil.provideRepository()
    }

    fun mgetCategoriesResponse(){
        getCategoriesResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            getCategoriesResponse.value = repository?.getCategories()
        }
    }

    fun mgetCategoriesObserver() : LiveData<NetworkResources<ParseApiResponse>>{
        return categoriesResponse
    }

    fun hitParseApi(endPoint:String){
        getParseResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            getParseResponse.value = repository?.hitParseApi(endPoint)
        }
    }

    fun getParseResponseObserver() : LiveData<NetworkResources<ParseApiResponse>>{
        return parseResponse
    }

    fun mgetNumbersResponse(){
        getNumbersResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            getNumbersResponse.value = repository?.getNumbers()
        }
    }

    fun mgetnumberssObserver() : LiveData<NetworkResources<ParseApiResponse>>{
        return numbersResponse
    }

    fun mgetColorsResponse(){
        getColorsResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            getColorsResponse.value = repository?.getColors()
        }
    }

    fun mgetColorObserver() : LiveData<NetworkResources<ParseApiResponse>>{
        return colorsResponse
    }

    fun mgetAnimalsResponse(){
        getAnimalsResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            getAnimalsResponse.value = repository?.getAnimals()
        }
    }

    fun mgetAnimalObserver() : LiveData<NetworkResources<ParseApiResponse>>{
        return animalsResponse
    }

    fun mgetBirdsResponse(){
        getBirdsResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            getBirdsResponse.value = repository?.getBirds()
        }
    }

    fun mgetBirdsObserver() : LiveData<NetworkResources<ParseApiResponse>>{
        return birdsResponse
    }

    fun mgetFlowersResponse(){
        getFlowersResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            getFlowersResponse.value = repository?.getFlowers()
        }
    }

    fun mgetFlowersObserver() : LiveData<NetworkResources<ParseApiResponse>>{
        return flowersResponse
    }

    fun mgetFruitsResponse(){
        getFruitsResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            getFruitsResponse.value = repository?.getFruits()
        }
    }

    fun mgetFruitsObserver() : LiveData<NetworkResources<ParseApiResponse>>{
        return fruitsResponse
    }

    fun mgetMonthResponse(){
        getMonthsResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            getMonthsResponse.value = repository?.getMonths()
        }
    }

    fun mgetMonthsObserver() : LiveData<NetworkResources<ParseApiResponse>>{
        return monthsResponse
    }

    fun mgetVegetableResponse(){
        getVegetablesResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            getVegetablesResponse.value = repository?.getVegetables()
        }
    }

    fun mgetVegetableObserver() : LiveData<NetworkResources<ParseApiResponse>>{
        return vegetablesResponse
    }

    fun mgetBodyPartsResponse(){
        getBodyPartsResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            getBodyPartsResponse.value = repository?.getBodyParts()
        }
    }

    fun mgetBodyPartsObserver() : LiveData<NetworkResources<ParseApiResponse>>{
        return bodyPartsResponse
    }

    fun mgetClothesResponse(){
        getClothesResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            getClothesResponse.value = repository?.getClothes()
        }
    }

    fun mgetClothesObserver() : LiveData<NetworkResources<ParseApiResponse>>{
        return clothesResponse
    }

    fun mgetCountryResponse(){
        getColorsResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            getCountryResponse.value = repository?.getCountry()
        }
    }

    fun mgetCountryObserver() : LiveData<NetworkResources<ParseApiResponse>>{
        return countryResponse
    }

    fun mgetFoodsResponse(){
        getFoodsResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            getFoodsResponse.value = repository?.getFoods()
        }
    }

    fun mgetFoodObserver() : LiveData<NetworkResources<ParseApiResponse>>{
        return foodsResponse
    }

    fun mgetGeometryResponse(){
        getGeometryResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            getGeometryResponse.value = repository?.getGeometry()
        }
    }

    fun mgetGeometryObserver() : LiveData<NetworkResources<ParseApiResponse>>{
        return geometryResponse
    }

    fun mgetHousesResponse(){
        getHousesResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            getHousesResponse.value = repository?.getHouses()
        }
    }

    fun mgetHousesObserver() : LiveData<NetworkResources<ParseApiResponse>>{
        return housesResponse
    }

    fun mgetJobsResponse(){
        getJobsResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            getJobsResponse.value = repository?.getJobs()
        }
    }

    fun mgetJobsObserver() : LiveData<NetworkResources<ParseApiResponse>>{
        return jobsResponse
    }

    fun mgetSchoolResponse(){
        getSchoolResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            getSchoolResponse.value = repository?.getSchool()
        }
    }

    fun mgetSchoolObserver() : LiveData<NetworkResources<ParseApiResponse>>{
        return schoolResponse
    }

    fun mgetSportsResponse(){
        getSportsResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            getSportsResponse.value = repository?.getSports()
        }
    }

    fun mgetSportObserver() : LiveData<NetworkResources<ParseApiResponse>>{
        return sportsResponse
    }

    fun mgetVeehiclesResponse(){
        getVehiclesResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            getVehiclesResponse.value = repository?.getVehicles()
        }
    }

    fun mgetVehicleObserver() : LiveData<NetworkResources<ParseApiResponse>>{
        return vehiclesResponse
    }

    fun mgetDailyRoutinesResponse(){
        getDailyRoutineResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            getDailyRoutineResponse.value = repository?.getDailyRoutine()
        }
    }

    fun mgetDailyRoutineObserver() : LiveData<NetworkResources<ParseApiResponse>>{
        return dailyRoutineResponse
    }

    fun mgetFaceExpResponse(){
        getFaceExpressionsResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            getFaceExpressionsResponse.value = repository?.getFaceExpressions()
        }
    }

    fun mgetFaceExpressionObserver() : LiveData<NetworkResources<ParseApiResponse>>{
        return faceExpressionsResponse
    }

    fun mgetAnimalHousesResponse(){
        getAnimalHousesResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            getAnimalHousesResponse.value = repository?.getAnimalHouses()
        }
    }

    fun mgetAnimalHouseObserver() : LiveData<NetworkResources<ParseApiResponse>>{
        return animalHousesResponse
    }

}